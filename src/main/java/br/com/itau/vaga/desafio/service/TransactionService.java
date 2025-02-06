package br.com.itau.vaga.desafio.service;

import br.com.itau.vaga.desafio.dto.TransactionDTO;
import br.com.itau.vaga.desafio.exceptions.MessageTransactionUnprocessable;
import br.com.itau.vaga.desafio.mapeamento.SummaryValueStatistics;
import br.com.itau.vaga.desafio.mapeamento.TransactionMapper;
import br.com.itau.vaga.desafio.model.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private Logger logger = Logger.getLogger(TransactionService.class.getName());

    private TransactionModel transactionModel;
    private TransactionDTO transactionDTO;

    @Autowired
    private TransactionMapper transactionMapper;

    private List<TransactionDTO> transactionDTOS = new ArrayList<>();

    @Autowired
    public TransactionService() {
        this.transactionModel = new TransactionModel();
        this.transactionDTO = new TransactionDTO();
    }

    public List<TransactionDTO> insertTransaction(TransactionDTO transactionDTO) {

        logger.info("Insert One for Many Values of Transaction...");
        /*
        Tenham os campos valor e dataHora preenchidos
        A transação NÃO DEVE acontecer no futuro
        A transação DEVE ter acontecido a qualquer momento no passado
        A transação NÃO DEVE ter valor negativo
        A transação DEVE ter valor igual ou maior que 0 (zero)
        */
        var valueNewIdentified = transactionMapper.transactionDTOFortransaction(transactionDTO);

        if (valueNewIdentified.getValor_model().doubleValue() <= 0.00) {
            throw new MessageTransactionUnprocessable("A transação NÃO DEVE ter valor negativo ou igual a ZERO !!!");
        }

        if (valueNewIdentified.getDatahora_model().isAfter(OffsetDateTime.now())) {
            throw new MessageTransactionUnprocessable("A transação NÃO DEVE acontecer no futuro !!!");
        }

        if (valueNewIdentified.getValor_model().doubleValue() > 0.00 && valueNewIdentified.getDatahora_model().isBefore(OffsetDateTime.now())) {
            valueNewIdentified = new TransactionModel(valueNewIdentified.getValor_model(), valueNewIdentified.getDatahora_model());
            var valueNewIdentifiedDTO = transactionMapper.transactionForTransactionDTO(valueNewIdentified);
            transactionDTOS.add(valueNewIdentifiedDTO);
        }

        return transactionDTOS;
    }

    public String deleteTransaction() {
        logger.info("Delete all Values of Transaction...");

        if (!transactionDTOS.isEmpty()) {
            transactionDTOS.clear();
            return "Todas as informações foram apagadas com sucesso";
        }
        return "";
    }


    public List<TransactionDTO> listAll() {
        logger.info("List all Values of Transaction...");

        return transactionDTOS.
                stream().
                toList();
    }

    public SummaryValueStatistics returnAllStatistic(int valorSegundos) {
        logger.info("Return all Values of Transaction...");

        SummaryValueStatistics summaryValueStatistics = null;

        OffsetDateTime localTime = OffsetDateTime.now();

        for (TransactionDTO transactionDTO1 : transactionDTOS) {
            // -- Identificar as ultimas transacoes a cada sessenta segundos atras ou pode ser maior do que o mesmo valor podendo informar 120
            if (transactionDTO1.getDataHoraDTO().isAfter(localTime.minus(valorSegundos, ChronoUnit.SECONDS))) {
                // -- Força uma filtragem para retornar realmente quem for no valor de 60 segundos ou até mesmo o valor de 120 segundos
                DoubleSummaryStatistics doubleSummaryStatistics = transactionDTOS
                        .stream()
                        .filter(transactionDTO2 -> transactionDTO2.getDataHoraDTO().isAfter(localTime.minus(valorSegundos, ChronoUnit.SECONDS)))
                        .map(TransactionDTO::getValorDTO)
                        .mapToDouble(Double::doubleValue)
                        .summaryStatistics();

                summaryValueStatistics = new SummaryValueStatistics(doubleSummaryStatistics.getCount()
                        , (doubleSummaryStatistics.getSum())
                        , (doubleSummaryStatistics.getAverage())
                        , (doubleSummaryStatistics.getMin())
                        , (doubleSummaryStatistics.getMax()));
            } else {
                summaryValueStatistics = new SummaryValueStatistics(0
                        , 0.0
                        , 0.0
                        , 0.0
                        , 0.0);
            }
        }
        return summaryValueStatistics;
    }

}