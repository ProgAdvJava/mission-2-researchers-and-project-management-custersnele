package be.pxl.projects.service;


import be.pxl.projects.api.ContactType;
import be.pxl.projects.api.ResearcherRequest;
import org.springframework.stereotype.Service;

@Service
public class ResearcherService {

    // TODO add ResearcherRepository here

    public long createResearcher(ResearcherRequest researcherRequest) {
        // Create Researcher-entity with ContactInformation-entity
        // save Researcher-entity (make sure the contact information is saved as well)
        return 0L;
    }

    public void updateContactInformation(long researcherId, ContactType field, String value) throws NoSuchMethodException {
        // retrieve researcher by researcherid
        switch (field) {
            case EMAIL -> throw new NoSuchMethodException("Implement this method."); // update email
            case PHONE -> throw new NoSuchMethodException("Implement this method.");// update phone
            case LINKEDIN -> throw new NoSuchMethodException("Implement this method.");// update linked in url
        }
    }

    public void deleteResearcher(long researcherId) {
        // delete the researcher with the given researcher id
    }
}
