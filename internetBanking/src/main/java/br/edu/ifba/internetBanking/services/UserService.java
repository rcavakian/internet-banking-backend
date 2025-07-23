package br.edu.ifba.internetBanking.services;

import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.dtos.UserDTO;
import br.edu.ifba.internetBanking.dtos.UserForm;
import br.edu.ifba.internetBanking.entitys.User;
import br.edu.ifba.internetBanking.repositorys.UserRepository;

@Service
public class UserService{
    private UserRepository userRepository;
    private AccountService accountService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO save(UserForm userForm) {
        User user = new User(userForm);
        user = userRepository.save(user);
        accountService.save(user);
        
        return new UserDTO(user);
    }
}
