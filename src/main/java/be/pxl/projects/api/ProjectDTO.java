package be.pxl.projects.api;

import be.pxl.projects.domain.Project;
import be.pxl.projects.domain.ProjectPhase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectDTO {
    private String name;
    private LocalDate start;
    private ProjectPhase projectPhase;
    private List<ResearcherDTO> researchers = new ArrayList<>();

    public ProjectDTO(Project project) {
        this.name = project.getName();
        this.start = project.getStart();
        this.projectPhase = project.getProjectPhase();
        researchers = project.getResearchers().stream().map(ResearcherDTO::new).toList();
    }

    public String getName() {
        return name;
    }

    public LocalDate getStart() {
        return start;
    }

    public List<ResearcherDTO> getResearchers() {
        return researchers;
    }

    public ProjectPhase getProjectPhase() {
        return projectPhase;
    }
}
