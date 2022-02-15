package com.laura.sistemabancario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.laura.sistemabancario.model.Conta;

@Transactional(readOnly = false)
public interface ContaRepository extends JpaRepository<Conta, Long> {

	Conta findByAgencia(int agencia);

	Conta findBynumeroConta(int numeroConta);

	@Modifying
	@Query("update Conta c set c.saldo = c.saldo + ?1 where c.numeroConta = ?2 and c.agencia =?3")
	void setSaldo(double valor, int numeroConta, int agencia);
//	
//	
//	@Modifying
//	@Query("update Conta c set c.saldo = c.saldo + ?1 where c.numeroConta = ?2 and c.agencia =?3")
//	void saqueSaldo(double valor, int numeroConta, int agencia);
	//querys jpa
	@Query("select c from Conta c where c.agencia = :agencia and  c.numeroConta = :numeroConta")
	Optional<Conta> findByAgenciaAndNumConta(@Param("numeroConta") int numeroConta, @Param("agencia") int agencia);
	
}
