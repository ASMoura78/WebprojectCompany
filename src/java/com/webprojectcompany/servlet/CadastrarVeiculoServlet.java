package com.webprojectcompany.servlet;

import com.webprojectcompany.db.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/cadastrar_veiculo")
@MultipartConfig
public class CadastrarVeiculoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String modelo = request.getParameter("modelo");
        String placa = request.getParameter("placa");
        String cor = request.getParameter("cor");
        String observacoes = request.getParameter("observacoes");
        Part filePart = request.getPart("imagem");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        File file = new File(uploadPath, fileName);
        filePart.write(file.getAbsolutePath());

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO veiculo (modelo, placa, cor, imagem, observacoes) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, modelo);
                statement.setString(2, placa);
                statement.setString(3, cor);
                statement.setString(4, fileName);
                statement.setString(5, observacoes);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao inserir dados no banco de dados", e);
        }

        response.sendRedirect(request.getContextPath() + "/sucesso.html");
    }
}


