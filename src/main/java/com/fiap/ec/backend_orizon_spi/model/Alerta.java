package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alerta")
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    @Column(nullable = false)
    private String mensagem;
    private String status;
    private String data_hora;
    @ManyToOne
    @JoinColumn(name = "ocorrencia_id")
    private Ocorrencia ocorrencia;

    public Alerta() {
    }

    public Alerta(String mensagem, String status, String data_hora, Ocorrencia ocorrencia) {
        this.mensagem = mensagem;
        this.status = status;
        this.data_hora = data_hora;
        this.ocorrencia = ocorrencia;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData_hora() {
        return data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }
}
