package com.fiap.ec.backend_orizon_spi.repository;

import com.fiap.ec.backend_orizon_spi.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
}
