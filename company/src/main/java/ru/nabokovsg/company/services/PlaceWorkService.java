package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.placeWork.PlaceWorkDto;
import ru.nabokovsg.company.models.PlaceWork;

public interface PlaceWorkService {

    PlaceWork save(PlaceWorkDto placeWorkDto);

    PlaceWork update(PlaceWorkDto placeWorkDto);
}
