package com.fiap.ec.backend_orizon_spi.repository;

import com.fiap.ec.backend_orizon_spi.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Long> {
}
