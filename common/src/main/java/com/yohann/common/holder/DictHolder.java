package com.yohann.common.holder;

import java.util.List;
import java.util.Map;

/**
 * @author Yohann
 * @since 2021/2/6 17:09
 */
public class DictHolder {
    /**
     * Dict type is map
     */
    private Map<String, Map<String, String>> dictMap;

    /**
     * Dict type is list
     */
    private Map<String, List<String>> listMap;

    public DictHolder() {
    }

    public DictHolder(Map<String, Map<String, String>> dictMap, Map<String, List<String>> listMap) {
        this.dictMap = dictMap;
        this.listMap = listMap;
    }

    public Map<String, Map<String, String>> getDictMap() {
        return dictMap;
    }

    public Map<String, List<String>> getListMap() {
        return listMap;
    }

    public Map<String, String> getDictMap(String code) {
        return dictMap.get(code);
    }

    public synchronized void setDictMap(Map<String, Map<String, String>> dictMap) {
        this.dictMap = dictMap;
    }

    public List<String> getListMap(String code) {
        return listMap.get(code);
    }

    public synchronized void setListMap(Map<String, List<String>> listMap) {
        this.listMap = listMap;
    }
}