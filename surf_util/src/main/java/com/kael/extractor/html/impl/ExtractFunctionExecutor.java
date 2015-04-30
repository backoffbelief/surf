package com.kael.extractor.html.impl;


import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kael.extractor.html.model.CssPath;

/**
 * 抽取函数执行引擎，提供的抽取函数有： 
 * 1、deleteChild(div.ep-source) 
 * 2、removeText(作者：)
 * 3、substring(0,19) 抽取函数的格式为：函数名称+(+逗号分割的参数+)
 *
 */
public class ExtractFunctionExecutor {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExtractFunctionExecutor.class);

    /**
     * 执行抽取函数
     *
     * @param text CSS路径抽取出来的文本
     * @param doc 根文档
     * @param cssPath CSS路径对象
     * @param parseExpression 抽取函数
     * @return 抽取函数处理之后的文本
     */
    public static String execute(String text, Document doc, CssPath cssPath, String parseExpression) {
        if (parseExpression.startsWith("deleteChild")) {
            return executeDeleteChild(text, doc, cssPath, parseExpression);
        }
        if (parseExpression.startsWith("removeText")) {
            return executeRemoveText(text, parseExpression);
        }
        if (parseExpression.startsWith("substring")) {
            return executeSubstring(text, parseExpression);
        }

        return null;
    }

    /**
     * 截取指定范围的文本 使用方法：substring(0,19)
     * 括号内的参数为2个，分别是字符索引下标，截取从0开始到19的字符串，索引包括0，不包括19,即[0 - 19)
     *
     * @param text CSS路径抽取出来的文本
     * @param parseExpression 抽取函数
     * @return 抽取函数处理之后的文本
     */
    public static String executeSubstring(String text, String parseExpression) {
        LOGGER.debug("substring抽取函数之前：" + text);
        String parameter = parseExpression.replace("substring(", "");
        parameter = parameter.substring(0, parameter.length() - 1);
        String[] attr = parameter.split(",");
        if (attr != null && attr.length == 2) {
            int beginIndex = Integer.parseInt(attr[0]);
            int endIndex = Integer.parseInt(attr[1]);
            text = text.substring(beginIndex, endIndex);
        }
        LOGGER.debug("substring抽取函数之后：" + text);
        return text;
    }

    /**
     * 删除指定的文本 使用方法：removeText(作者：) 括号内的参数为文本字符，从CSS路径匹配的文本中删除参数文本
     *
     * @param text CSS路径抽取出来的文本
     * @param parseExpression 抽取函数
     * @return 抽取函数处理之后的文本
     */
    public static String executeRemoveText(String text, String parseExpression) {
        LOGGER.debug("removeText抽取函数之前：" + text);
        String parameter = parseExpression.replace("removeText(", "");
        parameter = parameter.substring(0, parameter.length() - 1);
        text = text.replace(parameter, "");
        LOGGER.debug("removeText抽取函数之后：" + text);
        return text;
    }

    /**
     * 删除子CSS路径的内容 使用方法：deleteChild(div.ep-source)
     * 括号内的参数为相对CSS路径的子路径，从CSS路径匹配的文本中删除子路径匹配的文本
     *
     * @param text CSS路径抽取出来的文本
     * @param doc 根文档
     * @param cssPath CSS路径对象
     * @param parseExpression 抽取函数
     * @return 抽取函数处理之后的文本
     */
    public static String executeDeleteChild(String text, Document doc, CssPath cssPath, String parseExpression) {
        LOGGER.debug("deleteChild抽取函数之前：" + text);
        String parameter = parseExpression.replace("deleteChild(", "");
        parameter = parameter.substring(0, parameter.length() - 1);
        Elements elements = doc.select(cssPath.getCssPath() + " " + parameter);
        for (Element element : elements) {
            String t = element.text();
            if (StringUtils.isNotBlank(t)) {
                LOGGER.debug("deleteChild抽取函数删除：" + t);
                text = text.replace(t, "");
            }
        }
        LOGGER.debug("deleteChild抽取函数之后：" + text);
        return text;
    }
}
