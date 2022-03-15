package it.be.energy.util;

import java.io.FileReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

import it.be.energy.model.Comune;
import it.be.energy.model.Provincia;
import it.be.energy.repository.ComuneRepository;
import it.be.energy.repository.ProvinciaRepository;

@Component
public class CsvRunner implements CommandLineRunner {
	
	@Autowired
	ProvinciaRepository provinciaRepo;
	
	@Autowired
	ComuneRepository comuneRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		
		initComune();
		initProvincia();
		
	}

	private void initProvincia() throws Exception {
        try (CSVReader csvReader = new CSVReader(new FileReader("province-italiane_1.csv"));) {
            String[] values = null;
            csvReader.readNext();
            Optional<Provincia> pr;
            Provincia provincia;
            String nome;
            String[] valore;
            while ((values = csvReader.readNext()) != null) {
                valore = values[0].split(";");
                nome = valore[1];
                pr = provinciaRepo.findByNomeLike("%" + nome + "%");
                if (pr.isPresent()) {

                    provincia = pr.get();
                    provincia.setSigla(valore[0]);
                    provincia.setRegione(valore[2]);
                    provinciaRepo.save(provincia);
                } else {

                    provinciaRepo.save(new Provincia(valore[0], valore[1], valore[2]));
                }
            }
        }
    }
	
	private void initComune() throws Exception {
        try (CSVReader csvReader = new CSVReader(new FileReader("comuni-italiani_1.csv"));) {
            String[] values = null;
            csvReader.readNext();
            Optional<Provincia> p;
            String[] valore;
            Provincia provincia;
            while ((values = csvReader.readNext()) != null) {
                valore = values[0].split(";");
                p = provinciaRepo.findByCodProvincia(Long.valueOf(valore[0]));
                if (p.isPresent()) {
                    comuneRepo.save(new Comune(rimpiazza(valore[2]), p.get()));
                } else {

                    provincia = new Provincia();
                    provincia.setCodProvincia(Long.valueOf(valore[0]));
                    provincia.setNome(rimpiazza(valore[3]));
                    provinciaRepo.save(provincia);
                    comuneRepo.save(new Comune(valore[2], provincia));
                }
            }
        }
    }
	
	private String rimpiazza(String nome) {
        return nome.replace('-', ' ');
    }
	
	

}
