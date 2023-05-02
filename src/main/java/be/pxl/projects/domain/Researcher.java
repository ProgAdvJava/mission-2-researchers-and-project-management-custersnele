package be.pxl.projects.domain;

import be.pxl.projects.exception.InvalidProjectPhaseException;
import be.pxl.projects.exception.ResearcherAlreadyAssignedToProjectException;
import be.pxl.projects.exception.ResearcherNotAssignedToProjectException;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Researcher {

	private static final Logger LOGGER = LogManager.getLogger(Researcher.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 40, nullable = false)
	private String firstname;
	@Column(length = 40, nullable = false)
	private String lastname;
	@OneToOne(cascade = CascadeType.ALL)
	private ContactInformation contactInformation;
	@ManyToMany
	private List<Project> projects = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Researcher that = (Researcher) o;

		return id != null ? id.equals(that.id) : that.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	// TODO: write unit tests for this method
	public void addProject(Project project) {
		if (projects.contains(project)) {
			// TODO researcher already assigned to project
			LOGGER.info("Researcher [" + id + "] already assigned to [" + project.getName() + "]");
			throw new ResearcherAlreadyAssignedToProjectException("Researcher [" + id + "] already assigned to [" + project.getName() + "]");
		}
		if (project.isClosed()) {
			throw new InvalidProjectPhaseException("Project [" + project.getName() + "] is already closed.");
		}
		projects.add(project);
		project.addResearcher(this);
	}

	public void removeProject(Project project) {
		if (!projects.contains(project)) {
			throw new ResearcherNotAssignedToProjectException("Researcher [" + id + "] is not assigned to [" + project.getName() + "]");
		}
		if (project.isClosed()) {
			throw new InvalidProjectPhaseException("Project [" + project.getName() + "] is already closed.");
		}
		projects.remove(project);
		project.removeResearcher(this);
	}

	@Override
	public String toString() {
		return String.format("%s %s (%d)", firstname, lastname, id);
	}
}
