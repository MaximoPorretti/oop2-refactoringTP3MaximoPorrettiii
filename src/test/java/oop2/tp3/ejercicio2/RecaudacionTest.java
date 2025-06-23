package oop2.tp3.ejercicio2;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecaudacionTest {

   @Test
   public void testFiltrarPorEmpresa() throws IOException {
       var options = Map.of("company_name", "Facebook");
       assertEquals(7, Recaudacion.where(options).size());
   }

   @Test
   public void testFiltrarPorCiudad() throws IOException {
       var options = Map.of("city", "Tempe");
       assertEquals(3, Recaudacion.where(options).size());
   }

   @Test
   public void testFiltrarPorEstado() throws IOException {
       var options = Map.of("state", "CA");
       assertEquals(873, Recaudacion.where(options).size());
   }

   @Test
   public void testFiltrarPorRonda() throws IOException {
       var options = Map.of("round", "a");
       assertEquals(582, Recaudacion.where(options).size());
   }

   @Test
   public void testFiltrarPorMultiplesOpciones() throws IOException {
       var options = Map.of(
           "round", "a",
           "company_name", "Facebook"
       );
       assertEquals(1, Recaudacion.where(options).size());
   }

   @Test
   public void testFiltrarEmpresaInexistente() throws IOException {
       var options = Map.of("company_name", "NotFacebook");
       assertEquals(0, Recaudacion.where(options).size());
   }

   @Test
   public void testClavesCorrectasEnResultado() throws IOException {
       var options = Map.of("company_name", "Facebook");
       Map<String, String> row = Recaudacion.where(options).get(0);

       assertEquals("facebook", row.get("permalink"));
       assertEquals("Facebook", row.get("company_name"));
       assertEquals("450", row.get("number_employees"));
       assertEquals("web", row.get("category"));
       assertEquals("Palo Alto", row.get("city"));
       assertEquals("CA", row.get("state"));
       assertEquals("1-Sep-04", row.get("funded_date"));
       assertEquals("500000", row.get("raised_amount"));
       assertEquals("angel", row.get("round"));
   }}