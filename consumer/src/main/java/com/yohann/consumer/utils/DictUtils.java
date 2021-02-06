package com.yohann.consumer.utils;

import com.yohann.common.holder.DictHolder;

/**
 * @author Yohann
 * @since 2021/2/6 17:30
 */
public class DictUtils {
    public static DictHolder holder;

    public static DictHolder getDictHolderInstance() {
        return holder;
    }
}