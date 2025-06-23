package oop2.tp3.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CopiaLibroTest {
    @Test
    void testLibro() {
        Libro libro = new Libro("Don Quijote", Libro.REGULARES);
        CopiaLibro copia = new CopiaLibro(libro);
        assertEquals(libro, copia.libro());
    }
} 