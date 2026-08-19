package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ocorrencia_epi")
public class OcorrenciaEpi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ocorrencia_id")
    private Ocorrencia ocorrencia;
    @ManyToOne
    @JoinColumn(name = "epi_id")
    private Epi epi;

    public OcorrenciaEpi() {
    }

    public OcorrenciaEpi(Long id, Ocorrencia ocorrencia, Epi epi) {
        this.id = id;
        this.ocorrencia = ocorrencia;
        this.epi = epi;
    }

    public Long getId() {
        return id;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Epi getEpi() {
        return epi;
    }

    public void setEpi(Epi epi) {
        this.epi = epi;
    }
}
