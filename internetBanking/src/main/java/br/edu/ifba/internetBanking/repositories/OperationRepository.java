package br.edu.ifba.internetBanking.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.internetBanking.entities.Operation;
import br.edu.ifba.internetBanking.entities.OperationType;

public interface OperationRepository extends JpaRepository<Operation, Long>{

	List<Operation> findByDateTime(LocalDateTime dateTime);
	List<Operation> findByOperationType(OperationType operationType);
}
