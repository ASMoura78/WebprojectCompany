<%-- 
    Document   : cadastroVeiculo.jsp
    Created on : 12 de jan. de 2025, 18:31:42
    Author     : Alan Guedes
--%>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Veículo</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script src="scripts.js"></script>
</head>
<body>
    <form action="salvarVeiculo" method="post" onsubmit="return validarVeiculo()">
        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" name="modelo" required>
        <label for="cor">Cor:</label>
        <input type="text" id="cor" name="cor" required>
        <label for="placa">Placa:</label>
        <input type="text" id="placa" name="placa" required>
        <button type="submit">Salvar</button>
    </form>
</body>
</html>

