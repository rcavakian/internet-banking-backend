package br.edu.ifba.internetBanking.entities;

import java.math.BigDecimal;

import br.edu.ifba.internetBanking.dtos.AccountDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "/accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String agengy = "0001";
    private BigDecimal balance;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {}

    public Account(AccountDTO accountDTO) {
        this.id = accountDTO.id();
        this.number = accountDTO.number();
        this.agengy = accountDTO.agency();
        this.balance = accountDTO.balance();
        this.user = accountDTO.user();
    }

    public void deposit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void withdrawal(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAgengy() {
        return agengy;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
