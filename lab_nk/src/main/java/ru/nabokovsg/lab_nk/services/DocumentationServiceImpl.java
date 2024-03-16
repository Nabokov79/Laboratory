package ru.nabokovsg.lab_nk.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.dto.documentation.DocumentationDto;
import ru.nabokovsg.lab_nk.dto.documentation.ResponseDocumentationDto;
import ru.nabokovsg.lab_nk.exceptions.NotFoundException;
import ru.nabokovsg.lab_nk.mappers.DocumentationMapper;
import ru.nabokovsg.lab_nk.models.QDocumentation;
import ru.nabokovsg.lab_nk.repository.DocumentationRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DocumentationServiceImpl implements DocumentationService {

    private final DocumentationRepository repository;
    private final DocumentationMapper mapper;
    private final EntityManager em;

    @Override
    public ResponseDocumentationDto save(DocumentationDto documentationDto) {
        return mapper.mapToFullDocumentationDto(
                Objects.requireNonNullElseGet(repository.findByTitle(documentationDto.getTitle())
                        , () -> repository.save(mapper.mapToDocumentation(documentationDto)))
        );
    }

    @Override
    public ResponseDocumentationDto update(DocumentationDto documentationDto) {
        if (repository.existsById(documentationDto.getId())) {
            return mapper.mapToFullDocumentationDto(repository.save(mapper.mapToDocumentation(documentationDto)));
        }
        throw new NotFoundException(
                String.format("Documentation with id=%s not found for update", documentationDto.getId())
        );
    }

    @Override
    public List<ResponseDocumentationDto> getAll(List<Long> ids, String number, String title) {
        QDocumentation documentation = QDocumentation.documentation;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (ids != null && !ids.isEmpty()) {
            booleanBuilder.and(documentation.id.in(ids));
        }
        if (number != null) {
            booleanBuilder.and(documentation.number.eq(number));
        }
        if (title != null) {
            booleanBuilder.and(documentation.title.eq(title));
        }
        return new JPAQueryFactory(em).from(documentation)
                .select(documentation)
                .where(booleanBuilder)
                .fetch()
                .stream()
                .map(mapper::mapToFullDocumentationDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Documentation with id=%s not found for delete", id));
    }
}