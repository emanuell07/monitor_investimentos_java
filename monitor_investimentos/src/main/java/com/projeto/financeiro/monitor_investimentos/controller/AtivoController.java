package com.projeto.financeiro.monitor_investimentos.controller;

import com.projeto.financeiro.monitor_investimentos.model.Ativo;
import com.projeto.financeiro.monitor_investimentos.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @PostMapping
    public Ativo cadastrar(@RequestBody Ativo ativo) {
    // Cálculo: Total = Preço de Compra * Quantidade
    if (ativo.getPrecoCompra() != null && ativo.getQuantidade() != null) {
        BigDecimal total = ativo.getPrecoCompra().multiply(new BigDecimal(ativo.getQuantidade()));
        ativo.setValorTotal(total);
    }
    return repository.save(ativo);
}

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
     repository.deleteById(id);
}

@PutMapping("/{id}")
public Ativo atualizar(@PathVariable Long id, @RequestBody Ativo ativoAtualizado) {
    return repository.findById(id).map(ativo -> {
        ativo.setNome(ativoAtualizado.getNome());
        ativo.setTicker(ativoAtualizado.getTicker());
        ativo.setTipo(ativoAtualizado.getTipo());
        ativo.setQuantidade(ativoAtualizado.getQuantidade());
        ativo.setPrecoCompra(ativoAtualizado.getPrecoCompra());
        
        // Recalcula o valor total com os novos dados
        if (ativo.getPrecoCompra() != null && ativo.getQuantidade() != null) {
            BigDecimal total = ativo.getPrecoCompra().multiply(new BigDecimal(ativo.getQuantidade()));
            ativo.setValorTotal(total);
        }
        
        return repository.save(ativo);
    }).orElseThrow(() -> new RuntimeException("Ativo não encontrado com o id " + id));
}

}