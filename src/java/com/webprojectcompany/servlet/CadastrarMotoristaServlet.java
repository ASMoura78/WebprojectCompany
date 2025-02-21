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

        // Adicionando logs para verificar os valores recebidos
        System.out.println("Nome do Motorista: " + nomeMotorista);
        System.out.println("CPF: " + cpf);
        System.out.println("Número CNH: " + numeroCnh);
        System.out.println("Categoria CNH: " + categoriaCnh);
        System.out.println("Cursos Adicionais: " + cursosAdicionais);

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Conexão com o banco de dados estabelecida.");
            } else {
                System.out.println("Erro ao estabelecer conexão com o banco de dados.");
                throw new ServletException("Erro ao estabelecer conexão com o banco de dados.");
            }

            String sql = "INSERT INTO motorista (nome_motorista, cpf, numero_cnh, categoria_cnh, cursos_adicionais) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeMotorista);
                statement.setString(2, cpf);
                statement.setString(3, numeroCnh);
                statement.setString(4, categoriaCnh);
                statement.setString(5, cursosAdicionais);
                statement.executeUpdate();
                System.out.println("Dados inseridos com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao executar a instrução SQL: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao inserir dados no banco de dados", e);
        }

        response.sendRedirect(request.getContextPath() + "/sucesso.html");
    }
}


