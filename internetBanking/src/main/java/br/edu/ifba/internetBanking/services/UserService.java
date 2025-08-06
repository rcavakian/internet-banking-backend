package br.edu.ifba.internetBanking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.clients.EmailDTO;
import br.edu.ifba.internetBanking.clients.EmailClient;
import br.edu.ifba.internetBanking.dtos.UserDTO;
import br.edu.ifba.internetBanking.dtos.UserForm;
import br.edu.ifba.internetBanking.entities.User;
import br.edu.ifba.internetBanking.repositories.UserRepository;

@Service
public class UserService{
	
    @Autowired
    private EmailClient emailClient;
    private UserRepository userRepository;
    private AccountService accountService;

    public UserService(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public UserDTO save(UserForm userForm) {
        // Salva o usuário primeiro
        User user = new User(userForm);
        user = userRepository.save(user);
        
        accountService.createAccountForUser(user);
        
        emailClient.sendEmail(new EmailDTO("an.bezerra@gmail.com", 
        		                           "an.bezerra@gmail.com",
        		                           "Registry Successfull",
        		                           "Welcome to Caramelo Bank! Your account was successfully created."));
        return new UserDTO(user);
    }
    
    public List<UserDTO> list() {
    	
    	return this.userRepository.findAll().stream().map(UserDTO::new).toList();
    }
}
