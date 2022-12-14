package ptit.blog.dto.request.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateResultReq {
    private Long grandPrixId;
    List<UpdateResult> updates;
}
