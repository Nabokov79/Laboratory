package ru.nabokovsg.document.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import ru.nabokovsg.document.dto.TaskJournalDto;
import ru.nabokovsg.document.dto.document.DocumentSearchParam;
import ru.nabokovsg.document.mapper.DocumentMapper;
import ru.nabokovsg.document.models.Document;
import ru.nabokovsg.document.models.enums.RequestDataType;
import ru.nabokovsg.document.repository.DocumentRepository;
import ru.nabokovsg.document.dto.document.FullDocumentDto;
import ru.nabokovsg.lab_nk.exceptions.BadRequestException;
import ru.nabokovsg.lab_nk.exceptions.NotFoundException;
import ru.nabokovsg.lab_nk.mappers.DocumentMapper;
import ru.nabokovsg.lab_nk.models.Document;
import ru.nabokovsg.lab_nk.models.QDocument;
import ru.nabokovsg.lab_nk.models.QTasksJournal;
import ru.nabokovsg.lab_nk.models.TasksJournal;
import ru.nabokovsg.lab_nk.models.enums.RequestDataType;
import ru.nabokovsg.lab_nk.models.enums.Status;
import ru.nabokovsg.lab_nk.repository.DocumentRepository;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final DocumentMapper mapper;
    private final EntityManager em;

    @Override
    public Boolean save(TaskJournalDto taskJournalDto) {
        return null;
    }

    @Override
    public FullDocumentDto get(Long id, String type) {
        Document document = repository.findByTaskJournalId(id);
        switch (RequestDataType.from(type)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown data type=%s", type)))) {
            case RequestDataType.REMARK -> document = repository.findByRemarkId(id);
            case RequestDataType.TASK_JOURNAL -> document = repository.findByTaskJournalId(id);
        }
        return mapper.mapToFullDocumentDto(document);
    }

    @Override
    public List<FullDocumentDto> getAll(DocumentSearchParam param) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QDocument document = QDocument.document;
        if (param.getEquipmentId() != null && param.getEquipmentId() > 0) {
            booleanBuilder.and(QTDocument.tasksJournal.equipmentId.eq(param.getEquipmentId()));
        }
        if (param.getAddressId() != null && param.getAddressId() > 0) {
            booleanBuilder.and(QTasksJournal.tasksJournal.addressId.eq(param.getAddressId()));
        }
        if (param.getStartDate() != null && param.getEndDate() != null) {
            booleanBuilder.and(document.date.after(param.getStartDate()));
            booleanBuilder.and(document.date.before(param.getEndDate()));
        } else {
            booleanBuilder.and(QTasksJournal.tasksJournal.date.eq(LocalDate.now()));
        }
        QDocument document = QDocument.document;
        return new JPAQueryFactory(em).from(document)
                                      .select(document)
                                      .where(booleanBuilder)
                                      .fetch()
                                      .stream()
                                      .map(mapper::mapToFullDocumentDto)
                                      .toList();
    }

    @Override
    public void create(TasksJournal task) {
        Document document = repository.findByTaskJournalId(task.getId());
        repository.save(mapper.mapToDocument(document
                                           , task,task.getDate()
                                           , Status.WAITING
                                           , Status.WAITING
                                           , getDocumentNumber(document.getDocumentNumber())));
    }

    @Override
    public Document getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Document with id=%s not found", id)));
    }

    @Override
    public void validByTasksJournalId(Long id) {
        Document document = repository.findByTaskJournalId(id);
        if (document.getDateTransfer() != null || document.getDocumentTransfer() != null) {
            throw new BadRequestException(
                    String.format(
                            "You cannot change an entry in the task journal. " +
                                    "The document has been submitted: date=%s, document =%s"
                            , document.getDateTransfer(), document.getDocumentTransfer()));
        }
        if (document.getDocumentStatus().equals(Status.COMPLETED)) {
            throw new BadRequestException(
                    String.format("Completed document number=%s", document.getDocumentNumber()));
        }
    }

    private Integer getDocumentNumber(Integer number) {
        if (number != null) {
            return number;
        }
        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.with(firstDayOfYear());
        LocalDate lastDay = now.with(lastDayOfYear());
        QDocument document = QDocument.document;
        number = new JPAQueryFactory(em).from(document)
                .select(document.documentNumber.max())
                .where(document.date.after(firstDay).and(document.date.before(lastDay)))
                .fetchOne();
        if (number == null) {
            return 1;
        }
        return (++number);
    }
}