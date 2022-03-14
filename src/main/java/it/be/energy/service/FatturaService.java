package it.be.energy.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exception.FatturaException;
import it.be.energy.model.Fattura;
import it.be.energy.model.StatoFattura;
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
	
	public Fattura findById(Long numeroFattura) {
		Optional<Fattura> findById = fatturaRepo.findById(numeroFattura);
		if(findById.isPresent()) {
			return findById.get();
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
	
	public Page<Fattura> findByClienteRagioneSocialeLike(Pageable pageable, String nome) {
		return fatturaRepo.findByClienteRagioneSocialeLike(pageable, nome);
	}
	
	public Page<Fattura> findByStatoFattura(Pageable pageable, StatoFattura statoFattura) {
		return fatturaRepo.findByStatoFattura(pageable, statoFattura);
	}
	
	public Page<Fattura> findByData(Pageable pageable, Date data) {
		return fatturaRepo.findByData(pageable, data);
	}
	
	public Page<Fattura> findByAnno(Pageable pageable, Integer anno) {
		return fatturaRepo.findByAnno(pageable, anno);
	}
	
	public Page<Fattura> findByImportoBetween(Pageable pageable, BigDecimal min, BigDecimal max) {
		return fatturaRepo.findByImportoBetween(pageable, min, max);
	}
	
}
