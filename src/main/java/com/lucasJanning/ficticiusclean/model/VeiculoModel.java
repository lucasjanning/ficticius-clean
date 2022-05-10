package com.lucasJanning.ficticiusclean.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class VeiculoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String marca;
    @Column
    private String modelo;
    @Column
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFabricacao;
    @Column
    private Double consumoKmCidade;
    @Column
    private Double consumoKmRodovia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Double getConsumoKmCidade() {
        return consumoKmCidade;
    }

    public void setConsumoKmCidade(Double consumoKmCidade) {
        this.consumoKmCidade = consumoKmCidade;
    }

    public Double getConsumoKmRodovia() {
        return consumoKmRodovia;
    }

    public void setConsumoKmRodovia(Double consumoKmRodovia) {
        this.consumoKmRodovia = consumoKmRodovia;
    }
}
