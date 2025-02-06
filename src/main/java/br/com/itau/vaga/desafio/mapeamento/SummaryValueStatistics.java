package br.com.itau.vaga.desafio.mapeamento;

import java.math.BigDecimal;

public record SummaryValueStatistics(
        long count,
        Double sum,
        Double avg,
        Double min,
        Double max
) {
}


