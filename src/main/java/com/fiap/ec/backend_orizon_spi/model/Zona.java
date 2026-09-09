package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "zona")
public class Zona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_zona;
    @Column(nullable = false)
    private String nome;
    @ElementCollection
    private List<Ponto> poligonos;
    @ManyToOne
    @JoinColumn(name = "camera_id")
    private Camera camera;
    @ManyToOne
    @JoinColumn(name = "maquina_id")
    private Maquina maquina;
    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL)
    private List<ZonaEpi> epi;

    public Zona() {
    }

    public Zona(String nome, List<Ponto> poligonos, Camera camera, Maquina maquina, List<ZonaEpi> epi) {
        this.nome = nome;
        this.poligonos = poligonos;
        this.camera = camera;
        this.maquina = maquina;
        this.epi = epi;
    }

    public Long getId_zona() {
        return id_zona;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public List<Ponto> getPoligonos() {
        return poligonos;
    }

    public void setPoligonos(List<Ponto> poligonos) {
        this.poligonos = poligonos;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public List<ZonaEpi> getEpi() {
        return epi;
    }

    public void setEpi(List<ZonaEpi> epi) {
        this.epi = epi;
    }
}
