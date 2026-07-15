package com.fiap.ec.backend_orizon_spi.controller;

import com.fiap.ec.backend_orizon_spi.model.Epi;
import com.fiap.ec.backend_orizon_spi.service.EpiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/epi")
@CrossOrigin
public class EpiController {
    private final EpiService service;

    public EpiController(EpiService service) {
        this.service = service;
    }

    @PostMapping
    public Epi criar(@RequestBody Epi epi){
        return service.salvar(epi);
    }

    @GetMapping
    public List<Epi> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Epi buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Epi atualizar(@PathVariable Long id, @RequestBody Epi epi){
        return service.atualizar(id, epi);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
