/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifma.sistemadetransportadora.dao;

import com.ifma.sistemadetransportadora.modelo.Cidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carvalho
 */
public class CidadeDAO {
    private final Connection connection;

    public CidadeDAO(Connection connection) {
        this.connection = connection;
    }

    public Cidade salva(Cidade cidade) throws SQLException {
        String sql = "INSERT INTO Cidade (nome, UF, taxa)VALUES(?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, cidade.getNome());
            statement.setString(2, cidade.getUF());
            statement.setFloat(3, cidade.getTaxa());
            statement.execute();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                int id = keys.getInt(1);
                cidade.setCodigo_cidade(id);
            }
        }
        return cidade;
    }

    public Cidade buscaCidade(Integer id) {

        try {
            PreparedStatement ps = this.connection
                    .prepareStatement("SELECT codigo_cidade, nome, UF, taxa "
                            + "FROM Cidade "
                            + "WHERE codigo_cidade = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Cidade cidade = new Cidade();
            cidade.setCodigo_cidade(rs.getInt("codigo_cidade"));
            cidade.setNome(rs.getString("nome"));
            cidade.setUF(rs.getString("UF"));
            cidade.setTaxa(rs.getFloat("taxa"));
            ps.close();
            return cidade;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Cidade buscaCidade(String nome) {

        try {
            PreparedStatement ps = this.connection
                    .prepareStatement("SELECT nome, UF, taxa "
                            + "FROM Cidade "
                            + "WHERE nome = ?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Cidade cidade = new Cidade();
            cidade.setNome(rs.getString("nome"));
            cidade.setUF(rs.getString("UF"));
            cidade.setTaxa(rs.getFloat("taxa"));
            ps.close();
            return cidade;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void atualiza(Cidade cidade) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("UPDATE Cidade SET nome=?, UF=?, taxa=? "
							           + "WHERE codigo_cidade=?");
			ps.setInt(1, cidade.getCodigo_cidade());
			ps.setString(2, cidade.getNome());
			ps.setString(3, cidade.getUF());
			ps.setFloat(4, cidade.getTaxa());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

    public List<Cidade> lista() {

        try {
            PreparedStatement ps = this.connection
                    .prepareStatement("SELECT * FROM Cidade");
            ResultSet rs = ps.executeQuery();

            List<Cidade> lista = new ArrayList<Cidade>();

            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setNome(rs.getString("nome"));
                cidade.setUF(rs.getString("UF"));
                cidade.setTaxa(rs.getFloat("taxa"));
                lista.add(cidade);
            }

            ps.close();
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
