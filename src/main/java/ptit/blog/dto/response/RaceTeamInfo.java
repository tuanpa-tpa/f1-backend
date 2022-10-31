package ptit.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ptit.blog.dto.entity.RacerDto;
import ptit.blog.model.f1.Racer;
import ptit.blog.model.f1.RacerOfRaceTeam;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RaceTeamInfo {
    private String name;
    private String description;
    private Integer points;
    private Date createdAt;
    private Date updatedAt;
    List<RacerDto> racers;
}
