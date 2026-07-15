package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Epi;
import com.fiap.ec.backend_orizon_spi.repository.EpiRepository;

import java.util.List;

public class EpiService {
    private final EpiRepository repository;

    public EpiService(EpiRepository repository) {
        this.repository = repository;
    }

    public Epi salvar(Epi epi){
        return repository.save(epi);
    }

    public List<Epi> listar(){
        return repository.findAll();
    }

    public Epi buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Epi não encontrada"));
    }

    public Epi atualizar(Long id, Epi epiAtualizado){
        Epi epiExistente = buscarPorId(id);
        epiExistente.setDescricao(epiAtualizado.getDescricao());
        epiExistente.setNome(epiAtualizado.getNome());

        return repository.save(epiExistente);
    }

    public void deletar(Long id){
        Epi epi = buscarPorId(id);
        repository.delete(epi);
    }

}
