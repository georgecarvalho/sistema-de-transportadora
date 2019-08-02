/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifma.sistemadetransportadora.dao;

import com.ifma.sistemadetransportadora.modelo.Cidade;
import com.ifma.sistemadetransportadora.modelo.CidadeMaisFretes;
import com.ifma.sistemadetransportadora.modelo.Cliente;
import com.ifma.sistemadetransportadora.modelo.Frete;
import com.ifma.sistemadetransportadora.modelo.FreteMaiorValor;
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
public class FreteDAO {

    private final Connection connection;

    public FreteDAO(Connection connection) {
        this.connection = connection;
    }

    public Frete salva(Frete frete) throws SQLException {
        String sql = "INSERT INTO Frete (codigo_cidade, codigo_cliente, descricao, peso) VALUES(?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, frete.getCidade().getCodigo_cidade());
            statement.setInt(2, frete.getCliente().getCodigo_cliente());
            statement.setString(3, frete.getDescricao());
            statement.setDouble(4, frete.getPeso());
            statement.execute();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                int id = keys.getInt(1);
                frete.setCodigo_frete(id);
            }
        }
        return frete;
    }

    public Frete busca(int codigo) throws SQLException {

        boolean encontrado = false;
        ArrayList<Frete> listaProcuraFrete = (ArrayList<Frete>) lista();

        for (Frete frete : listaProcuraFrete) {
            if (codigo == frete.getCodigo_frete()) {
                encontrado = true;
                return frete;
            }
        }
        if (!encontrado) {
            return null;
        }
        return null;
    }

    public List<Frete> lista() throws SQLException {

        String sql = "select * from Frete";

        try (PreparedStatement statment = connection.prepareStatement(sql)) {
            /*stmt.execute();
			ResultSet resultSet = stmt.getResultSet();*/

            CidadeDAO cidadeDAO = new CidadeDAO(connection);
            ClienteDAO clienteDAO = new ClienteDAO(connection);

            ResultSet resultSet = statment.executeQuery(sql);

            List<Frete> fretes = new ArrayList<>();

            while (resultSet.next()) {
                int codigo_frete = resultSet.getInt("codigo_frete");
                int codigo_cidade = resultSet.getInt("codigo_cidade");
                int codigo_cliente = resultSet.getInt("codigo_cliente");
                String descricao = resultSet.getString("descricao");
                float peso = resultSet.getFloat("peso");

                Cidade cidade = cidadeDAO.buscaCidade(codigo_cidade);
                Cliente cliente = clienteDAO.buscaCliente(codigo_cliente);

                if (cidade != null && cliente != null) {
                    Frete frete = new Frete(cidade, cliente, descricao, peso);
                    frete.setCodigo_frete(codigo_frete);
                    fretes.add(frete);
                } else {
                    System.out.println("Chaves nulas!");
                    return null;
                }
            }
            return fretes;
        }
    }

    public Frete calculaValorDoFrete(Frete frete) throws SQLException {

        String sql = "SELECT ft.codigo_frete, ft.codigo_cidade, ft.descricao, ft.peso, cd.nome as destino, round(ft.peso*10 + cd.taxa, 2) as valorCalculado FROM Cidade cd INNER JOIN Frete ft on cd.codigo_cidade = ft.codigo_cidade GROUP BY cd.codigo_cidade";

        try (PreparedStatement statment = connection.prepareStatement(sql)) {

            CidadeDAO cidadeDAO = new CidadeDAO(connection);
            ClienteDAO clienteDAO = new ClienteDAO(connection);

            // statment.setInt(1, codigo); 						
            ResultSet resultSet = statment.executeQuery(sql);

            while (resultSet.next()) {

                int codigo_cidade = resultSet.getInt("codigo_cidade");
                int codigo_cliente = resultSet.getInt("codigo_cidade");
                String descricao = resultSet.getString("descricao");
                double peso = resultSet.getFloat("peso");
                double valor = resultSet.getFloat("valorCalculado");
                int id = resultSet.getInt("codigo_frete");

                Cidade cidade = cidadeDAO.buscaCidade(codigo_cidade);
                Cliente cliente = clienteDAO.buscaCliente(codigo_cliente);

                if (cidade != null && cliente != null) {
                    frete = new Frete(cidade, cliente, descricao, peso, valor);
                    frete.setCodigo_frete(id);

                } else {
                    System.out.println("Chave nula!\n");
                    return null;
                }
            }
        }
        return frete;
    }

    public ArrayList<Frete> listaFretesDeClientes(Integer codigo) throws SQLException {

        ArrayList<Frete> fretes = new ArrayList<>();

        String sql = "SELECT ft.codigo_frete, cd.codigo_cidade, cl.codigo_cliente, ft.descricao, ft.peso, ft.valor FROM Cliente cl INNER JOIN Frete ft ON cl.codigo_cliente = ft.codigo_cliente INNER JOIN Cidade cd ON ft.codigo_cidade = cd.codigo_cidade WHERE cl.codigo_cliente = " + codigo;
        try (PreparedStatement statment = connection.prepareStatement(sql)) {

            CidadeDAO cidadeDAO = new CidadeDAO(connection);
            ClienteDAO clienteDAO = new ClienteDAO(connection);

            ResultSet resultSet = statment.executeQuery(sql);

            while (resultSet.next()) {

                int codigo_cidade = resultSet.getInt("codigo_cidade");
                int codigo_cliente = resultSet.getInt("codigo_cliente");
                String descricao = resultSet.getString("descricao");
                double peso = resultSet.getDouble("peso");
                double valor = resultSet.getDouble("valor");
                int id = resultSet.getInt("codigo_frete");

                Cidade cidade = cidadeDAO.buscaCidade(codigo_cidade);
                Cliente cliente = clienteDAO.buscaCliente(codigo_cliente);

                if (fretes != null && fretes != null) {
                    Frete frete = new Frete(cidade, cliente, descricao, peso, valor);
                    frete.setCodigo_frete(id);
                    fretes.add(frete);

                } else {
                    System.out.println("Chaves nulas!\n");
                    return null;
                }
            }
        }
        return fretes;
    }

    public FreteMaiorValor retornaFreteMaiorValor(Frete frete) throws SQLException {

        FreteMaiorValor freteMaiorValor = new FreteMaiorValor();

        String sql = "SELECT ft.codigo_frete , ft.descricao, ft.valor FROM Frete ft WHERE ft.valor = ( SELECT MAX(ft.valor) FROM Frete )";

        try (PreparedStatement statment = connection.prepareStatement(sql)) {

            ResultSet resultSet = statment.executeQuery(sql);

            while (resultSet.next()) {

                if (frete != null) {
                    freteMaiorValor.setDescricao(resultSet.getString("descricao"));
                    freteMaiorValor.setValor(resultSet.getDouble("valor"));
                    freteMaiorValor.setCodigo_frete(resultSet.getInt("codigo_frete"));

                } else {
                    System.out.println("Chaves nulas!\n");
                    return null;
                }
            }
        }
        return freteMaiorValor;
    }

    public CidadeMaisFretes retornaCidadeMaisFretes(Frete frete) throws SQLException {

        CidadeMaisFretes cidadeMaisFretes = new CidadeMaisFretes();

        String sql = "SELECT cd.nome, MAX(ft.codigo_cidade) AS QuantidadeFretes FROM Frete ft INNER JOIN Cidade cd ON cd.codigo_cidade = ft.codigo_cidade";

        try (PreparedStatement statment = connection.prepareStatement(sql)) {

            ResultSet resultSet = statment.executeQuery(sql);

            while (resultSet.next()) {

                if (frete != null) {
                    cidadeMaisFretes.setNome(resultSet.getString("nome"));
                    cidadeMaisFretes.setQtdDeFretes(resultSet.getInt("QuantidadeFretes"));

                } else {
                    System.out.println("Chaves nulas!\n");
                    return null;
                }
            }
        }
        return cidadeMaisFretes;
    }

}
