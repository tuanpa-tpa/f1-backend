package ptit.blog.model.f1;

import lombok.*;
import ptit.blog.model.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tbl_grand_prix")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GrandPrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GrandPrixId")
    private Long grandPrixId;

    @Column(name = "Name", columnDefinition = "VARCHAR(50) CHARACTER SET utf8")
    private String name;
    @Column(name = "Laps", columnDefinition = "VARCHAR(50) CHARACTER SET utf8")
    private int laps;
    @Column(name = "Time")
    private LocalDateTime time;

    @OneToMany(mappedBy = "grandPrix")
    Set<Result> results;
    @ManyToOne
    @JoinColumn(name = "RaceCourseId", referencedColumnName = "RaceCourseId")
    private RaceCourse raceCourse;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SeasonId", referencedColumnName = "SeasonId")
    private Season season;
}