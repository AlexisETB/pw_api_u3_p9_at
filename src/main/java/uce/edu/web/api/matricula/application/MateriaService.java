package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infrastructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {
    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodos() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    public List<Materia> consultarPorDocente(String docente) {
        return this.materiaRepository.findByDocente(docente);
    }

    @Transactional
    public void crearMateria(Materia materia) {
        this.materiaRepository.persist(materia);
    }

    @Transactional
    public void actualizar(Integer id, Materia mat) {
        Materia mate = this.consultarPorId(id);
        mate.setNombre(mat.getNombre());
        mate.setCreditos(mat.getCreditos());
        mate.setHorasSemanales(mat.getHorasSemanales());
        mate.setDocente(mat.getDocente());
        // No es necesario persiste, gracias el dirty checking de hibernate ya que
        // detecta automaticamente los cambios
    }

    @Transactional
    public void actualizarParcial(Integer id, Materia mat) {
        Materia mate = this.consultarPorId(id);
        if (mat.getNombre() != null) {
            mate.setNombre(mat.getNombre());
        }
        if (mat.getCreditos() != null) {
            mate.setCreditos(mat.getCreditos());
        }
        if (mat.getHorasSemanales() != null) {
            mate.setHorasSemanales(mat.getHorasSemanales());
        }
        if (mat.getDocente() != null) {
            mate.setDocente(mat.getDocente());
        }

    }

    @Transactional
    public void actualizarDocenteGrupo(String docenteActual, String docenteNuevo) {
        List<Materia> materias = this.consultarPorDocente(docenteActual);
        for (Materia materia : materias) {
            materia.setDocente(docenteNuevo);
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.materiaRepository.deleteById(id.longValue());
    }
}
