package ptit.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.blog.dto.entity.ResultDto;
import ptit.blog.dto.request.result.CreateResultReq;
import ptit.blog.dto.request.result.SearchResult;
import ptit.blog.dto.request.result.UpdateResult;
import ptit.blog.dto.request.result.UpdateResultReq;
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

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateResultReq req) {
        log.info("Controller: create");
        ResponseObject<ResultDto> res = this.resultService.create(req);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateResultReq req) {
        log.info("Controller: update");
        ResponseObject<Boolean> res = this.resultService.update(req);
        return ResponseEntity.ok(res);
    }
}
