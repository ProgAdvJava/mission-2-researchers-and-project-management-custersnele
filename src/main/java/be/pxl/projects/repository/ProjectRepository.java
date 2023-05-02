package be.pxl.projects.repository;

import be.pxl.projects.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByName(String name);
}
