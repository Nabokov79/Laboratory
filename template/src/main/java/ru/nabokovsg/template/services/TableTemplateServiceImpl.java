package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.dto.table.ResponseTableTemplateDto;
import ru.nabokovsg.template.dto.table.TableTemplateDto;
import ru.nabokovsg.template.exceptions.NotFoundException;
import ru.nabokovsg.template.mappers.TableTemplateMapper;
import ru.nabokovsg.template.models.TableTemplate;
import ru.nabokovsg.template.models.enums.DocumentPartType;
import ru.nabokovsg.template.repository.TableTemplateRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableTemplateServiceImpl extends ConverterToEnum implements TableTemplateService {

    private final TableTemplateRepository repository;
    private final TableTemplateMapper mapper;
    private final ColumnHeaderTemplateService columnHeaderService;
    private final SubsectionTemplateService subsectionTemplateService;
    private final ProtocolReportTemplateService protocolReportTemplateService;
    private final ProtocolTemplateService protocolTemplateService;

    @Override
    public ResponseTableTemplateDto save(TableTemplateDto tableDto) {
        DocumentPartType type = convertToDocumentPartType(tableDto.getDocumentPartType());
        Long id = tableDto.getDocumentPartId();
        TableTemplate table = exists(type,id, tableDto.getTableName(), tableDto.getSequentialNumber());
        if (table == null) {
             table = repository.save(mapper.mapToTableTemplate(tableDto, convertToTableDataType(tableDto.getTableDataType())
                    , columnHeaderService.save(tableDto.getColumnHeaders())));
            switch (type) {
                case SUBSECTION -> subsectionTemplateService.addTable(tableDto.getDocumentPartId(), table);
                case PROTOCOL_REPORT -> protocolReportTemplateService.addTable(tableDto.getDocumentPartId(), table);
                case PROTOCOL -> protocolTemplateService.addTable(tableDto.getDocumentPartId(), table);
            }
        }
        return mapper.mapToFullTableTemplateDto(table);
    }

    @Override
    public ResponseTableTemplateDto update(TableTemplateDto tableDto) {
        if (repository.existsById(tableDto.getId())) {
            return mapper.mapToFullTableTemplateDto(repository.save(mapper.mapToTableTemplate(
                                                                tableDto
                                                              , convertToTableDataType(tableDto.getTableDataType())
                                                              , columnHeaderService.update(tableDto.getColumnHeaders()))
                    )
            );
        }
       throw new NotFoundException(String.format("Table template with id=%s not found for update", tableDto.getId()));
    }

    @Override
    public ResponseTableTemplateDto get(Long id) {
        return mapper.mapToFullTableTemplateDto(getById(id));
    }

    public TableTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Table template with id=%s not found", id)));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw  new NotFoundException(String.format("Table template with id=%s not found for delete", id));
    }

    private TableTemplate exists(DocumentPartType type, Long id, String tableName, Integer sequentialNumber) {
        TableTemplate table = null;
        switch (type) {
            case SUBSECTION -> table = repository.findBySubsection(id);
            case PROTOCOL ->  table = repository.findByProtocol(id)
                                                        .stream()
                                                        .collect(Collectors.toMap(TableTemplate::getTableName, t -> t))
                                                        .get(tableName);
            case PROTOCOL_REPORT -> { table = repository.findByProtocolReport(id)
                                                        .stream()
                                                        .collect(Collectors.toMap(TableTemplate::getTableName, t -> t))
                                                        .get(tableName);
               if (table != null && sequentialNumber != null && !table.getSequentialNumber().equals(sequentialNumber)) {
                   table = null;
               }
            }
        }
        return table;
    }
}