package com.fiap.ec.backend_orizon_spi.controller;

import com.fiap.ec.backend_orizon_spi.model.Usuario;
import com.fiap.ec.backend_orizon_spi.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario){
        return service.salvar(usuario);
    }

    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        return service.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
