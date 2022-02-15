package com.laura.sistemabancario.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Entity
public class LogTransacao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long idLogTranzacao;
	@ManyToOne(fetch = FetchType.EAGER)//seria usado quando não faz sentido puxar um objeto do BD sem puxar um outro também
	@JoinColumn(name = "codigoTransacao", nullable = false)
	private Transacao transacao;
	
}
