package br.edu.ifba.internetBanking.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.internetBanking.entitys.User;

public interface UserRepository extends JpaRepository<Long, User>{

}
