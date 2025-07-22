package br.edu.ifba.internetBanking.dtos;

import java.math.BigDecimal;

import br.edu.ifba.internetBanking.entitys.Account;


public record AccountDTO(Long id, String number, String agency, BigDecimal balence) {
    public AccountDTO(Account account) {
        this(account.getId(), account.getNumber(), account.getAgengy(), account.getBalence());
    }
}
