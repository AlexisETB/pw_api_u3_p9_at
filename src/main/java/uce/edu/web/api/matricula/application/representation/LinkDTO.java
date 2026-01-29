package uce.edu.web.api.matricula.application.representation;

public class LinkDTO {

    public String rel;
    public String href;

    public LinkDTO() {
    }

    public LinkDTO(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }

}
