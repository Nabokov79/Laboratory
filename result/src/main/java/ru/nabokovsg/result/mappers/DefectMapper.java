package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.result.dto.defects.DefectDto;
import ru.nabokovsg.result.dto.defects.FullDefectDto;
import ru.nabokovsg.result.models.Defect;

@Mapper(componentModel = "spring")
public interface DefectMapper {

    Defect mapToDefect(DefectDto defectDto);

    FullDefectDto mapToFullDefectDto(Defect defect);
}