package it.be.energy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.be.energy.model.Fattura;
import it.be.energy.service.FatturaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class FatturaController {
	
	@Autowired
	FatturaService fatturaService;

	@PostMapping("/fattura")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Fattura> save(@RequestBody Fattura fattura) {
		log.info("*** INIZIO save fattura ***");
		Fattura f = fatturaService.save(fattura);
		log.info("*** FINE save fattura ***");
		return new ResponseEntity<>(f, HttpStatus.CREATED);
	}
	
	@PutMapping("/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Fattura> update(@PathVariable Long id, @RequestBody Fattura fattura) {
		log.info("*** INIZIO update fattura ***");
		Fattura f = fatturaService.update(id, fattura);
		log.info("*** FINE update fattura ***");
		return new ResponseEntity<>(f, HttpStatus.OK);
	}
	
	@DeleteMapping("/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("*** INIZIO delete fattura ***");
		fatturaService.delete(id);
		log.info("*** FINE delete fattura ***");
		return new ResponseEntity<>("Fattura cancellata correttamente", HttpStatus.OK);
	}
	
	@GetMapping("/fatture")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findAll(Pageable pageable) {
		log.info("*** INIZIO findAll fatture ***");
		Page<Fattura> findAll = fatturaService.findAll(pageable);
		if(findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		}
		else {
			log.info("*** Fatture non trovate ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/fattura/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Fattura> findById(@PathVariable Long id) {
		log.info("*** INIZIO findById fattura ***");
		Optional<Fattura> findById = fatturaService.findById(id);
		log.info("*** FINE findById fattura ***");
		return new ResponseEntity<>(findById.get(), HttpStatus.OK);
	}
	
}
