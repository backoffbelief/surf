package com.kael.extractor.html.model;
/**
 * 网页结构化信息抽取结果项
 *
 *
 */
public class ExtractResultItem {
    /**
     * 抽取结果项保存到那个字段
     */
    private String field;
    /**
     * 抽取结果项的值
     */
    private String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ExtractResultItem [\nfield=" + field + ", \nvalue=" + value + "]";
    }
}
