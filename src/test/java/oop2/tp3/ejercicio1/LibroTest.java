package oop2.tp3.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {
    @Test
    void testGetters() {
        Libro libro = new Libro("Harry Potter", Libro.NUEVO_LANZAMIENTO);
        assertEquals("Harry Potter", libro.nombre());
        assertEquals(9, libro.calcularMonto(3)); // 3*3
        assertEquals(2, libro.calcularPuntos(3)); // >1 día
    }

    @Test
    void testConstantes() {
        assertEquals(0, Libro.REGULARES);
        assertEquals(1, Libro.NUEVO_LANZAMIENTO);
        assertEquals(2, Libro.INFANTILES);
    }
} 