package com.apipdv.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String returnErrorUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String returnErrorDev = ex.getCause().toString();
        List<Erro> erros = Arrays.asList(new Erro(returnErrorUser, returnErrorDev));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = createListErrors(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAcessException(RuntimeException ex, WebRequest request) {
        String returnErrorUser = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String returnErrorDev = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(returnErrorUser, returnErrorDev));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private List<Erro> createListErrors(BindingResult bindingResult) {
        List<Erro> errors = new ArrayList<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            String returnMsgErroUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String returnMsgErroDev = fieldError.toString();
            errors.add(new Erro(returnMsgErroUser, returnMsgErroDev));
        }

        return errors;
    }

    public static class Erro {

        private String returnErrorUser;
        private String returnErrorDev;

        public Erro(String returnErrorUser, String returnErrorDev) {
            this.returnErrorUser = returnErrorUser;
            this.returnErrorDev = returnErrorDev;
        }

        public String getReturnErrorUser() {
            return returnErrorUser;
        }

        public String getReturnErrorDev() {
            return returnErrorDev;
        }
    }
}
