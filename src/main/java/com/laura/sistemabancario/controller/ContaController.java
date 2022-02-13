package com.laura.sistemabancario.controller;
	
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@PostMapping("/salvar/{agencia}/{nconta}/{saldo}")
	@ApiOperation(value = "Salvar conta")
	// agência tem 4 números
	// Salvar conta
	@ExceptionHandler(BadRequest.class)
	public Conta salvarConta(@PathVariable int agencia, @PathVariable int nconta, double saldo) {
		Conta conta = new Conta(0, agencia, nconta, saldo, null);
		return contaService.salvar(conta);
				
	}
	//filtrar por conta e agencia
	@ApiOperation(value = "Consultar saldo")
	@RequestMapping(value = "depositar/{agencia}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorAgencia (@PathVariable int agencia) {
		 Conta conta = contaService.buscarPoragencia(agencia);
		 if(conta == null) { 
			 throw new ResourceNotFoundException("Agencia não encontrada para "+ agencia);
		    }

		    return ResponseEntity.status(HttpStatus.OK).body(conta);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
