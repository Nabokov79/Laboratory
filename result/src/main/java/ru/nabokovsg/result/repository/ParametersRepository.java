package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.result.models.Parameters;

import java.util.List;
import java.util.Set;

public interface ParametersRepository extends JpaRepository<Parameters, Long> {

    @Query("select p from Parameters p where p.parameterName in :parametersNames")
    Set<Parameters> findAllDefectParameterByParametersName(@Param("parametersNames") List<String> parametersNames);
}