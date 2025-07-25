package br.edu.ifba.internetBanking.services;

import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.dtos.AccountDTO;
import br.edu.ifba.internetBanking.dtos.UserForm;
import br.edu.ifba.internetBanking.entitys.Account;
import br.edu.ifba.internetBanking.entitys.User;
import br.edu.ifba.internetBanking.repositorys.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDTO save(UserForm userForm) {
        Account account = new Account();
        account.setUser(new User(userForm));
        account.setNumber(generateAccountNumber());
        account = accountRepository.save(account);

        return new AccountDTO(account);
    }

    private String generateAccountNumber() {
        String lastNumber = accountRepository.searchLastNumberCreated();
        int newNumber = 0;
        if(lastNumber != null) {
            newNumber = Integer.parseInt(lastNumber) + 1;
        }
        else {
            newNumber = 1;
        }

        return String.format("%08d", newNumber);
    }

}
