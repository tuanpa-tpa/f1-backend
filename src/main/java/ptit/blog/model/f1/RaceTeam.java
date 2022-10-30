package ptit.blog.model.f1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ptit.blog.model.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tbl_race_team")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RaceTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RaceTeamId")
    private long raceTeamId;

    @Column(name = "Name", columnDefinition = "VARCHAR(50) CHARACTER SET utf8")
    private String name;

    @Column(name = "Description", columnDefinition = "VARCHAR(255) CHARACTER SET utf8")
    private String description;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "UpdatedAt")
    private Date updatedAt;
}
