package com.yohann.common.annotation;

import java.lang.annotation.*;

/**
 * 翻译注解(需先声明开启翻译)
 *
 * @author Yohann
 * @since 2021/2/18 21:23
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Translate {
    /** 字典码 */
    String code();

    /** 字典项的键对应的字段名 */
    String keyField();
}
