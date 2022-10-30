package ptit.blog.model.f1;

import lombok.*;
import ptit.blog.model.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_race_course")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RaceCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RaceCourseId")
    private Long raceCourseId;

    @Column(name = "Name", columnDefinition = "VARCHAR(50) CHARACTER SET utf8")
    private String name;

    @OneToMany(mappedBy = "raceCourse")
    Set<GrandPrix> grandPrixes;
}