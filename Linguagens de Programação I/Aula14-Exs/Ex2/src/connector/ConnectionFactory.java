package connector;

import java.sql.*;

public class ConnectionFactory
{
    private static final String USER = "root";
    private static final String PASS = "root1234";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE = "imt_db";
    private static final int PORT = 3306;
    private static final String HOST = "localhost";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    private static final String ERRODB = "Erro ao conectar ao banco de dados";

    public static Connection getConn()
    {
        try
        {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }
        catch(ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(ERRODB, e);
        }
    }

    public static void closeConn(Connection conn)
    {
        try 
        {
            if(conn != null)
            {
                conn.close();
            }    
        } catch (SQLException e) 
        {
            throw new RuntimeException(ERRODB + e);
        }
    }

    public static void closeConn(Connection conn, PreparedStatement stmt)
    {
        closeConn(conn);
        try 
        {
            if(stmt != null)
            {
                stmt.close();
            }
        } 
        catch (SQLException e) 
        {
            throw new RuntimeException(ERRODB + e);
        } 
    }
}