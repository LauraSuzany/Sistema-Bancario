package com.laura.sistemabancario.controller;

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
import com.laura.sistemabancario.service.ContaService;
import com.laura.sistemabancario.service.TransacaoService;

import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping("/transacao")
public class TransacaoController {
	@Autowired
	private ContaService contaService;

	@Autowired
	private TransacaoService transacaoService;

	@ApiOperation(value = "Deposito")
	@RequestMapping(value = "depositar/{valor}/{numeroConta}/{agencia}", method = RequestMethod.PUT)
	public ResponseEntity<?> consultarAgenciaConta(@PathVariable double valor, @PathVariable int numeroConta,
			@PathVariable int agencia) {
		// verificar a existência da agência
		Conta agenciaObj = contaService.findAgencia(agencia);
		if (agenciaObj == null) {

			throw new ResourceNotFoundException("Agência Inexistente: " + agencia);

		}

		// verificar a existência do número da conta
		Conta numeroContaObj = contaService.findConta(numeroConta);
		if (numeroContaObj == null) {
			throw new ResourceNotFoundException("Conta Corrent Inexistente " + numeroConta);

		}
		// lógica para retornar o saldo só se os dois campos mencionados corresponderem
		if (agenciaObj != numeroContaObj) {
			throw new ResourceNotFoundException("Conta não encontrada para agência: " + agencia + " e número de conta: "
					+ numeroConta + " informados");
		}

		this.contaService.deposita(valor, numeroConta, agencia);
		//return ResponseEntity.status(HttpStatus.OK).body(numeroContaObj+"Deposito realizado com sucesso!");
		
		return ResponseEntity.status(HttpStatus.OK).body("Deposito realizado com sucesso!! "
				+ "\nValor: R$"+valor+  "\nAgência: "+agencia+" \nNúmero da Conta: "+numeroConta);
		// return userDocumentationRepository.findById(userId);
	}


}
