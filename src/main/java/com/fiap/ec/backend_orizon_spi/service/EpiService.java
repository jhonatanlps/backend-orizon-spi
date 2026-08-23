package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Epi;
import com.fiap.ec.backend_orizon_spi.repository.EpiRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpiService {
    private final EpiRepository repository;

    private final SimpMessagingTemplate messagingTemplate;

    public EpiService(EpiRepository repository, SimpMessagingTemplate messagingTemplate) {
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
    }

    public Epi salvar(Epi epi){
        Epi salvo = repository.save(epi);

        messagingTemplate.convertAndSend(
                "/topic/epi",
                salvo
        );

        return salvo;
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
