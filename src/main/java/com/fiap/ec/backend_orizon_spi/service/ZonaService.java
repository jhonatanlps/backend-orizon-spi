package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Epi;
import com.fiap.ec.backend_orizon_spi.model.Maquina;
import com.fiap.ec.backend_orizon_spi.model.Zona;
import com.fiap.ec.backend_orizon_spi.model.ZonaEpi;
import com.fiap.ec.backend_orizon_spi.repository.ZonaRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaService {
    private final ZonaRepository repository;

    private final MaquinaService maquinaService;

    private final EpiService epiService;

    private final SimpMessagingTemplate messagingTemplate;

    public ZonaService(ZonaRepository repository, MaquinaService maquinaService, EpiService epiService, SimpMessagingTemplate messagingTemplate) {
        this.repository = repository;
        this.maquinaService = maquinaService;
        this.epiService = epiService;
        this.messagingTemplate = messagingTemplate;
    }

    public Zona salvar(Zona zona){

        for (ZonaEpi zonaEpi : zona.getEpi()){
            Long epiId = zonaEpi.getEpi().getId_epi();

            Epi epi = epiService.buscarPorId(epiId);

            zonaEpi.setZona(zona);
            zonaEpi.setEpi(epi);
        }

        Long idMaquina = zona.getMaquina().getId_maquina();

        Maquina maquina = maquinaService.buscarPorId(idMaquina);

        zona.setMaquina(maquina);

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
                .orElseThrow(() -> new RuntimeException("Ocorrencia não encontrada"));
    }

    public Zona atualizar(Long id, Zona zonaAtualizada){
        Zona zonaExistente = buscarPorId(id);
        zonaExistente.setNome(zonaAtualizada.getNome());
        zonaExistente.setMaquina(zonaAtualizada.getMaquina());
        zonaExistente.setPoligonos(zonaAtualizada.getPoligonos());
        zonaExistente.setCamera(zonaAtualizada.getCamera());
        for (ZonaEpi zonaEpi : zonaAtualizada.getEpi()){
            Long epiId = zonaEpi.getEpi().getId_epi();

            Epi epi = epiService.buscarPorId(epiId);

            ZonaEpi novo = new ZonaEpi();
            novo.setEpi(epi);
            novo.setZona(zonaExistente);

            zonaExistente.getEpi().add(novo);
        }

        return repository.save(zonaExistente);
    }

    public void deletar(Long id){
        Zona zona = buscarPorId(id);
        repository.delete(zona);
    }
}
