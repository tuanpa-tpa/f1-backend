package ptit.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptit.blog.dto.request.grandfrix.SearchGrandPrix;
import ptit.blog.dto.request.result.SearchResult;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;
import ptit.blog.service.ResultService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/result")
public class ResultController {
    private final ResultService resultService;

    @PostMapping("/list")
    public ResponseEntity<?> getList(@RequestBody SearchResult req) {
        log.info("Controller: list");
        ResponseObject<ResponsePagination<Object>> res = this.resultService.search(req);
        return ResponseEntity.ok(res);
    }
}
