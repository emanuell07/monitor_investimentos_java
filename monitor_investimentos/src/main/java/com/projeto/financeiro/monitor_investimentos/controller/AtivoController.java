package com.projeto.financeiro.monitor_investimentos.controller;

import com.projeto.financeiro.monitor_investimentos.model.Ativo;
import com.projeto.financeiro.monitor_investimentos.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ativos")
public class AtivoController {

    @Autowired
    private AtivoRepository repository;

    @GetMapping
    public List<Ativo> listarTodos() {
        return repository.findAll();
    }

    // ADICIONE ESTE BLOCO ABAIXO:
    @PostMapping
    public Ativo salvar(@RequestBody Ativo ativo) {
        return repository.save(ativo);
    }
}