<%-- 
    Document   : cadastrar_veiculos
    Created on : 24 de fev. de 2025, 19:19:29
    Author     : Alan Guedes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Veículo</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <script type="text/javascript">
            // Função para limpar os campos do formulário
            function clearForm() {
                document.getElementById("modelo").value = "";
                document.getElementById("placa").value = "";
                document.getElementById("cor").value = "";
                document.getElementById("imagem").value = "";
                document.getElementById("observacoes").value = "";
            }

            window.onload = function() {
                <% if (session.getAttribute("message") != null) { %>
                    clearForm(); // Limpar os campos do formulário
                <% } %>
            };
        </script>
    </head>
    <body>
        <div class="cadastro-container">
            <h2>Cadastro de Veículo</h2>
            <form action="CadastrarVeiculoServlet" method="post" enctype="multipart/form-data">
                <label for="modelo">Modelo:</label>
                <input type="text" id="modelo" name="modelo" required><br><br>
                <label for="placa">Placa:</label>
                <input type="text" id="placa" name="placa" required><br><br>
                <label for="cor">Cor:</label>
                <input type="text" id="cor" name="cor" required><br><br>
                <label for="imagem">Imagem do Veículo (Opcional):</label>
                <input type="file" id="imagem" name="imagem"><br><br>
                <label for="observacoes">Observações:</label>
                <textarea id="observacoes" name="observacoes" rows="4" cols="50"></textarea><br><br>
                <button type="submit">Cadastrar</button>
                <button type="button" onclick="window.location.href='main.jsp'">Voltar</button>
            </form>
            
            <!-- Exibir mensagem de sucesso -->
            <%
                String message = (String) session.getAttribute("message");
                if (message != null) {
                    out.println("<p class='message success'>" + message + "</p>");
                    session.removeAttribute("message");
                }
            %>
        </div>
    </body>
</html>
