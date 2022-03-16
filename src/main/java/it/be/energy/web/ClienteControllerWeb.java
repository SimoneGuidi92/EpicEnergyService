package it.be.energy.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.be.energy.model.Cliente;
import it.be.energy.model.Comune;
import it.be.energy.model.Indirizzo;
import it.be.energy.service.ClienteService;
import it.be.energy.service.ComuneService;
import it.be.energy.service.IndirizzoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/web")
@Slf4j
public class ClienteControllerWeb {

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ComuneService comuneService;
	
	@Autowired
	IndirizzoService indirizzoService;
	
	@GetMapping("/mostraelencoclienti")
	public ModelAndView findAllClienti(Pageable pageable) {
		log.info("*** findAllClienti ***");
		ModelAndView view = new ModelAndView("visualizzaClienti");
		view.addObject("clienti", clienteService.findAll(pageable));
		return view;
	}
	
	@GetMapping("/mostraformaggiungi")
	public ModelAndView mostraFormAggiungi() {
		log.info("*** mostraFormCliente ***");
		List<Comune> comuni = comuneService.findAll();
		ModelAndView mav = new ModelAndView("aggiungiCliente", "cliente", new Cliente());
		mav.addObject("comuni", comuni);
		return mav;
	}
	
//	@PostMapping("/aggiungicliente")
//	// valid significa fa un controllo sul back end per verificare che il campo non sia vuoto
//	public String aggiungiStudente(@Valid Studente studente, BindingResult bindingResult, Model model) {
//		log.info("*** aggiunta in corso ***");
//		if(bindingResult.hasErrors()) {
//			model.addAttribute("corsi", corsoDiLaureaService.findAll());
//			return "aggiungiStudente";
//		}
//		studenteService.save(studente);
//		log.info("*** studente salvato ***");
//		return "redirect:/web/mostraelenco";	
//	}
	
	@PostMapping("/aggiungicliente")
	public ModelAndView aggiungiCliente(@ModelAttribute("cliente") Cliente cliente,BindingResult result, Model model, Pageable pageable) {
		log.info("*** aggiungi cliente ***");
		if(result.hasErrors()) {
			log.info("*** cliente non aggiunto ***");
			return new ModelAndView("aggiungiCliente", "cliente", new Cliente());
		}
		else {
		
			
			Indirizzo indirizzoSedeLegale = new Indirizzo();
			
			List<Comune> comuni = comuneService.findAll();
			
			Long comuneSedeLegale = cliente.getSedeLegale().getComune().getId();
			
			indirizzoSedeLegale.setVia(cliente.getSedeLegale().getVia());
			indirizzoSedeLegale.setCivico(cliente.getSedeLegale().getCivico());
			indirizzoSedeLegale.setCap(cliente.getSedeLegale().getCap());
			for (Comune comune : comuni) {
				if(comune.getId().equals(comuneSedeLegale)) {
					indirizzoSedeLegale.setComune(comune);
				}
			}
			indirizzoService.save(indirizzoSedeLegale);
			Optional<Indirizzo> findLegale = indirizzoService.findById(indirizzoSedeLegale.getId());
			cliente.setSedeLegale(findLegale.get());
			
			
			
			Indirizzo indirizzoSedeOperativa = new Indirizzo();
			
			Long comuneSedeOperativa = cliente.getSedeOperativa().getComune().getId();
			
			indirizzoSedeOperativa.setVia(cliente.getSedeOperativa().getVia());
			indirizzoSedeOperativa.setCivico(cliente.getSedeOperativa().getCivico());
			indirizzoSedeOperativa.setCap(cliente.getSedeOperativa().getCap());
			for (Comune comune : comuni) {
				if(comune.getId().equals(comuneSedeOperativa)) {
					indirizzoSedeOperativa.setComune(comune);
				}
			}
			indirizzoService.save(indirizzoSedeOperativa);
			Optional<Indirizzo> findOperativa = indirizzoService.findById(indirizzoSedeOperativa.getId());
			cliente.setSedeOperativa(findOperativa.get());
			
			
			log.info("*** cliente salvato ***");
			clienteService.save(cliente);
			return findAllClienti(pageable);
		}
		
		
	}
	
}
