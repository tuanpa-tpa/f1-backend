package ptit.blog.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.blog.dto.entity.RacerDto;
import ptit.blog.dto.request.grandfrix.SearchGrandPrix;
import ptit.blog.dto.request.ranking.SearchRankingTeam;
import ptit.blog.dto.response.RaceTeamInfo;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;
import ptit.blog.service.RankingService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/ranking")
public class RankingController {
    private final RankingService rankingService;
    @PostMapping("/team")
    public ResponseEntity<?> team(@RequestBody SearchRankingTeam req) {
        log.info("Controller: team");
        ResponseObject<ResponsePagination<Object>> res = this.rankingService.search(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping ("/info/{id}")
    public ResponseEntity<?> info(@PathVariable Long id) {
        log.info("Controller: team info");
        ResponseObject<RaceTeamInfo> res = this.rankingService.racerOfRaceTeam(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping ("/racer/{id}")
    public ResponseEntity<?> racerInfo(@PathVariable Long id) {
        log.info("Controller: racer info");
        ResponseObject<RacerDto> res = this.rankingService.findByRacer(id);
        return ResponseEntity.ok(res);
    }

}
