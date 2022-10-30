package ptit.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ptit.blog.dto.Mapper;
import ptit.blog.dto.entity.GrandPrixDto;
import ptit.blog.dto.entity.SeasonDto;
import ptit.blog.model.f1.Season;
import ptit.blog.repository.SeasonRepo;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponseStatus;
import ptit.blog.service.SeasonService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeasonServiceImpl implements SeasonService {
    private final SeasonRepo seasonRepo;


    @Override
    public ResponseObject<List<SeasonDto>> getAll() {
        ResponseObject<List<SeasonDto>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        List<Season> seasons = seasonRepo.findAll();
        res.setData(seasons.stream().map(Mapper::responseSeasonDtoFromModel).collect(Collectors.toList()));
        return res;
    }
}
