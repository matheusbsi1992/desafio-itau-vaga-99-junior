package br.com.itau.vaga.desafio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.itau.vaga.desafio.dto.TransactionDTO;
import br.com.itau.vaga.desafio.mapeamento.SummaryValueStatistics;
import br.com.itau.vaga.desafio.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Tag(name = "Transações")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("listAll")
    @Operation(summary = "List All", description = "List All")
    public ResponseEntity<List<TransactionDTO>> listAll() {
        List<TransactionDTO> transactionDTOS = transactionService.listAll();
        return ResponseEntity.ok(transactionDTOS);
    }

    @DeleteMapping("transacao")
    @Operation(summary = "Delete All Transactions", description = "Remove all transactions from the system",
            responses = {
                    @ApiResponse(description = "Deleted", responseCode = "201",
                            content =
                            @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = {
                                    @Content(mediaType = "application/json")
                            }),
                    @ApiResponse(description = "Not Found", responseCode = "404",
                            content = {
                                    @Content(mediaType = "application/json")
                            }),
                    @ApiResponse(description = "Internal Error", responseCode = "500",
                            content =
                            @Content(mediaType = "application/json"))
            })
    public ResponseEntity<?> deleteTransaction() {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.deleteTransaction());
    }

    @PostMapping("transacao")
    @Operation(summary = "Insert Transaction", description = "Insert transaction with value and date",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content =
                            @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = {
                                    @Content(mediaType = "application/json")
                            }),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422",
                            content = {
                                    @Content(mediaType = "application/json")
                            }),
                    @ApiResponse(description = "Not Found", responseCode = "404",
                            content = {
                                    @Content(mediaType = "application/json")
                            }),
                    @ApiResponse(description = "Internal Error", responseCode = "500",
                            content =
                            @Content(mediaType = "application/json"))
            })
    public ResponseEntity<?> insertTransaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.insertTransaction(transactionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("A transação foi aceita (ou seja foi validada, está válida e foi registrada)");
    }

    @GetMapping("estatistica")
    @Operation(summary = "Statistics", description = "Statistics Transaction with Value and Date",
            responses = {
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = {
                                    @Content(mediaType = "application/json")
                            }),
                    @ApiResponse(description = "Not Found", responseCode = "404",
                            content = {
                                    @Content(mediaType = "application/json")
                            }),
                    @ApiResponse(description = "Internal Error", responseCode = "500",
                            content =
                            @Content(mediaType = "application/json"))
            })
    public ResponseEntity<?> listAllStatistics() {
        SummaryValueStatistics transactionDTOS = transactionService.returnAllStatistic(60);
        return ResponseEntity.ok(transactionDTOS);
    }

}