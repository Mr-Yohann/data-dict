package com.yohann.common.dto;

/**
 * @author Yohann
 * @since 2021/2/6 17:05
 */
public class DictDTO {
    private Long id;

    private String dictKey;

    private String dictValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
}