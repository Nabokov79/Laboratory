package ru.nabokovsg.equipment.service;

import ru.nabokovsg.equipment.dto.standardSize.StandardSizeDto;
import ru.nabokovsg.equipment.models.StandardSize;

public interface StandardSizeService {

    StandardSize save(StandardSizeDto standardSizeDto);

    StandardSize update(StandardSizeDto standardSizeDto);
}