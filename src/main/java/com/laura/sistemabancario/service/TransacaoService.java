package com.laura.sistemabancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laura.sistemabancario.model.Conta;
import com.laura.sistemabancario.model.Transacao;
import com.laura.sistemabancario.repository.TransacaoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	

	public Transacao saveAllById(Transacao transacao) {
		return transacaoRepository.save(transacao);
	}



	public void saveAll() {
		// TODO Auto-generated method stub
		
	}

	}
