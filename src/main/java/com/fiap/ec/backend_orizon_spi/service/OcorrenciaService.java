package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Funcionario;
import com.fiap.ec.backend_orizon_spi.model.Ocorrencia;
import com.fiap.ec.backend_orizon_spi.model.Zona;
import com.fiap.ec.backend_orizon_spi.repository.OcorrenciaRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OcorrenciaService {
    private final OcorrenciaRepository repository;

    private final SimpMessagingTemplate messagingTemplate;

    private final ZonaService zonaService;

    private final FuncionarioService funcionarioService;

    public OcorrenciaService(OcorrenciaRepository repository, SimpMessagingTemplate messagingTemplate, ZonaService zonaService, FuncionarioService funcionarioService) {
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
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

        Ocorrencia salvo = repository.save(ocorrencia);

        List<Map<String, Object>> qtdStatus = qtdOcorrenciasPorStatus();

        messagingTemplate.convertAndSend(
                "/topic/ocorrencia/quantidade-status",
                qtdStatus
        );

        List<Map<String, Object>> qtdZonas = qtdConformidadePorZonas();

        messagingTemplate.convertAndSend(
                "/topic/ocorrencia/quantidade-zonas",
                qtdZonas
        );

        return salvo;
    }

    public List<Ocorrencia> listar(){
        return repository.findAll();
    }

    public Ocorrencia buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrencia não encontrada"));
    }

    public List<Map<String, Object>> qtdOcorrenciasPorStatus(){
        List<Object[]> resultados = repository.contarPorStatus();

        List<Map<String, Object>> resposta = new ArrayList<>();

        for (Object[] resultado : resultados){
            Map<String, Object> item = new HashMap<>();

            item.put("status", resultado[0]);
            item.put("quantidade", resultado[1]);

            resposta.add(item);
        }

        return resposta;
    }

    public List<Map<String, Object>> qtdConformidadePorZonas(){
        List<Object[]> resultados = repository.contarConformidadePorZonas();

        List<Map<String, Object>> resposta = new ArrayList<>();

        for (Object[] resultado : resultados){
            Map<String, Object> item = new HashMap<>();

            item.put("zona", resultado[0]);
            item.put("quantidade", resultado[1]);

            resposta.add(item);
        }

        return resposta;
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
