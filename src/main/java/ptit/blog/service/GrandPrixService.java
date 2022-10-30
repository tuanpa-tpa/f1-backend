package ptit.blog.service;

import ptit.blog.dto.entity.GrandPrixDto;
import ptit.blog.dto.request.grandfrix.CreateGrandPrixReq;
import ptit.blog.dto.request.grandfrix.SearchGrandPrix;
import ptit.blog.dto.request.grandfrix.UpdateGrandPrixReq;
import ptit.blog.response.ResponseObject;
import ptit.blog.response.ResponsePagination;

public interface GrandPrixService {
    ResponseObject<ResponsePagination<Object>> search(SearchGrandPrix req);
    ResponseObject<Boolean> delete(Long id);
    ResponseObject<GrandPrixDto> find(Long id);
    ResponseObject<GrandPrixDto> update(UpdateGrandPrixReq req);
    ResponseObject<GrandPrixDto> create(CreateGrandPrixReq req);
}
