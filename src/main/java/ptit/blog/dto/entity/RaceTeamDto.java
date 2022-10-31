package ptit.blog.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ptit.blog.model.f1.RaceTeam;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link RaceTeam} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RaceTeamDto implements Serializable {
    private long raceTeamId;
    private String name;
    private String description;
    private Integer points;
    private Date createdAt;
    private Date updatedAt;
}