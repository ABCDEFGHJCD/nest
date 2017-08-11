package com.jovezhao.nest.ddd.exception;

import com.jovezhao.nest.exception.CustomException;

/**
 * Created by zhaofujun on 2017/8/11.
 */
public class EmptyException extends CustomException {
    public EmptyException(String message, Object... arguments) {
        super(601, message, arguments);
    }
}
