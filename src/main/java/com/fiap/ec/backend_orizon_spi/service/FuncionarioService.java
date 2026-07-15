package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Funcionario;
import com.fiap.ec.backend_orizon_spi.repository.FuncionarioRepository;

import java.util.List;

public class FuncionarioService {
    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Funcionario salvar(Funcionario funcionario){
        return repository.save(funcionario);
    }

    public List<Funcionario> listar(){
        return repository.findAll();
    }

    public Funcionario buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
    }

    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado){
        Funcionario funcionarioExistente = buscarPorId(id);
        funcionarioExistente.setCargo(funcionarioAtualizado.getCargo());
        funcionarioExistente.setMatricula(funcionarioAtualizado.getMatricula());
        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setSetor(funcionarioAtualizado.getSetor());

        return repository.save(funcionarioExistente);
    }

    public void deletar(Long id){
        Funcionario funcionario = buscarPorId(id);
        repository.delete(funcionario);
    }
}
