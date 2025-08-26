package dao;

import connector.ConnectionFactory;
import java.sql.*;

public class LoginDAO {

    public boolean validar(String usuario, String senha) {
        String sql = "SELECT * FROM login_senha WHERE user = ? AND pass = ?";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean usuarioExiste(String usuario) {
        String sql = "SELECT * FROM login_senha WHERE user = ?";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cadastrarUsuario(String usuario, String senha) {
        if (usuarioExiste(usuario)) {
            return false; // já existe
        }

        String sql = "INSERT INTO login_senha (user, pass) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            int r = stmt.executeUpdate();
            return r > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
