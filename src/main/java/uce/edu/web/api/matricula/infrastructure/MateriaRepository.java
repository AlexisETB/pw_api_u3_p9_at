package uce.edu.web.api.matricula.infrastructure;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Materia;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {

    public List<Materia> findByDocente(String docente) {
        return find("docente", docente).list();
    }

}
