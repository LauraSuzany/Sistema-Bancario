package com.laura.sistemabancario.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.laura.sistemabancario.model.Transacao;

@Transactional //@Transactional trabalha dentro do escopo de uma transação no banco de dados
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	//deposito (+)
	@Modifying
	@Query("update Conta c set c.saldo = c.saldo + ?1 where c.numeroConta = ?2 and c.agencia =?3")
	void setSaldoDebito(double valor, int numeroConta, int agencia);
	//Saque (-)
	@Modifying
	@Query("update Conta c set c.saldo = c.saldo - ?1 where c.numeroConta = ?2 and c.agencia =?3")
	void setSaldoSaque(double valor, int numeroConta, int agencia);


  
	
}
