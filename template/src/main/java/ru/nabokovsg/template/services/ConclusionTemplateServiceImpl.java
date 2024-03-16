package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.template.dto.conclusion.ResponseConclusionTemplateDto;
import ru.nabokovsg.template.exceptions.NotFoundException;
import ru.nabokovsg.template.mappers.ConclusionTemplateMapper;
import ru.nabokovsg.template.models.ConclusionTemplate;
import ru.nabokovsg.template.models.enums.DocumentPartType;
import ru.nabokovsg.template.repository.ConclusionTemplateRepository;

@Service
@RequiredArgsConstructor
public class ConclusionTemplateServiceImpl extends ConverterToEnum implements ConclusionTemplateService {

    private final ConclusionTemplateRepository repository;
    private final ConclusionTemplateMapper mapper;
    private final ProtocolTemplateService protocolService;
    private final ProtocolReportTemplateService protocolReportService;

    @Override
    public ResponseConclusionTemplateDto save(ConclusionTemplateDto conclusionDto) {
        ConclusionTemplate conclusion = null;
        DocumentPartType type = convertToDocumentPartType(conclusionDto.getDocumentPartType());
        switch (type) {
            case PROTOCOL -> conclusion = repository.findByProtocolId(conclusionDto.getHeaderDocumentId());
            case PROTOCOL_REPORT -> conclusion = repository.findByProtocolReportId(conclusionDto.getHeaderDocumentId());
        }
        if (conclusion == null) {
            conclusion = repository.save(mapper.mapToConclusionTemplate(conclusionDto));
        }

        switch (type) {
            case PROTOCOL ->  protocolService.addConclusion(conclusionDto.getHeaderDocumentId(), conclusion);
            case PROTOCOL_REPORT -> protocolReportService.addConclusion(conclusionDto.getHeaderDocumentId(), conclusion);
        }
        return mapper.mapToFullConclusionTemplateDto(conclusion);
    }

    @Override
    public ResponseConclusionTemplateDto update(ConclusionTemplateDto conclusionDto) {
        if (repository.existsById(conclusionDto.getId())) {
            return mapper.mapToFullConclusionTemplateDto(
                    repository.save(mapper.mapToConclusionTemplate(conclusionDto))
            );
        }
       throw new NotFoundException(String.format("Conclusion template with id=%s not found", conclusionDto.getId()));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw  new NotFoundException(String.format("Conclusion template with id=%s not found for delete", id));
    }
}