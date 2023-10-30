package com.grocerystore.controller.error;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalControllerErrorHandler {
    private enum LogStatus {
        STACK_TRACE, MESSAGE_ONLY
    }

    @Data
    private class ExceptionMessage {
        private String message;
        private String statusReason;
        private int statusCode;
        private String timeStamp;
        private String uri;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ExceptionMessage handleNoSuchElementException(
            NoSuchElementException noSuchElementException, WebRequest webRequest) {
        return buildExceptionMessage(noSuchElementException, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY
        );
    }

    private ExceptionMessage buildExceptionMessage(
            NoSuchElementException noSuchElementException, HttpStatus status, WebRequest webRequest, LogStatus logStatus) {
        String message = noSuchElementException.toString();
        String statusReason = status.getReasonPhrase();
        int statusCode = status.value();
        String uri = null;
        String timeStamp =
                ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        if (webRequest instanceof ServletWebRequest swr) {
            uri = swr.getRequest().getRequestURI();
        }

        if (logStatus == LogStatus.MESSAGE_ONLY) {
            log.error("Exception : {}", noSuchElementException.toString());
        }
        else {
            log.error("Exception : {}", noSuchElementException);
        }
        ExceptionMessage exceptionMessage= new ExceptionMessage();
        exceptionMessage.setMessage(message);
        exceptionMessage.setStatusCode(statusCode);
        exceptionMessage.setStatusReason(statusReason);
        exceptionMessage.setTimeStamp(timeStamp);
        exceptionMessage.setUri(uri);
        return exceptionMessage;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ExceptionMessage handleIllegalArgumentException(
            IllegalArgumentException illegalArgumentException, WebRequest webRequest) {
        return buildExceptionMessage(illegalArgumentException, HttpStatus.UNSUPPORTED_MEDIA_TYPE, webRequest, LogStatus.MESSAGE_ONLY
        );
    }
    private ExceptionMessage buildExceptionMessage(
            IllegalArgumentException illegalArgumentException , HttpStatus status, WebRequest webRequest, LogStatus logStatus) {
        String message = illegalArgumentException.toString();
        String statusReason = status.getReasonPhrase();
        int statusCode = status.value();
        String uri = null;
        String timeStamp =
                ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        if (webRequest instanceof ServletWebRequest swr) {
            uri = swr.getRequest().getRequestURI();
        }

        if (logStatus == LogStatus.MESSAGE_ONLY) {
            log.error("Exception : {}", illegalArgumentException.toString());
        }
        else {
            log.error("Exception : {}", illegalArgumentException);
        }
        ExceptionMessage exceptionMessage= new ExceptionMessage();
        exceptionMessage.setMessage(message);
        exceptionMessage.setStatusCode(statusCode);
        exceptionMessage.setStatusReason(statusReason);
        exceptionMessage.setTimeStamp(timeStamp);
        exceptionMessage.setUri(uri);
        return exceptionMessage;


}}
