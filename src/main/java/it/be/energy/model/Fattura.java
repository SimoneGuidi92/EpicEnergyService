package it.be.energy.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private Long numeroFattura;
	private Integer anno;
	private Date data;
	private BigDecimal importo;
	@ManyToOne
	private StatoFattura statoFattura;
	
	@ManyToOne
	private Cliente cliente;
	
}
