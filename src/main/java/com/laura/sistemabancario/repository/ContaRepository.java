package com.laura.sistemabancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.laura.sistemabancario.model.Conta;
@Transactional(readOnly = false)
public interface ContaRepository extends JpaRepository<Conta, Long>{
	//	@Modifying
	//	@Query("update Conta c set c.saldo = c.saldo + ?1 where c.agecia like %?2% and c.conta like %?3%")
	//	void setFixedSaldoFor(double valor, String conta, String agencia);
	//	
	//Conta (findByAgenciaLike(String agencia) and findByContaLike(String nconta));
	
		Conta findByagencia(int agencia);
	//	Conta save(String agencia, String nConta, double saldo);

//		
}
	
