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
public class TesteFrete {
    public static void main(String[] args) throws SQLException {

        try (Connection connection = Database.getConnection()) {
            
            CidadeDAO cidadeDAO = new CidadeDAO(connection);
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            FreteDAO freteDAO = new FreteDAO(connection);
            
            Cidade cidade = cidadeDAO.buscaCidade(2);
            System.out.println(cidade.toString());
            Cliente cliente = clienteDAO.buscaCliente(1);
            System.out.println(cliente.toString());
            Frete busca = freteDAO.busca(1);
            System.out.println(busca.toString());
            connection.close();
        }
    }
}
