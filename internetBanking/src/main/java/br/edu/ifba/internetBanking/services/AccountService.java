package br.edu.ifba.internetBanking.services;

import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.dtos.AccountDTO;
import br.edu.ifba.internetBanking.dtos.UserForm;
import br.edu.ifba.internetBanking.entities.Account;
import br.edu.ifba.internetBanking.entities.User;
import br.edu.ifba.internetBanking.repositories.AccountRepository;

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
        Account lastAccount = accountRepository.findTopByOrderByIdDesc();
        int newNumber = 1;
        if(lastAccount != null) {
            newNumber = Integer.parseInt(lastAccount.getNumber()) + 1;
        }

        return String.format("%08d", newNumber);
    }

}
