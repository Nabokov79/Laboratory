package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.client.CompanyClient;
import ru.nabokovsg.template.client.dto.DivisionDto;
import ru.nabokovsg.template.dto.header.HeaderTemplateDto;
import ru.nabokovsg.template.mappers.DivisionDataMapper;
import ru.nabokovsg.template.models.DivisionData;
import ru.nabokovsg.template.repository.DivisionDataRepository;

@Service
@RequiredArgsConstructor
public class DivisionDataServiceImpl extends ConverterToEnum implements DivisionDataService {

    private final DivisionDataRepository repository;
    private final DivisionDataMapper mapper;
    private final CompanyClient client;
    private final StringBuilderService stringBuilder;

    @Override
    public DivisionData save(HeaderTemplateDto headerDto) {
        return repository.save(create(headerDto));
    }

    @Override
    public DivisionData update(Long divisionId, HeaderTemplateDto headerDto) {
        return repository.save(mapper.mapToUpdateDivisionData(divisionId, create(headerDto)));
    }

    private DivisionData create(HeaderTemplateDto headerDto) {
        DivisionDto divisionDto = getDivision(headerDto.getDivisionType(), headerDto.getDivisionId());
        String name = divisionDto.getShortName();
        String address = "";
        String license = "";
        String contacts = "";
        if (headerDto.getSpecifyFullName()) {
            name = divisionDto.getFullName();
        }
        if (headerDto.getSpecifyAddress()) {
            address = stringBuilder.buildFromAddress(divisionDto.getAddress());
        }
        if (headerDto.getSpecifyLicense()) {
            license = stringBuilder.buildFromLicense(divisionDto.getLicenses());
        }
        if (headerDto.getSpecifyContacts()) {
            contacts = stringBuilder.buildFromContacts(divisionDto.getContact());
        }
        return mapper.mapToDivisionData(name, address, license, contacts);
    }

    private DivisionDto getDivision(String divisionType, Long divisionId) {
        return client.getDivisionData(convertToDivisionType(divisionType), divisionId);
    }
}