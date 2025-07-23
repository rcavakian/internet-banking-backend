package br.edu.ifba.internetBanking.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.internetBanking.dtos.OperationDTO;
import br.edu.ifba.internetBanking.services.OperationService;

@RestController
@RequestMapping("/operations")
public class OperationController {

	private OperationService operationService;
	
	public OperationController(OperationService operationService) {
		this.operationService = operationService;
	}
	
	@PostMapping
	public ResponseEntity<OperationDTO> save(@RequestBody OperationDTO operationDTO, UriComponentsBuilder uriBuilder) {
		
		OperationDTO dto = this.operationService.save(operationDTO);
		URI uri = uriBuilder.path("/operations/{id}").buildAndExpand(dto.id()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
}
