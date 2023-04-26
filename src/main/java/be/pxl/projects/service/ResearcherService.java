package be.pxl.projects.service;

import be.pxl.projects.api.ContactType;
import be.pxl.projects.api.ResearcherRequest;
import be.pxl.projects.domain.ContactInformation;
import be.pxl.projects.domain.Researcher;
import be.pxl.projects.exception.ResourceNotFoundException;
import be.pxl.projects.repository.ResearcherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResearcherService {

    private final ResearcherRepository researcherRepository;

    public ResearcherService(ResearcherRepository researcherRepository) {
        this.researcherRepository = researcherRepository;
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
        }
    }

    public void deleteResearcher(long researcherId) {
        researcherRepository.deleteById(researcherId);
    }
}
