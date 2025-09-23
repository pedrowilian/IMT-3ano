package Ex1;


import javamysqlapp.ConnFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CrudProfessor {

    private static final String TABLE_NAME = "PROFESSOR";

    public void inserir(Connection conn, Professor professor){
        String   sqlInsert = "INSERT INTO " + TABLE_NAME + "(NOME, IDADE, MATRICULA, CODIGO) VALUES(?, ?, ?, NULL)";
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setInt(3, professor.getMatricula());
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

    }
    public void alterar(Connection conn, Professor professor){
        String   sqlUpdate = "UPDATE " + TABLE_NAME + " SET NOME = ?, IDADE = ? WHERE MATRICULA = ?";
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlUpdate);
            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setInt(3, professor.getMatricula());
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

    }
    public void excluir(Connection conn, int matricula){
        String   sqlDelete = "DELETE FROM "+ TABLE_NAME + " WHERE MATRICULA = ?";
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlDelete);
            stmt.setInt(1, matricula);
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
    public Professor consultar(Connection conn, int matricula){
        String   sqlSelect = "SELECT * FROM " + TABLE_NAME + " WHERE MATRICULA = ?";
        Professor professor = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        try
        {   stmt = conn.prepareStatement(sqlSelect);
            stmt.setInt(1, matricula);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                String name;
                int idade;

                name = rs.getString("NOME");
                idade = rs.getInt("IDADE");
                matricula = rs.getInt("MATRICULA");
                professor = new Professor(name, matricula, idade);
                System.out.println(professor.toString());
            }
            else
            {   System.out.println("Não encontrado");
            }
        }
        catch(SQLException ex)
        {   System.out.println("Erro ao consultar os dados" + ex.toString());
        }

        return professor;

    }

    public ArrayList<Professor> listaTodos(Connection conn){
        ArrayList<Professor> professorList = new ArrayList<>();
        String   sqlSelect = "SELECT * FROM " + TABLE_NAME;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try
        {   statement = conn.prepareStatement(sqlSelect);
            resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                String name;
                int idade;
                int matricula;

                name = resultSet.getString("NOME");
                idade = resultSet.getInt("IDADE");
                matricula = resultSet.getInt("MATRICULA");
                Professor professor = new Professor(name, matricula, idade);

                professorList.add(professor);
            }
        }
        catch(SQLException ex)
        {   System.out.println("Erro ao buscar todos os dados" + ex.toString());
        }

        return professorList;
    }

}
