package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ocorrencia_epi")
public class OcorrenciaEpi {
    @ManyToOne
    @JoinColumn(name = "ocorrencia_id")
    private Ocorrencia ocorrencia;
    @ManyToOne
    @JoinColumn(name = "epi_id")
    private Epi epi;

    public OcorrenciaEpi() {
    }

    public OcorrenciaEpi(Ocorrencia ocorrencia, Epi epi) {
        this.ocorrencia = ocorrencia;
        this.epi = epi;
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
