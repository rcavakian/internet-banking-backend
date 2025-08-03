package br.edu.ifba.internetBanking.dtos;

import java.time.LocalDateTime;

import br.edu.ifba.internetBanking.entities.User;

public record UserForm(Long id, String name, String cpf, String email, String passwordHash, LocalDateTime dateRegister) {
    public UserForm(User user) {
        this(user.getId(), user.getName(), user.getCpf(), user.getEmail(), user.getPasswordHash(), user.getDateRegister());
    }
}
