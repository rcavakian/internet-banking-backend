package br.edu.ifba.internetBanking.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.ifba.internetBanking.entities.Account;
import br.edu.ifba.internetBanking.entities.Operation;
import br.edu.ifba.internetBanking.entities.OperationType;

public record OperationDTO(Long id, OperationType operationType, BigDecimal value, LocalDateTime dateTime, String description, Account account) {
    public OperationDTO(Operation operation) {
        this(operation.getId(), operation.getOperationType(), operation.getValue(), operation.getDateTime(), operation.getDescription(), operation.getAccount());
    }
}
