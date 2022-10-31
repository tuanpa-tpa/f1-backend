package ptit.blog.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ptit.blog.model.f1.Racer;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Racer} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RacerDto implements Serializable {
    private Long racerId;
    private String name;
    private Date dateOfBirth;
    private String bio;
    private Float height;
    private Float weight;
    private String national;
    private Date createdAt;
    private Date updatedAt;
}