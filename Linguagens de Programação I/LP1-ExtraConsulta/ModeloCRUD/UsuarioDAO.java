// ------------------------------
// 🔹 DAO Fake (simulação de BD)
// ------------------------------

import java.util.*;

class UsuarioDAO {
    private java.util.List<Usuario> usuarios = new ArrayList<>();
    private int nextId = 1;

    public void create(String nome, String email) {
        usuarios.add(new Usuario(nextId++, nome, email));
    }

    public java.util.List<Usuario> read() {
        return usuarios;
    }

    public void update(int id, String novoNome, String novoEmail) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                u.setNome(novoNome);
                u.setEmail(novoEmail);
                break;
            }
        }
    }

    public void delete(int id) {
        usuarios.removeIf(u -> u.getId() == id);
    }
}