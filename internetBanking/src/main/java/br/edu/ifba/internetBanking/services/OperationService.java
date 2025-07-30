package br.edu.ifba.internetBanking.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.dtos.OperationDTO;
import br.edu.ifba.internetBanking.entities.Account;
import br.edu.ifba.internetBanking.entities.Operation;
import br.edu.ifba.internetBanking.entities.OperationType;
import br.edu.ifba.internetBanking.repositories.AccountRepository;
import br.edu.ifba.internetBanking.repositories.OperationRepository;

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

    public OperationDTO deposit(Long accountId, BigDecimal value) {
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

        OperationDTO operationDTO = new OperationDTO(operation);

        return operationDTO;
    }

    public OperationDTO withdrawal(Long accountId, BigDecimal value) {
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

        OperationDTO operationDTO = new OperationDTO(operation);

        return operationDTO;
    }

    public OperationDTO payment(Long accountId, BigDecimal value, String description) {
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

        OperationDTO operationDTO = new OperationDTO(operation);

        return operationDTO;
    }

    public List<OperationDTO> statement(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found."));

        return operationRepository.findAllByAccount(account).stream().map(OperationDTO :: new).toList();
    }
    
    public List<OperationDTO> listByMonthYear(int year, int month) {
    	
    	LocalDateTime dateTime = LocalDateTime.of(year, month, 1, 0, 0);
    	
    	return operationRepository.findByDateTime(dateTime).stream().map(OperationDTO::new).toList();
    }
    
    public List<OperationDTO> listByType(OperationType operationType) {
    	
    	List<Operation> operations = operationRepository.findByOperationType(operationType);
    	
    	return operations.stream().map(OperationDTO::new).toList();
    }
}
