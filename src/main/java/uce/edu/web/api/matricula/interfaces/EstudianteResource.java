package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijosRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDTO;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteRepresentation> listarTodos() {
        System.out.println("Listar todos los estudiantes");
        List<EstudianteRepresentation> lista = this.estudianteService.listarTodos();
        for (EstudianteRepresentation er : lista) {
            this.construirLinks(er);
        }
        return lista;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer id) {
        return this.construirLinks(this.estudianteService.consultarPorId(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(EstudianteRepresentation estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizar(@PathParam("id") Integer id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizar(id, estudiante);
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcial(@PathParam("id") Integer id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizarParcial(id, estudiante);
        return Response.status(209).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("/provincia")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteRepresentation> buscarPorProvincia(@QueryParam("provincia") String provincia) {
        System.out.println("Listar por PROVINCIA");
        return this.estudianteService.buscarPorProvincia(provincia);
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteRepresentation> buscarPorProvinciaGenero(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        return this.estudianteService.buscarPorProvinciaGenero(provincia, genero);
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HijosRepresentation> buscarPorIdEstudiante(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation est) {
        String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(est.id))
                .build().toString();
        String hijos = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(est.id))
                .path("hijos").build()
                .toString();
        est.links = List.of(new LinkDTO("self", self), new LinkDTO("hijos", hijos));

        return est;
    }

}
