package it.be.energy.controller.rest;

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
import it.be.energy.model.StatoFattura;
import it.be.energy.service.StatoFatturaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/statofattura")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class StatoFatturaController {

	@Autowired
	StatoFatturaService statoFatturaService;
	
	/*
	 * controller che permette di salvare un nuovo stato fattura
	 */
	@PostMapping("/salvastato")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di salvare un nuovo stato")
	public ResponseEntity<StatoFattura> save(StatoFattura statoFattura) {
		log.info("*** INIZIO save stato fattura ***");
		StatoFattura sf = statoFatturaService.save(statoFattura);
		log.info("*** FINE save stato fattura ***");
		return new ResponseEntity<>(sf, HttpStatus.CREATED);
	}
	
	/*
	 * controller che permette di modificare uno stato fattura
	 */
	@PutMapping("/modificastato/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di modificare uno stato fattura")
	public ResponseEntity<StatoFattura> update(@PathVariable Long id, @RequestBody StatoFattura statoFattura) {
		log.info("*** INIZIO update stato fattura ***");
		StatoFattura sf = statoFatturaService.update(id, statoFattura);
		log.info("*** FINE update stato fattura ***");
		return new ResponseEntity<>(sf, HttpStatus.OK);
	}
	
	/*
	 * controller che permette di cancellare una fattura tramite il suo id
	 */
	@DeleteMapping("/cancellastato/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di cancellare uno stato fattura tramite l'id")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("*** INIZIO delete stato fattura ***");
		statoFatturaService.delete(id);
		log.info("*** FINE delete stato fattura ***");
		return new ResponseEntity<>("Stato fattura cancellato correttamente", HttpStatus.OK);
	}
	
	/*
	 * controller che restituisce una lista di stati fattura
	 */
	@GetMapping("/tuttistatifattura")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Visualizza tutti gli stati fatture")
	public ResponseEntity<Page<StatoFattura>> findAll(Pageable pageable) {
		log.info("*** INIZIO findAll stato fatture ***");
		Page<StatoFattura> findAll = statoFatturaService.findAll(pageable);
		if(findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		}
		else {
			log.info("*** Stato Fattura non trovato ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * controller che permette di cercare uno stato fattura tramite il suo id
	 */
	@GetMapping("/statofatturaid/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca uno stato fattura tramite il suo id")
	public ResponseEntity<StatoFattura> findById(@PathVariable Long id) {
		log.info("*** findById stato fattura ***");
		StatoFattura findById = statoFatturaService.findById(id);
		log.info("*** FINE findById stato fattura ***");
		return new ResponseEntity<>(findById, HttpStatus.OK);
		
	}
	
}
