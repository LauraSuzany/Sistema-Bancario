package com.laura.sistemabancario.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.laura.sistemabancario.error.ResourceNotFoundException;
import com.laura.sistemabancario.model.Conta;
import com.laura.sistemabancario.service.ContaService;

import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping("/SistemaBancario")
public class ContaController {
	@Autowired
	private ContaService contaService;

	@PostMapping("/salvar/{agencia}/{numeroConta}/{saldo}")
	@ApiOperation(value = "Salvar conta")
	@ExceptionHandler(BadRequest.class)
	public Conta salvarConta(@PathVariable int agencia, @PathVariable int numeroConta, double saldo) {

		Conta conta = new Conta(0, agencia, numeroConta, saldo, null);
		return contaService.salvar(conta);

	}

	// retorna um saldo de uma determinada conta e agencia e informa se a
	@ApiOperation(value = "Consultar saldo por conta e agencia")
	@RequestMapping(value = "consultar/{numeroConta}/{agencia}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPoragenciaContaNum(@PathVariable int numeroConta, @PathVariable int agencia)
			throws SQLException {
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

		return ResponseEntity.status(HttpStatus.OK).body(numeroContaObj);

	}

}
