package oop2.tp3.ejercicio1;

public class Libro {
    private String nombre;
    private PrecioLibro precioLibro;

    public Libro(String nombre, int priceCode) {
        this.nombre = nombre;
        this.precioLibro = PrecioLibro.crear(priceCode);
    }

    public double calcularMonto(int diasAlquilados) {
        return precioLibro.calcularMonto(diasAlquilados);
    }

    public int calcularPuntos(int diasAlquilados) {
        return precioLibro.calcularPuntos(diasAlquilados);
    }

    public String nombre() {
        return nombre;
    }

    // Jerarquía de precios
    public static final int REGULARES = 0;
    public static final int NUEVO_LANZAMIENTO = 1;
    public static final int INFANTILES = 2;

    private static abstract class PrecioLibro {
        abstract double calcularMonto(int diasAlquilados);
        int calcularPuntos(int diasAlquilados) { return 1; }

        static PrecioLibro crear(int tipo) {
            return switch (tipo) {
                case REGULARES -> new PrecioRegular();
                case NUEVO_LANZAMIENTO -> new PrecioNuevoLanzamiento();
                case INFANTILES -> new PrecioInfantil();
                default -> throw new IllegalArgumentException("Tipo de libro inválido");
            };
        }
    }

    private static class PrecioRegular extends PrecioLibro {
        @Override
        double calcularMonto(int diasAlquilados) {
            double monto = 2;
            if (diasAlquilados > 2)
                monto += (diasAlquilados - 2) * 1.5;
            return monto;
        }
    }

    private static class PrecioNuevoLanzamiento extends PrecioLibro {
        @Override
        double calcularMonto(int diasAlquilados) {
            return diasAlquilados * 3;
        }
        @Override
        int calcularPuntos(int diasAlquilados) {
            return diasAlquilados > 1 ? 2 : 1;
        }
    }

    private static class PrecioInfantil extends PrecioLibro {
        @Override
        double calcularMonto(int diasAlquilados) {
            double monto = 1.5;
            if (diasAlquilados > 3)
                monto += (diasAlquilados - 3) * 1.5;
            return monto;
        }
    }
}