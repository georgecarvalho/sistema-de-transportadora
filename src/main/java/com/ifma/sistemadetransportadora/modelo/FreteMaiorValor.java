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
public class FreteMaiorValor {
    private String descricao;
    private double valor;
    private int codigo_frete;

    public FreteMaiorValor() {
    }
    
    public FreteMaiorValor(String descricao, double valor, int codigo_frete) {
        this.descricao = descricao;
        this.valor = valor;
        this.codigo_frete = codigo_frete;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCodigo_frete() {
        return codigo_frete;
    }

    public void setCodigo_frete(int codigo_frete) {
        this.codigo_frete = codigo_frete;
    }

    @Override
    public String toString() {
        return "FreteMaiorValor{" + "descricao=" + descricao + ", valor=" + valor + ", codigo_frete=" + codigo_frete + '}';
    }
    
}
