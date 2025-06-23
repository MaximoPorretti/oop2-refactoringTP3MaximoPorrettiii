package oop2.tp3.ejercicio3;

import java.util.List;

public class EjemploDeUsoDelReporte {
    public static void main(String[] args) {
        var reporte = new ReporteDeGastos();
        var gastos = List.of(
            new GastoDesayuno(1000),
            new GastoCena(4500),
            new GastoAlquilerAuto(15000)
        );
        System.out.println(reporte.generarReporte(gastos));
    }
}
