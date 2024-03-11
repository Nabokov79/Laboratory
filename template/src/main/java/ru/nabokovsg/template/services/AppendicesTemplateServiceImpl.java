package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.template.dto.appendices.FullAppendicesTemplateDto;
import ru.nabokovsg.template.exceptions.NotFoundException;
import ru.nabokovsg.template.mappers.AppendicesTemplateMapper;
import ru.nabokovsg.template.models.AppendicesTemplate;
import ru.nabokovsg.template.repository.AppendicesTemplateRepository;

@Service
@RequiredArgsConstructor
public class AppendicesTemplateServiceImpl implements AppendicesTemplateService {

    private final AppendicesTemplateRepository repository;
    private final AppendicesTemplateMapper mapper;
    private final ReportTemplateService reportService;
    private final ProtocolTemplateService protocolService;

    @Override
    public FullAppendicesTemplateDto save(AppendicesTemplateDto appendicesDto) {
        AppendicesTemplate appendices = repository.findByEquipmentTypeId(appendicesDto.getEquipmentTypeId());
        if (appendices == null) {
            appendices = repository.save(mapper.mapToAppendicesTemplate(appendicesDto));
            reportService.addAppendices(appendicesDto.getId(), appendices);
            protocolService.addAppendices(appendicesDto.getId(), appendices);
        }
        return mapper.mapToFullAppendicesTemplateDto(appendices);
    }

    @Override
    public FullAppendicesTemplateDto update(AppendicesTemplateDto appendicesDto) {
        if (repository.existsById(appendicesDto.getId())) {
            return mapper.mapToFullAppendicesTemplateDto(
                    repository.save(mapper.mapToAppendicesTemplate(appendicesDto))
            );
        }
        throw new NotFoundException(
                String.format("Appendices template with id=%s not found for update", appendicesDto.getId())
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw  new NotFoundException(String.format("Appendices template with id=%s not found for delete", id));
    }
}