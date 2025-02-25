<%-- 
    Document   : sucesso
    Created on : 24 de fev. de 2025, 19:23:40
    Author     : Alan Guedes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sucesso</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <script type="text/javascript">
            window.onload = function() {
                setTimeout(function() {
                    window.location.href = "main.jsp";
                }, 2000); // Redireciona após 2 segundos
            };
        </script>
    </head>
    <body>
        <div class="message-container">
            <h2>Sucesso</h2>
            <p class='message success'>Login efetuado com sucesso!</p>
        </div>
    </body>
</html>
