package com.yohann.dict.common.result;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 统一返回结果
 * </p>
 *
 * @author Yohann
 */
public class Result {
    private String message;
    private Integer code;
    private Boolean success;
    private Map<String, Object> data = new HashMap<>();

    private Result(ResultType resultType) {
        this.setCode(resultType.getCode());
        this.setMessage(resultType.getMessage());
    }

    public static Result success() {
        return new Result(ResultType.SUCCESS).success(true);
    }

    public static Result error() {
        return new Result(ResultType.ERROR).success(false);
    }

    public static Result status(Boolean status) {
        if (status) {
            return success();
        }
        return error();
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}