/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifma.sistemadetransportadora.dao;

/**
 *
 * @author Carvalho
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ifma.sistemadetransportadora.modelo.Cliente;
import java.sql.Statement;

public class ClienteDAO {

    private final Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public Cliente salva(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (nome, endereco, telefone)VALUES(?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEndereco());
            statement.setString(3, cliente.getTelefone());
            statement.execute();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                int id = keys.getInt(1);
                cliente.setCodigo_cliente(id);
            }
        }
        return cliente;
    }

    public Cliente buscaCliente(Integer id) {

        try {
            PreparedStatement ps = this.connection
                    .prepareStatement("SELECT codigo_cliente, nome, endereco, telefone "
                            + "FROM Cliente "
                            + "WHERE codigo_cliente = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Cliente cliente = new Cliente();
            cliente.setCodigo_cliente(rs.getInt("codigo_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setTelefone(rs.getString("telefone"));
            ps.close();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int retornaCodigoCliente(Cliente cliente){
        try {
            PreparedStatement ps = this.connection
                    .prepareStatement("SELECT codigo_cliente "
                            + "FROM Cliente ");
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            cliente.setCodigo_cliente(rs.getInt("codigo_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setTelefone(rs.getString("telefone"));
            ps.close();
            return cliente.getCodigo_cliente();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> lista() {

        try {
            PreparedStatement ps = this.connection
                    .prepareStatement("SELECT * FROM Cliente");
            ResultSet rs = ps.executeQuery();

            List<Cliente> lista = new ArrayList<Cliente>();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                lista.add(cliente);
            }

            ps.close();
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
