package oop2.tp3.ejercicio4;

import org.jdbi.v3.core.Jdbi;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:hsqldb:mem;create=true");
        new SetUpDatabase(jdbi).setUp();
        var repo = new PersonaRepository(jdbi);

        // Buscar por nombre - usa forEach directamente en la lista (puede estar vacía)
        repo.buscarPorNombre("Vla").forEach(persona -> 
            System.out.println(persona.nombre() + " " + persona.apellido())
        );

        // Buscar por ID - usa Optional
        repo.buscarId(1L).ifPresent(persona ->
            System.out.println(persona.nombre() + " " + persona.apellido())
        );
    }
}
