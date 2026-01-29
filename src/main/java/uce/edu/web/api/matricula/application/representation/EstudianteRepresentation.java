package uce.edu.web.api.matricula.application.representation;

import java.time.LocalDateTime;
import java.util.List;

public class EstudianteRepresentation {

    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDateTime fechaNac;
    public String provincia;
    public String genero;

    public List<LinkDTO> links;

}
