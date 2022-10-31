package ptit.blog.model.f1;

import lombok.*;
import ptit.blog.model.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
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
    private String time;

    @OneToMany(mappedBy = "grandPrix", orphanRemoval = true, cascade = CascadeType.ALL)
    Set<Result> results;
    @ManyToOne
    @JoinColumn(name = "RaceCourseId", referencedColumnName = "RaceCourseId")
    private RaceCourse raceCourse;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "SeasonId", referencedColumnName = "SeasonId")
    private Season season;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "UpdatedAt")
    private Date updatedAt;
}