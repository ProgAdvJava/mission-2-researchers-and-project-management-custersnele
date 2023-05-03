package be.pxl.projects.api;

import be.pxl.projects.domain.ProjectPhase;
import be.pxl.projects.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projects")
public class ProjectController {

    private  final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(path = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> findProjectByName(@RequestParam String name) {
        ProjectDTO project = projectService.findProjectByName(name);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PutMapping("/{projectId}/phase/{projectPhase}")
    public void updateProjectPhase(@PathVariable long projectId, @PathVariable ProjectPhase projectPhase) {
        projectService.updateProjectPhase(projectId, projectPhase);
    }
}
