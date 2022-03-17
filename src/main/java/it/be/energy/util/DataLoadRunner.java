package it.be.energy.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.be.energy.model.Cliente;
import it.be.energy.model.Comune;
import it.be.energy.model.Fattura;
import it.be.energy.model.Indirizzo;
import it.be.energy.model.StatoFattura;
import it.be.energy.model.TipoCliente;
import it.be.energy.repository.ClienteRepository;
import it.be.energy.repository.ComuneRepository;
import it.be.energy.repository.FatturaRepository;
import it.be.energy.repository.IndirizzoRepository;
import it.be.energy.repository.ProvinciaRepository;
import it.be.energy.repository.StatoFatturaRepository;

@Component
public class DataLoadRunner implements CommandLineRunner {

	@Autowired
	ProvinciaRepository provinciaRepo;
	
	@Autowired
	IndirizzoRepository indirizzoRepo;
	
	@Autowired
	ComuneRepository comuneRepo;
	
	@Autowired
	StatoFatturaRepository statoFatturaRepo;
	
	@Autowired
	FatturaRepository fatturaRepo;
	
	@Autowired
	ClienteRepository clienteRepo;
	
	

	@Override
	public void run(String... args) throws Exception {
		
		Comune c = comuneRepo.getById(3981L);
		
		Indirizzo i = new Indirizzo();
		i.setCap("16100");
		i.setCivico("19");
		i.setComune(c);
		i.setLocalita("Marina");
		i.setVia("Via di prova");
		indirizzoRepo.save(i);
		
		Indirizzo i2 = new Indirizzo();
		i2.setCap("16154");
		i2.setCivico("15");
		i2.setComune(c);
		i2.setLocalita("Marina");
		i2.setVia("Via sestri");
		indirizzoRepo.save(i2);
		
		Indirizzo i3 = new Indirizzo();
		i3.setCap("16100");
		i3.setCivico("22");
		i3.setComune(c);
		i3.setLocalita("Marina");
		i3.setVia("Via aurelia");
		indirizzoRepo.save(i3);
		
		Indirizzo i4 = new Indirizzo();
		i4.setCap("16100");
		i4.setCivico("27");
		i4.setComune(c);
		i4.setLocalita("Marina");
		i4.setVia("Via del commercio");
		indirizzoRepo.save(i4);
		
		LocalDate data1 = LocalDate.of(2022, 03, 20);
		LocalDate data2 = LocalDate.of(2022, 03, 20);
		
		StatoFattura pagata = new StatoFattura();
		pagata.setNome("Pagata");
		statoFatturaRepo.save(pagata);
		
		StatoFattura nonPagata = new StatoFattura();
		nonPagata.setNome("Non pagata");
		statoFatturaRepo.save(nonPagata);
		
		
		List<Fattura> fatture = new ArrayList<>();
		
		Cliente cli = new Cliente();
		cli.setCognomeContatto("Rossi");
		cli.setDataInserimento(LocalDate.parse("2021-04-22"));
		cli.setDataUltimoContatto(LocalDate.parse("2022-02-15"));
		cli.setEmail("azienda@gmail.com");
		cli.setEmailContatto("pippo@gmail.com");
		cli.setFatturatoAnnuale(new BigDecimal("250357.50"));
		cli.setFatture(fatture);
		cli.setNomeContatto("Marco");
		cli.setPartitaIva("P123456789");
		cli.setPec("pec@pec.com");
		cli.setRagioneSociale("L'azienda");
		cli.setSedeLegale(i);
		cli.setSedeOperativa(i2);
		cli.setTelefono("0104879856");
		cli.setTelefonoContatto("3486879845");
		cli.setTipo(TipoCliente.SPA);
		clienteRepo.save(cli);
		
		Fattura f = new Fattura();
		f.setAnno(2000);
		f.setCliente(cli);
		f.setData(data1);
		f.setImporto(new BigDecimal("10000"));
		f.setNumeroFattura(1L);
		f.setStatoFattura(pagata);
		fatture.add(f);
		
		fatturaRepo.save(f);
		
		
		List<Fattura> fatture2 = new ArrayList<>();
		
		
		Cliente cli2 = new Cliente();
		cli2.setCognomeContatto("Verdi");
		cli2.setDataInserimento(LocalDate.parse("2021-01-15"));
		cli2.setDataUltimoContatto(LocalDate.parse("2021-03-01"));
		cli2.setEmail("azienda.bianca@gmail.com");
		cli2.setEmailContatto("pluto@gmail.com");
		cli2.setFatturatoAnnuale(new BigDecimal("1200458.50"));
		cli2.setFatture(fatture2);
		cli2.setNomeContatto("Bianca");
		cli2.setPartitaIva("P854976858");
		cli2.setPec("email.pec@pec.com");
		cli2.setRagioneSociale("L'azienda di Bianca");
		cli2.setSedeLegale(i3);
		cli2.setSedeOperativa(i4);
		cli2.setTelefono("010584976");
		cli2.setTelefonoContatto("348584978");
		cli2.setTipo(TipoCliente.SPA);
		clienteRepo.save(cli2);
		
		Fattura f2 = new Fattura();
		f2.setAnno(2010);
		f2.setCliente(cli2);
		f2.setData(data2);
		f2.setImporto(new BigDecimal("9586.57"));
		f2.setNumeroFattura(1L);
		f2.setStatoFattura(nonPagata);
		fatture2.add(f2);
		
		fatturaRepo.save(f2);
		
		
		
		
		
		
		
		
	}
	
	
}
