package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infrastructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante e : this.estudianteRepository.listAll()) {
            list.add(this.mapperToEr(e));
        }
        return list;
    }

    public EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapperToEr(this.estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crearEstudiante(EstudianteRepresentation estudiante) {
        this.estudianteRepository.persist(this.mapperToE(estudiante));
    }

    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation est) {
        Estudiante estu = this.estudianteRepository.findById(id.longValue());
        if (estu != null) {
            estu.nombre = est.nombre;
            estu.apellido = est.apellido;
            estu.fechaNac = est.fechaNac;
            estu.provincia = est.provincia;
            estu.genero = est.genero;
            // No es necesario persistir, gracias al dirty checking de hibernate ya que
            // detecta automaticamente los cambios
        }
    }

    @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation est) {
        Estudiante estu = this.estudianteRepository.findById(id.longValue());
        if (estu != null) {
            if (est.nombre != null) {
                estu.nombre = est.nombre;
            }
            if (est.apellido != null) {
                estu.apellido = est.apellido;
            }
            if (est.fechaNac != null) {
                estu.fechaNac = est.fechaNac;
            }
            if (est.provincia != null) {
                estu.provincia = est.provincia;
            }
            if (est.genero != null) {
                estu.genero = est.genero;
            }
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia) {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante e : this.estudianteRepository.find("provincia", provincia).list()) {
            list.add(this.mapperToEr(e));
        }
        return list;
    }

    public List<EstudianteRepresentation> buscarPorProvinciaGenero(String provincia, String genero) {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante e : this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero)
                .list()) {
            list.add(this.mapperToEr(e));
        }
        return list;
    }

    private EstudianteRepresentation mapperToEr(Estudiante estudiante) {
        EstudianteRepresentation rep = new EstudianteRepresentation();
        rep.id = estudiante.id;
        rep.nombre = estudiante.nombre;
        rep.apellido = estudiante.apellido;
        rep.fechaNac = estudiante.fechaNac;
        rep.provincia = estudiante.provincia;
        rep.genero = estudiante.genero;
        return rep;
    }

    private Estudiante mapperToE(EstudianteRepresentation rep) {
        Estudiante estudiante = new Estudiante();
        estudiante.id = rep.id;
        estudiante.nombre = rep.nombre;
        estudiante.apellido = rep.apellido;
        estudiante.fechaNac = rep.fechaNac;
        estudiante.provincia = rep.provincia;
        estudiante.genero = rep.genero;
        return estudiante;
    }
}
