package com.fiap.ec.backend_orizon_spi.controller;

import com.fiap.ec.backend_orizon_spi.model.Maquina;
import com.fiap.ec.backend_orizon_spi.service.MaquinaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maquina")
@CrossOrigin
public class MaquinaController {
    private final MaquinaService service;

    public MaquinaController(MaquinaService service) {
        this.service = service;
    }

    @PostMapping
    public Maquina criar(@RequestBody Maquina maquina){
        return service.salvar(maquina);
    }

    @GetMapping
    public List<Maquina> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Maquina buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Maquina atualizar(@PathVariable Long id, @RequestBody Maquina maquina){
        return service.atualizar(id, maquina);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
