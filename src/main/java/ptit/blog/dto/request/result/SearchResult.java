package ptit.blog.dto.request.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SearchResult {
    private Integer page;
    private Integer size;
    private String[] sort;
    private String contains;
    private String fromDate;
    private String toDate;
}
