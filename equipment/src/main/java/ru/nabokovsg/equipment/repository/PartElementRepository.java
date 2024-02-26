package ru.nabokovsg.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.models.PartElement;

import java.util.Set;

public interface PartElementRepository extends JpaRepository<PartElement, Long> {

    PartElement findByElementIdAndPartName(Long elementId, String partName);

    Set<PartElement> findAllByElementId(Long elementId);
}