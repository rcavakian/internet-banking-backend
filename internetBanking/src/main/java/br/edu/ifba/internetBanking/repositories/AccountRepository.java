package br.edu.ifba.internetBanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.internetBanking.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findTopByOrderByIdDesc();
}
