package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Alerta;
import com.fiap.ec.backend_orizon_spi.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {
    private final AlertaRepository repository;

    public AlertaService(AlertaRepository repository) {
        this.repository = repository;
    }

    public Alerta salvar(Alerta alerta){
        return repository.save(alerta);
    }

    public List<Alerta> listar(){
        return repository.findAll();
    }

    public Alerta buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));
    }

    public Alerta atualizar(Long id, Alerta alertaAtualizado){
        Alerta alertaExistente = buscarPorId(id);
        alertaExistente.setData_hora(alertaAtualizado.getData_hora());
        alertaExistente.setMensagem(alertaAtualizado.getMensagem());
        alertaExistente.setOcorrencia(alertaAtualizado.getOcorrencia());
        alertaExistente.setStatus(alertaAtualizado.getStatus());

        return repository.save(alertaExistente);
    }

    public void deletar(Long id){
        Alerta alerta = buscarPorId(id);
        repository.delete(alerta);
    }
}
