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

@WebServlet("/cadastrar_atividade")
public class CadastrarAtividadeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeMotorista = request.getParameter("nome_motorista");
        String destino = request.getParameter("destino");
        String dataHorarioSaida = request.getParameter("data_horario_saida");
        String dataHorarioChegada = request.getParameter("data_horario_chegada");
        int kmSaida = Integer.parseInt(request.getParameter("km_saida"));
        int kmChegada = Integer.parseInt(request.getParameter("km_chegada"));
        String observacoes = request.getParameter("observacoes");

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO atividade (nome_motorista, destino, data_horario_saida, data_horario_chegada, km_saida, km_chegada, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeMotorista);
                statement.setString(2, destino);
                statement.setString(3, dataHorarioSaida);
                statement.setString(4, dataHorarioChegada);
                statement.setInt(5, kmSaida);
                statement.setInt(6, kmChegada);
                statement.setString(7, observacoes);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao inserir dados no banco de dados", e);
        }

        response.sendRedirect("sucesso.html");
    }
}



