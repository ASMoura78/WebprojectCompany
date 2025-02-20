package com.webprojectcompany.servlet;

import com.webprojectcompany.db.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/cadastrar_oficina")
public class CadastrarOficinaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String veiculo = request.getParameter("veiculo");
        String placa = request.getParameter("placa");
        String dataEntrada = request.getParameter("data_entrada");
        String dataSaida = request.getParameter("data_saida");
        String servicoRealizado = request.getParameter("servico_realizado");

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO oficina (veiculo, placa, data_entrada, data_saida, servico_realizado) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, veiculo);
                statement.setString(2, placa);
                statement.setString(3, dataEntrada);
                statement.setString(4, dataSaida);
                statement.setString(5, servicoRealizado);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao inserir dados no banco de dados", e);
        }

        response.sendRedirect("sucesso.html");
    }
}

