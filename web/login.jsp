<%-- 
    Document   : login
    Created on : 24 de fev. de 2025, 19:21:19
    Author     : Alan Guedes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tela de Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="login-container">
            <h2>Login</h2>
            <form action="LoginServlet" method="post">
                <label for="username">Usuário:</label>
                <input type="text" id="username" name="username" required><br><br>
                <label for="password">Senha:</label>
                <input type="password" id="password" name="password" required><br><br>
                <button type="submit">Entrar</button><br><br>
                <a href="#" onclick="alert('Por favor, procure o suporte de TI para recuperar sua senha')">Esqueceu a senha?</a>
            </form>
    !-- Exibir mensagem de sucesso ou erro -->
            <%
                String message = (String) session.getAttribute("message");
                if (message != null) {
                    if ("true".equals(request.getParameter("success"))) {
                        out.println("<p class='message success'>" + message + "</p>");
                    } else {
                        out.println("<p class='message error'>" + message + "</p>");
                    }
                    session.removeAttribute("message");
                }
            %>
        </div>
    </body>
</html>
