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
import it.be.energy.model.Provincia;
import it.be.energy.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;
	
	
	@GetMapping("/province")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Visualizza tutte le province")
	public ResponseEntity<Page<Provincia>> findAll(Pageable pageable) {
		log.info("*** INIZIO findAll province ***");
		Page<Provincia> findAll = provinciaService.findAll(pageable);
		if(findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		}
		else {
			log.info("*** province non trovate ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/provinciaid/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca una provincia tramite il suo id")
	public ResponseEntity<Provincia> findById(@PathVariable Long id) {
		log.info("*** findById provincia ***");
		Provincia findById = provinciaService.findById(id);
		log.info("*** FINE findById provincia ***");
		return new ResponseEntity<>(findById, HttpStatus.OK);
		
	}
	
	@GetMapping("/provincianome/{nome}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca una provincia tramite il suo nome")
	public ResponseEntity<Page<Provincia>> findByNomeContaining(@PathVariable String nome, Pageable pageable) {
		log.info("*** findByNome provincia ***");
		Page<Provincia> findByNome = provinciaService.findByNomeContaining(nome, pageable);
		log.info("*** FINE findByNome provincia ***");
		return new ResponseEntity<>(findByNome, HttpStatus.OK);
		
	}
	
}
