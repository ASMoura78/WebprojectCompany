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

@WebServlet(name = "CadastrarMultaServlet", urlPatterns = {"/CadastrarMultaServlet"})
public class CadastrarMultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nomeMotoristaInfrator = request.getParameter("nome_motorista_infrator");
        String veiculo = request.getParameter("veiculo");
        String placa = request.getParameter("placa");
        String dataHoraInfracao = request.getParameter("data_hora_infracao");
        String localInfracao = request.getParameter("local_infracao");
        String descricaoInfracao = request.getParameter("descricao_infracao");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO cadastroMultas (nome_motorista_infrator, veiculo, placa, data_hora_infracao, local_infracao, descricao_infracao) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nomeMotoristaInfrator);
            pst.setString(2, veiculo);
            pst.setString(3, placa);
            pst.setString(4, dataHoraInfracao);
            pst.setString(5, localInfracao);
            pst.setString(6, descricaoInfracao);
            pst.executeUpdate();

            // Adicionar mensagem de sucesso como atributo de requisição
            request.setAttribute("message", "Multa cadastrada com sucesso!");

            // Redirecionar de volta para a página de cadastro de multas
            request.getRequestDispatcher("Cadastro_Multas.jsp").forward(request, response);
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
        return "Servlet para cadastrar multas";
    }
}


