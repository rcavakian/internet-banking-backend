package br.edu.ifba.internetBanking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifba.internetBanking.clients.EmailClient;
import br.edu.ifba.internetBanking.clients.EmailDTO;
import br.edu.ifba.internetBanking.dtos.UserForm;
import br.edu.ifba.internetBanking.entities.User;
import br.edu.ifba.internetBanking.repositories.UserRepository;

@Service
public class UserService{
	
	@Autowired
	private EmailClient emailClient;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserForm save(UserForm userForm) {
        User user = new User(userForm);
        user = userRepository.save(user);
        
        emailClient.sendEmail(new EmailDTO("an.bezerra@gmail.com", 
        		                           "an.bezerra@gmail.com",
        		                           "Registry Successfull",
        		                           "Welcome to Caramelo Bank! Your account was successfully created."));
        return new UserForm(user);
    }
}
