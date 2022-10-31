package ptit.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.blog.model.f1.GrandPrix;
import ptit.blog.model.f1.Result;

import java.util.Date;

@Repository
public interface ResultRepo extends JpaRepository<Result, Long> {
    @Query("SELECT r FROM Result r WHERE (:contains is null)" +
            " AND (:grandPrixId is null or :grandPrixId = r.grandPrix.grandPrixId)"+
            " AND (:fromDate is null or :fromDate <= r.updatedAt)"+
            " AND (:toDate is null or :toDate >= r.updatedAt)")
    Page<Result> search(@Param("contains") String contains,
                           @Param("grandPrixId") Long grandPrixId,
                           @Param("fromDate") Date fromDate,
                           @Param("toDate") Date toDate,
                           Pageable pageable);
}
