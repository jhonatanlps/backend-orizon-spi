package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Alerta;
import com.fiap.ec.backend_orizon_spi.model.Ocorrencia;
import com.fiap.ec.backend_orizon_spi.repository.AlertaRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {
    private final AlertaRepository repository;

    private final SimpMessagingTemplate messagingTemplate;

    private final OcorrenciaService ocorrenciaService;

    public AlertaService(AlertaRepository repository, SimpMessagingTemplate messagingTemplate, OcorrenciaService ocorrenciaService) {
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
        this.ocorrenciaService = ocorrenciaService;
    }

    public Alerta salvar(Alerta alerta){

        Long idOcorrencia = alerta.getOcorrencia().getId_ocorrencia();

        Ocorrencia ocorrencia = ocorrenciaService.buscarPorId(idOcorrencia);

        alerta.setOcorrencia(ocorrencia);

        Alerta salvo = repository.save(alerta);

        messagingTemplate.convertAndSend(
                "/topic/alerta",
                salvo
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
