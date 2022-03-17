package it.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.be.energy.model.Cliente;
import it.be.energy.model.Fattura;
import it.be.energy.model.StatoFattura;
import it.be.energy.service.ClienteService;
import it.be.energy.service.FatturaService;
import it.be.energy.service.StatoFatturaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/web/fattura")
@Slf4j
public class FatturaControllerWeb {

	@Autowired
	FatturaService fatturaService;
	
	@Autowired
	StatoFatturaService statoFatturaService;
	
	@Autowired
	ClienteService clienteService;
	
	
	@GetMapping("/mostraelencofatture")
	public ModelAndView findAllFatture(Pageable pageable) {
		log.info("*** findAllFatture ***");
		ModelAndView view = new ModelAndView("visualizzaFatture");
		view.addObject("fatture", fatturaService.findAll(pageable));
		return view;
	}
	
	@GetMapping("/mostraformaggiungi")
	public ModelAndView mostraFormAggiungi(Pageable pageable) {
		log.info("*** mostraFormAggiungiFattura ***");
		Page<StatoFattura> statiFattura = statoFatturaService.findAll(pageable);
		Page<Cliente> clienti = clienteService.findAll(pageable);
		ModelAndView mav = new ModelAndView("aggiungiFattura", "fattura", new Fattura());
		mav.addObject("statiFattura", statiFattura);
		mav.addObject("clienti", clienti);
		return mav;
	}
	
	@PostMapping("/aggiungifattura")
	public ModelAndView aggiungiFattura(@ModelAttribute("fattura") Fattura fattura, BindingResult result, Model model, Pageable pageable) {
		log.info("*** aggiungi fattura ***");
		if(result.hasErrors()) {
			log.info("*** fattura non aggiunta ***");
			return new ModelAndView("aggiungiFattura", "fattura", new Fattura());
		}
		else {
		
			
			
			Page<StatoFattura> statiFattura = statoFatturaService.findAll(pageable);
			
			Long findIdStatoFattura = fattura.getStatoFattura().getId();
			
			
			for (StatoFattura statoFattura : statiFattura) {
				if(statoFattura.getId().equals(findIdStatoFattura)) {
					fattura.setStatoFattura(statoFattura);
				}
			}
			
			Page<Cliente> clienti = clienteService.findAll(pageable);
			
			Long findIdCliente = fattura.getCliente().getId();
			
			
			for (Cliente cliente : clienti) {
				if(cliente.getId().equals(findIdCliente)) {
					fattura.setCliente(cliente);
				}
			}
			
			log.info("*** fattura salvata ***");
			fatturaService.save(fattura);
			return findAllFatture(pageable);
		}
	}
	
	@GetMapping("/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model, Pageable pageable) {
		log.info("*** mostraFormAggiornaFattura ***");
		Fattura fattura = fatturaService.findById(id);
		Page<StatoFattura> statiFattura = statoFatturaService.findAll(pageable);
		Page<Cliente> clienti = clienteService.findAll(pageable);
		ModelAndView mav = new ModelAndView("aggiornaFattura", "fattura", new Fattura());
		mav.addObject("fattura", fattura);
		mav.addObject("statiFattura", statiFattura);
		mav.addObject("clienti", clienti);
		return mav;
	}
	
	@PostMapping("/aggiornafattura/{id}")
	public ModelAndView aggiornaFattura(@PathVariable Long id, @ModelAttribute("fattura") Fattura fattura, BindingResult result, Model model, Pageable pageable) {
		log.info("*** aggiorna fattura ***");
		if(result.hasErrors()) {
			log.info("*** fattura non aggiornata ***");
			return new ModelAndView("aggiornaFattura", "fattura", new Fattura());
		}
		else {
		
			
			
			Page<StatoFattura> statiFattura = statoFatturaService.findAll(pageable);
			
			Long findIdStatoFattura = fattura.getStatoFattura().getId();
			
			
			for (StatoFattura statoFattura : statiFattura) {
				if(statoFattura.getId().equals(findIdStatoFattura)) {
					fattura.setStatoFattura(statoFattura);
				}
			}
			
			Page<Cliente> clienti = clienteService.findAll(pageable);
			
			Long findIdCliente = fattura.getCliente().getId();
			
			
			for (Cliente cliente : clienti) {
				if(cliente.getId().equals(findIdCliente)) {
					fattura.setCliente(cliente);
				}
			}
			
			log.info("*** fattura aggiornata ***");
			fatturaService.update(id, fattura);
			return findAllFatture(pageable);
		}
	}
	
	@GetMapping("/eliminafattura/{id}")
	public ModelAndView eliminaFattura(@PathVariable("id") Long id, Model model, Pageable pageable) {
		fatturaService.delete(id);
		return findAllFatture(pageable);
	}
	
}
