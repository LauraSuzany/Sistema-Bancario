package com.laura.sistemabancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laura.sistemabancario.model.Transacao;
import com.laura.sistemabancario.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	public Transacao saveAll(Transacao trasacaoOnj) {
		return transacaoRepository.save(trasacaoOnj);

	}

	public Transacao salvar(Transacao trasacaoOnj) {
		return transacaoRepository.save(trasacaoOnj);
	}

	public void deposita(double valor, int numeroConta, int agencia) {
		transacaoRepository.setSaldoDebito(valor, numeroConta, agencia);

	}

	public void saque(double valor, int numeroConta, int agencia) {
		transacaoRepository.setSaldoSaque(valor, numeroConta, agencia);

	}

}
