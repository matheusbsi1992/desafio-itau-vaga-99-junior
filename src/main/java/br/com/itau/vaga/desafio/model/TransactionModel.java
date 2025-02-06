package br.com.itau.vaga.desafio.model;


import java.time.OffsetDateTime;

public class TransactionModel {

    private Double valor_model;
    private OffsetDateTime datahora_model;

    public TransactionModel() {}

    public TransactionModel(Double valor_model, OffsetDateTime datahora_model) {
        this.valor_model = valor_model;
        this.datahora_model = datahora_model;
    }

    public Double getValor_model() {
        return valor_model;
    }

    public OffsetDateTime getDatahora_model() {
        return datahora_model;
    }

    public void setDatahora_model(OffsetDateTime datahora_model) {
        this.datahora_model = datahora_model;
    }

    public void setValor_model(Double valor_model) {
        this.valor_model = valor_model;
    }
}