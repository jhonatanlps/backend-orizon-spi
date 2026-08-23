package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Maquina;
import com.fiap.ec.backend_orizon_spi.repository.MaquinaRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaquinaService {
    private final MaquinaRepository repository;

    private final SimpMessagingTemplate messagingTemplate;

    public MaquinaService(MaquinaRepository repository, SimpMessagingTemplate messagingTemplate) {
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
    }

    public Maquina salvar(Maquina maquina){
        Maquina salvo = repository.save(maquina);

        messagingTemplate.convertAndSend(
                "/topic/maquina",
                salvo
        );

        return salvo;
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
