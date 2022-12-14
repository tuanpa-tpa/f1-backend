package ptit.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ptit.blog.dto.Mapper;
import ptit.blog.dto.entity.ResultDto;
import ptit.blog.dto.request.result.CreateResultReq;
import ptit.blog.dto.request.result.SearchResult;
import ptit.blog.dto.request.result.UpdateResult;
import ptit.blog.dto.request.result.UpdateResultReq;
import ptit.blog.model.f1.GrandPrix;
import ptit.blog.model.f1.RaceTeam;
import ptit.blog.model.f1.RacerOfRaceTeam;
import ptit.blog.model.f1.Result;
import ptit.blog.repository.GrandPrixRepo;
import ptit.blog.repository.RaceTeamRepo;
import ptit.blog.repository.RacerOfRaceTeamRepo;
import ptit.blog.repository.ResultRepo;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;
import ptit.blog.response.ResponseStatus;
import ptit.blog.service.ResultService;
import ptit.blog.utilservice.PaginationCustom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResultServiceImpl implements ResultService {
    private final ResultRepo resultRepo;
    private final GrandPrixRepo grandPrixRepo;
    private final RacerOfRaceTeamRepo racerOfRaceTeamRepo;
    private final PaginationCustom paginationCustom;
    private final RaceTeamRepo raceTeamRepo;

    @Override
    public ResponseObject<ResponsePagination<Object>> search(SearchResult req) {
        ResponseObject<ResponsePagination<Object>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        int page = (req.getPage() == null) ? 0 : req.getPage();
        int size = (req.getSize() == null) ? 20 : req.getSize();
        String contains = ((req.getContains() == null) || (req.getContains().equals(""))) ? null : req.getContains().trim();
        Long grandPrixId = (req.getGrandPrixId() == null) ? null : req.getGrandPrixId();
        if (contains != null) {
            String[] words = contains.split("\\s+");
            contains = String.join(" ", words);
        }
        String[] sort = ((req.getSort() == null) || (req.getSort().length == 0)) ? new String[]{"resultId,asc"} : req.getSort();
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
            Page<Result> pageHsm = resultRepo.search(contains, grandPrixId, fromDate, toDate, pageable);

            res.setData(ResponsePagination.builder()
                    .data(pageHsm.stream().map(Mapper::responseResultDtoFromModel).collect(Collectors.toList()))
                    .size(pageHsm.getSize())
                    .currentPage(pageHsm.getNumber())
                    .totalPages(pageHsm.getTotalPages())
                    .totalItems(pageHsm.getTotalElements())
                    .build()
            );
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        return res;
    }
    @Override
    public ResponseObject<Boolean> delete(Long id) {
        return null;
    }
    @Override
    public ResponseObject<ResultDto> find(Long id) {
        return null;
    }

    /**
     * @param req
     * @return
     */
    @Override
    public ResponseObject<Boolean> update(UpdateResultReq req) {
        ResponseObject<Boolean> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try {
            List<Result> results = new ArrayList<>();
            req.getUpdates().forEach((UpdateResult u) -> {
                Result result = this.resultRepo.findById(u.getResultId())
                        .orElseThrow(() -> new RuntimeException("Could not find result"));
                result.setLapFinished(u.getLapFinished());
                result.setTimeFinished(u.getTimeFinished());
                Result newResult = this.resultRepo.save(result);
                results.add(newResult);
            });
            results.sort(Comparator.comparing(Result::getTimeFinished));
            GrandPrix grandPrix = this.grandPrixRepo.findById(req.getGrandPrixId())
                    .orElseThrow(() -> new RuntimeException("Could not found getGrandPrixId"));
            int sumLap = grandPrix.getLaps();
            AtomicInteger i = new AtomicInteger(1);
            results.forEach((Result result) -> {
                if (result.getLapFinished() == sumLap) {
                    result.setRanking(i.getAndIncrement());
                    result.setPoint(pointCalculate(result));
                } else {
                    result.setRanking(0);
                    result.setPoint(pointCalculate(result));
                }
                this.resultRepo.save(result);
                RaceTeam raceTeam = result.getRacerOfRaceTeam().getRaceTeam();
                int teamPoints = raceTeam.getPoints() == null ? 0 : raceTeam.getPoints()  + result.getPoint();
                raceTeam.setPoints(teamPoints);
                this.raceTeamRepo.save(raceTeam);
            });
            res.setData(true);
        } catch (Exception e) {
            res.setData(false);
            log.error(e.getMessage());
        }
        return res;
    }
    @Override
    public ResponseObject<ResultDto> create(CreateResultReq req) {
        ResponseObject<ResultDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        RacerOfRaceTeam racerOfRaceTeam = this.racerOfRaceTeamRepo.findById(req.getRacerOfRaceTeamId())
                .orElseThrow(() -> new RuntimeException("Could not find the racer of the team"));
        GrandPrix grandPrix = this.grandPrixRepo.findById(req.getGrandPrixId())
                .orElseThrow(() -> new RuntimeException("Could not find the grandPrix"));
        Result result = Result.builder()
                .grandPrix(grandPrix)
                .racerOfRaceTeam(racerOfRaceTeam)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        try {
            this.resultRepo.save(result);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        res.setData(Mapper.responseResultDtoFromModel(result));
        return res;
    }

    public static int pointCalculate(Result result) {
        int point = 21;
        return result.getRanking() == null  ? 0 : point - result.getRanking();
    }
}
