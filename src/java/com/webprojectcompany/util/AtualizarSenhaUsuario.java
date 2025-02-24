package com.webprojectcompany.util;

import org.mindrot.jbcrypt.BCrypt;
import com.webprojectcompany.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizarSenhaUsuario {

    public static void main(String[] args) {
        String nomeUsuario = "Admin"; // Nome de usuário a ser atualizado
        String senha = "123456"; // Nova senha em texto puro

        // Gerar o hash da senha usando bcrypt
        String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());

        // Adicionar o hash ao banco de dados
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE usuarios SET senha_hash = ? WHERE nome_usuario = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, senhaHash);
                statement.setString(2, nomeUsuario);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Senha atualizada com sucesso para o usuário: " + nomeUsuario);
                } else {
                    System.out.println("Usuário não encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

