package ru.nabokovsg.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.models.Element;

import java.util.Set;


public interface ElementRepository extends JpaRepository<Element, Long> {

    Set<Element> findAllByEquipmentId(Long id);
}