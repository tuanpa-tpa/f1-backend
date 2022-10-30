package ptit.blog.model.f1;


import lombok.*;
import ptit.blog.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_racer_of_team")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RacerOfRaceTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RacerOfRaceTeamId")
    private Long racerOfRaceTeamId;

    @ManyToOne
    @JoinColumn(name = "RacerId")
    Racer racer;

    @ManyToOne
    @JoinColumn(name = "RaceTeamId")
    RaceTeam raceTeam;

    @OneToMany(mappedBy = "racerOfRaceTeam", cascade = CascadeType.ALL)
    List<Result> results;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "UpdatedAt")
    private Date updatedAt;
}
