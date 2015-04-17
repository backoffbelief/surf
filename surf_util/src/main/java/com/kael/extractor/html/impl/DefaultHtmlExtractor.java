package com.kael.extractor.html.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kael.extractor.html.HtmlExtractor;
import com.kael.extractor.html.HtmlFetcher;
import com.kael.extractor.html.model.CssPath;
import com.kael.extractor.html.model.ExtractFailLog;
import com.kael.extractor.html.model.ExtractFunction;
import com.kael.extractor.html.model.ExtractResult;
import com.kael.extractor.html.model.ExtractResultItem;
import com.kael.extractor.html.model.HtmlTemplate;
import com.kael.extractor.html.model.UrlPattern;
import com.kael.util.StringUtil;

/**
 * 网页抽取工具
 * 根据URL模式、页面模板、CSS路径、抽取函数，抽取HTML页面
 *
 *
 */
public class DefaultHtmlExtractor implements HtmlExtractor{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHtmlExtractor.class);

    private ExtractRegular extractRegular;

    public DefaultHtmlExtractor(ExtractRegular extractRegular) {
        this.extractRegular = extractRegular;
    }

    /**
     * 抽取信息
     * @param url URL
     * @param html HTML
     * @return 抽取结果
     */
    @Override
    public List<ExtractResult> extract(String url, String html){
        List<ExtractResult> extractResults = new ArrayList<>();
        //同一个URL可能会有多个页面模板
        List<HtmlTemplate> htmlTemplates = extractRegular.getHtmlTemplate(url);
        if (htmlTemplates.isEmpty()) {
            LOGGER.debug("没有为此URL指定模板："+url);
            return extractResults;
        }
        try {
            Document doc = Jsoup.parse(html);
            Elements metas = doc.select("meta");
            String keywords = "";
            String description = "";
            for (Element meta : metas) {
                String name = meta.attr("name");
                if ("keywords".equals(name)) {
                    keywords = meta.attr("content");
                }
                if ("description".equals(name)) {
                    description = meta.attr("content");
                }
            }
            Set<String> tableNames = new HashSet<>();
            for (HtmlTemplate htmlTemplate : htmlTemplates) {
                if (tableNames.contains(htmlTemplate.getTableName())) {
                    LOGGER.debug("多个模板定义的tableName重复，这有可能会导致数据丢失，检查UrlPattern下定义的模板：" + htmlTemplate.getUrlPattern().getUrlPattern());
                    LOGGER.debug(htmlTemplates.toString());
                }
                tableNames.add(htmlTemplate.getTableName());
                try {
                    //按页面模板的定义对网页进行抽取
                    ExtractResult extractResult = extractHtmlTemplate(url, htmlTemplate, doc);
                    //同样的URL模式，因为改版等原因，可能会对应多套模板，多套模板中我们一般只需要抽取一套
                    if(!extractResult.getExtractFailLogs().isEmpty() || !extractResult.getExtractResultItems().isEmpty()) {
                        extractResult.setContent(html.getBytes("utf-8"));
                        extractResult.setEncoding("utf-8");
                        extractResult.setKeywords(keywords);
                        extractResult.setDescription(description);
                        extractResults.add(extractResult);
                    }else{
                        LOGGER.debug(url + " 的模板 " + htmlTemplate.getTemplateName() + " 未抽取到");
                    }
                } catch (Exception e) {
                    LOGGER.error("页面模板抽取失败：" + htmlTemplate.getTemplateName(), e);
                }
            }
        } catch (Exception e) {
            LOGGER.error("抽取网页出错: " + url, e);
        }
        return extractResults;
    }

    /**
     * 根据模板的定义抽取信息
     * @param url html页面路径
     * @param htmlTemplate html页面模板
     * @param doc jsoup文档
     * @return 抽取结果
     */
    private ExtractResult extractHtmlTemplate(String url, HtmlTemplate htmlTemplate, Document doc) {
        //一个页面模板对应一个抽取结果
        ExtractResult extractResult = new ExtractResult();
        extractResult.setUrl(url);
        extractResult.setTableName(htmlTemplate.getTableName());
        List<CssPath> cssPaths = htmlTemplate.getCssPaths();
	    //页面模板中定义的所有CSS路径和抽取表达式全部抽取成功，才算抽取成功
        //只要有一个CSS路径或抽取表达式失败，就是抽取失败
        for (CssPath cssPath : cssPaths) {
            // 抽取一条CSS PATH
            Elements elements = doc.select(cssPath.getCssPath());
            // 如果CSS路径匹配多个元素，则抽取字段为多值
            for (Element element : elements) {
                String text = null;
                if(StringUtil.isBlank(cssPath.getAttr())){
                    //提取文本
                    text = element.text();
                }else{
                    //提取属性
                    text = element.attr(cssPath.getAttr());
                }
                if (StringUtil.isNotBlank(text)) {
                    // 成功提取文本
                    if (cssPath.hasExtractFunction()) {
                        //使用CSS路径下的抽取函数做进一步抽取
                        for (ExtractFunction pf : cssPath.getExtractFunctions()) {
                            text = ExtractFunctionExecutor.execute(text, doc, cssPath, pf.getExtractExpression());
                            if (text != null) {
                                ExtractResultItem extractResultItem = new ExtractResultItem();
                                extractResultItem.setField(pf.getFieldName());
                                extractResultItem.setValue(text);
                                extractResult.addExtractResultItem(extractResultItem);
                            } else {
                                ExtractFailLog extractFailLog = new ExtractFailLog();
                                extractFailLog.setUrl(url);
                                extractFailLog.setUrlPattern(htmlTemplate.getUrlPattern().getUrlPattern());
                                extractFailLog.setTemplateName(htmlTemplate.getTemplateName());
                                extractFailLog.setCssPath(cssPath.getCssPath());
                                extractFailLog.setExtractExpression(pf.getExtractExpression());
                                extractFailLog.setTableName(htmlTemplate.getTableName());
                                extractFailLog.setFieldName(pf.getFieldName());
                                extractFailLog.setFieldDescription(pf.getFieldDescription());
                                extractResult.addExtractFailLog(extractFailLog);
                                //未抽取到结果，保存抽取失败日志并停止抽取，抽取失败
                                //快速失败模式
                                //如果要记录所有失败日志，则去除下面一行返回的代码
                                return extractResult;
                            }
                        }
                    } else {
                        //使用CSS路径抽取的结果
                        ExtractResultItem extractResultItem = new ExtractResultItem();
                        extractResultItem.setField(cssPath.getFieldName());
                        extractResultItem.setValue(text);
                        extractResult.addExtractResultItem(extractResultItem);
                    }
                } else {
                    //未抽取到结果，保存抽取失败日志并停止抽取，抽取失败
                    ExtractFailLog extractFailLog = new ExtractFailLog();
                    extractFailLog.setUrl(url);
                    extractFailLog.setUrlPattern(htmlTemplate.getUrlPattern().getUrlPattern());
                    extractFailLog.setTemplateName(htmlTemplate.getTemplateName());
                    extractFailLog.setCssPath(cssPath.getCssPath());
                    extractFailLog.setExtractExpression("");
                    extractFailLog.setTableName(htmlTemplate.getTableName());
                    extractFailLog.setFieldName(cssPath.getFieldName());
                    extractFailLog.setFieldDescription(cssPath.getFieldDescription());
                    extractResult.addExtractFailLog(extractFailLog);
                    //未抽取到结果，保存抽取失败日志并停止抽取，抽取失败
                    //快速失败模式
                    //如果要记录所有失败日志，则去除下面一行返回的代码
                    return extractResult;
                }
            }
        }
        return extractResult;
    }
    private static void usage1(){
        //1、构造抽取规则
        List<UrlPattern> urlPatterns = new ArrayList<>();
        //1.1、构造URL模式
        UrlPattern urlPattern = new UrlPattern();
        urlPattern.setUrlPattern("http://money.163.com/\\d{2}/\\d{4}/\\d{2}/[0-9A-Z]{16}.html");
        //1.2、构造HTML模板
        HtmlTemplate htmlTemplate = new HtmlTemplate();
        htmlTemplate.setTemplateName("网易财经频道");
        htmlTemplate.setTableName("finance");
        //1.3、将URL模式和HTML模板建立关联
        urlPattern.addHtmlTemplate(htmlTemplate);
        //1.4、构造CSS路径
        CssPath cssPath = new CssPath();
        cssPath.setCssPath("h1");
        cssPath.setFieldName("title");
        cssPath.setFieldDescription("标题");
        //1.5、将CSS路径和模板建立关联
        htmlTemplate.addCssPath(cssPath);
        //1.6、构造CSS路径
        cssPath = new CssPath();
        cssPath.setCssPath("div#endText");
        cssPath.setFieldName("content");
        cssPath.setFieldDescription("正文");
        //1.7、将CSS路径和模板建立关联
        htmlTemplate.addCssPath(cssPath);
        //可象上面那样构造多个URLURL模式
        urlPatterns.add(urlPattern);
        //2、获取抽取规则对象
        ExtractRegular extractRegular = ExtractRegular.getInstance(urlPatterns);
        //注意：可通过如下3个方法动态地改变抽取规则
        //extractRegular.addUrlPatterns(urlPatterns);
        //extractRegular.addUrlPattern(urlPattern);
        //extractRegular.removeUrlPattern(urlPattern.getUrlPattern());
        //3、获取HTML抽取工具
       HtmlExtractor htmlExtractor = new DefaultHtmlExtractor(extractRegular);
        //4、抽取网页
        String url = "http://money.163.com/08/1219/16/4THR2TMP002533QK.html";
        HtmlFetcher htmlFetcher = new JSoupHtmlFetcher();
        String html = htmlFetcher.fetch(url);
        List<ExtractResult> extractResults = htmlExtractor.extract(url, html);
        //5、输出结果
        int i = 1;
        for (ExtractResult extractResult : extractResults) {
            System.out.println((i++) + "、网页 " + extractResult.getUrl() + " 的抽取结果");
            if(!extractResult.isSuccess()){
                System.out.println("抽取失败：");
                for(ExtractFailLog extractFailLog : extractResult.getExtractFailLogs()){
                    System.out.println("\turl:"+extractFailLog.getUrl());
                    System.out.println("\turlPattern:"+extractFailLog.getUrlPattern());
                    System.out.println("\ttemplateName:"+extractFailLog.getTemplateName());
                    System.out.println("\tfieldName:"+extractFailLog.getFieldName());
                    System.out.println("\tfieldDescription:"+extractFailLog.getFieldDescription());
                    System.out.println("\tcssPath:"+extractFailLog.getCssPath());
                    if(extractFailLog.getExtractExpression()!=null) {
                        System.out.println("\textractExpression:" + extractFailLog.getExtractExpression());
                    }
                }
                continue;
            }
            Map<String, List<ExtractResultItem>> extractResultItems = extractResult.getExtractResultItems();
            for(String field : extractResultItems.keySet()){
                List<ExtractResultItem> values = extractResultItems.get(field);
                if(values.size() > 1){
                    int j=1;
                    System.out.println("\t多值字段:"+field);
                    for(ExtractResultItem item : values){
                        System.out.println("\t\t"+(j++)+"、"+field+" = "+item.getValue());   
                    }
                }else{
                    System.out.println("\t"+field+" = "+values.get(0).getValue());     
                }
            }
            System.out.println("\tdescription = "+extractResult.getDescription());
            System.out.println("\tkeywords = "+extractResult.getKeywords());
        }
    }
    private static void usage2(){
        String allExtractRegularUrl = "http://localhost:8080/html-extractor-web/api/all_extract_regular.jsp";
        String redisHost = "localhost";
        int redisPort = 6379;

        ExtractRegular extractRegular = ExtractRegular.getInstance(allExtractRegularUrl, redisHost, redisPort);
        HtmlExtractor htmlExtractor = new DefaultHtmlExtractor(extractRegular);

        String url = "http://money.163.com/08/1219/16/4THR2TMP002533QK.html";
        HtmlFetcher htmlFetcher = new JSoupHtmlFetcher();
        String html = htmlFetcher.fetch(url);
        List<ExtractResult> extractResults = htmlExtractor.extract(url, html);

        int i = 1;
        for (ExtractResult extractResult : extractResults) {
            System.out.println((i++) + "、网页 " + extractResult.getUrl() + " 的抽取结果");
            if(!extractResult.isSuccess()){
                System.out.println("抽取失败：");
                for(ExtractFailLog extractFailLog : extractResult.getExtractFailLogs()){
                    System.out.println("\turl:"+extractFailLog.getUrl());
                    System.out.println("\turlPattern:"+extractFailLog.getUrlPattern());
                    System.out.println("\ttemplateName:" + extractFailLog.getTemplateName());
                    System.out.println("\tfieldName:"+extractFailLog.getFieldName());
                    System.out.println("\tfieldDescription:"+extractFailLog.getFieldDescription());
                    System.out.println("\tcssPath:"+extractFailLog.getCssPath());
                    if(extractFailLog.getExtractExpression()!=null) {
                        System.out.println("\textractExpression:" + extractFailLog.getExtractExpression());
                    }
                }
                continue;
            }
            Map<String, List<ExtractResultItem>> extractResultItems = extractResult.getExtractResultItems();
            for(String field : extractResultItems.keySet()){
                List<ExtractResultItem> values = extractResultItems.get(field);
                if(values.size() > 1){
                    int j=1;
                    System.out.println("\t多值字段:"+field);
                    for(ExtractResultItem item : values){
                        System.out.println("\t\t"+(j++)+"、"+field+" = "+item.getValue());
                    }
                }else{
                    System.out.println("\t"+field+" = "+values.get(0).getValue());
                }
            }
            System.out.println("\tdescription = "+extractResult.getDescription());
            System.out.println("\tkeywords = "+extractResult.getKeywords());
        }
    }
    private static void usage3(){
        //1、构造抽取规则
        List<UrlPattern> urlPatterns = new ArrayList<>();
        //1.1、构造URL模式
        UrlPattern urlPattern = new UrlPattern();
        urlPattern.setUrlPattern("http://list.jd.com/list.html\\?cat=([\\d,]+)");
        //1.2、构造HTML模板
        HtmlTemplate htmlTemplate = new HtmlTemplate();
        htmlTemplate.setTemplateName("京东商品");
        htmlTemplate.setTableName("jd_goods");
        //1.3、将URL模式和HTML模板建立关联
        urlPattern.addHtmlTemplate(htmlTemplate);
        //1.4、构造CSS路径
        CssPath cssPath = new CssPath();
        cssPath.setCssPath("html body div div div ul li div div.p-name");
        cssPath.setFieldName("name");
        cssPath.setFieldDescription("名称");
        //1.5、将CSS路径和模板建立关联
        htmlTemplate.addCssPath(cssPath);
        //1.6、构造CSS路径
        cssPath = new CssPath();
        cssPath.setCssPath("html body div div div ul li div div.p-name a");
        cssPath.setAttr("href");
        cssPath.setFieldName("link");
        cssPath.setFieldDescription("链接");
        //1.7、将CSS路径和模板建立关联
        htmlTemplate.addCssPath(cssPath);
        //1.8、构造CSS路径
        cssPath = new CssPath();
        cssPath.setCssPath("html body div div div ul li div div.p-price strong");
        cssPath.setFieldName("price");
        cssPath.setFieldDescription("价格");
        //1.9、将CSS路径和模板建立关联
        htmlTemplate.addCssPath(cssPath);
        //可象上面那样构造多个URLURL模式
        urlPatterns.add(urlPattern);
        //2、获取抽取规则对象
        ExtractRegular extractRegular = ExtractRegular.getInstance(urlPatterns);
        //注意：可通过如下3个方法动态地改变抽取规则
        //extractRegular.addUrlPatterns(urlPatterns);
        //extractRegular.addUrlPattern(urlPattern);
        //extractRegular.removeUrlPattern(urlPattern.getUrlPattern());
        //3、获取HTML抽取工具
        HtmlExtractor htmlExtractor = new DefaultHtmlExtractor(extractRegular);
        //4、抽取网页
        String url = "http://list.jd.com/list.html?cat=9987,653,655";
        HtmlFetcher htmlFetcher = new JSoupHtmlFetcher();
        String html = htmlFetcher.fetch(url);
        List<ExtractResult> extractResults = htmlExtractor.extract(url, html);
        //5、输出结果
        int i = 1;
        for (ExtractResult extractResult : extractResults) {
            System.out.println((i++) + "、网页 " + extractResult.getUrl() + " 的抽取结果");
            if(!extractResult.isSuccess()){
                System.out.println("抽取失败：");
                for(ExtractFailLog extractFailLog : extractResult.getExtractFailLogs()){
                    System.out.println("\turl:"+extractFailLog.getUrl());
                    System.out.println("\turlPattern:"+extractFailLog.getUrlPattern());
                    System.out.println("\ttemplateName:"+extractFailLog.getTemplateName());
                    System.out.println("\tfieldName:"+extractFailLog.getFieldName());
                    System.out.println("\tfieldDescription:"+extractFailLog.getFieldDescription());
                    System.out.println("\tcssPath:"+extractFailLog.getCssPath());
                    if(extractFailLog.getExtractExpression()!=null) {
                        System.out.println("\textractExpression:" + extractFailLog.getExtractExpression());
                    }
                }
                continue;
            }
            Map<String, List<ExtractResultItem>> extractResultItems = extractResult.getExtractResultItems();
            for(String field : extractResultItems.keySet()){
                List<ExtractResultItem> values = extractResultItems.get(field);
                if(values.size() > 1){
                    int j=1;
                    System.out.println("\t多值字段:"+field);
                    for(ExtractResultItem item : values){
                        System.out.println("\t\t"+(j++)+"、"+field+" = "+item.getValue());   
                    }
                }else{
                    System.out.println("\t"+field+" = "+values.get(0).getValue());     
                }
            }
            System.out.println("\tdescription = "+extractResult.getDescription());
            System.out.println("\tkeywords = "+extractResult.getKeywords());
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //下面的三种方法代表了3种不同的使用模式，只能单独使用
        //usage1();
        //usage2();
        usage3();
    }
}