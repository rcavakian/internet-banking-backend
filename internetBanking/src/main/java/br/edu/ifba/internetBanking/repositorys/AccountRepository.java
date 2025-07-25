package br.edu.ifba.internetBanking.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifba.internetBanking.entitys.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    @Query("SELECT c.numero FROM Conta c ORDER BY c.id DESC LIMIT 1")
    String searchLastNumberCreated();
}
