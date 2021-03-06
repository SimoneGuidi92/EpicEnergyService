package it.be.energy.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exception.ClienteException;
import it.be.energy.model.Cliente;
import it.be.energy.model.Fattura;
import it.be.energy.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepo;
	
	public Cliente save(Cliente cliente) {
		return clienteRepo.save(cliente);
	}
	
	public void delete(Long id) {
		Optional<Cliente> find = clienteRepo.findById(id);
		if(find.isPresent()) {
			Cliente delete = find.get();
			for(Fattura fattura : delete.getFatture()) {
				fattura.setCliente(null);
			}
			delete.setFatture(null);
			delete.setSedeLegale(null);
			delete.setSedeOperativa(null);
			clienteRepo.deleteById(id);
		}
		else {
			throw new ClienteException("Cliente non trovato");
		}
		
	}
	
	public Cliente update(Long id, Cliente cliente) {
		Optional<Cliente> clienteResult = clienteRepo.findById(id);
		if(clienteResult.isPresent()) {
			Cliente clienteUpdate = clienteResult.get();
			clienteUpdate.setCognomeContatto(cliente.getCognomeContatto());
			clienteUpdate.setDataInserimento(cliente.getDataInserimento());
			clienteUpdate.setDataUltimoContatto(cliente.getDataUltimoContatto());
			clienteUpdate.setEmail(cliente.getEmail());
			clienteUpdate.setEmailContatto(cliente.getEmailContatto());
			clienteUpdate.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
			clienteUpdate.setFatture(cliente.getFatture());
			clienteUpdate.setNomeContatto(cliente.getNomeContatto());
			clienteUpdate.setPartitaIva(cliente.getPartitaIva());
			clienteUpdate.setPec(cliente.getPec());
			clienteUpdate.setRagioneSociale(cliente.getRagioneSociale());
			clienteUpdate.setSedeLegale(cliente.getSedeLegale());
			clienteUpdate.setSedeOperativa(cliente.getSedeOperativa());
			clienteUpdate.setTelefono(cliente.getTelefono());
			clienteUpdate.setTelefonoContatto(cliente.getTelefonoContatto());
			clienteUpdate.setTipo(cliente.getTipo());
			return clienteRepo.save(clienteUpdate);
		}
		else {
			throw new ClienteException("Cliente non modificato");
		}
	}
	
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepo.findAll(pageable);
	}
	
	public Optional<Cliente> findById(Long id) {
		Optional<Cliente> find = clienteRepo.findById(id);
		if(find.isPresent()) {
			return find;
		}
		else {
			throw new ClienteException("Non ?? stato trovato nessun cliente con questo id");
		}
	}
	
	public Cliente getById(Long id) {
		return clienteRepo.getById(id);
	}
	
	/*
	 * metodo che ritorna una lista di tutti i clienti in ordine per ragione sociale ascendente
	 */
	public Page<Cliente> findAllOrderByRagioneSocialeAsc(Pageable pageable) {
		return clienteRepo.findAllByOrderByRagioneSocialeAsc(pageable);
	}
	
	/*
	 * metodo che ritorna una lista di tutti i clienti in ordine per fatturato annuale ascendente
	 */
	public Page<Cliente> findAllOrderByFatturatoAnnualeDesc(Pageable pageable) {
		return clienteRepo.findAllByOrderByFatturatoAnnualeDesc(pageable);
	}
	
	/*
	 * metodo che ritorna una lista di tutti i clienti in ordine per data inserimento
	 */
	public Page<Cliente> findAllOrderByDataInserimentoDesc(Pageable pageable) {
		return clienteRepo.findAllByOrderByDataInserimentoDesc(pageable);
	}
	
	/*
	 * metodo che ritorna una lista di tutti i clienti in ordine per data ultimo contatto
	 */
	public Page<Cliente> findAllOrderByDataUltimoContattoDesc(Pageable pageable) {
		return clienteRepo.findAllByOrderByDataUltimoContattoDesc(pageable);
	}
	
	/*
	 * metodo che ritorna una lista di tutti i clienti in ordine per sede legale provincia e comune
	 */
	public Page<Cliente> findAllByOrderBySedeLegaleComuneProvincia(Pageable pageable) {
		return clienteRepo.findAllByOrderBySedeLegaleComuneProvincia(pageable);
	}
	
	/*
	 * metodo che restituisce una lista di clienti con fatturato annuale maggiore o uguale a quello inserito
	 */
	public Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(BigDecimal fatturato, Pageable pageable) {
		return clienteRepo.findByFatturatoAnnualeGreaterThanEqual(fatturato, pageable);
	}
	
	/*
	 * metodo che restituisce una lista di clienti con fatturato annuale minore o uguale a quello inserito
	 */
	public Page<Cliente> findByFatturatoAnnualeLessThanEqual(BigDecimal fatturato, Pageable pageable) {
		return clienteRepo.findByFatturatoAnnualeLessThanEqual(fatturato, pageable);
	}
	
	/*
	 * metodo che restituisce una lisa di clienti con fatturato annuale compreso tra i due fatturati inseriti
	 */
	public Page<Cliente> findByFatturatoAnnualeBetween(BigDecimal fatturato1, BigDecimal fatturato2, Pageable pageable) {
		return clienteRepo.findByFatturatoAnnualeBetween(fatturato1, fatturato2, pageable);
	}
	
	/*
	 * metodo che restituisce una lista di clienti con data inserimento maggiore o uguale alla data inserita
	 */
	public Page<Cliente> findByDataInserimentoGreaterThanEqual(LocalDate data, Pageable pageable) {
		return clienteRepo.findByDataInserimentoGreaterThanEqual(data, pageable);
	}
	
	/*
	 * metodo che restituisce una lista di clienti con data ultimo contatto minore o uguale alla data inserita
	 */
	public Page<Cliente> findByDataInserimentoLessThanEqual(LocalDate data, Pageable pageable) {
		return clienteRepo.findByDataInserimentoLessThanEqual(data, pageable);
	}
	
	/*
	 * metodo che restituisce una lista di clienti con data inserimento compresa tra le due date inserite
	 */
	public Page<Cliente> findByDataInserimentoBetween(LocalDate data1, LocalDate data2, Pageable pageable) {
		return clienteRepo.findByDataInserimentoBetween(data1, data2, pageable);
	}
	
	/*
	 * metodo che restituisce una lista di clienti con data ultimo contatto maggiore o uguale alla data inserita
	 */
	public Page<Cliente> findByDataUltimoContattoGreaterThanEqual(LocalDate data, Pageable pageable) {
		return clienteRepo.findByDataUltimoContattoGreaterThanEqual(data, pageable);
	}
	
	/*
	 * metodo che restituisce una lista di clienti con data ultimo contatto minore o uguale alla data inserita
	 */
	public Page<Cliente> findByDataUltimoContattoLessThanEqual(LocalDate data, Pageable pageable) {
		return clienteRepo.findByDataUltimoContattoLessThanEqual(data, pageable);
	}
	
	/*
	 * metodo che restituisce una lista di clienti con data ultimo contatto compresa tra le due date inserite
	 */
	public Page<Cliente> findByDataUltimoContattoBetween(LocalDate data1, LocalDate data2, Pageable pageable) {
		return clienteRepo.findByDataUltimoContattoBetween(data1, data2, pageable);
	}
	
	/*
	 * metodo che restituisce una lista di clienti con porzione del nome della ragione sociale inserita
	 */
	public Page<Cliente> findByRagioneSocialeContaining(String ragioneSociale, Pageable pageable) {
		return clienteRepo.findByRagioneSocialeContaining(ragioneSociale, pageable);
	}
	
		
}
