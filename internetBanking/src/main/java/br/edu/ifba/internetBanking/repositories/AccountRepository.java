package br.edu.ifba.internetBanking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.internetBanking.entities.Account;
import br.edu.ifba.internetBanking.entities.User;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findTopByOrderByIdDesc();
    List<Account> findByUser(User user);
}
