package com.laura.sistemabancario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laura.sistemabancario.model.Conta;
import com.laura.sistemabancario.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	private ContaRepository contaRepository;

	public Conta salvar(Conta conta) {
		return contaRepository.save(conta);
	}

	public Conta findAgencia(int agencia) {
		return contaRepository.findByAgencia(agencia);

	}

	public Conta findConta(int numeroConta) {

		return contaRepository.findBynumeroConta(numeroConta);
	}

	public void deposita(double valor, int numeroConta, int agencia) {
		contaRepository.setSaldo(valor, numeroConta, agencia);
		
	}

}
