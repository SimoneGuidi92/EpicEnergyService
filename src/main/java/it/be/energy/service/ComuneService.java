package it.be.energy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exception.ComuneException;
import it.be.energy.model.Comune;
import it.be.energy.repository.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepo;
	
	
	public Page<Comune> findAll(Pageable pageable) {
		return comuneRepo.findAll(pageable);
	}
	
	public List<Comune> findAll() {
		return comuneRepo.findAll();
	}
	
	public Comune findById(Long id) {
		Optional<Comune> find = comuneRepo.findById(id);
		if(find.isPresent()) {
			return find.get();
		}
		else {
			throw new ComuneException("Comune non trovato");
		}
	}
	
	public Page<Comune> findByNomeContaining(String nome, Pageable pageable) {
		Page<Comune> find = comuneRepo.findByNomeContaining(nome, pageable);
		if(find.hasContent()) {
			return find;
		}
		else {
			throw new ComuneException("Comune non trovato");
		}
		
	}
	
}
