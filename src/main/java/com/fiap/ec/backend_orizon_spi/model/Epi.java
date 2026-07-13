package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "epi")
public class Epi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_epi;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String descricao;

    public Epi() {
    }

    public Epi(String name, String descricao) {
        this.name = name;
        this.descricao = descricao;
    }

    public Long getId_epi() {
        return id_epi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
