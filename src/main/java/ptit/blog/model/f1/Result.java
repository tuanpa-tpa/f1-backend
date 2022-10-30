package ptit.blog.model.f1;

import lombok.*;
import ptit.blog.model.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_result")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @Column
    private Integer point;
    @Column
    private Integer ranking;
    @Column
    private LocalDateTime finishTime;
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "RacerOfRaceTeamId", referencedColumnName = "RacerOfRaceTeamId")
    private RacerOfRaceTeam racerOfRaceTeam;
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn( name = "GrandPrixId", referencedColumnName = "GrandPrixId")
    private GrandPrix grandPrix;
}
