package ptit.blog.dto.request.grandfrix;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchGrandPrix {
    private Integer page;
    private Integer size;
    private String[] sort;
    private String contains;
    private String fromDate;
    private String toDate;
}
