package br.edu.ifba.internetBanking.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.internetBanking.dtos.UserForm;
import br.edu.ifba.internetBanking.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<UserForm> save(@RequestBody UserForm userForm, UriComponentsBuilder uriBuilder) {
		
		UserForm form = this.userService.save(userForm);
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(form.id()).toUri();
		
		return ResponseEntity.created(uri).body(form);
	}
}
