package ptit.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ptit.blog.dto.Mapper;
import ptit.blog.dto.entity.RacerDto;
import ptit.blog.dto.request.ranking.SearchRankingTeam;
import ptit.blog.dto.response.RaceTeamInfo;
import ptit.blog.model.f1.RaceTeam;
import ptit.blog.model.f1.Racer;
import ptit.blog.model.f1.RacerOfRaceTeam;
import ptit.blog.repository.RaceTeamRepo;
import ptit.blog.repository.RacerOfRaceTeamRepo;
import ptit.blog.repository.RacerRepo;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;
import ptit.blog.response.ResponseStatus;
import ptit.blog.service.RankingService;
import ptit.blog.utilservice.PaginationCustom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class RankingServiceImpl implements RankingService {
    private final RaceTeamRepo raceTeamRepo;
    private final RacerOfRaceTeamRepo racerOfRaceTeamRepo;
    private final RacerRepo racerRepo;
    private final PaginationCustom paginationCustom;

    @Override
    public ResponseObject<ResponsePagination<Object>> search(SearchRankingTeam req) {
        ResponseObject<ResponsePagination<Object>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        int page = (req.getPage() == null) ? 0 : req.getPage();
        int size = (req.getSize() == null) ? 20 : req.getSize();
        String contains = ((req.getContains() == null) || (req.getContains().equals(""))) ? null : req.getContains().trim();
        Long seasonId = (req.getSeasonId() == null) ? null : req.getSeasonId();
        if (contains != null) {
            String[] words = contains.split("\\s+");
            contains = String.join(" ", words);
        }
        String[] sort = ((req.getSort() == null) || (req.getSort().length == 0)) ? new String[]{"points,desc"} : req.getSort();
        //Convert fromDate and toDate String to Date
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date fromDate = null, toDate = null;
        try {
            //Create Pagable
            Pageable pageable = this.paginationCustom.createPaginationCustom(page, size, sort);
            //set default value for fromDate and toDate
            if (req.getFromDate() != null && !req.getFromDate().equals(""))
                fromDate = sf.parse(req.getFromDate() + " 00:00:00");
            if (req.getToDate() != null && !req.getToDate().equals(""))
                toDate = sf.parse(req.getToDate() + " 23:59:59");
            //Get list TokenModel from search method in repo
            Page<RaceTeam> pageRaceTeam = this.raceTeamRepo.search(contains,seasonId, fromDate, toDate, pageable);
            res.setData(ResponsePagination.builder()
                    .data(pageRaceTeam.stream().map(Mapper::responseRaceTeamDtoFromModel).collect(Collectors.toList()))
                    .size(pageRaceTeam.getSize())
                    .currentPage(pageRaceTeam.getNumber())
                    .totalPages(pageRaceTeam.getTotalPages())
                    .totalItems(pageRaceTeam.getTotalElements())
                    .build()
            );
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        return res;
    }

    @Override
    public ResponseObject<RaceTeamInfo> racerOfRaceTeam(Long raceTeamId) {
        ResponseObject<RaceTeamInfo> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);

        RaceTeam raceTeam = this.raceTeamRepo.findById(raceTeamId)
                .orElseThrow(() -> new RuntimeException("Could not find raceTeam"));
        List<RacerOfRaceTeam> racerOfRaceTeam = this.racerOfRaceTeamRepo.findByRaceTeam(raceTeamId);
        res.setData(Mapper.responseRaceTeamInfoFromModel(raceTeam, racerOfRaceTeam));
        return res;
    }

    @Override
    public ResponseObject<RacerDto> findByRacer(Long racerId) {
        ResponseObject<RacerDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        Racer racer = this.racerRepo.findById(racerId)
                .orElseThrow(() -> new RuntimeException("Could not find raceId"));
        res.setData(Mapper.responseRaceDtoFromModel(racer));
        return res;
    }
}
