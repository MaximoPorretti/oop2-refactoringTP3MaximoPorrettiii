package oop2.tp3.ejercicio2;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Recaudacion {

    private final List<String[]> datos;

    private static final List<String> CAMPOS = List.of(
            "permalink", "company_name", "number_employees", "category", "city",
            "state", "funded_date", "raised_amount", "raised_currency", "round"
    );

    private static final Map<String, Integer> CAMPOS_FILTRABLES = Map.of(
            "company_name", CAMPOS.indexOf("company_name"),
            "city", CAMPOS.indexOf("city"),
            "state", CAMPOS.indexOf("state"),
            "round", CAMPOS.indexOf("round")
    );

    public Recaudacion(String pathArchivoCsv) {
        this.datos = new LectorCsv().leerCsv(pathArchivoCsv);
    }

    public List<Map<String, String>> where(Map<String, String> filtros) {
        List<String[]> datosFiltrados = new ArrayList<>(datos);
        for (Map.Entry<String, String> filtro : filtros.entrySet()) {
            Integer indice = CAMPOS_FILTRABLES.get(filtro.getKey());
            if (indice != null) {
                datosFiltrados = filtrarPorCampo(datosFiltrados, indice, filtro.getValue());
            }
        }
        return mapearFilas(datosFiltrados);
    }

    private List<String[]> filtrarPorCampo(List<String[]> datos, int indice, String valor) {
        return datos.stream()
                .filter(fila -> fila[indice].equals(valor))
                .toList();
    }

    private List<Map<String, String>> mapearFilas(List<String[]> filas) {
        return filas.stream()
                .map(this::mapearFila)
                .toList();
    }

    private Map<String, String> mapearFila(String[] fila) {
        Map<String, String> resultado = new HashMap<>();
        for (int i = 0; i < CAMPOS.size(); i++) {
            resultado.put(CAMPOS.get(i), fila[i]);
        }
        return resultado;
    }

    // Clase auxiliar para leer el CSV
    private static class LectorCsv {
        public List<String[]> leerCsv(String path) {
            List<String[]> datos = new ArrayList<>();
            try (CSVReader reader = new CSVReader(new FileReader(path))) {
                String[] fila;
                boolean primera = true;
                while ((fila = reader.readNext()) != null) {
                    if (primera) {
                        primera = false; // saltar encabezado
                        continue;
                    }
                    datos.add(fila);
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            }
            return datos;
        }
    }
}
