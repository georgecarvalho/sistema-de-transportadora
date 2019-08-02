/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifma.sistemadetransportadora.testes;

import com.ifma.sistemadetransportadora.dao.ClienteDAO;
import com.ifma.sistemadetransportadora.infra.Database;
import com.ifma.sistemadetransportadora.modelo.Cliente;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Carvalho
 */
public class TesteCliente {

    public static void main(String[] args) throws SQLException {

        try (Connection connection = Database.getConnection()) {

            ClienteDAO clienteDAO = new ClienteDAO(connection);
            //Cliente cliente = new Cliente("Cássia", "Alameda Paço do Lumiar", "98988888888");
            //Cliente salva = clienteDAO.salva(cliente);
            //System.out.println(cliente.toString());
            
            Cliente busca = clienteDAO.buscaCliente(2);
            System.out.println(busca.toString());
            
            connection.close();
        }
    }
}
