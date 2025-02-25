package com.webprojectcompany.servlet;

import com.webprojectcompany.db.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try (Connection conn = DatabaseConnection.getConnection()) {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM usuarios WHERE username=? AND password=?");
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();

                HttpSession session = request.getSession();
                
                if (rs.next()) {
                    session.setAttribute("username", username);
                    response.sendRedirect("sucesso.jsp");
                } else {
                    session.setAttribute("message", "Usuário ou senha inválido.");
                    response.sendRedirect("login.jsp?error=true");
                }
            } catch (Exception e) {
                out.println("<html><body>");
                out.println("<h3>Erro ao conectar ao banco de dados:</h3>");
                out.println("<pre>" + e.getMessage() + "</pre>");
                e.printStackTrace(out);
                out.println("</body></html>");
            }
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
        return "Short description";
    }
}

