package com.WebProjectCompany.servlet;

import com.webprojectcompany.db.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarOficinaServlet", urlPatterns = {"/CadastrarOficinaServlet"})
public class CadastrarOficinaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String veiculo = request.getParameter("veiculo");
        String placa = request.getParameter("placa");
        String dataEntrada = request.getParameter("dataEntrada");
        String dataSaida = request.getParameter("dataSaida");
        String servicoRealizado = request.getParameter("servicoRealizado");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO cadastroOficina (veiculo, placa, data_entrada, data_saida, servico_realizado) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, veiculo);
            pst.setString(2, placa);
            pst.setString(3, dataEntrada);
            pst.setString(4, dataSaida);
            pst.setString(5, servicoRealizado);
            pst.executeUpdate();

            // Adicionar mensagem de sucesso como atributo de requisição
            request.setAttribute("message", "Serviço de oficina cadastrado com sucesso!");

            // Redirecionar de volta para a página de cadastro de oficina
            request.getRequestDispatcher("Cadastro_oficina.jsp").forward(request, response);
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
        return "Servlet para cadastrar serviços de oficina";
    }
}


