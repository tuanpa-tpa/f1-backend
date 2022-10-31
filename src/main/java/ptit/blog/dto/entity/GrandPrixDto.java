package ptit.blog.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GrandPrixDto implements Serializable {
    private Long grandPrixId;
    private String name;
    private int laps;
    private String time;
    private Long seasonId;
    private Long raceCourseId;
}