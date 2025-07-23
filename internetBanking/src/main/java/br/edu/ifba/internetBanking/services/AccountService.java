package br.edu.ifba.internetBanking.services;

import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.dtos.AccountDTO;
import br.edu.ifba.internetBanking.entitys.Account;
import br.edu.ifba.internetBanking.entitys.User;
import br.edu.ifba.internetBanking.repositorys.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDTO save(AccountDTO accountDTO) {
        Account account = new Account();
        account = accountRepository.save(account);

        return new AccountDTO(account);
    }

    public AccountDTO save(User user) {
        Account account = new Account();
        account.setUser(user);
        account = accountRepository.save(account);

        return new AccountDTO(account);
    }

}
