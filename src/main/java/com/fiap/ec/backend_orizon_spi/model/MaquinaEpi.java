package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "maquina_epi")
public class MaquinaEpi {
    @ManyToOne
    @JoinColumn(name = "maquina_id")
    private Maquina maquina;
    @ManyToOne
    @JoinColumn(name = "epi_id")
    private Epi epi;

    public MaquinaEpi() {
    }

    public MaquinaEpi(Maquina maquina, Epi epi) {
        this.maquina = maquina;
        this.epi = epi;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Epi getEpi() {
        return epi;
    }

    public void setEpi(Epi epi) {
        this.epi = epi;
    }
}
