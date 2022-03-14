package it.be.energy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exception.ProvinciaException;
import it.be.energy.model.Provincia;
import it.be.energy.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepo;
	
	public Page<Provincia> findAll(Pageable pageable) {
		return provinciaRepo.findAll(pageable);
	}
	
	public Provincia findById(Long id) {
		Optional<Provincia> find = provinciaRepo.findById(id);
		if(find.isPresent()) {
			return find.get();
		}
		else {
			throw new ProvinciaException("Provincia non trovata");
		}
	}
	
}
