package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Camera;
import com.fiap.ec.backend_orizon_spi.repository.CameraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {
    private final CameraRepository cameraRepository;

    public CameraService(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    public Camera salvar(Camera camera){
        return cameraRepository.save(camera);
    }

    public List<Camera> listar(){
        return cameraRepository.findAll();
    }

    public Camera buscarPorId(Long id){
        return cameraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Camera não encontrada"));
    }

    public Camera atualizar(Long id, Camera cameraAtualizado){
        Camera cameraExistente = buscarPorId(id);
        cameraExistente.setNome(cameraAtualizado.getNome());
        cameraExistente.setDescricao(cameraAtualizado.getDescricao());
        cameraExistente.setAcesso(cameraAtualizado.getAcesso());

        return cameraRepository.save(cameraExistente);
    }

    public void deletar(Long id){
        Camera camera = buscarPorId(id);
        cameraRepository.delete(camera);
    }


}
