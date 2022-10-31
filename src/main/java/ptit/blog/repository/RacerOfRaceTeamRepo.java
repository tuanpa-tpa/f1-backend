package ptit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.blog.model.f1.RacerOfRaceTeam;

import java.util.List;

@Repository
public interface RacerOfRaceTeamRepo extends JpaRepository<RacerOfRaceTeam, Long> {
    @Query("SELECT r FROM RacerOfRaceTeam r WHERE r.raceTeam.raceTeamId = :raceTeamId")
    List<RacerOfRaceTeam> findByRaceTeam(@Param("raceTeamId") Long teamId);
}
