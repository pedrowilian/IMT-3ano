package dao;

import connector.ConnectionFactory;
import model.Nota;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotasDAO {

    public List<Nota> getNotasDoUsuario(String usuario) {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT id, disciplina, nota, faltas, ultima_alteracao FROM notas_faltas WHERE user = ?";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Nota n = new Nota(
                    rs.getInt("id"),
                    usuario,
                    rs.getString("disciplina"),
                    rs.getFloat("nota"),
                    rs.getInt("faltas"),
                    rs.getTimestamp("ultima_alteracao")
                );
                lista.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean atualizarNota(Nota n) {
    String sqlUpdate = "UPDATE notas_faltas SET nota = ?, faltas = ?, ultima_alteracao = NOW() WHERE id = ?";
    String sqlSelect = "SELECT ultima_alteracao FROM notas_faltas WHERE id = ?";

    try (Connection conn = ConnectionFactory.getConn();
         PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
         PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {

        // Atualiza nota e faltas
        stmtUpdate.setFloat(1, n.getNota());
        stmtUpdate.setInt(2, n.getFaltas());
        stmtUpdate.setInt(3, n.getId());

        int r = stmtUpdate.executeUpdate();

        if (r > 0) {
            // Busca a data atualizada
            stmtSelect.setInt(1, n.getId());
            ResultSet rs = stmtSelect.executeQuery();
            if (rs.next()) {
                n.setUltimaAlteracao(rs.getTimestamp("ultima_alteracao"));
            }
            return true;
        } else {
            return false;
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }


    public boolean inserirNota(Nota n) {
    String sqlInsert = "INSERT INTO notas_faltas (user, disciplina, nota, faltas, ultima_alteracao) VALUES (?, ?, ?, ?, NOW())";
    String sqlSelect = "SELECT ultima_alteracao FROM notas_faltas WHERE id = ?";

    try (Connection conn = ConnectionFactory.getConn();
         PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {

        stmtInsert.setString(1, n.getUser());
        stmtInsert.setString(2, n.getDisciplina());
        stmtInsert.setFloat(3, n.getNota());
        stmtInsert.setInt(4, n.getFaltas());

        int r = stmtInsert.executeUpdate();

        if (r > 0) {
            ResultSet rs = stmtInsert.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                n.setId(idGerado);

                // Buscar a data da última alteração
                stmtSelect.setInt(1, idGerado);
                ResultSet rsData = stmtSelect.executeQuery();
                if (rsData.next()) {
                    n.setUltimaAlteracao(rsData.getTimestamp("ultima_alteracao"));
                }
            }
            return true;
        } else {
            return false;
        }
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }


    public boolean removerNota(int id) {
        String sql = "DELETE FROM notas_faltas WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int r = stmt.executeUpdate();
            return r > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
