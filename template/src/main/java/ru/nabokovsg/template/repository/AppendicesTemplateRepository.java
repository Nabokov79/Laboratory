package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.template.models.AppendicesTemplate;

public interface AppendicesTemplateRepository extends JpaRepository<AppendicesTemplate, Long> {

    AppendicesTemplate findByEquipmentTypeId(Long equipmentTypeId);
}