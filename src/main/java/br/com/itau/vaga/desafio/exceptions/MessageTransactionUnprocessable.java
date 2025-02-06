package br.com.itau.vaga.desafio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class MessageTransactionUnprocessable extends RuntimeException implements Serializable {

    public MessageTransactionUnprocessable(String ex){
        super(ex);
    }

    public  MessageTransactionUnprocessable(String ex, Throwable cause){
        super(ex,cause);
    }

}