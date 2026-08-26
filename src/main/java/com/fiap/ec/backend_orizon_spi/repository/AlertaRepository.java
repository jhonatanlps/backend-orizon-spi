package com.fiap.ec.backend_orizon_spi.repository;

import com.fiap.ec.backend_orizon_spi.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    @Query("""
            SELECT a
            FROM Alerta a
            WHERE FORMATDATETIME(data_hora, 'dd/MM/yyyy') = FORMATDATETIME(CURRENT_DATE, 'dd/MM/yyyy')
            ORDER BY a.data_hora DESC
            """) List<Alerta> alertasDoDia();
}
