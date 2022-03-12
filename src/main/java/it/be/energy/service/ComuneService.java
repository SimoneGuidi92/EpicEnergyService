package it.be.energy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.be.energy.repository.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository clienteRepo;
	
	
	
//	public Comune nuovoComune(String progressivo, String nome, String provincia) {
//		Comune nuovo = new Comune();
//		nuovo.setCodProgressivoComune(Long.valueOf(progressivo));
//		nuovo.setNome(nome);
//		nuovo.setProvincia();
//	}
	
}
