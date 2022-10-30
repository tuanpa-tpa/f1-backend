package ptit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.blog.model.f1.RaceCourse;

@Repository
public interface RaceCourseRepo extends JpaRepository<RaceCourse, Long> {
}
