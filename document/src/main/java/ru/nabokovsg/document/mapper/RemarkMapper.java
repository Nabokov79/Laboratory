package ru.nabokovsg.document.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.document.dto.remark.FullRemarkDto;
import ru.nabokovsg.document.models.Document;
import ru.nabokovsg.document.models.Remark;
import ru.nabokovsg.document.models.Subscriber;
import ru.nabokovsg.document.models.enums.RemarkStatus;

@Mapper(componentModel = "spring")
public interface RemarkMapper {

    @Mapping(source = "remark", target = "remark")
    @Mapping(source = "inspector", target = "inspector")
    @Mapping(source = "verificationStatus", target = "verificationStatus")
    @Mapping(source = "correctionStatus", target = "correctionStatus")
    @Mapping(source = "document", target = "document")
    @Mapping(source = "employees", target = "employees")
    Remark mapToRemark(String remark
                     , Subscriber inspector
                     , RemarkStatus verificationStatus
                     , RemarkStatus correctionStatus
                     , Document document);

    FullRemarkDto mapToFullRemarkDto(Remark remark);
}