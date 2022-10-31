package ptit.blog.service;

import ptit.blog.dto.entity.GrandPrixDto;
import ptit.blog.dto.entity.ResultDto;
import ptit.blog.dto.request.grandfrix.CreateGrandPrixReq;
import ptit.blog.dto.request.grandfrix.SearchGrandPrix;
import ptit.blog.dto.request.grandfrix.UpdateGrandPrixReq;
import ptit.blog.dto.request.result.CreateResultReq;
import ptit.blog.dto.request.result.SearchResult;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;

public interface ResultService {
    ResponseObject<ResponsePagination<Object>> search(SearchResult req);
    ResponseObject<Boolean> delete(Long id);
    ResponseObject<ResultDto> find(Long id);
    ResponseObject<ResultDto> update(UpdateGrandPrixReq req);
    ResponseObject<ResultDto> create(CreateResultReq req);
}
