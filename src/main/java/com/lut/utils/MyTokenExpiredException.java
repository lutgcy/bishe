package com.lut.utils;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "认证已到期")
public class MyTokenExpiredException extends TokenExpiredException {
    public MyTokenExpiredException(String message) {
        super(message);
    }
}
