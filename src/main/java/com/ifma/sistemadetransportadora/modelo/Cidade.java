/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifma.sistemadetransportadora.modelo;

/**
 *
 * @author Carvalho
 */
public class Cidade {
    private Integer codigo_cidade;
    private String nome;
    private String UF;
    private float taxa;

    public Cidade() {
    }

    public Cidade(Integer codigo_cidade, String nome, String UF, float taxa) {
        this.codigo_cidade = codigo_cidade;
        this.nome = nome;
        this.UF = UF;
        this.taxa = taxa;
    }
    
    public Cidade(String nome, String UF, float taxa) {
        this.nome = nome;
        this.UF = UF;
        this.taxa = taxa;
    }

    public Integer getCodigo_cidade() {
        return codigo_cidade;
    }

    public void setCodigo_cidade(Integer codigo_cidade) {
        this.codigo_cidade = codigo_cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public float getTaxa() {
        return taxa;
    }

    public void setTaxa(float taxa) {
        this.taxa = taxa;
    }

    @Override
    public String toString() {
        return "Cidade{" + "codigo_cidade=" + codigo_cidade + ", nome=" + nome + ", UF=" + UF + ", taxa=" + taxa + '}';
    }

}
