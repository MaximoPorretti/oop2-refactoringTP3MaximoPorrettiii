package oop2.tp3.ejercicio4;

import org.jdbi.v3.core.Jdbi;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:hsqldb:mem;create=true");
        new SetUpDatabase(jdbi).setUp();
        var repo = new PersonaRepository(jdbi);

        repo.buscarPorNombre("Vla").forEach(persona ->
            System.out.println(persona.nombre() + " " + persona.apellido())
        );

        repo.buscarId(1L).ifPresent(persona ->
            System.out.println(persona.nombre() + " " + persona.apellido())
        );
    }
}
