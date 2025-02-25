<%-- 
    Document   : cadastrar_oficina
    Created on : 24 de fev. de 2025, 19:17:16
    Author     : Alan Guedes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Oficina</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <div class="cadastro-container">
            <h2>Cadastro de Oficina</h2>
            <form action="CadastrarOficinaServlet" method="post">
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
                <select id="placa" name="placa" required>
                    <option value="" disabled selected>Selecione a placa</option>
                    <%
                        List<String> placas = (List<String>) request.getAttribute("placas");
                        if (placas != null) {
                            for (String placa : placas) {
                                out.println("<option value='" + placa + "'>" + placa + "</option>");
                            }
                        } else {
                            out.println("<option value='' disabled>Erro ao carregar placas</option>");
                        }
                    %>
                </select><br><br>
                <label for="dataEntrada">Data de Entrada:</label>
                <input type="datetime-local" id="dataEntrada" name="dataEntrada" required><br><br>
                <label for="dataSaida">Data de Saída:</label>
                <input type="datetime-local" id="dataSaida" name="dataSaida" required><br><br>
                <label for="servicoRealizado">Serviço Realizado:</label>
                <textarea id="servicoRealizado" name="servicoRealizado" rows="4" cols="50" required></textarea><br><br>
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
