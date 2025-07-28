package br.edu.ifba.internetBanking.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.dtos.AccountDTO;
import br.edu.ifba.internetBanking.dtos.OperationDTO;
import br.edu.ifba.internetBanking.entitys.Operation;
import br.edu.ifba.internetBanking.repositorys.OperationRepository;

@Service
public class OperationService {
    private OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public OperationDTO save(OperationDTO operationDTO) {
        Operation operation = new Operation(operationDTO);
        operation = operationRepository.save(operation);
        
        return new OperationDTO(operation);
    }

    public OperationDTO deposit(AccountDTO accountDTO, BigDecimal value) {
        return null;
    }

    public OperationDTO withdrawal(AccountDTO accountDTO, BigDecimal value) {
        return null;
    }

    public OperationDTO payment(AccountDTO accountDTO, BigDecimal value) {
        return null;
    }

    public List<OperationDTO> statement() {
        return null;
    }
}
