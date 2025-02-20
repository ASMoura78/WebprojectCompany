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

@WebServlet("/cadastrar_motorista")
public class CadastrarMotoristaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeMotorista = request.getParameter("nome_motorista");
        String cpf = request.getParameter("cpf");
        String numeroCnh = request.getParameter("numero_cnh");
        String categoriaCnh = request.getParameter("categoria_cnh");
        String cursosAdicionais = request.getParameter("cursos_adicionais");

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO motorista (nome_motorista, cpf, numero_cnh, categoria_cnh, cursos_adicionais) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeMotorista);
                statement.setString(2, cpf);
                statement.setString(3, numeroCnh);
                statement.setString(4, categoriaCnh);
                statement.setString(5, cursosAdicionais);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao inserir dados no banco de dados", e);
        }

        response.sendRedirect(request.getContextPath() + "/sucesso.html");
    }
}


