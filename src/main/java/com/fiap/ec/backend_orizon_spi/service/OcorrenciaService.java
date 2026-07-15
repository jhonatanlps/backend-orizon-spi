package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Ocorrencia;
import com.fiap.ec.backend_orizon_spi.repository.OcorrenciaRepository;

import java.util.List;

public class OcorrenciaService {
    private final OcorrenciaRepository repository;

    public OcorrenciaService(OcorrenciaRepository repository) {
        this.repository = repository;
    }

    public Ocorrencia salvar(Ocorrencia ocorrencia){
        return repository.save(ocorrencia);
    }

    public List<Ocorrencia> listar(){
        return repository.findAll();
    }

    public Ocorrencia buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrencia não encontrada"));
    }

    public Ocorrencia atualizar(Long id, Ocorrencia ocorrenciaAtualizado){
        Ocorrencia ocorrenciaExistente = buscarPorId(id);
        ocorrenciaExistente.setData_hora(ocorrenciaAtualizado.getData_hora());
        ocorrenciaExistente.setFuncionario(ocorrenciaAtualizado.getFuncionario());
        ocorrenciaExistente.setStatus(ocorrenciaAtualizado.getStatus());
        ocorrenciaExistente.setMaquina(ocorrenciaAtualizado.getMaquina());

        return repository.save(ocorrenciaExistente);
    }

    public void deletar(Long id){
        Ocorrencia ocorrencia = buscarPorId(id);
        repository.delete(ocorrencia);
    }
}
