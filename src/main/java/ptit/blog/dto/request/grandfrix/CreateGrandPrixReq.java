package ptit.blog.dto.request.grandfrix;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateGrandPrixReq {
    private String name;
    private int laps;
    private String time;
    private Long raceCourseId;
    private Long seasonId;
}
