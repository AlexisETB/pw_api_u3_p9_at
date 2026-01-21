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
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/todos")
    public List<Estudiante> listarTodos() {
        return this.estudianteService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Estudiante consultarPorId(@PathParam("id") Integer id) {
        return this.estudianteService.consultarPorId(id);
    }

    @POST
    @Path("/crear")
    public void guardar(Estudiante estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizar(id, estudiante);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarParcial(id, estudiante);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("/buscarPorProvincia")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia) {
        return this.estudianteService.buscarPorProvincia(provincia);
    }

    @GET
    @Path("/buscarPorProvinciaGenero")
    public List<Estudiante> buscarPorProvinciaGenero(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        return this.estudianteService.buscarPorProvinciaGenero(provincia, genero);
    }

}
