package be.pxl.projects.domain;

import be.pxl.projects.exception.InvalidProjectPhaseException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate start;
	@ManyToMany(mappedBy = "projects")
	private List<Researcher> researchers = new ArrayList<>();
	@Enumerated(value= EnumType.STRING)
	private ProjectPhase projectPhase;

	public Project() {
	}

	public Project(String name) {
		this.name = name;
		this.start = LocalDate.now();
		this.projectPhase = ProjectPhase.INITIATING;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public List<Researcher> getResearchers() {
		return researchers;
	}

	public ProjectPhase getProjectPhase() {
		return projectPhase;
	}

	public void setProjectPhase(ProjectPhase projectPhase) {
		if (this.projectPhase.nextState() != projectPhase) {
			throw new InvalidProjectPhaseException("Project [" + name + "] is currently in state [" + this.projectPhase + "]. Next state: [" + this.projectPhase.nextState() + "]");
		}
		this.projectPhase = projectPhase;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Project project = (Project) o;

		return id != null ? id.equals(project.id) : project.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	public void addResearcher(Researcher researcher) {
		researchers.add(researcher);
	}

	public void removeResearcher(Researcher researcher) {
		researchers.remove(researcher);
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean isClosed() {
		return ProjectPhase.CLOSING.equals(projectPhase);
	}
}
