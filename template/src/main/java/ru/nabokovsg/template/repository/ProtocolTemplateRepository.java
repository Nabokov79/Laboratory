package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.template.models.ProtocolTemplate;

public interface ProtocolTemplateRepository extends JpaRepository<ProtocolTemplate, Long> {

     ProtocolTemplate findByHeaderDocumentIdAndEquipmentTypeId(Long headerDocumentId, Long equipmentTypeId);

     ProtocolTemplate findByHeaderDocumentId(Long headerDocumentId);

     ProtocolTemplate findByEquipmentTypeId(Long equipmentTypeId);
}