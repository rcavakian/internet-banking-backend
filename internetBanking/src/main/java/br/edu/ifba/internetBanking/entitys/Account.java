package br.edu.ifba.internetBanking.entitys;

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
    private String agengy;
    private BigDecimal balence;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {}

    public Account(AccountDTO accountDTO) {
        this.id = accountDTO.id();
        this.number = accountDTO.number();
        this.agengy = accountDTO.agency();
        this.balence = accountDTO.balence();
        this.user = accountDTO.user();
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

    public void setAgengy(String agengy) {
        this.agengy = agengy;
    }

    public BigDecimal getBalence() {
        return balence;
    }

    public void setBalence(BigDecimal balence) {
        this.balence = balence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
