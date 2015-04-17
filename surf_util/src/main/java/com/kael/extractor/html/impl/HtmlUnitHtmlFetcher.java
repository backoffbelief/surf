package com.kael.extractor.html.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.kael.extractor.html.HtmlFetcher;

/**
 *
 * 使用HtmlUnit获取页面内容，HtmlUnit能执行JS
 * 动态渲染网页，但不是所有JS都能渲染，需要测试
 * @author 杨尚川
 */
public class HtmlUnitHtmlFetcher implements HtmlFetcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUnitHtmlFetcher.class);

    private static final WebClient WEB_CLIENT = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);

    /**
     * 使用HtmlUnit获取页面内容，HtmlUnit能执行JS，动态渲染网页，但不是所有JS都能渲染，需要测试
     * @param url html页面路径
     * @return
     */
    @Override
    public String fetch(String url) {
        try{
            LOGGER.debug("url:"+url);
            HtmlPage htmlPage = WEB_CLIENT.getPage(url);
            String html = htmlPage.getBody().asXml();
            LOGGER.debug("html:"+html);
            return html;
        }catch (Exception e) {
            LOGGER.error("获取URL："+url+"页面出错", e);
        }
        return "";
    }
}