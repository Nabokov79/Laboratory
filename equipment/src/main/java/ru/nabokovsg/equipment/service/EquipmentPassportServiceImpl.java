package ru.nabokovsg.equipment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.passport.EquipmentPassportDto;
import ru.nabokovsg.equipment.dto.passport.FullEquipmentPassportDto;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mappers.EquipmentPassportMapper;
import ru.nabokovsg.equipment.models.EquipmentPassport;
import ru.nabokovsg.equipment.repository.EquipmentPassportRepository;

@Service
@RequiredArgsConstructor
public class EquipmentPassportServiceImpl implements EquipmentPassportService {

    private final EquipmentPassportRepository repository;
    private final EquipmentPassportMapper mapper;
    private final EquipmentService equipmentService;

    @Override
    public FullEquipmentPassportDto save(EquipmentPassportDto passportDto) {
        EquipmentPassport passport = repository.findByHeaderAndEquipmentId(passportDto.getHeader()
                                                                         , passportDto.getEquipmentId());
        if (passport == null)  {
            passport = repository.save(mapper.mapToEquipmentPassport(passportDto
                                                , equipmentService.getById(passportDto.getEquipmentId())));
        }
        return mapper.mapToFullEquipmentPassportDto(passport);
    }

    @Override
    public FullEquipmentPassportDto update(EquipmentPassportDto passportDto) {
        if (repository.existsById(passportDto.getId())) {
            return mapper.mapToFullEquipmentPassportDto(repository.save(mapper.mapToEquipmentPassport(passportDto
                    , equipmentService.getById(passportDto.getEquipmentId()))));
        }
        throw new NotFoundException(
                String.format("Equipment Passport with id=%s not found for update", passportDto.getId())
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Equipment Passport with id=%s not found for delete", id));
    }
}