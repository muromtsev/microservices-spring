package com.muromtsev.employee.controller;

import com.muromtsev.employee.model.utils.ErrorMessage;
import com.muromtsev.employee.model.utils.ResponseWrapper;
import com.muromtsev.employee.model.utils.RestErrorList;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.util.Collections.singletonMap;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    public @ResponseBody ResponseEntity<ResponseWrapper> handException(
            HttpServletRequest request,
            ResponseWrapper responseWrapper) {
        return ResponseEntity.ok(responseWrapper);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<ResponseWrapper> handleIOException(
            HttpServletRequest request,
            RuntimeException exception) {

        RestErrorList errorList = new RestErrorList(HttpStatus.NOT_ACCEPTABLE,
                new ErrorMessage(exception.getMessage(), exception.getMessage()));
        ResponseWrapper responseWrapper = new ResponseWrapper(null,
                singletonMap("status", HttpStatus.NOT_ACCEPTABLE), errorList);

        return ResponseEntity.ok(responseWrapper);

    }



}
