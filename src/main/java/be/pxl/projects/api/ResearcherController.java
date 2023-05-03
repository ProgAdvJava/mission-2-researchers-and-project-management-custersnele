package be.pxl.projects.api;

import be.pxl.projects.service.ResearcherService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("researchers")
public class ResearcherController {

    private final ResearcherService researcherService;

    public ResearcherController(ResearcherService researcherService) {
        this.researcherService = researcherService;
    }

    @PutMapping("{researcherId}/{contactType}")
    public void updateContactInformation(@PathVariable Long researcherId,
                                         @PathVariable ContactType contactType,
                                         @RequestBody @Valid ContactTypeValue contactTypeValue) {
        researcherService.updateContactInformation(researcherId, contactType, contactTypeValue.value());
    }

    @PostMapping("{researcherId}/join")
    public void assignResearcherToProject(@PathVariable Long researcherId, @RequestBody @Valid ProjectRequest projectRequest) {
        researcherService.addResearcherToProject(researcherId, projectRequest.project());
    }

    @PostMapping("{researcherId}/leave")
    public void removeResearcherFromProject(@PathVariable Long researcherId, @RequestBody @Valid ProjectRequest projectRequest) {
        researcherService.removeResearcherFromProject(researcherId, projectRequest.project());
    }

    @DeleteMapping("{researcherId}")
    public void deleteResearcher(@PathVariable long researcherId) {
        researcherService.deleteResearcher(researcherId);
    }

}
