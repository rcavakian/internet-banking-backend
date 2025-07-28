package br.edu.ifba.internetBanking.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.internetBanking.entitys.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findTopByOrderByIdDesc();
}
