package it.be.energy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.be.energy.model.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

	public Optional<Provincia> findByNomeLike(String string);

    public Optional<Provincia> findByCodProvincia(Long codice);
    
    public Page<Provincia> findAll(Pageable pageable);
    
    public Page<Provincia> findByNomeContaining(String nome, Pageable pageable);
    
	
}
