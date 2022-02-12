package com.laura.sistemabancario.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Transacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long idTransacao;
	private byte codigoTransacao;
	private String descricao;
	private String natuTransacao; // se é C- ou D+ 
	private double valor;
	private LocalDateTime data;
	@ManyToOne
	@JoinColumn(name = "idConta", nullable = false)
	private Conta conta;	
	@OneToMany(mappedBy = "transacao", fetch = FetchType.EAGER)
	private Set<LogTransacao> logTransacaos;
	
}
