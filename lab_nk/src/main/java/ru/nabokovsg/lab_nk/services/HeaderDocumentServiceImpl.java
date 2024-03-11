package ru.nabokovsg.lab_nk.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.dto.headerDocument.HeaderDocumentDto;
import ru.nabokovsg.lab_nk.dto.headerDocument.FullHeaderDocumentDto;
import ru.nabokovsg.lab_nk.exceptions.BadRequestException;
import ru.nabokovsg.lab_nk.exceptions.NotFoundException;
import ru.nabokovsg.lab_nk.mappers.HeaderDocumentMapper;
import ru.nabokovsg.lab_nk.models.enums.TypeDocument;
import ru.nabokovsg.lab_nk.repository.HeaderDocumentRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HeaderDocumentServiceImpl implements HeaderDocumentService {

    private final HeaderDocumentRepository repository;
    private final HeaderDocumentMapper mapper;

    @Override
    public FullHeaderDocumentDto save(HeaderDocumentDto headerDocumentDto) {
        TypeDocument typeDocument = convertToTypeDocument(headerDocumentDto.getTypeDocument());
        return mapper.mapToFullHeaderDocumentDto(
                Objects.requireNonNullElseGet(
                        repository.findByTitleAndHeadingAndTypeDocument(headerDocumentDto.getTitle()
                                                                    , headerDocumentDto.getHeading()
                                                                    , typeDocument)
                        , () -> repository.save(mapper.mapToHeaderDocument(headerDocumentDto, typeDocument)))
        );
    }

    @Override
    public FullHeaderDocumentDto update(HeaderDocumentDto headerDocumentDto) {
        if (repository.existsById(headerDocumentDto.getId())) {
            return mapper.mapToFullHeaderDocumentDto(
                    repository.save(mapper.mapToHeaderDocument(headerDocumentDto
                            , convertToTypeDocument(headerDocumentDto.getTypeDocument()))));
        }
        throw new NotFoundException(
                String.format("HeaderDocument with id = %s not found for update", headerDocumentDto.getId())
        );
    }

    @Override
    public FullHeaderDocumentDto get(Long id) {
        return mapper.mapToFullHeaderDocumentDto(
                repository.findById(id)
                     .orElseThrow(() -> new NotFoundException(String.format("HeaderDocument with id=%s not found", id)))
        );
    }

    @Override
    public List<FullHeaderDocumentDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToFullHeaderDocumentDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("HeaderDocument with id = %s not found for delete", id));
    }

    private TypeDocument convertToTypeDocument(String typeDocument) {
        return TypeDocument.from(typeDocument)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown type=%s", typeDocument)));
    }
}