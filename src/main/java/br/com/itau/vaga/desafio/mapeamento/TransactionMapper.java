package br.com.itau.vaga.desafio.mapeamento;

import br.com.itau.vaga.desafio.dto.TransactionDTO;
import br.com.itau.vaga.desafio.model.TransactionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mappings({
            @Mapping(source = "valor_model", target = "valorDTO", numberFormat = "0.00" ),//resultType = BigDecimal.class, qualifiedByName = "scaleBigDecimal"),
            @Mapping(source = "datahora_model", target = "dataHoraDTO"),
    })
    TransactionDTO transactionForTransactionDTO(TransactionModel transactionModel);

    @Mappings({
            @Mapping(source = "valorDTO", target = "valor_model", numberFormat = "0.00"),//, resultType = BigDecimal.class, qualifiedByName = "scaleBigDecimal"),
            @Mapping(source = "dataHoraDTO", target = "datahora_model"),
    })
    TransactionModel transactionDTOFortransaction(TransactionDTO transactionDTO);


    /*@Named("scaleBigDecimal")
    default BigDecimal scaleBigDecimal(BigDecimal value) {
        return value != null ? value.setScale(2, RoundingMode.HALF_UP) : null;
    }*/
}
