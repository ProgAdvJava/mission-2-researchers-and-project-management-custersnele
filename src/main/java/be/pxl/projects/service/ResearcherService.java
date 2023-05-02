package be.pxl.projects.service;

import be.pxl.projects.api.ContactType;
import be.pxl.projects.api.ResearcherRequest;
import be.pxl.projects.domain.ContactInformation;
import be.pxl.projects.domain.Project;
import be.pxl.projects.domain.Researcher;
import be.pxl.projects.exception.ResourceNotFoundException;
import be.pxl.projects.repository.ProjectRepository;
import be.pxl.projects.repository.ResearcherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResearcherService {

    private final ResearcherRepository researcherRepository;
    private final ProjectRepository projectRepository;

    public ResearcherService(ResearcherRepository researcherRepository, ProjectRepository projectRepository) {
        this.researcherRepository = researcherRepository;
        this.projectRepository = projectRepository;
    }

    public long createResearcher(ResearcherRequest researcherRequest) {
        Researcher researcher = new Researcher();
        researcher.setFirstname(researcherRequest.firstname());
        researcher.setLastname(researcherRequest.lastname());
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail(researcherRequest.email());
        contactInformation.setPhone(researcherRequest.phone());
        contactInformation.setLinkedIn(researcherRequest.linkedIn());
        researcher.setContactInformation(contactInformation);
        return researcherRepository.save(researcher).getId();
    }

    @Transactional
    public void updateContactInformation(long researcherId, ContactType field, String value) {
        Researcher researcher = researcherRepository.findById(researcherId).orElseThrow(() -> new ResourceNotFoundException("Researcher", "id", researcherId));
        switch (field) {
            case EMAIL -> researcher.getContactInformation().setEmail(value);
            case PHONE -> researcher.getContactInformation().setPhone(value);
            case LINKEDIN -> researcher.getContactInformation().setLinkedIn(value);
        }
    }

    @Transactional
    public void addResearcherToProject(long researcherId, String projectName) {
        Researcher researcher = researcherRepository.findById(researcherId).orElseThrow(() -> new ResourceNotFoundException("Researcher", "Id", researcherId));
        Project project = projectRepository.findByName(projectName).orElseGet(() ->  {
            Project newProject = new Project(projectName);
            projectRepository.save(newProject);
            return newProject;
        });
        researcher.addProject(project);
    }

    @Transactional
    public void removeResearcherFromProject(long researcherId, String projectName) {
        Researcher researcher = researcherRepository.findById(researcherId).orElseThrow(() -> new ResourceNotFoundException("Researcher", "Id", researcherId));
        Project project = projectRepository.findByName(projectName).orElseThrow(() ->  new ResourceNotFoundException("Project", "Name", projectName));
        researcher.removeProject(project);
    }

    public void deleteResearcher(long researcherId) {
        researcherRepository.deleteById(researcherId);
    }
}
