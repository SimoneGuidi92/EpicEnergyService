package it.be.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.model.Comune;
import it.be.energy.service.ComuneService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class ComuneController {

	@Autowired
	ComuneService comuneService;
	
	
	@GetMapping("/comuni")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Visualizza tutte le province")
	public ResponseEntity<Page<Comune>> findAll(Pageable pageable) {
		log.info("*** INIZIO findAll comuni ***");
		Page<Comune> findAll = comuneService.findAll(pageable);
		if(findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		}
		else {
			log.info("*** comuni non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/comuneid/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca un comune tramite il suo id")
	public ResponseEntity<Comune> findById(@PathVariable Long id) {
		log.info("*** findById comune ***");
		Comune findById = comuneService.findById(id);
		log.info("*** FINE findById comune ***");
		return new ResponseEntity<>(findById, HttpStatus.OK);
		
	}
	
	@GetMapping("/comunenome/{nome}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca un comune tramite il suo nome")
	public ResponseEntity<Page<Comune>> findByNomeContaining(@PathVariable String nome, Pageable pageable) {
		log.info("*** findByNome comune ***");
		Page<Comune> findByNome = comuneService.findByNomeContaining(nome, pageable);
		log.info("*** FINE findByNome comune ***");
		return new ResponseEntity<>(findByNome, HttpStatus.OK);
		
	}
	
}
