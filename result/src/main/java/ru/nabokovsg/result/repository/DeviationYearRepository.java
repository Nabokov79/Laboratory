package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.result.models.DeviationYear;

import java.util.List;
import java.util.Set;

public interface DeviationYearRepository extends JpaRepository<DeviationYear, Long> {

    @Query("select d from DeviationYear d where d.referencePoint.id in :ids")
    Set<DeviationYear> findAllByReferencePointId(@Param("ids") List<Long> ids);
}