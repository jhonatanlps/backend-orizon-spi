package com.fiap.ec.backend_orizon_spi.controller;

import com.fiap.ec.backend_orizon_spi.model.Camera;
import com.fiap.ec.backend_orizon_spi.service.CameraService;
import com.fiap.ec.backend_orizon_spi.service.CameraStreamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cameras")
@CrossOrigin
public class CameraController {
    private CameraService cameraService;
    private CameraStreamService cameraStreamService;

    public CameraController(CameraService cameraService, CameraStreamService cameraStreamService) {
        this.cameraService = cameraService;
        this.cameraStreamService = cameraStreamService;
    }

    @PostMapping
    public Camera criar(@RequestBody Camera camera){
        cameraStreamService.registerAndStartNewCamera(camera);
        return cameraService.salvar(camera);
    }

    @GetMapping
    public List<Camera> listar(){
        return cameraService.listar();
    }

    @GetMapping("/{id}")
    public Camera buscarPorId(@PathVariable Long id){
        return cameraService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Camera atualizar(@PathVariable Long id, @RequestBody Camera camera){
        return cameraService.atualizar(id, camera);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        cameraService.deletar(id);
    }
}
