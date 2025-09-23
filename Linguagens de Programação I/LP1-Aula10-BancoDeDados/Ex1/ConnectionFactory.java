package Ex1;

import java.sql.*;

public  class ConnectionFactory
    {
    private static final String USER =   "Pedro";
    private static final String PASS =   "Bora123@";

    private static final String DATABASE = "AULA10";
    private static final String HOST = "localhost";
    private static final int POST = 3306;

    private static final String URL  =   "jdbc:mysql://" + HOST + ":" + POST + "/" + DATABASE;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String ERROBD = "Erro na conexão com o Banco de Dados: "; 

    public static Connection getConn()
    {   try
        {   Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }
        catch(ClassNotFoundException | SQLException e) 
        {   throw new RuntimeException(ERROBD + e);
        }
    }

    public static void closeConn(Connection conn)
    {   try
        {   if(conn != null) 
            {   conn.close();
            }
        } 
        catch(SQLException e) 
        {   throw new RuntimeException(ERROBD + e);
        }
    }
    public static void closeConn(Connection conn, PreparedStatement stmt) 
    {   closeConn(conn);
        try
        {   if(stmt != null) 
            {   stmt.close();
            }
        } 
        catch(SQLException e) 
        {   throw new RuntimeException(ERROBD + e);
        }
    }
    public static void closeConn(Connection conn, PreparedStatement stmt, ResultSet rs) 
    {   closeConn(conn, stmt);
        try
        {   if(rs != null) 
            {   rs.close();
            }
        } 
        catch(SQLException e) 
        {   throw new RuntimeException(ERROBD + e);
        }
    }
}
