package it.be.energy.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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
import it.be.energy.model.Cliente;
import it.be.energy.service.ClienteService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@PostMapping("/cliente")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di inserire un nuovo cliente")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		log.info("*** INIZIO save cliente ***");
		Cliente c = clienteService.save(cliente);
		log.info("*** FINE save cliente ***");
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("/cliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di modificare un cliente")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		log.info("*** INIZIO update cliente ***");
		Cliente c = clienteService.update(id, cliente);
		log.info("*** FINE update cliente ***");
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	@DeleteMapping("/cliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di cancellare un cliente tramite l'id")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("*** INIZIO delete cliente ***");
		clienteService.delete(id);
		log.info("*** FINE delete cliente ***");
		return new ResponseEntity<>("Cliente cancellato correttamente", HttpStatus.OK);
	}
	
	
	@GetMapping("/clienti")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Visualizza tutti i clienti")
	public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
		log.info("*** findAll clienti ***");
		Page<Cliente> findAll = clienteService.findAll(pageable);
		if(findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/cliente/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca un cliente tramite il suo id")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		log.info("*** findById cliente ***");
		Optional<Cliente> findById = clienteService.findById(id);
		if(findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		}
		else {
			log.info("*** Cliente non trovato ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	/*
	 * Controller che riordina per ragione sociale i clienti
	 */
	@GetMapping("/clienteragionesociale")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Permette di ordinare per ragione sociale i clienti")
	public ResponseEntity<Page<Cliente>> findAllOrderByRagioneSocialeAsc(Pageable pageable) {
		log.info("*** ordine ragione sociale clienti ***");
		Page<Cliente> find = clienteService.findAllOrderByRagioneSocialeAsc(pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
			
	}
	
	/*
	 * Controller che riordina per fatturato annuale i clienti
	 */
	@GetMapping("/clientefatturatoannuale")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Permette di ordinare per fatturato annuale i clienti")
	public ResponseEntity<Page<Cliente>> findAllOrderByFatturatoAnnualeAsc(Pageable pageable) {
		log.info("*** ordine per fatturato annuale cliente ***");
		Page<Cliente> find = clienteService.findAllOrderByFatturatoAnnualeAsc(pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che riordina per data inserimento i clienti
	 */
	@GetMapping("/clientedatainserimento")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Permette di ordinare per data inserimento i clienti")
	public ResponseEntity<Page<Cliente>> findAllOrderByDataInserimento(Pageable pageable) {
		log.info("*** ordine per data inserimento cliente ***");
		Page<Cliente> find = clienteService.findAllOrderByDataInserimento(pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che riordina per data ultimo contatto i clienti
	 */
	@GetMapping("/clientedataultimocontatto")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Permette di ordinare per data ultimo contatto i clienti")
	public ResponseEntity<Page<Cliente>> findAllOrderByDataUltimoContatto(Pageable pageable) {
		log.info("*** ordine per data ultimo contatto cliente ***");
		Page<Cliente> find = clienteService.findAllOrderByDataUltimoContatto(pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che riordina per sede legale, provincia e comune i clienti
	 */
	@GetMapping("/clientesedelegale")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Permette di ordinare per sede legale, provincia e comune i clienti")
	public ResponseEntity<Page<Cliente>> findAllOrderBySedeLegaleComuneProvincia(Pageable pageable) {
		log.info("*** ordine per sede legale, comune, provincia cliente ***");
		Page<Cliente> find = clienteService.findAllByOrderBySedeLegaleComuneProvincia(pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che trova i clienti con fatturato maggiore o uguale di quello inserito
	 */
	@GetMapping("/clientefatturatoannualemaggiore/{fatturato}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca i clienti con fatturato maggiore di quello inserito")
	public ResponseEntity<Page<Cliente>> findByFatturatoAnnualeGreaterThanEqual(@PathVariable BigDecimal fatturato, Pageable pageable) {
		log.info("*** filtro cliente per fatturato maggiore di ***");
		Page<Cliente> find = clienteService.findByFatturatoAnnualeGreaterThanEqual(fatturato, pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che trova i clienti con fatturato minore o uguale di quello inserito
	 */
	@GetMapping("/clientefatturatoannualeminore/{fatturato}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca i clienti con fatturato minore di quello inserito")
	public ResponseEntity<Page<Cliente>> findByFatturatoAnnualeLessThanEqual(@PathVariable BigDecimal fatturato, Pageable pageable) {
		log.info("*** filtro cliente per fatturato minore di ***");
		Page<Cliente> find = clienteService.findByFatturatoAnnualeLessThanEqual(fatturato, pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che trova i clienti con fatturato compreso tra quelli inseriti
	 */
	@GetMapping("/clientefatturatoannualetra/{fatturato1}/{fatturato2}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Cerca i clienti con fatturato minore di quello inserito")
	public ResponseEntity<Page<Cliente>> findByFatturatoAnnualeBetween(@PathVariable BigDecimal fatturato1, @PathVariable BigDecimal fatturato2, Pageable pageable) {
		log.info("*** filtro cliente per fatturato compreso tra ***");
		Page<Cliente> find = clienteService.findByFatturatoAnnualeBetween(fatturato1, fatturato2, pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che trova i clienti con data inserimento maggiore o uguale a quella inserita
	 */
	@GetMapping("/clientedatainserimentomaggiore/{data}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti con data inserimento maggiore o uguale a quella inserita")
	public ResponseEntity<Page<Cliente>> findByDataInserimentoGreaterThanEqual(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, Pageable pageable) {
		log.info("*** filtro cliente per data inserimento maggiore o uguale di ***");
		Page<Cliente> find = clienteService.findByDataInserimentoGreaterThanEqual(data, pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che trova i clienti con data inserimento minore o uguale a quella inserita
	 */
	@GetMapping("/clientedatainserimentominore/{data}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti con data inserimento minore o uguale a quella inserita")
	public ResponseEntity<Page<Cliente>> findByDataInserimentoLessThanEqual(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, Pageable pageable) {
		log.info("*** filtro cliente per data inserimento minore o uguale di ***");
		Page<Cliente> find = clienteService.findByDataInserimentoLessThanEqual(data, pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che trova i clienti con data inserimento compresa tra quelle inserite
	 */
	@GetMapping("/clientedatainserimentotra/{data1}/{data2}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti con data inserimento compresa tra quelle inserite")
	public ResponseEntity<Page<Cliente>> findByDataInserimentoBetween(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data1, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data2, Pageable pageable) {
		log.info("*** filtro cliente per data inserimento compresa tra ***");
		Page<Cliente> find = clienteService.findByDataInserimentoBetween(data1, data2, pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che trova i clienti con data ultimo contatto maggiore o uguale a quella inserita
	 */
	@GetMapping("/clientedataultimocontattomaggiore/{data}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti con data ultimo contatto maggiore o uguale a quella inserita")
	public ResponseEntity<Page<Cliente>> findByDataUltimoContattoGreaterThanEqual(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, Pageable pageable) {
		log.info("*** filtro cliente per data inserimento maggiore o uguale di ***");
		Page<Cliente> find = clienteService.findByDataUltimoContattoGreaterThanEqual(data, pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che trova i clienti con data ultimo contatto minore o uguale a quella inserita
	 */
	@GetMapping("/clientedataultimocontattominore/{data}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti con data ultimo contatto minore o uguale a quella inserita")
	public ResponseEntity<Page<Cliente>> findByDataUltimoContattoLessThanEqual(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, Pageable pageable) {
		log.info("*** filtro cliente per data inserimento minore o uguale di ***");
		Page<Cliente> find = clienteService.findByDataUltimoContattoLessThanEqual(data, pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che trova i clienti con data ultimo contatto compresa tra quelle inserite
	 */
	@GetMapping("/clientedataultimocontattotra/{data1}/{data2}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti con data ultimo contatto compresa tra quelle inserite")
	public ResponseEntity<Page<Cliente>> findByDataUltimoContattoBetween(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data1, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data2, Pageable pageable) {
		log.info("*** filtro cliente per data ultimo contatto compresa tra ***");
		Page<Cliente> find = clienteService.findByDataUltimoContattoBetween(data1, data2, pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Controller che trova i clienti tramite porzione del nome della ragione sociale
	 */
	@GetMapping("/clienteragionesocialefilter/{ragioneSociale}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti tramite porzione del nome della ragione sociale")
	public ResponseEntity<Page<Cliente>> findByRagioneSocialeContaining(@PathVariable String ragioneSociale, Pageable pageable) {
		log.info("*** filtro cliente per porzione del nome della ragione sociale ***");
		Page<Cliente> find = clienteService.findByRagioneSocialeContaining(ragioneSociale, pageable);
		if(find.hasContent()) {

			return new ResponseEntity<>(find, HttpStatus.OK);
		}
		else {
			log.info("*** Clienti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	
	
	
	
}
