<%-- 
    Document   : cadastroMotorista.jsp
    Created on : 12 de jan. de 2025, 18:31:01
    Author     : Alan Guedes
--%>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Motorista</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script src="scripts.js"></script>
</head>
<body>
    <form action="salvarMotorista" method="post" onsubmit="return validarMotorista()">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>
        <label for="cpf">CPF:</label>
        <input type="text" id="cpf" name="cpf" required>
        <label for="cnh">Número da CNH:</label>
        <input type="text" id="cnh" name="cnh" required>
        <label for="categoriaCnh">Categoria da CNH:</label>
        <input type="text" id="categoriaCnh" name="categoriaCnh" required>
        <button type="submit">Salvar</button>
    </form>
</body>
</html>

