<%-- 
    Document   : cadastrar_oficina
    Created on : 24 de fev. de 2025, 19:17:16
    Author     : Alan Guedes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Oficina</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="form-container">
        <h2>Cadastrar Oficina</h2>
        <form action="cadastrar_oficina" method="post">
            <input type="text" name="veiculo" placeholder="Veículo" required>
            <input type="text" name="placa" placeholder="Placa" required>
            <input type="date" name="data_entrada" placeholder="Data de Entrada" required>
            <input type="date" name="data_saida" placeholder="Data de Saída" required>
            <textarea name="servico_realizado" placeholder="Serviço Realizado" required></textarea>
            <div class="button-group">
                <button type="submit">Cadastrar</button>
                <button type="button" onclick="window.location.href='menu.html'">Voltar</button>
            </div>
        </form>
    </div>
</body>
</html>
