package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Usuario;
import com.fiap.ec.backend_orizon_spi.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService{
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    public List<Usuario> listar(){
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado){
        Usuario usuarioExistente = buscarPorId(id);
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setPerfil(usuarioAtualizado.getPerfil());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());

        return repository.save(usuarioExistente);
    }

    public void deletar(Long id){
        Usuario usuario = buscarPorId(id);
        repository.delete(usuario);
    }
}
