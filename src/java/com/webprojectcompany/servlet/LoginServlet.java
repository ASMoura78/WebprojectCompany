package com.webprojectcompany.servlet;

import com.webprojectcompany.db.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeUsuario = request.getParameter("nome_usuario");
        String senha = request.getParameter("senha");

        // Adicionando log para verificar os valores recebidos
        System.out.println("Tentativa de login para o usuário: " + nomeUsuario);
        System.out.println("Senha fornecida: " + senha);

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT senha_hash FROM usuarios WHERE nome_usuario = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeUsuario);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String senhaHash = resultSet.getString("senha_hash");

                        // Adicionando log para verificar o hash da senha recuperado
                        System.out.println("Hash da Senha Armazenada: " + senhaHash);

                        if (BCrypt.checkpw(senha, senhaHash)) {
                            // Autenticação bem-sucedida
                            System.out.println("Autenticação bem-sucedida.");
                            // Redirecionar para menu.html após login bem-sucedido
                            response.sendRedirect(request.getContextPath() + "/menu.html");
                        } else {
                            // Senha incorreta
                            System.out.println("Senha incorreta.");
                            response.sendRedirect(request.getContextPath() + "/login_erro.html");
                        }
                    } else {
                        // Usuário não encontrado
                        System.out.println("Usuário não encontrado.");
                        response.sendRedirect(request.getContextPath() + "/login_erro.html");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }
}

