package com.projeto.financeiro.monitor_investimentos.controller;

import com.projeto.financeiro.monitor_investimentos.dto.AtivoRequestDTO;
import com.projeto.financeiro.monitor_investimentos.dto.AtivoResponseDTO;
import com.projeto.financeiro.monitor_investimentos.service.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ativos")
public class AtivoController {

    @Autowired
    private AtivoService service;

    @GetMapping
    public List<AtivoResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public AtivoResponseDTO buscarPorId(@PathVariable Long id) {
    return service.buscarPorId(id);
    }

    @PostMapping
    public AtivoResponseDTO cadastrar(@RequestBody AtivoRequestDTO dto) {
        return service.cadastrar(dto);
    }

    @PutMapping("/{id}")
    public AtivoResponseDTO atualizar(@PathVariable Long id, @RequestBody AtivoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}