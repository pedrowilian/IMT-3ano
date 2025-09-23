package Ex3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudAgencia {

    private static final String TABLE_NAME = "AGENCIA";

    public void create(Connection conn, Agencia agencia) {
        String sqlInsert = "INSERT INTO " + TABLE_NAME + "(NUMERO, DIGITO) VALUES(?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlInsert);
            stmt.setInt(1, agencia.getNumero());
            stmt.setInt(2, agencia.getDigito());
            stmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Erro ao incluir os dados" + e.toString());
        }
    }

    public void read(Connection conn, int numero) {
        String sqlSelect = "SELECT * FROM " + TABLE_NAME + " WHERE NUMERO = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setInt(1, numero);
            stmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Erro ao ler os dados" + e.toString());
        }
    }

    public void update(Connection conn, Agencia agencia) {
        String sqlUpdate = "UPDATE " + TABLE_NAME + " SET DIGITO = ? WHERE NUMERO = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlUpdate);
            stmt.setInt(1, agencia.getDigito());
            stmt.setInt(2, agencia.getNumero());
            stmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Erro ao alterar os dados" + e.toString());
        }
    }

    public void delete(Connection conn, int numero) {
        String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE NUMERO = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setInt(1, numero);
            stmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Erro ao excluir os dados" + e.toString());
        }
    }



}
