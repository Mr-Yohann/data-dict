package com.yohann.common.annotation;

import java.lang.annotation.*;

/**
 * 开启字典翻译功能 (需配合mybatis拦截器实现)
 *
 * @author Yohann
 * @since 2021/2/18 21:17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableTranslation {}
