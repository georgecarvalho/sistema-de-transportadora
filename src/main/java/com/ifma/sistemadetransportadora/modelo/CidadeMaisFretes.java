/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifma.sistemadetransportadora.modelo;

/**
 *
 * @author WtiDev
 */
public class CidadeMaisFretes {
    private String nome;
    private int qtdDeFretes;

    public CidadeMaisFretes() {
    }
    
    public CidadeMaisFretes(String nome, int qtdDeFretes) {
        this.nome = nome;
        this.qtdDeFretes = qtdDeFretes;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdDeFretes() {
        return qtdDeFretes;
    }

    public void setQtdDeFretes(int qtdDeFretes) {
        this.qtdDeFretes = qtdDeFretes;
    }

    @Override
    public String toString() {
        return "CidadeMaisFretes{" + "nome=" + nome + ", qtdDeFretes=" + qtdDeFretes + '}';
    }
    
}
