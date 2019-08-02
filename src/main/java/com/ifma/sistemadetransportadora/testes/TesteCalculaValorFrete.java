/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifma.sistemadetransportadora.testes;

import com.ifma.sistemadetransportadora.dao.CidadeDAO;
import com.ifma.sistemadetransportadora.dao.ClienteDAO;
import com.ifma.sistemadetransportadora.dao.FreteDAO;
import com.ifma.sistemadetransportadora.infra.Database;
import com.ifma.sistemadetransportadora.modelo.Cidade;
import com.ifma.sistemadetransportadora.modelo.Cliente;
import com.ifma.sistemadetransportadora.modelo.Frete;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Carvalho
 */
public class TesteCalculaValorFrete {
    public static void main(String[] args) throws SQLException {

        try (Connection connection = Database.getConnection()) {
            
            CidadeDAO cidadeDAO = new CidadeDAO(connection);
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            FreteDAO freteDAO = new FreteDAO(connection);
            
            Cidade cidade = cidadeDAO.buscaCidade(2);
            System.out.println(cidade.toString());
            Cliente cliente = clienteDAO.buscaCliente(1);
            System.out.println(cliente.toString());
            
            Frete frete = new Frete(cidade, cliente, "Trigo", 500);
            Frete salva = freteDAO.salva(frete);
            
            Frete freteCalculado = freteDAO.calculaValorDoFrete(salva);
            System.out.println("Valor do frete: " + freteCalculado.getValor());
            connection.close();
        }
    }
}
