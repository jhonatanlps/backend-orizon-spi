package com.fiap.ec.backend_orizon_spi.repository;

import com.fiap.ec.backend_orizon_spi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
