package it.be.energy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exception.IndirizzoException;
import it.be.energy.model.Cliente;
import it.be.energy.model.Indirizzo;
import it.be.energy.repository.ClienteRepository;
import it.be.energy.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepo;
	
	@Autowired
	ClienteRepository clienteRepo;
	
	
	public Indirizzo save(Indirizzo indirizzo) {
		return indirizzoRepo.save(indirizzo);
	}
	
	public void delete(Long id) {
		Optional<Indirizzo> find = indirizzoRepo.findById(id);
		List<Cliente> allClienti = clienteRepo.findAll();
		for (Cliente cliente : allClienti) {
			if(cliente.getSedeLegale().getId().equals(id)) {
				cliente.setSedeLegale(null);
			}
			else if(cliente.getSedeOperativa().getId().equals(id)) {
				cliente.setSedeOperativa(null);
			}
		}
		if(find.isPresent()) {
			Indirizzo delete = find.get();
			delete.setComune(null);
			indirizzoRepo.deleteById(id);
		}
		else {
			throw new IndirizzoException("Indirizzo non trovato");
		}
		
	}
	
	public Indirizzo update(Long id, Indirizzo indirizzo) {
		Optional<Indirizzo> indirizzoResult = indirizzoRepo.findById(id);
		if(indirizzoResult.isPresent()) {
			Indirizzo indirizzoUpdate = indirizzoResult.get();
			indirizzoUpdate.setCap(indirizzo.getCap());
			indirizzoUpdate.setCivico(indirizzo.getCivico());
			indirizzoUpdate.setComune(indirizzo.getComune());
			indirizzoUpdate.setLocalita(indirizzo.getLocalita());
			indirizzoUpdate.setVia(indirizzo.getVia());
			return indirizzoRepo.save(indirizzoUpdate);
		}
		else {
			throw new IndirizzoException("Indirizzo non modificato");
		}
	}
	
	public Page<Indirizzo> findAll(Pageable pageable) {
		return indirizzoRepo.findAll(pageable);
	}
	
	public Optional<Indirizzo> findById(Long id) {
		Optional<Indirizzo> find = indirizzoRepo.findById(id);
		if(find.isPresent()) {
			return find;
		}
		else {
			throw new IndirizzoException("Non è stata trovata nessuna fattura con questo id");
		}
	}
	
	
}
