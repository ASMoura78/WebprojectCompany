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

@WebServlet("/cadastrar_multa")
public class CadastrarMultaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeMotoristaInfrator = request.getParameter("nome_motorista_infrator");
        String veiculo = request.getParameter("veiculo");
        String placa = request.getParameter("placa");
        String dataHoraInfracao = request.getParameter("data_hora_infracao");
        String localInfracao = request.getParameter("local_infracao");
        String descricaoInfracao = request.getParameter("descricao_infracao");

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO multa (nome_motorista_infrator, veiculo, placa, data_hora_infracao, local_infracao, descricao_infracao) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeMotoristaInfrator);
                statement.setString(2, veiculo);
                statement.setString(3, placa);
                statement.setString(4, dataHoraInfracao);
                statement.setString(5, localInfracao);
                statement.setString(6, descricaoInfracao);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao inserir dados no banco de dados", e);
        }

        response.sendRedirect("sucesso.html");
    }
}

