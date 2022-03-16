package it.be.energy.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fattura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long numeroFattura;
	private Integer anno;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Madrid")
	private Date data;
	private BigDecimal importo;
	
	@ManyToOne
	private StatoFattura statoFattura;
	
	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIgnoreProperties({"dataInserimento", "dataUltimoContatto", "pec", "fatture", "sedeLegale", "sedeOperativa"})
	private Cliente cliente;
	
}
