package br.edu.ifba.internetBanking.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.internetBanking.dtos.AuthenticationData;
import br.edu.ifba.internetBanking.dtos.TokenJWTData;
import br.edu.ifba.internetBanking.entities.User;
import br.edu.ifba.internetBanking.services.JWTokenService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private JWTokenService tokenService;
    
    public AuthenticationController(AuthenticationManager authenticationManager, JWTokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Operation(summary = "Login", description = "Login")
	@ApiResponse(responseCode = "200", description = "Login")
    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthenticationData authenticationData) {
        var data = new UsernamePasswordAuthenticationToken(authenticationData.login(), authenticationData.password());
        var authentication = authenticationManager.authenticate(data);
        var token = tokenService.genereteToken((User)authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTData(token));
    }
}
