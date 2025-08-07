package br.edu.ifba.internetBanking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AccountService accountService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO save(UserForm userForm) {
        // Criptografa a senha antes de salvar
        String encryptedPassword = passwordEncoder.encode(userForm.passwordHash());
        UserForm encryptedUserForm = new UserForm(userForm.name(), userForm.cpf(), userForm.email(), encryptedPassword);
        
        // Salva o usuário primeiro
        User user = new User(encryptedUserForm);
        user = userRepository.save(user);
        
        accountService.createAccountForUser(user);
        
        emailClient.sendEmail(new EmailDTO("an.bezerra@gmail.com", 
        		                           user.getEmail(),
        		                           "Registry Successfull",
        		                           "Welcome to Caramelo Bank, " +
                                           user.getName() + "! Your account was successfully created."));
        return new UserDTO(user);
    }
    
    public List<UserDTO> list() {
    	
    	return this.userRepository.findAll().stream().map(UserDTO::new).toList();
    }
}
