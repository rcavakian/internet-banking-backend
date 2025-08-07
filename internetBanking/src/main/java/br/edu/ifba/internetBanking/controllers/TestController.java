package br.edu.ifba.internetBanking.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.internetBanking.dtos.UserForm;
import br.edu.ifba.internetBanking.services.UserService;

@RestController
@RequestMapping("/test")
public class TestController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public TestController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Service is running!");
    }

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Public endpoint working!");
    }

    @PostMapping("/create-test-user")
    public ResponseEntity<String> createTestUser() {
        try {
            UserForm testUser = new UserForm("Test User", "11111111111", "test@test.com", "123456");
            userService.save(testUser);
            return ResponseEntity.ok("Test user created! CPF: 11111111111, Password: 123456");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating test user: " + e.getMessage());
        }
    }

    @GetMapping("/encode-password")
    public ResponseEntity<String> encodePassword() {
        String plainPassword = "123456";
        String encodedPassword = passwordEncoder.encode(plainPassword);
        return ResponseEntity.ok("Plain: " + plainPassword + " | Encoded: " + encodedPassword);
    }
}
