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

@WebServlet("/cadastrar_usuario")
public class CadastrarUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeUsuario = request.getParameter("nome_usuario");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO usuario (nome_usuario, senha, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeUsuario);
                statement.setString(2, senha);
                statement.setString(3, email);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao inserir dados no banco de dados", e);
        }

        response.sendRedirect(request.getContextPath() + "/sucesso.html");
    }
}
