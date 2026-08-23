package com.fiap.ec.backend_orizon_spi.controller;

import com.fiap.ec.backend_orizon_spi.model.Zona;
import com.fiap.ec.backend_orizon_spi.service.ZonaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zona")
@CrossOrigin
public class zonaController {
    private final ZonaService service;

    public zonaController(ZonaService service) {
        this.service = service;
    }

    @PostMapping
    public Zona criar(@RequestBody Zona zona){
        return service.salvar(zona);
    }

    @GetMapping
    public List<Zona> listar(){
        return service.listar();
    }

    @GetMapping("{id}")
    public Zona buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("{id}")
    public Zona atualizar(@PathVariable Long id, @RequestBody Zona zona){
        return service.atualizar(id, zona);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

}
