package ru.nabokovsg.document.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.nabokovsg.document.dto.remark.ResponseRemarkDto;
import ru.nabokovsg.document.dto.remark.RemarkDto;
import ru.nabokovsg.document.exceptions.BadRequestException;
import ru.nabokovsg.document.exceptions.NotFoundException;
import ru.nabokovsg.document.mapper.RemarkMapper;
import ru.nabokovsg.document.models.QRemark;
import ru.nabokovsg.document.models.QSubscriber;
import ru.nabokovsg.document.models.Remark;
import ru.nabokovsg.document.models.enums.DocumentStatus;
import ru.nabokovsg.document.repository.RemarkRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RemarkServiceImpl implements RemarkService {

    private final RemarkRepository repository;
    private final RemarkMapper mapper;
    private final DocumentService documentService;
    private final EntityManager em;
    private final SubscriberService subscriberService;

    @Override
    public ResponseRemarkDto save(RemarkDto remarkDto) {
        Remark remark = repository.findByDocumentIdAndRemark(remarkDto.getDocumentId(), remarkDto.getRemark());
        if (remark == null) {
            remark = repository.save(mapper.mapToRemark(remarkDto.getRemark()
                            , subscriberService.getById(remarkDto.getInspectorId())
                            , documentService.getById(remarkDto.getDocumentId())));
            documentService.updateStatus(remarkDto.getDocumentId(), DocumentStatus.REMARK);
            return mapper.mapToFullRemarkDto(remark);
        }
       throw new BadRequestException(String.format("Remark was found: %s", remark.getRemark()));
    }

    @Override
    public ResponseRemarkDto update(RemarkDto remarkDto) {
        Remark remark = getById(remarkDto.getId());
        if (remarkDto.getDocumentCorrected()) {
            delete(remarkDto.getId());
            documentService.updateStatus(remarkDto.getDocumentId(), DocumentStatus.ACCEPTED);
            return mapper.mapToFullRemarkDto(remark);
        } else {
            return mapper.mapToFullRemarkDto(
                    repository.save(mapper.mapToRemark(remarkDto.getRemark()
                            , subscriberService.getById(remarkDto.getInspectorId())
                            , documentService.getById(remarkDto.getDocumentId()))));
        }
    }

    @Override
    public List<ResponseRemarkDto> getAll(Long id, Boolean inspector) {
        if (inspector) {
            return repository.findAllByInspectorIdOrderByIdDesc(id)
                             .stream()
                             .map(mapper::mapToFullRemarkDto)
                             .toList();
        } else {
            QRemark remark = QRemark.remark1;
            return new JPAQueryFactory(em).from(remark)
                                          .select(remark)
                                          .where(QSubscriber.subscriber.id.eq(id))
                                          .orderBy(remark.id.desc())
                                          .fetch()
                                          .stream()
                                          .map(mapper::mapToFullRemarkDto)
                                          .toList();
        }
    }

    private Remark getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Remark with id=%s not found", id)));
    }

   private void delete(Long id) {
        repository.deleteById(id);
    }
}