package com.fiap.ec.backend_orizon_spi.controller;

import com.fiap.ec.backend_orizon_spi.model.Funcionario;
import com.fiap.ec.backend_orizon_spi.service.FuncionarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin
public class FuncionarioController {
    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @PostMapping
    public Funcionario criar(@RequestBody Funcionario funcionario){
        return service.salvar(funcionario);
    }

    @GetMapping
    public List<Funcionario> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Funcionario buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Funcionario atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario){
        return service.atualizar(id, funcionario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
