package com.fiap.ec.backend_orizon_spi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "zona_epi")
public class ZonaEpi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zona_id")
    @JsonIgnore
    private Zona zona;
    @ManyToOne
    @JoinColumn(name = "epi_id")
    private Epi epi;

    public ZonaEpi() {
    }

    public ZonaEpi(Long id, Zona zona, Epi epi) {
        this.id = id;
        this.zona = zona;
        this.epi = epi;
    }

    public Long getId() {
        return id;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Epi getEpi() {
        return epi;
    }

    public void setEpi(Epi epi) {
        this.epi = epi;
    }
}
