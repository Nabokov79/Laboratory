package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.lab_nk.dto.remark.FullRemarkDto;
import ru.nabokovsg.lab_nk.dto.remark.RemarkDto;
import ru.nabokovsg.lab_nk.models.Document;
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;
import ru.nabokovsg.lab_nk.models.Remark;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RemarkMapper {

    @Mapping(source = "remarkDto.remark", target = "remark")
    @Mapping(source = "remarkDto.employeeId", target = "employeeId")
    @Mapping(source = "employee", target = "employee")
    @Mapping(source = "remarkDto.documentCorrected", target = "documentCorrected")
    @Mapping(source = "document", target = "document")
    @Mapping(source = "employees", target = "employees")
    @Mapping(source = "remarkDto.id", target = "id")
    Remark mapToRemark(RemarkDto remarkDto, String employee, Document document, Set<LaboratoryEmployee> employees);

    FullRemarkDto mapToFullRemarkDto(Remark remark);
}