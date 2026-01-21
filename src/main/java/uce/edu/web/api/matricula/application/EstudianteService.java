package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infrastructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return this.estudianteRepository.listAll();
    }

    public Estudiante consultarPorId(Integer id) {
        return this.estudianteRepository.findById(id.longValue());
    }

    @Transactional
    public void crearEstudiante(Estudiante estudiante) {
        this.estudianteRepository.persist(estudiante);
    }

    @Transactional
    public void actualizar(Integer id, Estudiante est) {
        Estudiante estu = this.consultarPorId(id);
        estu.nombre = est.nombre;
        estu.apellido = est.apellido;
        estu.fechaNac = est.fechaNac;
        estu.provincia = est.provincia;
        estu.genero = est.genero;
        // No es necesario persiste, gracias el dirty checking de hibernate ya que
        // detecta automaticamente los cambios
    }

    @Transactional
    public void actualizarParcial(Integer id, Estudiante est) {
        Estudiante estu = this.consultarPorId(id);
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

    @Transactional
    public void eliminar(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<Estudiante> buscarPorProvincia(String provincia) {
        return this.estudianteRepository.find("provincia", provincia).list();
    }

    public List<Estudiante> buscarPorProvinciaGenero(String provincia, String genero) {
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();
    }
}
