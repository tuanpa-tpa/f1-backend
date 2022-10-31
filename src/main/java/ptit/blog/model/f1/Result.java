package ptit.blog.model.f1;

import lombok.*;
import ptit.blog.model.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Column(name = "ResultId")
    private Long resultId;

    @Column(name = "Point")
    private Integer point;

    @Column (name = "Ranking")
    private Integer ranking;

    @Column(name = "TimeFinished")
    private String timeFinished;

    @Column(name="LapFinished")
    private Integer lapFinished;
    @ManyToOne( cascade = CascadeType.REMOVE)
    @JoinColumn(name = "RacerOfRaceTeamId", referencedColumnName = "RacerOfRaceTeamId")
    private RacerOfRaceTeam racerOfRaceTeam;
    @ManyToOne( cascade = CascadeType.REMOVE)
    @JoinColumn( name = "GrandPrixId", referencedColumnName = "GrandPrixId")
    private GrandPrix grandPrix;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "UpdatedAt")
    private Date updatedAt;
}
