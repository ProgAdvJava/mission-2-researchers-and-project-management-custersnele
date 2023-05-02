package be.pxl.projects.api;

import be.pxl.projects.domain.Researcher;

public class ResearcherDTO {
    private String firstname;
    private String lastname;
    private String email;

    public ResearcherDTO(Researcher researcher) {
        firstname = researcher.getFirstname();
        lastname = researcher.getLastname();
        email = researcher.getContactInformation().getEmail();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
}
