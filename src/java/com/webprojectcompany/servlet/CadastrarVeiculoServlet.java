package com.WebProjectCompany.servlet;

import com.webprojectcompany.db.DatabaseConnection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet(name = "CadastrarVeiculoServlet", urlPatterns = {"/CadastrarVeiculoServlet"})
@MultipartConfig
public class CadastrarVeiculoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String modelo = request.getParameter("modelo");
        String placa = request.getParameter("placa");
        String cor = request.getParameter("cor");
        String observacoes = request.getParameter("observacoes");
        Part imagemPart = request.getPart("imagem");
        InputStream imagemInputStream = null;

        if (imagemPart != null && imagemPart.getSize() > 0) {
            imagemInputStream = imagemPart.getInputStream();
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO cadastroveiculo (modelo, placa, cor, imagem, observacoes) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, modelo);
            pst.setString(2, placa);
            pst.setString(3, cor);
            if (imagemInputStream != null) {
                pst.setBlob(4, imagemInputStream);
            } else {
                pst.setNull(4, java.sql.Types.BLOB);
            }
            pst.setString(5, observacoes);
            pst.executeUpdate();

            HttpSession session = request.getSession();
            session.setAttribute("message", "Veículo cadastrado com sucesso!");

            response.sendRedirect("Cadastro_veiculos.jsp"); // Redireciona de volta para a página de cadastro
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para cadastrar veículos";
    }
}


