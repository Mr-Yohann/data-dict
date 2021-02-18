package com.yohann.dict.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 全局异常
 * </p>
 *
 * @author Yohann
 * @since 2021/1/22 22:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictException extends RuntimeException{
    private Integer code;
    private String msg;
}