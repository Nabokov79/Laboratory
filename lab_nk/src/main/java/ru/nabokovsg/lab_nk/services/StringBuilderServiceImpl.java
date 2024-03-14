package ru.nabokovsg.lab_nk.services;

import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.client.dto.AddressDto;
import ru.nabokovsg.lab_nk.client.dto.BuildingDto;
import ru.nabokovsg.lab_nk.client.dto.EquipmentDto;

@Service
public class StringBuilderServiceImpl implements StringBuilderService {

    @Override
    public String buildFromBuilding(BuildingDto buildingDto) {
        String building = buildingDto.getBuildingType();
        if (buildingDto.getLogin() != null) {
            building = String.join(" ", building, buildingDto.getLogin());
        }
        building = String.join(" ", buildFromAddress(buildingDto.getAddress()));
        return building;
    }

    @Override
    public String buildFromAddress(AddressDto address) {
        String string = String.join(", ", address.getCity()
                , String.join(" ", address.getStreet()
                        , "д.", String.valueOf(address.getHouseNumber())));
        if (address.getBuildingNumber() != null) {
            string = String.join(", ", string, String.join(""
                    , "корп.", String.valueOf(address.getBuildingNumber())));
        }
        if (address.getLetter() != null) {
            string = String.join(", ", string, String.join("", "лит.", address.getLetter()));
        }
        if (address.getIndex() != null) {
            return String.join(", ", String.valueOf(address.getIndex()), string);
        } else {
            return string;
        }
    }

    @Override
    public String getStringEquipment(EquipmentDto equipment) {
        String name = equipment.getFullName();
        if (equipment.getModel() != null) {
            name = String.join(" ", name, equipment.getModel());
        }
        if (equipment.getStationaryNumber() != null) {
            name = String.join(" ", name, "cт. № ", String.valueOf(equipment.getStationaryNumber()));
        }
        if (equipment.getVolume() != null) {
            name = String.join(" ", name, "V = ", String.valueOf(equipment.getVolume()), "м3");
        }
        return name;
    }
}