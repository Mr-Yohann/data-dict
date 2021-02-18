package com.yohann.common.config;

import com.yohann.common.annotation.EnableTranslation;
import com.yohann.common.annotation.Translate;
import com.yohann.common.utils.DictHolder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;

/**
 * 翻译功能拦截器配置
 *
 * @author Yohann
 * @since 2021/2/18 21:27
 */
@Configuration
public class TranslationConfig {
    @Bean
    public TranslationInterceptor getInterceptor() {
      return new TranslationInterceptor();
    }

    /** 自定义拦截器 */
    @Intercepts({
        @Signature(
            // 只拦截查询
            method = "query",
            type = Executor.class,
            args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(
            type = ResultSetHandler.class,
            method = "handleResultSets",
            args = Statement.class)
    })
    class TranslationInterceptor implements Interceptor {

        @SuppressWarnings("unchecked")
        public Object intercept(Invocation invocation) throws Throwable {
            // 获取结果集
            List<Object> objects = (List<Object>) invocation.proceed();

            if (!objects.isEmpty()) {

                Object o = objects.get(0);
                if (o != null) {

                    Class<?> resultsClass = o.getClass();
                    // 通过EnableTranslation注解区分是否翻译
                    if (resultsClass.getAnnotation(EnableTranslation.class) != null) {

                        // 获取字典实例
                        DictHolder dictHolder = DictHolder.getInstance();
                        for (Object object : objects) {

                            // 获取所有字段
                            Field[] fields = resultsClass.getDeclaredFields();
                            for (Field field : fields) {

                                // 判断字段是否需要翻译
                                Translate translate = field.getAnnotation(Translate.class);
                                if (translate != null) {
                                    // 获取注解参数
                                    String code = translate.code();
                                    String keyFieldName = translate.keyField();

                                    // 字典项的键对应的字段的值
                                    Field keyField = resultsClass.getDeclaredField(keyFieldName);
                                    keyField.setAccessible(true);
                                    Object keyFieldValue = keyField.get(object);

                                    // 在字典值查询对应的映射值
                                    String translationResult = dictHolder.getDictMap(code).get(keyFieldValue.toString());

                                    // 设置需翻译字段属性
                                    field.setAccessible(true);
                                    field.set(object, translationResult);
                                }
                            }
                        }
                }
              }
            }

            return objects;
        }
    }
}
