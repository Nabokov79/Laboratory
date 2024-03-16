package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.template.dto.table.ResponseTableTemplateDto;
import ru.nabokovsg.template.dto.table.TableTemplateDto;
import ru.nabokovsg.template.models.ColumnHeaderTemplate;
import ru.nabokovsg.template.models.TableTemplate;
import ru.nabokovsg.template.models.enums.TableDataType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableTemplateMapper {

    @Mapping(source = "tableDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "tableDataType", target = "tableDataType")
    @Mapping(source = "tableDto.tableName", target = "tableName")
    @Mapping(source = "columnHeaders", target = "columnHeaders")
    @Mapping(source = "tableDto.id", target = "id")
    TableTemplate mapToTableTemplate(TableTemplateDto tableDto, TableDataType tableDataType
                                    , List<ColumnHeaderTemplate> columnHeaders);

    ResponseTableTemplateDto mapToFullTableTemplateDto(TableTemplate table);
}