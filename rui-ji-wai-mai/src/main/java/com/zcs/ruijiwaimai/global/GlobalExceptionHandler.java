package com.zcs.ruijiwaimai.global;

import com.zcs.ruijiwaimai.pojo.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result allUnHandlerException(Exception e) {
        log.error("Error", e);
        return Result.fail("服务异常");
    }
}
