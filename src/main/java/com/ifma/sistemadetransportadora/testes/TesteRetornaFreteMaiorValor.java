package com.ifma.sistemadetransportadora.testes;

import java.sql.Connection;
import java.sql.SQLException;

import com.ifma.sistemadetransportadora.dao.FreteDAO;
import com.ifma.sistemadetransportadora.infra.Database;
import com.ifma.sistemadetransportadora.modelo.Frete;
import com.ifma.sistemadetransportadora.modelo.FreteMaiorValor;

public class TesteRetornaFreteMaiorValor {
	public static void main(String[] args) throws SQLException {

        try (Connection connection = Database.getConnection()) {

            FreteDAO freteDAO = new FreteDAO(connection);
            Frete busca = freteDAO.busca(1);
            
            FreteMaiorValor freteMaiorValor = freteDAO.retornaFreteMaiorValor(busca);
            System.out.println("Frete de maior valor: " + freteMaiorValor.toString());
            connection.close();
        }
    }
}
