package oop2.tp3.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlquilerTest {
    @Test
    void testGetters() {
        Libro libro = new Libro("El Hobbit", Libro.REGULARES);
        CopiaLibro copia = new CopiaLibro(libro);
        Alquiler alquiler = new Alquiler(copia, 5);
        assertEquals(5, alquiler.diasAlquilados());
        assertEquals(copia, alquiler.copia());
    }

    @Test
    void testCalcularMontoYPuntos() {
        Libro libro = new Libro("Matrix", Libro.NUEVO_LANZAMIENTO);
        CopiaLibro copia = new CopiaLibro(libro);
        Alquiler alquiler = new Alquiler(copia, 2);
        assertEquals(6, alquiler.calcularMonto(), 0.01);
        assertEquals(2, alquiler.calcularPuntos());
    }
} 