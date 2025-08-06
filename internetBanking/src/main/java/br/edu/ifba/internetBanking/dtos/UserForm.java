package br.edu.ifba.internetBanking.dtos;

import br.edu.ifba.internetBanking.entities.User;

public record UserForm(String name, String cpf, String email, String passwordHash) {
    public UserForm(User user) {
        this(user.getName(), user.getCpf(), user.getEmail(), user.getPasswordHash());
    }
}
