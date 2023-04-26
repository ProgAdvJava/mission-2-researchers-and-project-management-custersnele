package be.pxl.projects.repository;

import be.pxl.projects.domain.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearcherRepository extends JpaRepository<Researcher, Long> {
}
