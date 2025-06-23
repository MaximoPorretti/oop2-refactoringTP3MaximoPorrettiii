package oop2.tp3.ejercicio4;

import org.jdbi.v3.core.Jdbi;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonaRepository {
    private final Jdbi jdbi;

    public PersonaRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<Persona> buscarPorNombre(String nombreOParte) {
        return jdbi.withHandle(handle -> {
            var rs = handle
                    .select("select nombre, apellido from persona where nombre like ?")
                    .bind(0, "%" + nombreOParte + "%")
                    .mapToMap(String.class)
                    .list();

            if (rs.isEmpty()) {
                return new ArrayList<>();
            }

            var personas = new ArrayList<Persona>();
            for (Map<String, String> map : rs) {
                personas.add(new Persona(map.get("nombre"), map.get("apellido")));
            }
            return personas;
        });
    }


    public Optional<Persona> buscarId(Long id) {
        return jdbi.withHandle(handle -> {
            var rs = handle
                    .select("select nombre, apellido from persona where id_persona = ?")
                    .bind(0, id)
                    .mapToMap(String.class)
                    .list();

            if (rs.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(new Persona(
                rs.get(0).get("nombre"),
                rs.get(0).get("apellido")
            ));
        });
    }
}
