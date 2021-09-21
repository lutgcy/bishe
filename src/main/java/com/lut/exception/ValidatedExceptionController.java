package com.lut.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author guochengye
 * @create 2021-09-12 22:41
 * @description
 */

@Slf4j
@ControllerAdvice(basePackages = "com.lut.controller")
@RestController
public class ValidatedExceptionController {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public HashMap<String, String> handleValidatedException(MethodArgumentNotValidException e) {

        log.error("数据校验出现异常{}，异常类型{}", e.getMessage(), e.getClass());

        HashMap<String, String> errorMap = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getFieldErrors().forEach((item) -> {
            errorMap.put(item.getField(), item.getDefaultMessage());
        });

        return errorMap;
    }

}
