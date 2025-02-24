<%-- 
    Document   : cadastrar_motorista
    Created on : 24 de fev. de 2025, 19:13:30
    Author     : Alan Guedes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastrar Motorista</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div class="form-container">
            <h2>Cadastrar Motorista</h2>
            <form action="cadastrar_motorista" method="post">
                <input type="text" name="nome_motorista" placeholder="Nome do Motorista" required>
                <input type="text" name="cpf" placeholder="CPF" required>
                <input type="text" name="numero_cnh" placeholder="Nº CNH" required>
                <label for="categoria_cnh">Categoria CNH:</label>
                <select id="categoria_cnh" name="categoria_cnh">
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                    <option value="E">E</option>
                </select>
                <textarea name="cursos_adicionais" placeholder="Cursos Adicionais"></textarea>
                <div class="button-group">
                    <button type="submit">Cadastrar</button>
                    <button type="button" onclick="window.location.href = 'menu.html'">Voltar</button>
                </div>
            </form>
        </div>
    </body>
</html>
