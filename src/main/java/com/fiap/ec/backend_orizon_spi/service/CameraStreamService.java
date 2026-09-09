package com.fiap.ec.backend_orizon_spi.service;

import com.fiap.ec.backend_orizon_spi.model.Camera;
import com.fiap.ec.backend_orizon_spi.repository.CameraRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CameraStreamService {

    private final CameraRepository cameraRepository;
    private final Map<String, Process> activeProcesses = new ConcurrentHashMap<>();
    private final String baseDir = System.getProperty("java.io.tmpdir") + "/orizon-hls/";

    public CameraStreamService(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @PostConstruct
    public void initAllStreams() {
        File dir = new File(baseDir);
        if (!dir.exists()) dir.mkdirs();

        // Busca direto do banco H2
        List<Camera> cameras = cameraRepository.findAll();
        for (Camera cam : cameras) {
            startStream(String.valueOf(cam.getId()), cam.getAcesso());
        }
    }

    public void startStream(String cameraId, String rtspUrl) {
        if (activeProcesses.containsKey(cameraId) && activeProcesses.get(cameraId).isAlive()) {
            return;
        }

        File camDir = new File(baseDir + cameraId);
        if (!camDir.exists()) camDir.mkdirs();

        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",
                "-rtsp_transport", "tcp",
                "-i", rtspUrl,
                "-c:v", "libx264",
                "-preset", "ultrafast",
                "-tune", "zerolatency",
                "-hls_time", "2",
                "-hls_list_size", "3",
                "-hls_flags", "delete_segments",
                camDir.getAbsolutePath() + "/stream.m3u8"
        );

        try {
            pb.redirectErrorStream(true);
            Process process = pb.start();
            activeProcesses.put(cameraId, process);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método útil caso você cadastre uma câmera nova via API e queira iniciar a stream dela na hora
    public void registerAndStartNewCamera(Camera camera) {
        cameraRepository.save(camera);
        startStream(String.valueOf(camera.getId()), camera.getAcesso());
    }

    @PreDestroy
    public void stopAllStreams() {
        activeProcesses.forEach((id, process) -> {
            if (process.isAlive()) {
                process.destroy();
            }
        });
        activeProcesses.clear();
    }
}