package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "maquina")
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_maquina;
    @Column(nullable = false)
    private String nome;
    private String setor;
    @Column(nullable = true)
    private String descricao;

    public Maquina() {
    }

    public Maquina(String nome, String setor, String descricao) {
        this.nome = nome;
        this.setor = setor;
        this.descricao = descricao;
    }

    public Long getId_maquina() {
        return id_maquina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
