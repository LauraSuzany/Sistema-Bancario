package com.laura.sistemabancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laura.sistemabancario.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

}
