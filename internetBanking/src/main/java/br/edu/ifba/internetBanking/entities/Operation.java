package br.edu.ifba.internetBanking.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.ifba.internetBanking.dtos.OperationDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    public BigDecimal value;
    private LocalDateTime dateTime;
    private String description;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Operation() {}

    public Operation(OperationType operationType, BigDecimal value, String description, Account account) {
        
    }

    public Operation(OperationDTO operationDTO) {
        this.id = operationDTO.id();
        this.operationType = operationDTO.operationType();
        this.value = operationDTO.value();
        this.dateTime = operationDTO.dateTime();
        this.description = operationDTO.description();
        this.account = operationDTO.account();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
