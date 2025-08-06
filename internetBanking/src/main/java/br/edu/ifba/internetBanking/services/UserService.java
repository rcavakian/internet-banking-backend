package br.edu.ifba.internetBanking.services;

import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.clients.EmailClient;
import br.edu.ifba.internetBanking.clients.EmailDTO;
import br.edu.ifba.internetBanking.dtos.UserDTO;
import br.edu.ifba.internetBanking.dtos.UserForm;
import br.edu.ifba.internetBanking.entities.User;
import br.edu.ifba.internetBanking.repositories.UserRepository;

@Service
public class UserService{
	private EmailClient emailClient;
    private UserRepository userRepository;

    public UserService(EmailClient emailClient, UserRepository userRepository) {
        this.emailClient = emailClient;
        this.userRepository = userRepository;
    }

    public UserDTO save(UserForm userForm) {
        User user = new User(userForm);
        user = userRepository.save(user);
        
        emailClient.sendEmail(new EmailDTO("an.bezerra@gmail.com", 
        		                           "an.bezerra@gmail.com",
        		                           "Registry Successfull",
        		                           "Welcome to Caramelo Bank! Your account was successfully created."));
        return new UserDTO(user);
    }
}
