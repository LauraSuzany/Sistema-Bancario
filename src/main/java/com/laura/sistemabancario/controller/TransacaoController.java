package com.laura.sistemabancario.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.laura.sistemabancario.error.ResourceNotFoundException;
import com.laura.sistemabancario.model.Conta;
import com.laura.sistemabancario.model.Transacao;
import com.laura.sistemabancario.service.ContaService;
import com.laura.sistemabancario.service.TransacaoService;

import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping("/transacao")
public class TransacaoController {
	@Autowired
	private ContaService contaService;
//
	@Autowired
	private TransacaoService transacaoService;
	
	//logica para deposito com validação de campos agencia e conta
	@ApiOperation(value = "Deposito")
	@RequestMapping(value = "depositar/{descricao}/{valor}/{numeroConta}/{agencia}", method = RequestMethod.PUT)
	public ResponseEntity<?> deposito(@PathVariable String descricao, @PathVariable double valor,
		@PathVariable int numeroConta, @PathVariable int agencia) {

		Conta agenciaObj = contaService.findByAgenciaAndNumConta(agencia, numeroConta)
				.orElseThrow(() -> new ResourceNotFoundException("Conta Corrente ou agência Inexistente"));

		Transacao trasacaoOnj = new Transacao();
		trasacaoOnj.setNatuTransacao("Deposito");
		trasacaoOnj.setDataTransacao(LocalDate.now());
		trasacaoOnj.setDescricao(descricao);
		trasacaoOnj.setValor(valor);
		trasacaoOnj.setConta(agenciaObj);
		this.transacaoService.salvar(trasacaoOnj);
		this.transacaoService.deposita(valor, numeroConta, agencia);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Deposito realizado com sucesso!! " + "\nValor: R$" + valor + "\nAgência: " + agencia
						+ " \nNúmero da Conta: " + numeroConta);

	}
	//logica para saque com validação de campos agencia e conta
	@ApiOperation(value = "Saque")
	@RequestMapping(value = "saque/{descricao}/{valor}/{numeroConta}/{agencia}", method = RequestMethod.PUT)
	public ResponseEntity<?> saque(@PathVariable String descricao, @PathVariable double valor,
		@PathVariable int numeroConta, @PathVariable int agencia) {

		Conta agenciaObj = contaService.findByAgenciaAndNumConta(agencia, numeroConta)
				.orElseThrow(() -> new ResourceNotFoundException("Conta Corrente ou agência Inexistente"));
		
		if (agenciaObj.getSaldo() < valor) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Saldo insuficiente \n"
							+"saldo em conta: R$"+agenciaObj.getSaldo());
		}
		Transacao trasacaoOnj = new Transacao();
		trasacaoOnj.setNatuTransacao("Saque");
		trasacaoOnj.setDataTransacao(LocalDate.now());
		trasacaoOnj.setDescricao(descricao);
		trasacaoOnj.setValor(valor);
		trasacaoOnj.setConta(agenciaObj);
		this.transacaoService.salvar(trasacaoOnj);
		this.transacaoService.saque(valor, numeroConta, agencia);
		// this.transacaoService.salvarIdConta(agenciaObj.getIdConta());
		return ResponseEntity.status(HttpStatus.OK)
				.body("Saque realizado com sucesso!! " + "\nValor: R$ -" + valor + "\nAgência: " + agencia
						+ " \nNúmero da Conta: " + numeroConta +" saldo em conta: R$"+agenciaObj.getSaldo());

	}
}

