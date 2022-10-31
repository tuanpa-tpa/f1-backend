package ptit.blog.service;

import ptit.blog.dto.entity.RacerDto;
import ptit.blog.dto.request.ranking.SearchRankingTeam;
import ptit.blog.dto.response.RaceTeamInfo;
import ptit.blog.model.f1.Racer;
import ptit.blog.model.f1.RacerOfRaceTeam;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;

import java.util.List;

public interface RankingService {
    ResponseObject<ResponsePagination<Object>> search(SearchRankingTeam req);
    ResponseObject<RaceTeamInfo> racerOfRaceTeam(Long raceTeamId);
    ResponseObject<RacerDto> findByRacer(Long racerId);
}
