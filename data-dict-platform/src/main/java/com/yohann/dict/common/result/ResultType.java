package com.yohann.dict.common.result;

/**
 * <p>
 * 返回结果枚举
 * </p>
 *
 * @author Yohann
 */
public enum ResultType {
    //返回状态枚举
    SUCCESS(200, "成功"),
    ERROR(201, "失败");

    private Integer code;
    private String message;

    ResultType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}