package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.result.models.SizeParameters;

import java.util.List;
import java.util.Set;

public interface SizeParametersRepository extends JpaRepository<SizeParameters, Long> {

    @Query("select p from SizeParameters p where p.parametersName in :parametersNames")
    Set<SizeParameters> findAllDefectParameterByParametersName(@Param("parametersNames") List<String> parametersNames);
}