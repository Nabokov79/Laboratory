package ru.nabokovsg.template.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.template.client.dto.*;
import ru.nabokovsg.template.exceptions.BadRequestException;
import ru.nabokovsg.template.models.enums.DivisionType;

@Service
public class CompanyClient extends Client {

    private static final String DELIMITER = "/";
    private static final String API_PREFIX_ORGANIZATION = "/organization";
    private static final String API_PREFIX_BRANCH = "/branch";
    private static final String API_PREFIX_DEPARTMENT = "/department";
    private static final String API_PREFIX_HEAT_SUPPLE_AREA = "/heat/supply/area";
    private static final String API_PREFIX_EXPLOITATION_REGION = "/exploitation";

    @Autowired
    public CompanyClient(@Value("${company-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public DivisionDto getDivisionData(DivisionType divisionType, Long id) {
        switch (divisionType) {
            case ORGANIZATION -> {
                return getDivision(String.join(DELIMITER, API_PREFIX_ORGANIZATION, String.valueOf(id))).block();
            }
            case BRANCH -> {
                return getDivision(String.join(DELIMITER, API_PREFIX_BRANCH, String.valueOf(id))).block();
            }
            case DEPARTMENT -> {
                return getDivision(String.join(DELIMITER, API_PREFIX_DEPARTMENT, String.valueOf(id))).block();
            }
            case HEAT_SUPPLE_AREA -> {
                return getDivision(String.join(DELIMITER, API_PREFIX_HEAT_SUPPLE_AREA, String.valueOf(id))).block();
            }
            case EXPLOITATION_REGION -> {
              return getDivision(String.join(DELIMITER, API_PREFIX_EXPLOITATION_REGION, String.valueOf(id))).block();
            }
            default ->
                    throw new BadRequestException(
                            String.format(String.format("Unknown divisionType=%s", divisionType))
                    );
        }
    }
}