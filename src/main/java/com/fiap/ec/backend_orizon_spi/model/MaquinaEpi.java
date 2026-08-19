package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "maquina_epi")
public class MaquinaEpi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "maquina_id")
    private Maquina maquina;
    @ManyToOne
    @JoinColumn(name = "epi_id")
    private Epi epi;

    public MaquinaEpi() {
    }

    public MaquinaEpi(Long id, Maquina maquina, Epi epi) {
        this.id = id;
        this.maquina = maquina;
        this.epi = epi;
    }

    public Long getId() {
        return id;
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
