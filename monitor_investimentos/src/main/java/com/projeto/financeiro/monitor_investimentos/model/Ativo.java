package com.projeto.financeiro.monitor_investimentos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_ativos")
@Data
public class Ativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ticker;
    private String nome;
    private String tipo;
    private Integer quantidade;
    private BigDecimal precoCompra;
    private BigDecimal valorTotal;
}