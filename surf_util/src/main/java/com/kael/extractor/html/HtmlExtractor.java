package com.kael.extractor.html;

import java.util.List;

import com.kael.extractor.html.model.ExtractResult;

/**
 * 网页抽取工具
 * 根据URL模式、页面模板、CSS路径、抽取函数，抽取HTML页面
 *
 *
 */
public interface HtmlExtractor {
    /**
     * 抽取信息
     * @param url URL
     * @param html HTML
     * @return 抽取结果
     */
    public List<ExtractResult> extract(String url, String html);
}