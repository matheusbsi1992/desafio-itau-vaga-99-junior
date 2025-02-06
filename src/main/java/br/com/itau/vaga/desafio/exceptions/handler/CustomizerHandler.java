package br.com.itau.vaga.desafio.exceptions.handler;

import br.com.itau.vaga.desafio.exceptions.MessageException;

import br.com.itau.vaga.desafio.exceptions.MessageTransactionUnprocessable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MessageTransactionUnprocessable.class)
    public final ResponseEntity<MessageException> handlerNotFoundException(Exception ex, WebRequest request) {

        MessageException exceptionResponse = new MessageException(
                ex.getMessage(),
                request.getDescription(false),
                new Date());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}