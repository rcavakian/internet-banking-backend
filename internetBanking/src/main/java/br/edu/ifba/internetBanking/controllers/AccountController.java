package br.edu.ifba.internetBanking.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.internetBanking.dtos.AccountDTO;
import br.edu.ifba.internetBanking.dtos.UserForm;
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
	public ResponseEntity<AccountDTO> save(@RequestBody UserForm userForm, UriComponentsBuilder uriBuilder) {
		
		AccountDTO dto = this.accountService.save(userForm);
		URI uri = uriBuilder.path("/accounts/{id}").buildAndExpand(dto.id()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
}
