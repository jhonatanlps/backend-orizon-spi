package com.fiap.ec.backend_orizon_spi.repository;

import com.fiap.ec.backend_orizon_spi.model.Alerta;
import com.fiap.ec.backend_orizon_spi.model.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZonaRepository extends JpaRepository<Zona, Long> {
    @Query(value = "SELECT * FROM ZONA WHERE CAMERA_ID = :idCamera", nativeQuery = true)
    List<Zona> zonaPorCamera(@Param("idCamera") Long idCamera);
}
