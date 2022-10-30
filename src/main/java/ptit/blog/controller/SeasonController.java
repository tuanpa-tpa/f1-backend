package ptit.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.blog.dto.entity.SeasonDto;
import ptit.blog.dto.request.grandfrix.SearchGrandPrix;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;
import ptit.blog.service.SeasonService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/season")
public class SeasonController {
    private final SeasonService seasonService;


    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        log.info("Controller: get-all season");
        ResponseObject<List<SeasonDto>> res = this.seasonService.getAll();
        return ResponseEntity.ok(res);
    }

}
