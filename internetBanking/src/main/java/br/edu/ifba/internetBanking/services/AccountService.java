package br.edu.ifba.internetBanking.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.dtos.AccountDTO;
import br.edu.ifba.internetBanking.entities.Account;
import br.edu.ifba.internetBanking.entities.User;
import br.edu.ifba.internetBanking.repositories.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDTO save(AccountDTO accountDTO) {
        Account account = new Account();
        account.setUser(accountDTO.user());
        account.setNumber(generateAccountNumber());
        account.setBalance(BigDecimal.ZERO);
        account = accountRepository.save(account);

        return new AccountDTO(account);
    }
    
    public Account createAccountForUser(User user) {
        Account account = new Account();
        account.setUser(user);
        account.setNumber(generateAccountNumber());
        account.setBalance(BigDecimal.ZERO);
        // A agência já é definida como "0001" por padrão na entidade
        return accountRepository.save(account);
    }
    
    public List<AccountDTO> list() {
        return this.accountRepository.findAll().stream().map(AccountDTO::new).toList();
    }
    
    public List<AccountDTO> findAccountsByUser(User user) {
        return this.accountRepository.findByUser(user).stream()
            .map(AccountDTO::new)
            .toList();
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
