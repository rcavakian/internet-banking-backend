package br.edu.ifba.internetBanking.dtos;

import java.math.BigDecimal;

import br.edu.ifba.internetBanking.entities.Account;
import br.edu.ifba.internetBanking.entities.User;


public record AccountDTO(Long id, String number, String agency, BigDecimal balance, User user) {
    public AccountDTO(Account account) {
        this(account.getId(), account.getNumber(), account.getAgengy(), account.getBalance(), account.getUser());
    }
}
