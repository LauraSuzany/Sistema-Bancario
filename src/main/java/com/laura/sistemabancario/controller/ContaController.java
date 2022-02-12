package com.laura.sistemabancario.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import com.laura.sistemabancario.model.Conta;
import com.laura.sistemabancario.service.ContaService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/SistemaBancario")
public class ContaController {
	@Autowired //ponto de injeção
	ContaService contaService;
	
	@PostMapping("/conta")
	@ApiOperation(value = "Salvar conta")
	@ExceptionHandler(BadRequest.class)
	public Conta SalvarConta(@Valid @RequestBody Conta conta) {
		return contaService.salvar(conta);
		
	}
		
}
