package com.fiap.ec.backend_orizon_spi.repository;

import com.fiap.ec.backend_orizon_spi.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    @Query("""
            SELECT o.status, COUNT(*)
            FROM Ocorrencia o
            WHERE FORMATDATETIME(o.data_hora, 'yyyy-MM-dd') = FORMATDATETIME(CURRENT_DATE, 'yyyy-MM-dd')
            GROUP BY o.status
            """) List<Object[]> contarPorStatus();

    @Query("""
            SELECT z.nome, COUNT(*)
            FROM Ocorrencia o
            JOIN o.zona z
            WHERE o.status LIKE 'OK' AND FORMATDATETIME(o.data_hora, 'yyyy-MM-dd') = FORMATDATETIME(CURRENT_DATE, 'yyyy-MM-dd')
            GROUP BY z.nome
            """) List<Object[]> contarConformidadePorZonas();
}
