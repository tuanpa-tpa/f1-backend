package ptit.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.blog.model.f1.GrandPrix;

import java.util.Date;

@Repository
public interface GrandPrixRepo extends JpaRepository<GrandPrix, Long> {
    @Query("SELECT g FROM GrandPrix g WHERE (:contains is null or (g.name LIKE %:contains%))"+
            " AND (:fromDate is null or :fromDate <= g.updatedAt)"+
            " AND (:toDate is null or :toDate >= g.updatedAt)")
    Page<GrandPrix> search(@Param("contains") String contains,
                           @Param("fromDate") Date fromDate,
                           @Param("toDate") Date toDate,
                           Pageable pageable);
}
