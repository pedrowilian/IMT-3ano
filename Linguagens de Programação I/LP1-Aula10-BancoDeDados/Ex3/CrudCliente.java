package Ex3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudCliente {
    private static final String TABLE_NAME = "CLIENTE";

    public void create(Connection conn, Cliente cliente) {
        String sqlInsert = "INSERT INTO " + TABLE_NAME + "(NOME, CPF) VALUES(?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Erro ao incluir os dados" + e.toString());
        }
    }

    public void read(Connection conn, String cpf) {
        String sqlSelect = "SELECT * FROM " + TABLE_NAME + " WHERE CPF = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Erro ao ler os dados" + e.toString());
        }
    }

    public void update(Connection conn, Cliente cliente) {
        String sqlUpdate = "UPDATE " + TABLE_NAME + " SET NOME = ? WHERE CPF = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlUpdate);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Erro ao alterar os dados" + e.toString());
        }
    }

    public void delete(Connection conn, String cpf) {
        String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE CPF = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Erro ao excluir os dados" + e.toString());
        }
    }
}
