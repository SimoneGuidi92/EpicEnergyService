package it.be.energy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exception.FatturaException;
import it.be.energy.model.Fattura;
import it.be.energy.repository.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepo;
	
	/*
	 * metodo che restituisce tutte le fatture presenti
	 */
	public Page<Fattura> findAll(Pageable pageable) {
		return fatturaRepo.findAll(pageable);
	}
	
	public Optional<Fattura> findById(Long numeroFattura) {
		Optional<Fattura> findById = fatturaRepo.findById(numeroFattura);
		if(findById.isPresent()) {
			return findById;
		}
		else {
			throw new FatturaException("Fattura non trovata");
		}
	}
	
	public Fattura save(Fattura fattura) {
		return fatturaRepo.save(fattura);
	}
	
	public void delete(Long numeroFattura) {
		Optional<Fattura> find = fatturaRepo.findById(numeroFattura);
		if(find.isPresent()) {
			fatturaRepo.deleteById(numeroFattura);
		}
		else {
			throw new FatturaException("Fattura non trovata");
		}
	}
	
	public Fattura update(Long numeroFattura, Fattura fattura) {
		Optional<Fattura> findFattura = fatturaRepo.findById(numeroFattura);
		if(findFattura.isPresent()) {
			Fattura fatturaUpdate = findFattura.get();
			fatturaUpdate.setAnno(fattura.getAnno());
			fatturaUpdate.setCliente(fattura.getCliente());
			fatturaUpdate.setData(fattura.getData());
			fatturaUpdate.setImporto(fattura.getImporto());
			fatturaUpdate.setStatoFattura(fattura.getStatoFattura());
			return fatturaRepo.save(fatturaUpdate);
		}
		else {
			throw new FatturaException("Fattura non modificata");
		}
	}
	
}
