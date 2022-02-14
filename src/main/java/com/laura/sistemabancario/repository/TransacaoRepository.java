package com.laura.sistemabancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laura.sistemabancario.model.Conta;
import com.laura.sistemabancario.model.Transacao;
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

  
	
}
