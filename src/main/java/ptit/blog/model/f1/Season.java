package ptit.blog.model.f1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ptit.blog.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_season")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeasonId")
    private Long seasonId;

    @Column(name = "Name", columnDefinition = "VARCHAR(50) CHARACTER SET utf8")
    private String name;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    List<GrandPrix> grandPrixes;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "UpdatedAt")
    private Date updatedAt;
}