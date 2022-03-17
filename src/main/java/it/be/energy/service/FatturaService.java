package it.be.energy.service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	
	public Fattura findById(Long id) {
		Optional<Fattura> findById = fatturaRepo.findById(id);
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
	
	public void delete(Long id) {
		Optional<Fattura> find = fatturaRepo.findById(id);
		if(find.isPresent()) {
			fatturaRepo.deleteById(id);
		}
		else {
			throw new FatturaException("Fattura non trovata");
		}
	}
	
	public Fattura update(Long id, Fattura fattura) {
		Optional<Fattura> findFattura = fatturaRepo.findById(id);
		if(findFattura.isPresent()) {
			Fattura fatturaUpdate = findFattura.get();
			fatturaUpdate.setNumeroFattura(fattura.getNumeroFattura());
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
	
	public Page<Fattura> findByClienteRagioneSocialeContaining(Pageable pageable, String nome) {
		return fatturaRepo.findByClienteRagioneSocialeContaining(pageable, nome);
	}
	
	public Page<Fattura> findByStatoFatturaId(Pageable pageable, Long id) {
		return fatturaRepo.findByStatoFatturaId(pageable, id);
	}
	
	public Page<Fattura> findByData(Pageable pageable, LocalDate data) {
		return fatturaRepo.findByData(pageable, data);
	}
	
	public Page<Fattura> findByAnno(Pageable pageable, Integer anno) {
		return fatturaRepo.findByAnno(pageable, anno);
	}
	
	public Page<Fattura> findByImportoBetween(Pageable pageable, BigDecimal min, BigDecimal max) {
		return fatturaRepo.findByImportoBetween(pageable, min, max);
	}
	
}
