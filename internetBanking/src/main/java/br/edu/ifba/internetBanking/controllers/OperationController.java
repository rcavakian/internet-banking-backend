package br.edu.ifba.internetBanking.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.internetBanking.dtos.OperationDTO;
import br.edu.ifba.internetBanking.entities.OperationType;
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
	
	@GetMapping("/filterDateTime")
	public ResponseEntity<List<OperationDTO>> listByDateTime(@RequestParam int year, @RequestParam int month) {
		
		List<OperationDTO> operations = operationService.listByMonthYear(year, month);
		
		return ResponseEntity.ok(operations);
	}
	
	@GetMapping("/filterOperationType")
	public ResponseEntity<List<OperationDTO>> listByOperationType(@RequestParam OperationType operationType) {
		
		List<OperationDTO> operations = operationService.listByType(operationType);
		
		return ResponseEntity.ok(operations);
	}
}
