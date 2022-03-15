package it.be.energy.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
import it.be.energy.model.Fattura;
import it.be.energy.service.FatturaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class FatturaController {
	
	@Autowired
	FatturaService fatturaService;

	
	/*
	 * controller che permette di salvare una nuova fattura
	 */
	@PostMapping("/fattura")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di salvare una nuova fattura")
	public ResponseEntity<Fattura> save(@RequestBody Fattura fattura) {
		log.info("*** INIZIO save fattura ***");
		Fattura f = fatturaService.save(fattura);
		log.info("*** FINE save fattura ***");
		return new ResponseEntity<>(f, HttpStatus.CREATED);
	}
	
	/*
	 * controller che permette di modificare una fattura
	 */
	@PutMapping("/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di modificare una fattura")
	public ResponseEntity<Fattura> update(@PathVariable Long id, @RequestBody Fattura fattura) {
		log.info("*** INIZIO update fattura ***");
		Fattura f = fatturaService.update(id, fattura);
		log.info("*** FINE update fattura ***");
		return new ResponseEntity<>(f, HttpStatus.OK);
	}
	
	/*
	 * controller che permette di cancellare una fattura
	 */
	@DeleteMapping("/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di cancellare una fattura tramite l'id")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("*** INIZIO delete fattura ***");
		fatturaService.delete(id);
		log.info("*** FINE delete fattura ***");
		return new ResponseEntity<>("Fattura cancellata correttamente", HttpStatus.OK);
	}
	
	/*
	 * controller che restituisce una lista di tutte le fatture
	 */
	@GetMapping("/fatture")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Visualizza tutte le fatture")
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
	
	/*
	 * controller che permette di cercare una fattura tramite il suo id
	 */
	@GetMapping("/fattura/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca una fattura tramite il suo id")
	public ResponseEntity<Fattura> findById(@PathVariable Long id) {
		log.info("*** findById fattura ***");
		Fattura findById = fatturaService.findById(id);
		log.info("*** FINE findById fattura ***");
		return new ResponseEntity<>(findById, HttpStatus.OK);
		
	}
	
	/*
	 * controller che permette di cercare una fattura tramite porzione della ragione sociale di un cliente
	 */
	@GetMapping("/fatturaragionesociale/{nome}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova le fatture con porzione del nome inserito")
	public ResponseEntity<Page<Fattura>> findByClienteRagioneSocialeContaining(Pageable pageable, @PathVariable String nome) {
		log.info("*** cerca fattura tramite ragione sociale cliente ***");
		Page<Fattura> find = fatturaService.findByClienteRagioneSocialeContaining(pageable, nome);
		if(find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * controller che permette di cercare le fatture tramite l'id dello stato fattura
	 */
	@GetMapping("/statofattura/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova le fatture tramite l'id dello stato fattura inserito")
	public ResponseEntity<Page<Fattura>> findByStatoFattura(Pageable pageable, @PathVariable Long id) {
		log.info("*** cerca fattura tramite stato fattura ***");
		Page<Fattura> find = fatturaService.findByStatoFatturaId(pageable, id);
		if(find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * controller che permette di cercare una fattura tramite la data inserita
	 */
	@GetMapping("/fatturadata/{data}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova le fatture tramite la data inserita")
	public ResponseEntity<Page<Fattura>> findByData(Pageable pageable, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {
		log.info("*** cerca fattura tramite stato fattura ***");
		Page<Fattura> find = fatturaService.findByData(pageable, data);
		if(find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * controller che permette di cercare una fattura tramite l'anno inserito
	 */
	@GetMapping("/fatturaanno/{anno}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova le fatture tramite l'anno inserito")
	public ResponseEntity<Page<Fattura>> findByAnno(Pageable pageable, @PathVariable Integer anno) {
		log.info("*** cerca fattura tramite stato fattura ***");
		Page<Fattura> find = fatturaService.findByAnno(pageable, anno);
		if(find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * controller che cerca tutte le fatture comprese in un range di importi inseriti
	 */
	@GetMapping("/fatturaimporto/{min}/{max}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova le fatture range di importo")
	public ResponseEntity<Page<Fattura>> findByImportoBetween(Pageable pageable, @PathVariable BigDecimal min, @PathVariable BigDecimal max) {
		log.info("*** cerca fattura tramite range di importi ***");
		Page<Fattura> find = fatturaService.findByImportoBetween(pageable, min, max);
		if(find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
}
