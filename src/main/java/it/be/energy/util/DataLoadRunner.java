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
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataLoadRunner implements CommandLineRunner {
	
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
            csvReader.readNext(); //
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
	
	
	
	
//	@Autowired
//	BookRepository bookRepo;
//
//	@Autowired
//	CasaEditriceRepository casaEditriceRepo;
//
//	@Autowired
//	AutoreRepository autoreRepo;
//
//	@Override
//	public void run(String... args) throws Exception {
//		log.info("*** INIZIO IMPORT DATI ***");
//
//		initCasaEditore();
//		initAutore();
//		initBook();
//		initRelazioniEditori();
//
//		log.info("*** FINE IMPORT DATI ***");
//
//	}
//
//	private void initRelazioniEditori() throws FileNotFoundException, IOException {
//		try (CSVReader csvReader = new CSVReader(new FileReader("editori.csv"));) {
//			String[] values = null;
//			csvReader.readNext(); // aggiungo questa linea di codice per far si che il reader salti la prima riga
//									// del file csv
//			CasaEditrice casaEditrice;
//			List<Book> books;
//			List<Autore> autori;
//			while ((values = csvReader.readNext()) != null) {
//
//				casaEditrice = casaEditriceRepo.findById(Long.valueOf(values[0])).get();
//
//				books = casaEditrice.getBooks();
//				books.add(bookRepo.findById(Long.valueOf(values[2])).get());
//				casaEditrice.setBooks(books);
//
//				autori = casaEditrice.getAutori();
//				autori.add(autoreRepo.findById(Long.valueOf(values[1])).get());
//				casaEditrice.setAutori(autori);
//
//				casaEditriceRepo.save(casaEditrice);
//			}
//		}
//	}
//
//	private void initCasaEditore() throws FileNotFoundException, IOException {
//		try (CSVReader csvReader = new CSVReader(new FileReader("casaeditore.csv"));) {
//			String[] values = null;
//			csvReader.readNext(); // aggiungo questa linea di codice per far si che il reader salti la prima riga
//									// del file csv
//			while ((values = csvReader.readNext()) != null) {
//				casaEditriceRepo.save(new CasaEditrice(values[0], values[1]));
//			}
//		}
//	}
//
//	private void initAutore() throws FileNotFoundException, IOException {
//		try (CSVReader csvReader = new CSVReader(new FileReader("autore.csv"));) {
//			String[] values = null;
//			csvReader.readNext(); // aggiungo questa linea di codice per far si che il reader salti la prima riga
//									// del file csv
//			while ((values = csvReader.readNext()) != null) {
//				autoreRepo.save(new Autore(values[0], values[1]));
//			}
//		}
//	}
//
//	private void initBook() throws FileNotFoundException, IOException {
//		try (CSVReader csvReader = new CSVReader(new FileReader("book.csv"));) {
//			String[] values = null;
//			csvReader.readNext(); // aggiungo questa linea di codice per far si che il reader salti la prima riga
//									// del file csv
//			while ((values = csvReader.readNext()) != null) {
//				bookRepo.save(new Book(values[0], values[1], values[2]));
//			}
//		}
//	}

	// @Override
//	public void run(String... args) throws Exception {
//		
//		//List<List<String>> records = new ArrayList<List<String>>();
//		try (CSVReader csvReader = new CSVReader(new FileReader("book.csv"));) {
//		    String[] values = null;
//		    csvReader.readNext();	// aggiungo questa linea di codice per far si che il reader salti la prima riga del file csv
//		    while ((values = csvReader.readNext()) != null) {
//		        //records.add(Arrays.asList(values));
//		    	bookRepo.save(new Book(values[0],values[1]));
//		    }
//		}
//		System.out.println("TEST");
//	}

}
