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
    public void updateContactInformation(@PathVariable Long researcherId, @PathVariable ContactType contactType, @RequestBody @Valid ContactTypeValue contactTypeValue) {
        researcherService.updateContactInformation(researcherId, contactType, contactTypeValue.value());
    }
}
