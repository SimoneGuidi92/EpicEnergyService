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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.model.Indirizzo;
import it.be.energy.service.IndirizzoService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class IndirizzoController {

	@Autowired
	IndirizzoService indirizzoService;
	
	@PostMapping("/indirizzo")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di salvare un nuovo indirizzo")
	public ResponseEntity<Indirizzo> save(@RequestBody Indirizzo indirizzo) {
		log.info("*** INIZIO save indirizzo ***");
		Indirizzo f = indirizzoService.save(indirizzo);
		log.info("*** FINE save indirizzo ***");
		return new ResponseEntity<>(f, HttpStatus.CREATED);
	}
	
	@PutMapping("/indirizzo/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di modificare un indirizzo")
	public ResponseEntity<Indirizzo> update(@PathVariable Long id, @RequestBody Indirizzo indirizzo) {
		log.info("*** INIZIO update indirizzo ***");
		Indirizzo f = indirizzoService.update(id, indirizzo);
		log.info("*** FINE update indirizzo ***");
		return new ResponseEntity<>(f, HttpStatus.OK);
	}
	
	@DeleteMapping("/indirizzo/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di cancellare un indirizzo tramite il suo id")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("*** INIZIO delete indirizzo ***");
		indirizzoService.delete(id);
		log.info("*** FINE delete indirizzo ***");
		return new ResponseEntity<>("Indirizzo cancellato correttamente", HttpStatus.OK);
	}
	
	@GetMapping("/indirizzi")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Visualizza tutti gli indirizzi")
	public ResponseEntity<Page<Indirizzo>> findAll(Pageable pageable) {
		log.info("*** INIZIO findAll indirizzi ***");
		Page<Indirizzo> findAll = indirizzoService.findAll(pageable);
		if(findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		}
		else {
			log.info("*** Indirizzi non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/indirizzo/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca un indirizzo tramite il suo id")
	public ResponseEntity<Indirizzo> findById(@PathVariable Long id) {
		log.info("*** INIZIO findById indirizzo ***");
		Optional<Indirizzo> findById = indirizzoService.findById(id);
		log.info("*** FINE findById indirizzo ***");
		return new ResponseEntity<>(findById.get(), HttpStatus.OK);
	}
	
}
