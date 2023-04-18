package be.pxl.projects.api;

import be.pxl.projects.service.ResearcherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SetupController {
    private final ResearcherService researcherService;

    public SetupController(ResearcherService researcherService) {
        this.researcherService = researcherService;
    }

    @GetMapping("/setup")
    public void initData() {
        generatedData().forEach(data -> {
            String[] parts = data.split(",");
            researcherService.createResearcher(new ResearcherRequest(parts[0], parts[1], parts[2], parts[3], parts[4]));
        });
    }

    private List<String> generatedData() {
        List<String> data = new ArrayList<>();
        data.add("Rebecka,Dulany,rdulany0@istockphoto.com,450-916-7808,https://www.linkedin.com/in/rdulany");
        data.add("Aurora,Sellar,asellar1@auda.org.au,646-492-7234,https://www.linkedin.com/in/asellar");
        data.add("Carlotta,Wycliffe,cwycliffe2@rakuten.co.jp,180-245-0554,https://www.linkedin.com/in/cwycliffe");
        data.add("Karel,Lippitt,klippitt4@twitpic.com,629-517-8080,https://www.linkedin.com/in/karel-lippitt");
        data.add("Florenza,Casier,fcasierb@usnews.com,169-887-8679,https://www.linkedin.com/in/flo");
        return data;
    }
}
