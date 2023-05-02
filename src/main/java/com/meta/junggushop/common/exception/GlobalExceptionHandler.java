package com.meta.junggushop.common.exception;

import com.meta.junggushop.common.dto.ResponseDto;
import com.meta.junggushop.common.message.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.meta.junggushop.common.message.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.meta.junggushop.common.message.ErrorCode.INVALID_EMAIL_PATTERN;
import static com.meta.junggushop.common.message.ErrorCode.INVALID_PASSWORD_PATTERN;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseDto<ErrorCode> handleCustomException(CustomException ex) {
        return new ResponseDto<>(ex.getErrorCode(), ex.getErrorCode());
    }

    //Vaildation 예외처리
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseDto<ErrorCode> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        for (FieldError error : result.getFieldErrors()) {
            if (error.getField().equals("email")) {
                return new ResponseDto<>(INVALID_EMAIL_PATTERN, INVALID_EMAIL_PATTERN);
            } else {
                return new ResponseDto<>(INVALID_PASSWORD_PATTERN, INVALID_PASSWORD_PATTERN);
            }
        }
        return new ResponseDto<>(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseDto<HttpStatus> handleServerException(Exception e) {
        return new ResponseDto<>(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
