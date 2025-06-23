package oop2.tp3.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    @Test
    void testCalcularDeudaYPuntosObtenidos() {
        Libro libro = new Libro("El Principito", Libro.REGULARES);
        CopiaLibro copia = new CopiaLibro(libro);
        Alquiler alquiler = new Alquiler(copia, 3); // 2 + (3-2)*1.5 = 3.5
        Cliente cliente = new Cliente("Juan");
        cliente.alquilar(alquiler);
        Cliente.ResumenAlquiler resultado = cliente.calcularDeudaYPuntosObtenidos();
        assertEquals(3.5, resultado.montoTotal(), 0.01);
        assertEquals(1, resultado.puntos());
    }

    @Test
    void testAlquilar() {
        Cliente cliente = new Cliente("Ana");
        Libro libro = new Libro("Cuentos", Libro.INFANTILES);
        CopiaLibro copia = new CopiaLibro(libro);
        Alquiler alquiler = new Alquiler(copia, 2);
        cliente.alquilar(alquiler);
        Cliente.ResumenAlquiler resultado = cliente.calcularDeudaYPuntosObtenidos();
        assertEquals(1.5, resultado.montoTotal(), 0.01);
        assertEquals(1, resultado.puntos());
    }
} 