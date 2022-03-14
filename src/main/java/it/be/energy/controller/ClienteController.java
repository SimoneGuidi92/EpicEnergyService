package it.be.energy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		log.info("*** INIZIO save cliente ***");
		Cliente c = clienteService.save(cliente);
		log.info("*** FINE save cliente ***");
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		log.info("*** INIZIO update cliente ***");
		Cliente c = clienteService.update(id, cliente);
		log.info("*** FINE update cliente ***");
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("*** INIZIO delete cliente ***");
		clienteService.delete(id);
		log.info("*** FINE delete cliente ***");
		return new ResponseEntity<>("Cliente cancellato correttamente", HttpStatus.OK);
	}
	
	
	@GetMapping("/clienti")
	public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
		log.info("*** INIZIO findAll clienti ***");
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
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		log.info("*** INIZIO findById cliente ***");
		Optional<Cliente> findById = clienteService.findById(id);
		log.info("*** FINE findById cliente ***");
		return new ResponseEntity<>(findById.get(), HttpStatus.OK);
	}
	
	
	
	
	
}
