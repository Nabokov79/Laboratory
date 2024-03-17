package ru.nabokovsg.document.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.document.dto.document.LaboratoryEmployeeDto;
import ru.nabokovsg.document.dto.document.DocumentDto;
import ru.nabokovsg.document.dto.document.DocumentSearchParam;
import ru.nabokovsg.document.mapper.DocumentMapper;
import ru.nabokovsg.document.models.Document;
import ru.nabokovsg.document.models.QDocument;
import ru.nabokovsg.document.models.QSubscriber;
import ru.nabokovsg.document.models.Subscriber;
import ru.nabokovsg.document.models.enums.DocumentStatus;
import ru.nabokovsg.document.repository.DocumentRepository;
import ru.nabokovsg.document.dto.document.ResponseDocumentDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final DocumentMapper mapper;
    private final EntityManager em;
    private final SubscriberService subscriberService;

    @Override
    public void save(DocumentDto documentDto) {
        documentDto.getEmployees().add(documentDto.getChief());
        Map<Long, Subscriber> subscribers = subscriberService.save(
                documentDto.getEmployees()).stream().collect(Collectors.toMap(Subscriber::getEmployeeId, s -> s)
        );
        Document document = mapper.mapToDocument(documentDto
                                               , subscribers.get(documentDto.getChief().getId())
                                               , getDocumentNumber()
                                               , DocumentStatus.NO_DOCUMENT
                                               , DocumentStatus.NO_DRAWING);
        List<Long> inspectorIds = documentDto.getEmployees().stream()
                                                               .map(LaboratoryEmployeeDto::getId)
                                                               .toList();
        document.setInspectors(subscribers.values()
                                          .stream()
                                          .filter(s -> inspectorIds.contains(s.getEmployeeId()))
                                          .collect(Collectors.toSet()));
        repository.save(document);
    }

    @Override
    public ResponseDocumentDto get(Long id) {
        return mapper.mapToFullDocumentDto(repository.findByTaskJournalId(id));
    }

    public Document getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Document with id=%s not found", id))
                );
    }

    @Override
    public void updateStatus(Long documentId, DocumentStatus status) {

    }

    @Override
    public List<ResponseDocumentDto> getAll(DocumentSearchParam param) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QDocument document = QDocument.document;
        if (param.getTaskJournalId() != null && param.getTaskJournalId() > 0) {
            booleanBuilder.and(document.taskJournalId.eq(param.getTaskJournalId()));
        }
        if (param.getChiefId() != null && param.getChiefId() > 0) {
            booleanBuilder.and(document.chief.employeeId.eq(param.getChiefId()));
        }
        if (param.getChiefId() != null && param.getChiefId() > 0) {
            booleanBuilder.and(QSubscriber.subscriber.employeeId.eq(param.getInspectorId()));
        }
        if (param.getStartDate() != null && param.getEndDate() != null) {
            booleanBuilder.and(document.date.after(param.getStartDate()));
            booleanBuilder.and(document.date.before(param.getEndDate()));
        } else {
            booleanBuilder.and(document.date.eq(LocalDate.now()));
        }
        return new JPAQueryFactory(em).from(document)
                                      .select(document)
                                      .where(booleanBuilder)
                                      .fetch()
                                      .stream()
                                      .map(mapper::mapToFullDocumentDto)
                                      .toList();
    }

    private Integer getDocumentNumber() {
        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.with(firstDayOfYear());
        LocalDate lastDay = now.with(lastDayOfYear());
        QDocument document = QDocument.document;
        Integer number = new JPAQueryFactory(em).from(document)
                .select(document.documentNumber.max())
                .where(document.date.after(firstDay).and(document.date.before(lastDay)))
                .fetchOne();
        if (number == null) {
            return 1;
        }
        return (++number);
    }
}