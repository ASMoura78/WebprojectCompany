package com.WebProjectCompany.servlet;

import com.webprojectcompany.db.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarAtividadeServlet", urlPatterns = {"/CadastrarAtividadeServlet"})
public class CadastrarAtividadeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String motorista = request.getParameter("motorista");
        String destino = request.getParameter("destino");
        String dataSaida = request.getParameter("dataSaida");
        String dataChegada = request.getParameter("dataChegada");
        String quilometragemSaida = request.getParameter("quilometragemSaida");
        String quilometragemChegada = request.getParameter("quilometragemChegada");
        String observacoes = request.getParameter("observacoes");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO cadastroAtividade (motorista, destino, data_saida, data_chegada, quilometragem_saida, quilometragem_chegada, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, motorista);
            pst.setString(2, destino);
            pst.setString(3, dataSaida);
            pst.setString(4, dataChegada);
            pst.setString(5, quilometragemSaida);
            pst.setString(6, quilometragemChegada);
            pst.setString(7, observacoes);
            pst.executeUpdate();

            request.setAttribute("message", "Atividade cadastrada com sucesso!");

            List<String> motoristas = new ArrayList<>();
            sql = "SELECT nome FROM motoristas";
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                motoristas.add(rs.getString("nome"));
            }
            request.setAttribute("motoristas", motoristas);

            request.getRequestDispatcher("Cadastro_Atividade.jsp").forward(request, response);
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
        return "Servlet para cadastrar atividades";
    }
}




