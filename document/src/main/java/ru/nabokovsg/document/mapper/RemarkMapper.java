package ru.nabokovsg.document.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.document.dto.remark.ResponseRemarkDto;
import ru.nabokovsg.document.models.Document;
import ru.nabokovsg.document.models.Remark;
import ru.nabokovsg.document.models.Subscriber;

@Mapper(componentModel = "spring")
public interface RemarkMapper {

    @Mapping(source = "remark", target = "remark")
    @Mapping(source = "inspector", target = "inspector")
    @Mapping(source = "document", target = "document")
    @Mapping(target = "id", ignore = true)
    Remark mapToRemark(String remark, Subscriber inspector, Document document);

    ResponseRemarkDto mapToFullRemarkDto(Remark remark);
}