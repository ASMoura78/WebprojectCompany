<%-- 
    Document   : cadastrar_multas
    Created on : 24 de fev. de 2025, 19:15:05
    Author     : Alan Guedes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Multas</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <div class="cadastro-container">
            <h2>Cadastro de Multas</h2>
            <form action="CadastrarMultaServlet" method="post">
                <label for="nome_motorista_infrator">Nome do Motorista Infrator:</label>
                <input type="text" id="nome_motorista_infrator" name="nome_motorista_infrator" required><br><br>
                <label for="veiculo">Veículo:</label>
                <select id="veiculo" name="veiculo" required>
                    <option value="" disabled selected>Selecione o veículo</option>
                    <%
                        List<String> veiculos = (List<String>) request.getAttribute("veiculos");
                        if (veiculos != null) {
                            for (String veiculo : veiculos) {
                                out.println("<option value='" + veiculo + "'>" + veiculo + "</option>");
                            }
                        } else {
                            out.println("<option value='' disabled>Erro ao carregar veículos</option>");
                        }
                    %>
                </select><br><br>
                <label for="placa">Placa:</label>
                <input type="text" id="placa" name="placa" required><br><br>
                <label for="data_hora_infracao">Data e Hora da Infração:</label>
                <input type="datetime-local" id="data_hora_infracao" name="data_hora_infracao" required><br><br>
                <label for="local_infracao">Local da Infração:</label>
                <input type="text" id="local_infracao" name="local_infracao" required><br><br>
                <label for="descricao_infracao">Descrição da Infração:</label>
                <textarea id="descricao_infracao" name="descricao_infracao" rows="4" cols="50" required></textarea><br><br>
                <button type="submit">Cadastrar</button>
                <button type="button" onclick="window.location.href='main.jsp'">Voltar</button>
            </form>
            <!-- Exibir mensagem de sucesso -->
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
                    out.println("<p class='message success'>" + message + "</p>");
                }
            %>
        </div>
    </body>
</html>
