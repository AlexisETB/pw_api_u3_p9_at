package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todos")
    public List<Materia> listarTodos() {
        return this.materiaService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") Integer id) {
        return this.materiaService.consultarPorId(id);
    }

    @GET
    @Path("/consultarPorDocente/{docente}")
    public List<Materia> consultarPorDocente(@PathParam("docente") String docente) {
        return this.materiaService.consultarPorDocente(docente);
    }

    @POST
    @Path("/crear")
    public void guardar(Materia materia) {
        this.materiaService.crearMateria(materia);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizar(id, materia);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarParcial(id, materia);
    }

    @PATCH
    @Path("/actualizarDocenteGrupo/{docenteActual}/{docenteNuevo}")
    public void actualizarDocenteGrupo(@PathParam("docenteActual") String docenteActual,
            @PathParam("docenteNuevo") String docenteNuevo) {
        this.materiaService.actualizarDocenteGrupo(docenteActual, docenteNuevo);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrar(@PathParam("id") Integer id) {
        this.materiaService.eliminar(id);
    }

}
