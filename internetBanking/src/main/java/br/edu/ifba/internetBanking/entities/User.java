package br.edu.ifba.internetBanking.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifba.internetBanking.dtos.UserForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique =  true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String passwordHash;
    private LocalDateTime dateRegister;

    public User(){}

    public User(Long id, String name, String cpf, String email, String passwordHash, LocalDateTime dateRegister) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.passwordHash = passwordHash;
        this.dateRegister = dateRegister;
    }

    public User(UserForm userForm) {
        this.name = userForm.name();
        this.cpf = userForm.cpf();
        this.email = userForm.email();
        this.passwordHash = userForm.passwordHash();
        setDateRegister();
        this.dateRegister = getDateRegister();
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setpasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister() {
        this.dateRegister = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

}
