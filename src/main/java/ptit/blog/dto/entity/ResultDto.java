package ptit.blog.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ptit.blog.model.f1.Result;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Result} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultDto implements Serializable {
    private Long resultId;
    private String finishTime;
    private Integer lapFinished;
    private String racerName;
    private String raceTeam;
    private Date createdAt;
    private Date updatedAt;
}