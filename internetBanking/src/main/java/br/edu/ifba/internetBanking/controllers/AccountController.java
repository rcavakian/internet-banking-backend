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

import br.edu.ifba.internetBanking.dtos.AccountDTO;
import br.edu.ifba.internetBanking.entities.User;
//import br.edu.ifba.internetBanking.dtos.UserForm;
import br.edu.ifba.internetBanking.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Operation(summary = "Save Account", description = "Save Accounts")
	@ApiResponse(responseCode = "200", description = "Save Account")
	@PostMapping
	public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO accountDTO, UriComponentsBuilder uriBuilder) {

		AccountDTO dto = this.accountService.save(accountDTO);
		URI uri = uriBuilder.path("/accounts/{id}").buildAndExpand(dto.id()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@Operation(summary = "List User Accounts", description = "List accounts for authenticated user")
	@ApiResponse(responseCode = "200", description = "List of user accounts")
	@GetMapping
	public List<AccountDTO> list() {
		// Obter o usuário autenticado atual
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return this.accountService.findAccountsByUser(currentUser);
	}
}
