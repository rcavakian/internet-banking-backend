package br.edu.ifba.internetBanking.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.internetBanking.dtos.UserDTO;
import br.edu.ifba.internetBanking.dtos.UserForm;
import br.edu.ifba.internetBanking.entities.User;
import br.edu.ifba.internetBanking.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@Operation(summary = "Save User", description = "Save User")
	@ApiResponse(responseCode = "200", description = "Save User")
	@PostMapping
	public ResponseEntity<UserDTO> save(@RequestBody UserForm userForm, UriComponentsBuilder uriBuilder) {
		UserDTO userDTO = this.userService.save(userForm);
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(userDTO.id()).toUri();
		
		return ResponseEntity.created(uri).body(userDTO);
	}
	
	@GetMapping
	public List<UserDTO> list() {
		
		return this.userService.list();
	}
	
	@Operation(summary = "Get current user", description = "Get current authenticated user information")
	@ApiResponse(responseCode = "200", description = "Current user information")
	@GetMapping("/me")
	public UserDTO getCurrentUser() {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new UserDTO(currentUser);
	}
}
