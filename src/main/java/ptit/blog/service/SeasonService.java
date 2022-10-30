package ptit.blog.service;

import ptit.blog.dto.entity.SeasonDto;
import ptit.blog.response.ResponseObject;

import java.util.List;

public interface SeasonService {
    ResponseObject<List<SeasonDto>> getAll();
}
