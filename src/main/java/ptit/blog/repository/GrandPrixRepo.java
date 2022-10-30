package ptit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.blog.model.f1.GrandPrix;

@Repository
public interface GrandPrixRepo extends JpaRepository<GrandPrix, Long> {
}
