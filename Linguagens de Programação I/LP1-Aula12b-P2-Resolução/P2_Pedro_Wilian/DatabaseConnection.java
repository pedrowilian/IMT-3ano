import java.sql.*;
public class DatabaseConnection {
    private static final String USER = "root";
    private static final String PASSWORD = "imtdb";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE = "cem_db";
    private static final int PORT = 3310;
    private static final String HOST = "localhost";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    private static final String ERRODB = "Erro ao conectar ao banco de dados";
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void salvarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, valor_kwh, comodo1, consumo1, comodo2, consumo2, comodo3, consumo3) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setDouble(2, cliente.getValorKwh());
            Comodo[] comodos = cliente.getComodos();
            for (int i = 0; i < 3; i++) {
                stmt.setString(3 + i * 2, comodos[i] != null ? comodos[i].getNome() : "");
                stmt.setDouble(4 + i * 2, comodos[i] != null ? comodos[i].getConsumo() : 0.0);
            }
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
            }
        }
    }

    public Cliente lerCliente(String nome) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE nome = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"), rs.getDouble("valor_kwh"));
                cliente.setId(rs.getInt("id"));
                Comodo[] comodos = new Comodo[3];
                comodos[0] = new Comodo(rs.getString("comodo1"), rs.getDouble("consumo1"));
                comodos[1] = new Comodo(rs.getString("comodo2"), rs.getDouble("consumo2"));
                comodos[2] = new Comodo(rs.getString("comodo3"), rs.getDouble("consumo3"));
                for (Comodo comodo : comodos) {
                    comodo.calcularCusto(cliente.getValorKwh());
                    cliente.adicionarComodo(0, comodos[0]);
                    cliente.adicionarComodo(1, comodos[1]);
                    cliente.adicionarComodo(2, comodos[2]);
                }
                return cliente;
            }
        }
        return null;
    }
}

