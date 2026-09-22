package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.*;
import com.fiap.ec.backend_orizon_spi.repository.ZonaRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZonaService {
    private final ZonaRepository repository;
    private final MaquinaService maquinaService;
    private final EpiService epiService;
    private final CameraService cameraService;
    private final SimpMessagingTemplate messagingTemplate;

    public ZonaService(ZonaRepository repository, MaquinaService maquinaService, EpiService epiService, CameraService cameraService, SimpMessagingTemplate messagingTemplate) {
        this.repository = repository;
        this.maquinaService = maquinaService;
        this.epiService = epiService;
        this.cameraService = cameraService;
        this.messagingTemplate = messagingTemplate;
    }

    public Zona salvar(Zona zona){

        // 1. Processar e associar os EPIs
        if (zona.getEpi() != null) {
            for (ZonaEpi zonaEpi : zona.getEpi()){
                if (zonaEpi != null && zonaEpi.getEpi() != null && zonaEpi.getEpi().getId_epi() != null) {
                    Long epiId = zonaEpi.getEpi().getId_epi();
                    Epi epi = epiService.buscarPorId(epiId);

                    zonaEpi.setZona(zona);
                    zonaEpi.setEpi(epi);
                }
            }
        }

        // 2. Processar a Máquina
        if (zona.getMaquina() != null && zona.getMaquina().getId_maquina() != null) {
            Long idMaquina = zona.getMaquina().getId_maquina();
            Maquina maquina = maquinaService.buscarPorId(idMaquina);
            zona.setMaquina(maquina);
        }

        // 3. 🔴 CORREÇÃO DO ERRO: Processar e buscar a Câmera do banco
        if (zona.getCamera() != null && zona.getCamera().getId() != null) {
            Long idCamera = zona.getCamera().getId();
            Camera camera = cameraService.buscarPorId(idCamera);
            zona.setCamera(camera); // Associa a entidade gerenciada pelo JPA
        } else {
            zona.setCamera(null); // Se não veio ID, garante que fica nulo
        }

        Zona salvo = repository.save(zona);

        messagingTemplate.convertAndSend(
                "/topic/zona",
                salvo
        );

        return salvo;
    }

    public List<Zona> listar(){
        return repository.findAll();
    }

    public Zona buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));
    }

    public Zona atualizar(Long id, Zona zonaAtualizada){
        Zona zonaExistente = buscarPorId(id);
        zonaExistente.setNome(zonaAtualizada.getNome());
        zonaExistente.setResolucao(zonaAtualizada.getResolucao());
        zonaExistente.setPoligonos(zonaAtualizada.getPoligonos());
        zonaExistente.setCamera(zonaAtualizada.getCamera());

        if (zonaAtualizada.getMaquina() != null && zonaAtualizada.getMaquina().getId_maquina() != null) {
            Long idMaquina = zonaAtualizada.getMaquina().getId_maquina();
            Maquina maquina = maquinaService.buscarPorId(idMaquina);
            zonaExistente.setMaquina(maquina);
        }

        if (zonaAtualizada.getEpi() != null) {
            if (zonaExistente.getEpi() == null) {
                zonaExistente.setEpi(new ArrayList<>());
            } else {
                zonaExistente.getEpi().clear();
            }

            for (ZonaEpi zonaEpi : zonaAtualizada.getEpi()){
                if (zonaEpi != null && zonaEpi.getEpi() != null && zonaEpi.getEpi().getId_epi() != null) {
                    Long epiId = zonaEpi.getEpi().getId_epi();
                    Epi epi = epiService.buscarPorId(epiId);

                    ZonaEpi novo = new ZonaEpi();
                    novo.setEpi(epi);
                    novo.setZona(zonaExistente);

                    zonaExistente.getEpi().add(novo);
                }
            }
        }

        return repository.save(zonaExistente);
    }

    public void deletar(Long id){
        Zona zona = buscarPorId(id);
        repository.delete(zona);
    }
}