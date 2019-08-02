/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifma.sistemadetransportadora.testes;

import com.ifma.sistemadetransportadora.dao.CidadeDAO;
import com.ifma.sistemadetransportadora.infra.Database;
import com.ifma.sistemadetransportadora.modelo.Cidade;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Carvalho
 */
public class TesteCidade {
    public static void main(String[] args) throws SQLException {

        try (Connection connection = Database.getConnection()) {
            
            CidadeDAO cidadeDAO = new CidadeDAO(connection);
            /*
            Cidade cidade = new Cidade("São Luís", "MA", (float) 0.2);
            Cidade salva = cidadeDAO.salva(cidade);
            System.out.println("salva " + cidade.toString());
            */
            Cidade busca = cidadeDAO.buscaCidade(1);
            System.out.println("buscaCod " + busca.toString());
            busca = cidadeDAO.buscaCidade("Paço do Lumiar");
            System.out.println("buscaNome " + busca.toString());
            
            busca.setCodigo_cidade(1);
            busca.setNome("Paço do Lumiar");
            busca.setUF("MA");
            busca.setTaxa((float) 0.35);
            cidadeDAO.atualiza(busca);
            busca = cidadeDAO.buscaCidade(1);
            System.out.println("atualiza " + busca.toString());
            
            connection.close();
        }
    }
}
