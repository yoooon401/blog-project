package com.app.blog.project.common.exception;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionHandler {

    /**
     * RuntimeException
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleResponseStatusException(final RuntimeException ex, HttpServletRequest request) {
        log.error(":::::::::: handleRuntimeException ::::::::::");
        log.error("1. URI               : {} ", request.getRequestURI());
        log.error("2. ERROR MESSAGE     : {} ", ex.getMessage());
        log.error("HEADERS ");
        requestInfo(request);

        return new ResponseEntity<>(ApiError.of(request.getRequestURI(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, NullPointerException.class, NullPointerException.class})
    public ResponseEntity<ApiError> handleIllegalArgument(final IllegalArgumentException ex, HttpServletRequest request) {
        String message = ex.getCause() != null ? ex.getCause().getLocalizedMessage() : ex.getMessage();
        log.error(":::::::::: handleIllegalArgument ::::::::::");
        log.error("1. URI               : {} ", request.getRequestURI());
        log.error("2. ERROR MESSAGE     : {} ", ex.getMessage());
        log.error("EXCEPTION Trace ");
        ex.printStackTrace();
        log.error("HEADERS ");
        requestInfo(request);
        return new ResponseEntity<>(ApiError.of(request.getRequestURI(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {FeignException.class} )
    public ResponseEntity<ApiError> handleFeignException(final FeignException ex, HttpServletRequest request) {
        log.error(":::::::::: handleFeignException ::::::::::");
        log.error("1. URI                           : {} ", request.getRequestURI());
        log.error("2. ERROR MESSAGE                 : {} ", ex.getMessage());
        log.error("HEADERS ");
        requestInfo(request);

        return new ResponseEntity<>(ApiError.of(request.getRequestURI(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class} )
    public ResponseEntity<ApiError> exception(final Exception ex, HttpServletRequest request) {
        log.error(":::::::::: handleException ::::::::::");
        log.error("1. URI               : {} ", request.getRequestURI());
        log.error("2. ERROR MESSAGE     : {} ", ex.getMessage());
        log.error("HEADERS ");
        requestInfo(request);

        return new ResponseEntity<>(ApiError.of(request.getRequestURI(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private void requestInfo(HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String headerName = names.nextElement();
            log.error("          {} : {}", headerName, request.getHeader(headerName));
        }

        log.error("PARAMS ");
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            log.error("          {} : {}", paramName, request.getParameter(paramName));
        }
    }
}