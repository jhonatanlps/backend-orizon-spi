package com.fiap.ec.backend_orizon_spi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fiap.ec.backend_orizon_spi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
