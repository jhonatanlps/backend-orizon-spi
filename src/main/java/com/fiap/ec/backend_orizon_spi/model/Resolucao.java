package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Resolucao {
    private Long largura;
    private Long altura;

    public Resolucao() {

    }

    public Resolucao(Long largura, Long altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public Long getLargura() {
        return largura;
    }

    public void setLargura(Long largura) {
        this.largura = largura;
    }

    public Long getAltura() {
        return altura;
    }

    public void setAltura(Long altura) {
        this.altura = altura;
    }
}
