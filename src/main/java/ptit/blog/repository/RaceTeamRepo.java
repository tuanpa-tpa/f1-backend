package ptit.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.blog.model.f1.RaceTeam;

import java.util.Date;

@Repository
public interface RaceTeamRepo extends JpaRepository<RaceTeam, Long> {
    @Query("SELECT t FROM RaceTeam t WHERE (:contains is null or (t.name LIKE %:contains%))" +
            " AND (:seasonId is null or :seasonId = t.season.seasonId)"+
            " AND (:fromDate is null or :fromDate <= t.updatedAt)"+
            " AND (:toDate is null or :toDate >= t.updatedAt)")
    Page<RaceTeam> search(@Param("contains") String contains,
                           @Param("seasonId") Long seasonId,
                           @Param("fromDate") Date fromDate,
                           @Param("toDate") Date toDate,
                           Pageable pageable);
}
