package br.com.itau.vaga.desafio.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class MessageException {
    private String mensagem;
    private String detalhes;
    private Date dataMensagem;

    public MessageException(String mensagem, String detalhes, Date dataMensagem) {
        this.mensagem = mensagem;
        this.detalhes = detalhes;
        this.dataMensagem = dataMensagem;
    }
}
