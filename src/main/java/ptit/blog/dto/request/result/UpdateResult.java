package ptit.blog.dto.request.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateResult {
    private Long resultId;
    private String timeFinished;
    private int lapFinished;
}
