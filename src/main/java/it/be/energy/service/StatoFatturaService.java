package it.be.energy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exception.StatoFatturaException;
import it.be.energy.model.StatoFattura;
import it.be.energy.repository.StatoFatturaRepository;

@Service
public class StatoFatturaService {

	@Autowired
	StatoFatturaRepository statoFatturaRepo;
	
	public StatoFattura save(StatoFattura statoFattura) {
		return statoFatturaRepo.save(statoFattura);
	}
	
	public StatoFattura update(Long id, StatoFattura statoFattura) {
		Optional<StatoFattura> findFattura = statoFatturaRepo.findById(id);
		if(findFattura.isPresent()) {
			StatoFattura fatturaUpdate = findFattura.get();
			fatturaUpdate.setNome(statoFattura.getNome());
			return statoFatturaRepo.save(fatturaUpdate);
		}
		else {
			throw new StatoFatturaException("Stato non modificato");
		}
	}
	
	public void delete(Long id) {
		Optional<StatoFattura> find = statoFatturaRepo.findById(id);
		if(find.isPresent()) {
			statoFatturaRepo.deleteById(id);
		}
		else {
			throw new StatoFatturaException("Stato non cancellato");
		}
	}
	
	
	public Page<StatoFattura> findAll(Pageable pageable) {
		return statoFatturaRepo.findAll(pageable);
	}
	
	public StatoFattura findById(Long id) {
		Optional<StatoFattura> find = statoFatturaRepo.findById(id);
		if(find.isPresent()) {
			return find.get();
		}
		else {
			throw new StatoFatturaException("Fattura non trovata");
		}
	}
	
}
