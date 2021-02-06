package com.yohann.dict.common.exception;

/**
 * <p>
 * 全局异常
 * </p>
 *
 * @author Yohann
 * @since 2021/1/22 22:37
 */
public class DictException extends RuntimeException{
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}