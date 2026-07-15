package com.fiap.ec.backend_orizon_spi.controller;

import com.fiap.ec.backend_orizon_spi.model.Alerta;
import com.fiap.ec.backend_orizon_spi.service.AlertaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerta")
@CrossOrigin
public class AlertaController {
    private final AlertaService service;

    public AlertaController(AlertaService service) {
        this.service = service;
    }

    @PostMapping
    public Alerta criar(@RequestBody Alerta alerta){
        return service.salvar(alerta);
    }

    @GetMapping
    public List<Alerta> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Alerta buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Alerta atualizar(@PathVariable Long id, @RequestBody Alerta alerta){
        return service.atualizar(id, alerta);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
