package com.laura.sistemabancario.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Transacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long codigoTransacao;
	private String descricao;
	private String natuTransacao; // se é C- ou D+ 
	private double valor;
	@Column
	private LocalDateTime dataTransacao;	
	@ManyToOne
	@JoinColumn(name = "idConta")
	private Conta conta;	
	@OneToMany(mappedBy = "transacao", fetch = FetchType.EAGER)
	private Set<LogTransacao> logTransacaos;	
	
}








