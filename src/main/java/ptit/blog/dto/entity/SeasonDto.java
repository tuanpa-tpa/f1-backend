package ptit.blog.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link ptit.blog.model.f1.Season} entity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeasonDto implements Serializable {
    private Long seasonId;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}