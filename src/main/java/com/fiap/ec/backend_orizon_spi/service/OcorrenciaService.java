package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Funcionario;
import com.fiap.ec.backend_orizon_spi.model.Ocorrencia;
import com.fiap.ec.backend_orizon_spi.model.Zona;
import com.fiap.ec.backend_orizon_spi.repository.OcorrenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcorrenciaService {
    private final OcorrenciaRepository repository;

    private final ZonaService zonaService;

    private final FuncionarioService funcionarioService;

    public OcorrenciaService(OcorrenciaRepository repository, ZonaService zonaService, FuncionarioService funcionarioService) {
        this.repository = repository;
        this.zonaService = zonaService;
        this.funcionarioService = funcionarioService;
    }

    public Ocorrencia salvar(Ocorrencia ocorrencia){

        Long idZona = ocorrencia.getZona().getId_zona();
        Long idFuncionario = ocorrencia.getFuncionario().getId_funcionario();

        Zona zona = zonaService.buscarPorId(idZona);
        Funcionario funcionario = funcionarioService.buscarPorId(idFuncionario);

        ocorrencia.setZona(zona);
        ocorrencia.setFuncionario(funcionario);

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
        ocorrenciaExistente.setZona(ocorrenciaAtualizado.getZona());

        return repository.save(ocorrenciaExistente);
    }

    public void deletar(Long id){
        Ocorrencia ocorrencia = buscarPorId(id);
        repository.delete(ocorrencia);
    }
}
