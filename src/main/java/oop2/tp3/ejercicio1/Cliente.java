package oop2.tp3.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private List<Alquiler> alquileres = new ArrayList<>();
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public ResumenAlquiler calcularDeudaYPuntosObtenidos() {
        double total = 0;
        int puntos = 0;
        for (Alquiler alquiler : alquileres) {
            total += alquiler.calcularMonto();
            puntos += alquiler.calcularPuntos();
        }
        return new ResumenAlquiler(total, puntos);
    }

    public void alquilar(Alquiler alquiler) {
        alquileres.add(alquiler);
    }

    public String nombre() {
        return nombre;
    }

    // Clase auxiliar para encapsular el resultado
    public static class ResumenAlquiler {
        private final double montoTotal;
        private final int puntos;
        public ResumenAlquiler(double montoTotal, int puntos) {
            this.montoTotal = montoTotal;
            this.puntos = puntos;
        }
        public double montoTotal() { return montoTotal; }
        public int puntos() { return puntos; }
    }
}