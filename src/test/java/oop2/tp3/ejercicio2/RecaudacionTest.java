package oop2.tp3.ejercicio2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecaudacionTest {

    private Recaudacion recaudacion;

    @BeforeEach
    public void setUp() {
        recaudacion = new Recaudacion("src/main/resources/data.csv");
    }

    @Test
    public void testFiltrarPorEmpresa() {
        var options = Map.of("company_name", "Facebook");
        assertEquals(7, recaudacion.where(options).size());
    }

    @Test
    public void testFiltrarPorCiudad() {
        var options = Map.of("city", "Tempe");
        assertEquals(3, recaudacion.where(options).size());
    }

    @Test
    public void testFiltrarPorEstado() {
        var options = Map.of("state", "CA");
        assertEquals(873, recaudacion.where(options).size());
    }

    @Test
    public void testFiltrarPorRonda() {
        var options = Map.of("round", "a");
        assertEquals(582, recaudacion.where(options).size());
    }

    @Test
    public void testFiltrarPorMultiplesOpciones() {
        var options = Map.of(
                "round", "a",
                "company_name", "Facebook"
        );
        assertEquals(1, recaudacion.where(options).size());
    }

    @Test
    public void testFiltrarEmpresaInexistente() {
        var options = Map.of("company_name", "NotFacebook");
        assertEquals(0, recaudacion.where(options).size());
    }

    @Test
    public void testClavesCorrectasEnResultado() {
        var options = Map.of("company_name", "Facebook");
        Map<String, String> row = recaudacion.where(options).get(0);

        assertEquals("facebook", row.get("permalink"));
        assertEquals("Facebook", row.get("company_name"));
        assertEquals("450", row.get("number_employees"));
        assertEquals("web", row.get("category"));
        assertEquals("Palo Alto", row.get("city"));
        assertEquals("CA", row.get("state"));
        assertEquals("1-Sep-04", row.get("funded_date"));
        assertEquals("500000", row.get("raised_amount"));
        assertEquals("angel", row.get("round"));
    }
}
