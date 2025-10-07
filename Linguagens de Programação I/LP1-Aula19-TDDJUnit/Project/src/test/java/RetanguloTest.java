import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetanguloTest {

    @Test
    @DisplayName("Testa cálculo da área")
    public void testaCalculaArea() {
        Retangulo retangulo = new Retangulo(2, 3);
        assertEquals(6, retangulo.calculaArea());
    }

    @Test
    @DisplayName("Teste cálculo perímetro")
    public void testeCalculaPerimetro(){
        Retangulo retangulo = new Retangulo(2, 3);
        assertEquals(10, retangulo.calculaPerimetro());
    }
}
