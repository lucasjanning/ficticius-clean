package com.lucasJanning.ficticiusclean.model;

public class CombustivelModel {
     private String nome;
     private String marca;
     private String modelo;
     private int ano;
     private Double combustivelGasto;
     private Double valorGasto;

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

     public int getAno() {
          return ano;
     }

     public void setAno(int ano) {
          this.ano = ano;
     }

     public Double getCombustivelGasto() {
          return combustivelGasto;
     }

     public void setCombustivelGasto(Double combustivelGasto) {
          this.combustivelGasto = combustivelGasto;
     }

     public Double getValorGasto() {
          return valorGasto;
     }

     public void setValorGasto(Double valorGasto) {
          this.valorGasto = valorGasto;
     }
}
