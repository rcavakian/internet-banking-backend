package br.edu.ifba.internetBanking.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.internetBanking.entitys.Operation;

public interface OperationRepository extends JpaRepository<Long, Operation>{

}
