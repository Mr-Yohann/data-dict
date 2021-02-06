package com.yohann.dict.common.exception;

import com.yohann.dict.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author Yohann
 */
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        log.error("======>\n" +
                "Error message：{}\n" +
                "Error cause：{}", e.getMessage(), e.getCause());
        return Result.error().message("An error occurred !");
    }

    @ExceptionHandler(DictException.class)
    @ResponseBody
    public Result error(DictException e) {
        e.printStackTrace();
        log.error("======>\n" +
                "Error message：{}\n" +
                "Error cause：{}", e.getMessage(), e.getCause());
        return Result.error().code(e.getCode()).message(e.getMessage());
    }
}