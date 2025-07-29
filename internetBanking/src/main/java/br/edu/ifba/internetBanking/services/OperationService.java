package br.edu.ifba.internetBanking.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.dtos.OperationDTO;
import br.edu.ifba.internetBanking.entitys.Account;
import br.edu.ifba.internetBanking.entitys.Operation;
import br.edu.ifba.internetBanking.entitys.OperationType;
import br.edu.ifba.internetBanking.repositorys.AccountRepository;
import br.edu.ifba.internetBanking.repositorys.OperationRepository;

@Service
public class OperationService {
    private OperationRepository operationRepository;
    private AccountRepository accountRepository;

    public OperationService(OperationRepository operationRepository, AccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    public OperationDTO save(OperationDTO operationDTO) {
        Operation operation = new Operation(operationDTO);
        operation = operationRepository.save(operation);
        
        return new OperationDTO(operation);
    }

    public void deposit(Long accountId, BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Value should be more than 0.");
        }
        
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found."));
        account.deposit(value);
        accountRepository.save(account);

        Operation operation = new Operation();
        operation.setOperationType(OperationType.DEPOSIT);
        operation.setAccount(account);

        operationRepository.save(operation);
    }

    public void withdrawal(Long accountId, BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Value should be more than 0.");
        }

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found."));

        if (value.compareTo(account.getBalance()) > 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        account.withdrawal(value);
        accountRepository.save(account);

        Operation operation = new Operation();
        operation.setOperationType(OperationType.WITHDRAWAL);
        operation.setAccount(account);

        operationRepository.save(operation);
    }

    public void payment(Long accountId, BigDecimal value, String description) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Value should be more than 0.");
        }

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found."));

        if (value.compareTo(account.getBalance()) > 0) {
            throw new IllegalArgumentException("Payment denied. Insufficient funds.");
        }

        account.withdrawal(value);
        accountRepository.save(account);

        Operation operation = new Operation();
        operation.setOperationType(OperationType.PAYMENT);
        operation.setDescription(description);
        operation.setAccount(account);

        operationRepository.save(operation);
    }

    public List<OperationDTO> statement() {
        return null;
    }
}
