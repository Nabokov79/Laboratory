package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.dto.header.FullHeaderTemplateDto;
import ru.nabokovsg.template.dto.header.HeaderTemplateDto;
import ru.nabokovsg.template.exceptions.BadRequestException;
import ru.nabokovsg.template.exceptions.NotFoundException;
import ru.nabokovsg.template.mappers.HeaderTemplateMapper;
import ru.nabokovsg.template.models.HeaderTemplate;
import ru.nabokovsg.template.repository.HeaderTemplateRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HeaderTemplateServiceImpl extends ConverterToEnum implements HeaderTemplateService {

    private final HeaderTemplateRepository repository;
    private final HeaderTemplateMapper mapper;
    private final DivisionDataService divisionDataService;

    @Override
    public FullHeaderTemplateDto save(HeaderTemplateDto headerDto) {
        HeaderTemplate headerTemplate;
        switch (convertToDivisionType(headerDto.getDivisionType())) {
            case ORGANIZATION ->
                    headerTemplate = Objects.requireNonNullElseGet(
                            repository.findByHeaderDocumentIdAndOrganizationId(headerDto.getHeaderDocumentId()
                                                                             , headerDto.getDivisionId())
                    , () -> repository.save(mapper.mapOrganizationWithHeaderTemplate(headerDto.getId()
                                                                               , divisionDataService.save(headerDto))));
            case BRANCH ->
                    headerTemplate = Objects.requireNonNullElseGet(
                            repository.findByHeaderDocumentIdAndBranchId(headerDto.getHeaderDocumentId()
                                                                       , headerDto.getDivisionId())
                            , () -> repository.save(mapper.mapBranchWithHeaderTemplate(headerDto.getId()
                                                                               , divisionDataService.save(headerDto))));
            case DEPARTMENT ->
                    headerTemplate = Objects.requireNonNullElseGet(
                            repository.findByHeaderDocumentIdAndDepartmentId(headerDto.getHeaderDocumentId()
                                                                           , headerDto.getDivisionId())
                            , () -> repository.save(mapper.mapDepartmentWithHeaderTemplate(headerDto.getId()
                                                                               , divisionDataService.save(headerDto))));
            case HEAT_SUPPLE_AREA ->
                    headerTemplate = Objects.requireNonNullElseGet(
                            repository.findByHeaderDocumentIdAndHeatSupplyAreaId(headerDto.getHeaderDocumentId()
                                                                               , headerDto.getDivisionId())
                            , () -> repository.save(mapper.mapHeatSupplyAreaWithHeaderTemplate(headerDto.getId()
                                                                               , divisionDataService.save(headerDto))));
            case EXPLOITATION_REGION ->
                    headerTemplate = Objects.requireNonNullElseGet(
                            repository.findByHeaderDocumentIdAndExploitationRegionId(headerDto.getHeaderDocumentId()
                                                                                   , headerDto.getDivisionId())
                            , () -> repository.save(mapper.mapExploitationRegionWithHeaderTemplate(headerDto.getId()
                                                                               , divisionDataService.save(headerDto))));
            default -> throw new BadRequestException(
                    String.format(String.format("Unknown divisionType=%s", headerDto.getDivisionType()))
            );
        }
        return mapper.mapToFullHeaderTemplateDto(headerTemplate);
    }

    @Override
    public FullHeaderTemplateDto update(HeaderTemplateDto headerDto) {
        HeaderTemplate headerTemplate = getById(headerDto.getId());
        switch (convertToDivisionType(headerDto.getDivisionType())) {
            case ORGANIZATION -> headerTemplate = mapper.mapOrganizationWithHeaderTemplate(headerDto.getId()
                                     , divisionDataService.update(headerTemplate.getOrganization().getId(), headerDto));
            case BRANCH -> headerTemplate = mapper.mapBranchWithHeaderTemplate(headerDto.getId()
                                           , divisionDataService.update(headerTemplate.getBranch().getId(), headerDto));
            case DEPARTMENT -> headerTemplate = mapper.mapDepartmentWithHeaderTemplate(headerDto.getId()
                                       , divisionDataService.update(headerTemplate.getDepartment().getId(), headerDto));
            case HEAT_SUPPLE_AREA -> headerTemplate = mapper.mapHeatSupplyAreaWithHeaderTemplate(headerDto.getId()
                               , divisionDataService.update(headerTemplate.getExploitationRegion().getId(), headerDto));
            case EXPLOITATION_REGION -> headerTemplate =
                    mapper.mapExploitationRegionWithHeaderTemplate(headerDto.getId()
                               , divisionDataService.update(headerTemplate.getExploitationRegion().getId(), headerDto));
            default -> throw new BadRequestException(
                    String.format(String.format("Unknown divisionType=%s", headerDto.getDivisionType()))
            );
        }
        return mapper.mapToFullHeaderTemplateDto(headerTemplate);
    }

    @Override
    public FullHeaderTemplateDto get(Long id) {
        return mapper.mapToFullHeaderTemplateDto(getById(id));
    }

    @Override
    public HeaderTemplate getByTypeDocument(Long headerDocumentId) {
        return repository.findByHeaderDocumentId(headerDocumentId);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("HeaderTemplate with id=%s not found for delete", id));
    }

    public HeaderTemplate getById(Long id) {
        return repository.findById(id)
                    .orElseThrow(() -> new NotFoundException(String.format("HeaderTemplate with id=%s not found", id)));
    }
}