package ptit.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ptit.blog.dto.entity.GrandPrixDto;
import ptit.blog.dto.entity.SeasonDto;
import ptit.blog.dto.request.grandfrix.CreateGrandPrixReq;
import ptit.blog.dto.request.grandfrix.SearchGrandPrix;
import ptit.blog.dto.request.grandfrix.UpdateGrandPrixReq;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;
import ptit.blog.service.GrandPrixService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value ="/grandPrix")
public class GrandPrixController {
    private final GrandPrixService grandPrixService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> create(@RequestBody CreateGrandPrixReq createGrandPrixReq) {
        log.info("Controller: create");
        ResponseObject<GrandPrixDto> res = grandPrixService.create(createGrandPrixReq);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<?> deleteGrandPrix(@PathVariable Long id) {
        log.info("Controller: delete");
        ResponseObject<Boolean> res = this.grandPrixService.delete(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/list")
    public ResponseEntity<?> getList(@RequestBody SearchGrandPrix req) {
        log.info("Controller: list");
        ResponseObject<ResponsePagination<Object>> res = this.grandPrixService.search(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER','SUPER_ADMIN')")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        log.info("Controller: get details");
        ResponseObject<GrandPrixDto> res = this.grandPrixService.find(id);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGrandPrix(@RequestBody UpdateGrandPrixReq req) {
        log.info("Controller: update");
        ResponseObject<GrandPrixDto> res = this.grandPrixService.update(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        log.info("Controller: get-all");
        ResponseObject<List<GrandPrixDto>> res = this.grandPrixService.getAll();
        return ResponseEntity.ok(res);
    }

}
