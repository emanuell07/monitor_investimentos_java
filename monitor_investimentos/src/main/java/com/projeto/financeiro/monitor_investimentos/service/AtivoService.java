package com.projeto.financeiro.monitor_investimentos.service;

import com.projeto.financeiro.monitor_investimentos.exception.ResourceNotFoundException;
import com.projeto.financeiro.monitor_investimentos.dto.AtivoRequestDTO;
import com.projeto.financeiro.monitor_investimentos.dto.AtivoResponseDTO;
import com.projeto.financeiro.monitor_investimentos.model.Ativo;
import com.projeto.financeiro.monitor_investimentos.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtivoService {

    @Autowired
    private AtivoRepository repository;

    public List<AtivoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AtivoResponseDTO cadastrar(AtivoRequestDTO dto) {
        Ativo ativo = toEntity(dto);
        calcularValorTotal(ativo);
        return toResponse(repository.save(ativo));
    }

    public AtivoResponseDTO atualizar(Long id, AtivoRequestDTO dto) {
        return repository.findById(id).map(ativo -> {
            ativo.setTicker(dto.getTicker());
            ativo.setNome(dto.getNome());
            ativo.setTipo(dto.getTipo());
            ativo.setQuantidade(dto.getQuantidade());
            ativo.setPrecoCompra(dto.getPrecoCompra());
            calcularValorTotal(ativo);
            return toResponse(repository.save(ativo));
        }).orElseThrow(() -> new ResourceNotFoundException("Ativo não encontrado com o id " + id));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private void calcularValorTotal(Ativo ativo) {
        if (ativo.getPrecoCompra() != null && ativo.getQuantidade() != null) {
            BigDecimal total = ativo.getPrecoCompra()
                    .multiply(new BigDecimal(ativo.getQuantidade()));
            ativo.setValorTotal(total);
        }
    }

    private Ativo toEntity(AtivoRequestDTO dto) {
        Ativo ativo = new Ativo();
        ativo.setTicker(dto.getTicker());
        ativo.setNome(dto.getNome());
        ativo.setTipo(dto.getTipo());
        ativo.setQuantidade(dto.getQuantidade());
        ativo.setPrecoCompra(dto.getPrecoCompra());
        return ativo;
    }

    private AtivoResponseDTO toResponse(Ativo ativo) {
        AtivoResponseDTO dto = new AtivoResponseDTO();
        dto.setId(ativo.getId());
        dto.setTicker(ativo.getTicker());
        dto.setNome(ativo.getNome());
        dto.setTipo(ativo.getTipo());
        dto.setQuantidade(ativo.getQuantidade());
        dto.setPrecoCompra(ativo.getPrecoCompra());
        dto.setValorTotal(ativo.getValorTotal());
        return dto;
    }

    public AtivoResponseDTO buscarPorId(Long id) {
    return repository.findById(id)
            .map(this::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Ativo não encontrado com o id " + id));
    }
}