package oop2.tp3.ejercicio3;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ReporteDeGastosTest {

    private String normalizarSaltosDeLinea(String texto) {

        return texto.replace("\r\n", "\n").replace("\r", "\n");
    }

    @Test
    void testReporteConUnGastoDeDesayuno() {
        ReporteDeGastos reporte = new ReporteDeGastos();
        List<Gasto> gastos = List.of(new GastoDesayuno(1000));

        String resultado = reporte.generarReporte(gastos);
        String esperado = String.format("Expenses %s%n" +
                "Desayuno\t1000\t %n" +
                "Gastos de comida: 1000%n" +
                "Total de gastos: 1000", LocalDate.now());

        assertEquals(normalizarSaltosDeLinea(esperado), normalizarSaltosDeLinea(resultado));
    }

    @Test
    void testReporteConGastoDeDesayunoExcesivo() {
        ReporteDeGastos reporte = new ReporteDeGastos();
        List<Gasto> gastos = List.of(new GastoDesayuno(1500));

        String resultado = reporte.generarReporte(gastos);
        String esperado = String.format("Expenses %s%n" +
                "Desayuno\t1500\tX%n" +
                "Gastos de comida: 1500%n" +
                "Total de gastos: 1500", LocalDate.now());

        assertEquals(normalizarSaltosDeLinea(esperado), normalizarSaltosDeLinea(resultado));
    }

    @Test
    void testReporteConGastoDeCenaExcesivo() {
        ReporteDeGastos reporte = new ReporteDeGastos();
        List<Gasto> gastos = List.of(new GastoCena(6000));

        String resultado = reporte.generarReporte(gastos);
        String esperado = String.format("Expenses %s%n" +
                "Cena\t6000\tX%n" +
                "Gastos de comida: 6000%n" +
                "Total de gastos: 6000", LocalDate.now());

        assertEquals(normalizarSaltosDeLinea(esperado), normalizarSaltosDeLinea(resultado));
    }

    @Test
    void testReporteConMultiplesGastos() {
        ReporteDeGastos reporte = new ReporteDeGastos();
        List<Gasto> gastos = List.of(
                new GastoDesayuno(800),
                new GastoCena(4000),
                new GastoAlquilerAuto(15000)
        );

        String resultado = reporte.generarReporte(gastos);
        String esperado = String.format("Expenses %s%n" +
                "Desayuno\t800\t %n" +
                "Cena\t4000\t %n" +
                "Alquiler de Autos\t15000\t %n" +
                "Gastos de comida: 4800%n" +
                "Total de gastos: 19800", LocalDate.now());

        assertEquals(normalizarSaltosDeLinea(esperado), normalizarSaltosDeLinea(resultado));
    }
}
