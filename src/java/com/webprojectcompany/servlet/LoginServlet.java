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
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeUsuario = request.getParameter("nome_usuario");
        String senha = request.getParameter("senha");

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT senha_hash FROM usuarios WHERE nome_usuario = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeUsuario);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String senhaHash = resultSet.getString("senha_hash");
                        if (BCrypt.checkpw(senha, senhaHash)) {
                            // Autenticação bem-sucedida
                            response.sendRedirect("bem_vindo.html");
                        } else {
                            // Senha incorreta
                            response.sendRedirect("login_erro.html");
                        }
                    } else {
                        // Usuário não encontrado
                        response.sendRedirect("login_erro.html");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }
}
