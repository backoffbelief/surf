package com.kael.extractor.html.impl;

import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kael.extractor.html.HtmlFetcher;

/**
 *
 * 使用JSoup获取网页内容
 */
public class JSoupHtmlFetcher implements HtmlFetcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSoupHtmlFetcher.class);

    private static final String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    private static final String ENCODING = "gzip, deflate";
    private static final String LANGUAGE = "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3";
    private static final String CONNECTION = "keep-alive";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:36.0) Gecko/20100101 Firefox/36.0";

    @Override
    public String fetch(String url) {
        try {
            LOGGER.debug("url:"+url);
            String host = new URL(url).getHost();
            Connection conn = Jsoup.connect(url)
                    .header("Accept", ACCEPT)
                    .header("Accept-Encoding", ENCODING)
                    .header("Accept-Language", LANGUAGE)
                    .header("Connection", CONNECTION)
                    .header("Referer", "http://"+host)
                    .header("Host", host)
                    .header("User-Agent", USER_AGENT)
                    .ignoreContentType(true);
            String html = conn.get().html();
            LOGGER.debug("html:"+html);
            return html;
        }catch (Exception e){
            LOGGER.error("获取URL："+url+"页面出错", e);
        }
        return "";
    }
}