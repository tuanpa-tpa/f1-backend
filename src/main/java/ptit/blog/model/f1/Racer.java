package ptit.blog.model.f1;

import lombok.*;
import ptit.blog.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_racer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Racer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RacerId")
    private Long racerId;

    @Column(name = "Name", columnDefinition = "VARCHAR(50) CHARACTER SET utf8")
    private String name;
    @Column(name = "DateOfBirth")

    private Date dateOfBirth;

    @Column(name = "Bio", columnDefinition = "VARCHAR(255) CHARACTER SET utf8")
    private String bio;

    @Column(name = "Height")
    private Float height;
    @Column(name = "Weight")
    private Float weight;

    @Column(name = "National", columnDefinition = "VARCHAR(255) CHARACTER SET utf8")
    private String national;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "UpdatedAt")
    private Date updatedAt;
}
