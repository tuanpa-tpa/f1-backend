package ptit.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ptit.blog.dto.Mapper;
import ptit.blog.dto.entity.GrandPrixDto;
import ptit.blog.dto.request.grandfrix.CreateGrandPrixReq;
import ptit.blog.dto.request.grandfrix.SearchGrandPrix;
import ptit.blog.dto.request.grandfrix.UpdateGrandPrixReq;
import ptit.blog.model.f1.GrandPrix;
import ptit.blog.model.f1.RaceCourse;
import ptit.blog.model.f1.Season;
import ptit.blog.repository.GrandPrixRepo;
import ptit.blog.repository.RaceCourseRepo;
import ptit.blog.repository.SeasonRepo;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;
import ptit.blog.response.ResponseStatus;
import ptit.blog.service.GrandPrixService;
import ptit.blog.utilservice.PaginationCustom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class GrandPrixServiceImpl implements GrandPrixService {
    private final GrandPrixRepo grandPrixRepo;
    private final SeasonRepo seasonRepo;
    private final RaceCourseRepo raceCourseRepo;
    private final PaginationCustom paginationCustom;

    @Override
    public ResponseObject<ResponsePagination<Object>> search(SearchGrandPrix req) {
        ResponseObject<ResponsePagination<Object>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        int page = (req.getPage() == null) ? 0 : req.getPage();
        int size = (req.getSize() == null) ? 20 : req.getSize();
        String contains = ((req.getContains() == null) || (req.getContains().equals(""))) ? null : req.getContains().trim();
        if (contains != null) {
            String[] words = contains.split("\\s+");
            contains = String.join(" ", words);
        }
        String[] sort = ((req.getSort() == null) || (req.getSort().length == 0)) ? new String[]{"grandPrixId,asc"} : req.getSort();
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
            Page<GrandPrix> pageHsm = grandPrixRepo.search(contains, fromDate, toDate, pageable);

            res.setData(ResponsePagination.builder()
                    .data(pageHsm.stream().map(Mapper::responseGrandPrixDtoFromModel).collect(Collectors.toList()))
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
        ResponseObject<Boolean> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try {
            grandPrixRepo.deleteById(id);
            res.setData(true);
        } catch (Exception e) {
            res.setData(false);
            log.error(e.getMessage());
        }
        return res;
    }

    @Override
    public ResponseObject<GrandPrixDto> find(Long id) {
        ResponseObject<GrandPrixDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        GrandPrix grandPrix = this.grandPrixRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Could not find grandrix"));
        res.setData(Mapper.responseGrandPrixDtoFromModel(grandPrix));
        return res;
    }

    @Override
    public ResponseObject<GrandPrixDto> update(UpdateGrandPrixReq req) {
        ResponseObject<GrandPrixDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        Season season = this.seasonRepo.findById(req.getSeasonId())
                .orElseThrow(() -> new RuntimeException("Could not find season"));
        RaceCourse raceCourse = this.raceCourseRepo.findById(req.getRaceCourseId())
                .orElseThrow(() -> new RuntimeException("Could not find race course"));
        GrandPrix grandPrix = this.grandPrixRepo.findById(req.getGrandPrixId())
                .orElseThrow(()-> new RuntimeException("Could not find grandrix"));
        try {
            grandPrix.setLaps(req.getLaps());
            grandPrix.setName(req.getName());
            grandPrix.setTime(req.getTime());
            grandPrix.setSeason(season);
            grandPrix.setRaceCourse(raceCourse);
            grandPrix.setUpdatedAt(new Date());
            GrandPrix update = this.grandPrixRepo.save(grandPrix);
            res.setData(Mapper.responseGrandPrixDtoFromModel(update));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return res;
    }

    @Override
    public ResponseObject<GrandPrixDto> create(CreateGrandPrixReq req) {
        ResponseObject<GrandPrixDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        Season season = this.seasonRepo.findById(req.getSeasonId())
                .orElseThrow(() -> new RuntimeException("Could not find season"));
        RaceCourse raceCourse = this.raceCourseRepo.findById(req.getRaceCourseId())
                .orElseThrow(() -> new RuntimeException("Could not find race course"));
        try {
            GrandPrix grandPrix = GrandPrix
                    .builder()
                    .name(req.getName())
                    .laps(req.getLaps())
                    .time(req.getTime())
                    .season(season)
                    .raceCourse(raceCourse)
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();
            GrandPrix create =  this.grandPrixRepo.save(grandPrix);
            res.setData(Mapper.responseGrandPrixDtoFromModel(create));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return res;
    }
}
