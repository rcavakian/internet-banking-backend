package br.edu.ifba.internetBanking.entitys;

import java.time.LocalDateTime;

import br.edu.ifba.internetBanking.dtos.UserForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique =  true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String senhaHash;
    private LocalDateTime dateRegister;

    public User(){}

    public User(UserForm userForm) {
        this.id = userForm.id();
        this.name = userForm.name();
        this.cpf = userForm.cpf();
        this.email = userForm.email();
        this.senhaHash = userForm.senhaHash();
        this.dateRegister = userForm.dateRegister();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

}
