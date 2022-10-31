package ptit.blog.service;

import ptit.blog.dto.entity.ResultDto;
import ptit.blog.dto.request.result.CreateResultReq;
import ptit.blog.dto.request.result.SearchResult;
import ptit.blog.dto.request.result.UpdateResultReq;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;

import java.util.List;

public interface ResultService {
    ResponseObject<ResponsePagination<Object>> search(SearchResult req);
    ResponseObject<Boolean> delete(Long id);
    ResponseObject<ResultDto> find(Long id);
    ResponseObject<Boolean> update(UpdateResultReq req);
    ResponseObject<ResultDto> create(CreateResultReq req);
}
