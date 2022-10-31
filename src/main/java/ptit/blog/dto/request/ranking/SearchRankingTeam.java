package ptit.blog.dto.request.ranking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchRankingTeam {
    private Integer page;
    private Integer size;
    private String[] sort;
    private String contains;
    private String fromDate;
    private String toDate;
    private Long SeasonId;
}
