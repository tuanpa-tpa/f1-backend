package ptit.blog.dto.request.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateResultReq {
    private Long racerOfRaceTeamId;
    private Long grandPrixId;
    private Date createdAt;
    private Date updatedAt;
}
