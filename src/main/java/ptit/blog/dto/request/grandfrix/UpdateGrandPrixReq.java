package ptit.blog.dto.request.grandfrix;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ptit.blog.model.f1.RaceCourse;
import ptit.blog.model.f1.Season;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateGrandPrixReq {
    private Long grandPrixId;
    private String name;
    private int laps;
    private String time;
    private Long raceCourseId;
    private Long seasonId;
}
