package Ex2;

import Ex1.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CrudDisciplina {
    private static final String TABLE_NAME = "DISCIPLINA";

    public void inserir(Connection conn, Disciplina disciplina){
        String   sqlInsert = "INSERT INTO " + TABLE_NAME + "(NOME, CODIGO_DISCIPLINA) VALUES(?, ?)";
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, disciplina.getNome());
            stmt.setString(2, disciplina.getCodigo());
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {   try
            {
                conn.rollback();
            }
            catch(SQLException ex)
            {   System.out.println("Erro ao incluir os dados" + ex.toString());
            }
        }

        for (Professor professor: disciplina.getProfessores()) {
            String sqlInsertProfessorDisciplina = "UPDATE PROFESSOR SET CODIGO = ? WHERE MATRICULA = ?";
            try {
                stmt = conn.prepareStatement(sqlInsertProfessorDisciplina);
                stmt.setString(1, disciplina.getCodigo());
                stmt.setInt(2, professor.getMatricula());
                stmt.executeUpdate();
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("Erro ao incluir os dados" + ex.toString());
                }
            }
        }

    }
    public void alterar(Connection conn, Disciplina disciplina){
        String   sqlUpdate = "UPDATE " + TABLE_NAME + " SET NOME = ? WHERE CODIGO_DISCIPLINA = ?";
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlUpdate);
            stmt.setString(1, disciplina.getNome());
            stmt.setString(2, disciplina.getCodigo());
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {   try
            {   conn.rollback();
            }
            catch(SQLException ex)
            {   System.out.println("Erro ao alterar os dados" + ex.toString());
            }
        }

        for (Professor professor: disciplina.getProfessores()) {
            String sqlInsertProfessorDisciplina = "UPDATE PROFESSOR SET CODIGO = ? WHERE MATRICULA = ?";
            try {
                stmt = conn.prepareStatement(sqlInsertProfessorDisciplina);
                stmt.setInt(1, professor.getMatricula());
                stmt.setString(2, disciplina.getCodigo());
                stmt.executeUpdate();
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("Erro ao incluir os dados" + ex.toString());
                }
            }
        }
    }
    public void excluir(Connection conn, String codigo){
        String   sqlDelete = "DELETE FROM "+ TABLE_NAME + " WHERE CODIGO_DISCIPLINA = ?";
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {   try
            {   conn.rollback();
            }
            catch(SQLException ex)
            {   System.out.println("Erro ao excluir os dados" + ex.toString());
            }
        }
    }
    public Disciplina consultar(Connection conn, String codigo){
        String   sqlSelect = "SELECT * FROM " + TABLE_NAME + " WHERE CODIGO_DISCIPLINA = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Disciplina disciplina = null;
        try
        {   stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                disciplina = new Disciplina();
                disciplina.setNome(rs.getString("NOME"));
                disciplina.setCodigo(rs.getString("CODIGO_DISCIPLINA"));
            }
        }
        catch(SQLException e)
        {   try
            {   conn.rollback();
            }
            catch(SQLException ex)
            {   System.out.println("Erro ao consultar os dados" + ex.toString());
            }
        }

        String sqlSelectProfessorDisciplina = "SELECT * FROM PROFESSOR WHERE CODIGO = ?";
        try {
            stmt = conn.prepareStatement(sqlSelectProfessorDisciplina);
            assert disciplina != null;
            stmt.setString(1, disciplina.getCodigo());
            rs = stmt.executeQuery();
            ArrayList<Professor> professores = new ArrayList<>();
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setMatricula(rs.getInt("MATRICULA"));
                professor.setNome(rs.getString("NOME"));
                professor.setIdade(rs.getInt("Idade"));
                professores.add(professor);
            }
            disciplina.setProfessores(professores);
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Erro ao consultar os dados" + ex.toString());
            }
        }

        return disciplina;

    }

}
