package oop2.tp3.ejercicio2;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Recaudacion {
    private static List<String[]> csvData;
    private static final String[] CAMPOS = {"permalink", "company_name", "number_employees", "category", "city", "state", "funded_date", "raised_amount", "raised_currency", "round"};
    private static final Map<String, Integer> INDICES = Map.of(
            "company_name", 1,
            "city", 4,
            "state", 5,
            "round", 9
    );

    static {
        try {
            csvData = new CsvReader().leerCsv("src/main/resources/data.csv");
        } catch (IOException e) {
            csvData = List.of(); // Lista vacía si hay error
            e.printStackTrace();
        }
    }

    public static List<Map<String, String>> where(Map<String, String> options) {
        List<String[]> filtrados = new ArrayList<>(csvData);
        for (var entry : options.entrySet()) {
            if (INDICES.containsKey(entry.getKey())) {
                filtrados = filtrar(filtrados, INDICES.get(entry.getKey()), entry.getValue());
            }
        }
        List<Map<String, String>> output = new ArrayList<>();
        for (String[] fila : filtrados) {
            Map<String, String> mapped = new HashMap<>();
            for (int i = 0; i < CAMPOS.length; i++) {
                mapped.put(CAMPOS[i], fila[i]);
            }
            output.add(mapped);
        }
        return output;
    }

    private static List<String[]> filtrar(List<String[]> datos, int indice, String valor) {
        List<String[]> results = new ArrayList<>();
        for (String[] fila : datos) {
            if (fila[indice].equals(valor)) {
                results.add(fila);
            }
        }
        return results;
    }

    // Clase interna para lectura de CSV
    private static class CsvReader {
        public List<String[]> leerCsv(String path) throws IOException {
            List<String[]> data = new ArrayList<>();
            try (CSVReader reader = new CSVReader(new FileReader(path))) {
                String[] row;
                boolean first = true;
                while ((row = reader.readNext()) != null) {
                    if (first) { first = false; continue; } // Saltar encabezado
                    data.add(row);
                }
            }
            return data;
        }
    }
}
