package be.pxl.projects.service;

import be.pxl.projects.api.ProjectDTO;
import be.pxl.projects.domain.Project;
import be.pxl.projects.domain.ProjectPhase;
import be.pxl.projects.exception.ResourceNotFoundException;
import be.pxl.projects.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public void updateProjectPhase(long projectId, ProjectPhase projectPhase) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project", "Id", projectId));
        project.setProjectPhase(projectPhase);

    }

    @Transactional(readOnly = true)
    public ProjectDTO findProjectByName(String name) {
        Project project = projectRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Project", "Name", name));
        return new ProjectDTO(project);
    }
}
