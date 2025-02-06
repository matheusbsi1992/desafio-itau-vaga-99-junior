package br.com.itau.vaga.desafio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;


public class TransactionDTO {


    @JsonProperty("valor")
    private Double valorDTO;

    @JsonProperty("dataHora")
    private OffsetDateTime dataHoraDTO;

    public TransactionDTO(Double valorDTO, OffsetDateTime dataHoraDTO) {
        this.valorDTO = valorDTO;
        this.dataHoraDTO = dataHoraDTO;
    }

    public TransactionDTO() {

    }

    public Double getValorDTO() {
        return valorDTO;
    }

    public void setValorDTO(Double valorDTO) {
        this.valorDTO = valorDTO;
    }

    public OffsetDateTime getDataHoraDTO() {
        return dataHoraDTO;
    }

    public void setDataHoraDTO(OffsetDateTime dataHoraDTO) {
        this.dataHoraDTO = dataHoraDTO;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDTO that)) return false;

        return valorDTO.equals(that.valorDTO) && dataHoraDTO.equals(that.dataHoraDTO);
    }

    @Override
    public int hashCode() {
        int result = valorDTO.hashCode();
        result = 31 * result + dataHoraDTO.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "valorDTO=" + valorDTO +
                ", dataHoraDTO=" + dataHoraDTO +
                '}';
    }
}