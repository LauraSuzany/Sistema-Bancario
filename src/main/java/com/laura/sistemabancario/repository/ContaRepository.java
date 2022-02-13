package com.laura.sistemabancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.laura.sistemabancario.model.Conta;

@Transactional(readOnly = false)
public interface ContaRepository extends JpaRepository<Conta, Long> {

	Conta findByAgencia(int agencia);

	Conta findBynumeroConta(int numeroConta);

}
