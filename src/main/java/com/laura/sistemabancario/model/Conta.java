package com.laura.sistemabancario.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Conta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long idConta;
	private String agencia;
	private String nConta;
	@Column(nullable= false)
	private double saldo;
	@OneToMany(mappedBy = "conta", fetch = FetchType.EAGER)
	private Set<Transacao> transacaos; 
	
	
}
