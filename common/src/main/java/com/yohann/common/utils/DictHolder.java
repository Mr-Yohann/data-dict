package com.yohann.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yohann
 * @since 2021/2/6 17:09
 */
public class DictHolder {
    private final static DictHolder HOLDER = new DictHolder();
    /**
     * Dict type is map
     */
    private Map<String, Map<String, String>>  dictMap = new HashMap<>();

    /**
     * Dict type is list
     */
    private Map<String, List<String>> listMap = new HashMap<>();

    private DictHolder() {
    }

    public DictHolder(Map<String, Map<String, String>> dictMap, Map<String, List<String>> listMap) {
        this.dictMap = dictMap;
        this.listMap = listMap;
    }

    public Map<String, String> getDictMap(String code) {
        return dictMap.get(code);
    }

    synchronized void setDictMap(Map<String, Map<String, String>> dictMap) {
        if (!dictMap.isEmpty()) {
            this.dictMap.clear();
            this.dictMap.putAll(dictMap);
        }
    }

    public List<String> getListMap(String code) {
        return listMap.get(code);
    }

    synchronized void setListMap(Map<String, List<String>> listMap) {
        if (!listMap.isEmpty()) {
            this.listMap.clear();
            this.listMap.putAll(listMap);
        }
    }

    public static DictHolder getInstance() {
        return HOLDER;
    }
}