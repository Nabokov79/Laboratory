package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.client.LNKClient;
import ru.nabokovsg.template.client.dto.LaboratoryEmployeeDto;
import ru.nabokovsg.template.dto.pageTitle.FullPageTitleTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.template.exceptions.NotFoundException;
import ru.nabokovsg.template.mappers.PageTitleTemplateMapper;
import ru.nabokovsg.template.models.PageTitleTemplate;
import ru.nabokovsg.template.repository.PageTitleTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PageTitleTemplateServiceImpl implements PageTitleTemplateService {

    private final PageTitleTemplateRepository repository;
    private final PageTitleTemplateMapper mapper;
    private final HeaderTemplateService headerService;
    private final ReportTemplateService reportService;
    private final StringBuilderService stringBuilder;
    private final LNKClient client;

    @Override
    public FullPageTitleTemplateDto save(PageTitleTemplateDto pageTitleDto) {
        PageTitleTemplate pageTitle = reportService.getByParam(pageTitleDto.getHeaderDocumentId()
                                                      , pageTitleDto.getEquipmentTypeId())
                                                       .getPageTitle();
        if (pageTitle == null) {
            pageTitle = repository.save(mapping(pageTitleDto));
            reportService.addPageTitleTemplate(pageTitleDto.getHeaderDocumentId()
                                             , pageTitleDto.getEquipmentTypeId()
                                             , pageTitle);
        }
        return mapper.mapToFullPageTitleTemplateDto(pageTitle);
    }

    @Override
    public FullPageTitleTemplateDto update(PageTitleTemplateDto pageTitleDto) {
        if (repository.existsById(pageTitleDto.getId())) {
            return mapper.mapToFullPageTitleTemplateDto(repository.save(mapping(pageTitleDto)));
        }
        throw new NotFoundException(
                String.format("Page title template with id=%s not found for update", pageTitleDto.getId())
        );
    }

    @Override
    public List<ShortPageTitleTemplateDto> getAll() {
        return repository.findAll().stream().map(mapper::mapToShortPageTitleTemplateDto).toList();
    }

    private PageTitleTemplate mapping(PageTitleTemplateDto pageTitleDto) {
        LaboratoryEmployeeDto employeeDto = client.getLaboratoryEmployee(pageTitleDto.getEmployeeId());
        return mapper.mapToPageTitleTemplate(
                pageTitleDto
                , headerService.getByTypeDocument(pageTitleDto.getHeaderDocumentId())
                , client.getHeaderDocument(pageTitleDto.getHeaderDocumentId())
                , employeeDto.getPost()
                , stringBuilder.buildFromEmployeeInitials(employeeDto));
    }
}