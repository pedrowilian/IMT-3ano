import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class OrdenaTest {
    int propostos[] = new int[] {9, 10, 8};
    int esperado[] = new int[] {10, 9, 8};
    int inesperado[] = new int[] {9};

    Ordena ordena = new Ordena();

    @Test
    @DisplayName("Testa números decrescentes")
    public void testaOrdenaNumerosDecrescente() {
        ordena.ordenaNumerosDecrescente(propostos);
        assertArrayEquals(esperado, propostos);
    }
    @Test
    @DisplayName("Teste errado")
    public void testaOrdenaNumerosDecrescenteErrado() {
        ordena.ordenaNumerosDecrescente(propostos);
        assertNotEquals(inesperado.length, propostos.length);
    }

}
