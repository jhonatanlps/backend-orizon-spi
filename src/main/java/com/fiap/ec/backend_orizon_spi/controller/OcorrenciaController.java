package com.fiap.ec.backend_orizon_spi.controller;

import com.fiap.ec.backend_orizon_spi.model.Ocorrencia;
import com.fiap.ec.backend_orizon_spi.service.OcorrenciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ocorrencia")
@CrossOrigin
public class OcorrenciaController {
    private final OcorrenciaService service;

    public OcorrenciaController(OcorrenciaService service) {
        this.service = service;
    }

    @PostMapping
    public Ocorrencia criar(@RequestBody Ocorrencia ocorrencia){
        return service.salvar(ocorrencia);
    }

    @GetMapping
    public List<Ocorrencia> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Ocorrencia buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping("/quantidade-status")
    public List<Map<String, Object>> qtdOcorrenciasPorStatus(){
        return service.qtdOcorrenciasPorStatus();
    }

    @GetMapping("/quantidade-zonas")
    public List<Map<String, Object>> qtdConformidadePorZonas(){
        return service.qtdConformidadePorZonas();
    }

    @PutMapping("/{id}")
    public Ocorrencia atualizar(@PathVariable Long id, @RequestBody Ocorrencia ocorrencia){
        return service.atualizar(id, ocorrencia);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
