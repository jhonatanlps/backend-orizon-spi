package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Maquina;
import com.fiap.ec.backend_orizon_spi.repository.MaquinaRepository;

import java.util.List;

public class MaquinaService {
    private final MaquinaRepository repository;

    public MaquinaService(MaquinaRepository repository) {
        this.repository = repository;
    }

    public Maquina salvar(Maquina maquina){
        return repository.save(maquina);
    }

    public List<Maquina> listar(){
        return repository.findAll();
    }

    public Maquina buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maquina não encontrada"));
    }

    public Maquina atualizar(Long id, Maquina maquinaAtualizado){
        Maquina maquinaExistente = buscarPorId(id);
        maquinaExistente.setDescricao(maquinaAtualizado.getDescricao());
        maquinaExistente.setNome(maquinaAtualizado.getNome());
        maquinaExistente.setSetor(maquinaAtualizado.getSetor());

        return repository.save(maquinaExistente);
    }

    public void deletar(Long id){
        Maquina maquina = buscarPorId(id);
        repository.delete(maquina);
    }
}
