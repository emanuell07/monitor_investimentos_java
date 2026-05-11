package com.projeto.financeiro.monitor_investimentos.repository;

import com.projeto.financeiro.monitor_investimentos.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
}