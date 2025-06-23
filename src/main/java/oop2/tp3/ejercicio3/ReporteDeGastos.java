package oop2.tp3.ejercicio3;

import java.time.LocalDate;
import java.util.List;

abstract class Gasto {
    private final int monto;

    protected Gasto(int monto) {
        this.monto = monto;
    }

    public int getMonto() {
        return monto;
    }

    public abstract String getNombre();

    public abstract boolean esGastoDeComida();

    public abstract boolean excedeLimite();
}

class GastoCena extends Gasto {
    private static final int LIMITE = 5000;

    public GastoCena(int monto) {
        super(monto);
    }

    @Override
    public String getNombre() {
        return "Cena";
    }

    @Override
    public boolean esGastoDeComida() {
        return true;
    }

    @Override
    public boolean excedeLimite() {
        return getMonto() > LIMITE;
    }
}

class GastoDesayuno extends Gasto {
    private static final int LIMITE = 1000;

    public GastoDesayuno(int monto) {
        super(monto);
    }

    @Override
    public String getNombre() {
        return "Desayuno";
    }

    @Override
    public boolean esGastoDeComida() {
        return true;
    }

    @Override
    public boolean excedeLimite() {
        return getMonto() > LIMITE;
    }
}

class GastoAlquilerAuto extends Gasto {

    public GastoAlquilerAuto(int monto) {
        super(monto);
    }

    @Override
    public String getNombre() {
        return "Alquiler de Autos";
    }

    @Override
    public boolean esGastoDeComida() {
        return false;
    }

    @Override
    public boolean excedeLimite() {
        return false;
    }
}

public class ReporteDeGastos {

    public String generarReporte(List<Gasto> gastos) {
        StringBuilder reporte = new StringBuilder();
        int total = 0;
        int gastosDeComida = 0;

        reporte.append("Expenses ").append(LocalDate.now()).append("\n");

        for (Gasto gasto : gastos) {
            if (gasto.esGastoDeComida()) {
                gastosDeComida += gasto.getMonto();
            }

            String marcaExcesoComidas = gasto.excedeLimite() ? "X" : " ";

            reporte.append(gasto.getNombre())
                    .append("\t")
                    .append(gasto.getMonto())
                    .append("\t")
                    .append(marcaExcesoComidas)
                    .append("\n");

            total += gasto.getMonto();
        }

        reporte.append("Gastos de comida: ").append(gastosDeComida).append("\n");
        reporte.append("Total de gastos: ").append(total);

        return reporte.toString();
    }
}
