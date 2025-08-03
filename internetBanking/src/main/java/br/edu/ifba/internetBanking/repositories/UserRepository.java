package br.edu.ifba.internetBanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.internetBanking.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByCpf(String cpf);
}
