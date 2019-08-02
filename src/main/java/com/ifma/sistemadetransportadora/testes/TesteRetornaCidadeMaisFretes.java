package com.ifma.sistemadetransportadora.testes;

import com.ifma.sistemadetransportadora.dao.FreteDAO;
import com.ifma.sistemadetransportadora.infra.Database;
import com.ifma.sistemadetransportadora.modelo.CidadeMaisFretes;
import com.ifma.sistemadetransportadora.modelo.Frete;
import java.sql.Connection;
import java.sql.SQLException;

public class TesteRetornaCidadeMaisFretes {
    public static void main(String[] args) throws SQLException {

        try (Connection connection = Database.getConnection()) {
            
            FreteDAO freteDAO = new FreteDAO(connection);
            
            Frete busca = freteDAO.busca(1);
            CidadeMaisFretes cidadeMaisFretes = freteDAO.retornaCidadeMaisFretes(busca);
            System.out.println("Cidade com mais fretes: " + cidadeMaisFretes.toString());
            
            connection.close();
        }
    }
}
