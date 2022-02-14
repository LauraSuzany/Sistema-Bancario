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
	
	@Autowired
	private TransacaoService  transacaoService;
	
	@ApiOperation(value = "Consultar saldo por conta e agencia")
	@RequestMapping(value = "depositar/{valor}/{numeroConta}/{agencia}", method = RequestMethod.PUT)
	public ResponseEntity<?> consultarAgenciaConta( @PathVariable double valor, @PathVariable int numeroConta, @PathVariable int agencia) {
		Conta agenciaObj = contaService.findAgencia(agencia);
		if (agenciaObj == null) {
			// valor num conata agencia

			throw new ResourceNotFoundException("Agência Inexistente: " + agencia);

		}

		Conta numeroContaObj = contaService.findConta(numeroConta);
		if (numeroContaObj == null) {
			throw new ResourceNotFoundException("Conta Corrent Inexistente " + numeroConta);

		}


		// acho que vou fazer tudo aqui com o put e ainda mandar salvar coisas dentro de
		// transacao	
		
//		
//		Transacao transacao = transacaoService.saveAllById(transacao);
//		transacaoService.saveAll(transacao);
//		//transacaoService.saveAll(transacao);
			
		this.contaService.deposita(valor, numeroConta, agencia);
		return ResponseEntity.status(HttpStatus.OK).body("Deposito realizado com sucesso!");
		//return userDocumentationRepository.findById(userId);
	}	
	
	
}
