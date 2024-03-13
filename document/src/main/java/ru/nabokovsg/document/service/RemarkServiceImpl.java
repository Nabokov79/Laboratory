package ru.nabokovsg.document.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.dto.remark.FullRemarkDto;
import ru.nabokovsg.lab_nk.dto.remark.RemarkDto;
import ru.nabokovsg.lab_nk.exceptions.BadRequestException;
import ru.nabokovsg.lab_nk.exceptions.NotFoundException;
import ru.nabokovsg.lab_nk.mappers.RemarkMapper;
import ru.nabokovsg.lab_nk.models.*;
import ru.nabokovsg.lab_nk.repository.RemarkRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RemarkServiceImpl implements RemarkService {

    private final RemarkRepository repository;
    private final RemarkMapper mapper;
    private final DocumentService documentService;
    private final LaboratoryEmployeeService laboratoryEmployeeService;
    private final EntityManager em;

    @Override
    public FullRemarkDto save(RemarkDto remarkDto) {
        Remark remark = repository.findByDocumentIdAndEmployeeIdAndRemark(remarkDto.getDocumentId()
                                                                        , remarkDto.getEmployeeId()
                                                                        , remarkDto.getRemark());
        if (remark == null) {
            Document document = documentService.getById(remarkDto.getDocumentId());
            return mapper.mapToFullRemarkDto(
                    repository.save(mapper.mapToRemark(remarkDto
                                                     , getEmployee(remarkDto.getEmployeeId())
                                                     , document
                                                     , document.getTaskJournal().getEmployees()))
            );
        }
       throw new BadRequestException(String.format("Remark was found: %s", remark.getRemark()));
    }

    @Override
    public FullRemarkDto update(RemarkDto remarkDto) {
        if (repository.existsById(remarkDto.getId())) {
            if (!remarkDto.getDocumentCorrected()) {
                Document document = documentService.getById(remarkDto.getDocumentId());
                return mapper.mapToFullRemarkDto(repository.save(mapper.mapToRemark(remarkDto
                        , getEmployee(remarkDto.getEmployeeId())
                        , document
                        , document.getTaskJournal().getEmployees())));
                }
            delete(remarkDto.getId());
            return new FullRemarkDto();
        }
       throw new NotFoundException(String.format("Remark with id=%s not found for update", remarkDto.getId()));
    }

    @Override
    public List<FullRemarkDto> getAll(Long id,  Boolean inspector) {
        if (inspector) {
            return repository.findAllByEmployeeIdOrderByIdDesc(id)
                             .stream()
                             .map(mapper::mapToFullRemarkDto)
                             .toList();
        } else {
            QRemark remark = QRemark.remark1;
            return new JPAQueryFactory(em).from(remark)
                                          .select(remark)
                                          .where(QLaboratoryEmployee.laboratoryEmployee.id.eq(id))
                                          .orderBy(remark.id.desc())
                                          .fetch()
                                          .stream()
                                          .map(mapper::mapToFullRemarkDto)
                                          .toList();
        }
    }

   private void delete(Long id) {
        repository.deleteById(id);
    }

    private String getEmployee(Long id) {
        LaboratoryEmployee employee = laboratoryEmployeeService.getById(id);
        return String.join("/"
                , employee.getPost()
                , String.join(". ", String.join(".", String.valueOf(employee.getName().charAt(0))
                                        , String.valueOf(employee.getPatronymic().charAt(0)))
                                .toUpperCase()
                        , employee.getSurname()));
    }
}