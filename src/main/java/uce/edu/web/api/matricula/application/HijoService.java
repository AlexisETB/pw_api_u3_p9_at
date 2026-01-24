package uce.edu.web.api.matricula.application;

import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infrastructure.HijoRepository;

@ApplicationScoped
public class HijoService {

    @Inject
    HijoRepository hijoRepository;

    // READ - Buscar todos los hijos
    public List<Hijo> buscarTodos() {
        return this.hijoRepository.listAll();
    }

    // READ - Buscar hijo por ID
    public Hijo buscarPorId(Long id) {
        return this.hijoRepository.findById(id.longValue());
    }

    // READ - Buscar hijos por ID del estudiante
    public List<Hijo> buscarPorIdEstudiante(Integer estudianteId) {
        return this.hijoRepository.buscarPorIdEstudiante(estudianteId);
    }

    // CREATE - Crear un nuevo hijo
    @Transactional
    public void crear(Hijo hijo) {
        this.hijoRepository.persist(hijo);
    }

    // UPDATE - Actualizar un hijo existente
    @Transactional
    public void actualizar(Long id, Hijo hijoActualizado) {
        Hijo hijoExistente = this.hijoRepository.findById(id);
        if (hijoExistente != null) {
            hijoExistente.nombre = hijoActualizado.nombre;
            hijoExistente.apellido = hijoActualizado.apellido;
            hijoExistente.estudiante = hijoActualizado.estudiante;
        }
    }

    // DELETE - Eliminar hijo por ID
    @Transactional
    public boolean eliminar(Long id) {
        return this.hijoRepository.deleteById(id);
    }

    // DELETE - Eliminar todos los hijos de un estudiante
    @Transactional
    public long eliminarPorIdEstudiante(Integer estudianteId) {
        return this.hijoRepository.delete("estudiante.id", estudianteId);
    }
}
