package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Alerta;
import com.fiap.ec.backend_orizon_spi.repository.AlertaRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {
    private final AlertaRepository repository;

    private final SimpMessagingTemplate messagingTemplate;

    public AlertaService(AlertaRepository repository, SimpMessagingTemplate messagingTemplate) {
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
    }

    public Alerta salvar(Alerta alerta){
        Alerta salvo = repository.save(alerta);

        Alerta alertaCompleto = buscarPorId(salvo.getId_alerta());

        messagingTemplate.convertAndSend(
                "/topic/alerta",
                alertaCompleto
        );

        return salvo;
    }

    public List<Alerta> listar(){
        return repository.findAll();
    }

    public Alerta buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));
    }

    public Alerta resolverAlerta(Long id){
        Alerta alertaExistente = buscarPorId(id);
        alertaExistente.setStatus("Resolvido");

        Alerta atualizado = repository.save(alertaExistente);

        messagingTemplate.convertAndSend(
                "/topic/alerta",
                atualizado
        );

        return atualizado;
    }

    public void deletar(Long id){
        Alerta alerta = buscarPorId(id);
        repository.delete(alerta);
    }
}
