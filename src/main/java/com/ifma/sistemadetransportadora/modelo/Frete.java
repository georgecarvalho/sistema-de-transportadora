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
public class Frete {
    private int codigo_frete;
    private Cidade cidade;
    private Cliente cliente;
    private String descricao;
    private double peso;
    private double valor;

    public Frete() {
    }
    
    public Frete(int codigo_frete, String descricao, double valor) {
		super();
		this.codigo_frete = codigo_frete;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Frete(Cidade cidade, String descricao, double peso, double valor) {
        this.cidade = cidade;
        this.descricao = descricao;
        this.peso = peso;
        this.valor = valor;
    }

    public Frete(Cidade cidade, Cliente cliente, String descricao, double peso) {
        this.cidade = cidade;
        this.cliente = cliente;
        this.descricao = descricao;
        this.peso = peso;
    }

    public Frete(Cidade cidade, Cliente cliente, String descricao, double peso, double valor) {
        this.cidade = cidade;
        this.cliente = cliente;
        this.descricao = descricao;
        this.peso = peso;
        this.valor = valor;
    }

    public int getCodigo_frete() {
        return codigo_frete;
    }

    public void setCodigo_frete(int codigo_frete) {
        this.codigo_frete = codigo_frete;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Frete{" + "codigo_cidade=" + cidade.getCodigo_cidade() + ", codigo_cliente=" + cliente.getCodigo_cliente() + ", descricao=" + descricao + ", peso=" + peso + '}';
    }
    
}
