package ru.nabokovsg.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.dto.templates.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.gateway.dto.templates.appendices.UpdateAppendicesTemplateDto;
import ru.nabokovsg.gateway.dto.templates.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.gateway.dto.templates.conclusion.UpdateConclusionTemplateDto;
import ru.nabokovsg.gateway.dto.templates.header.NewHeaderTemplateDto;
import ru.nabokovsg.gateway.dto.templates.header.UpdateHeaderTemplateDto;
import ru.nabokovsg.gateway.dto.templates.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.gateway.dto.templates.pageTitle.UpdatePageTitleTemplateDto;
import ru.nabokovsg.gateway.dto.templates.protocols.NewProtocolTemplateDto;
import ru.nabokovsg.gateway.dto.templates.protocols.UpdateProtocolTemplateDto;
import ru.nabokovsg.gateway.dto.templates.reportProtocol.NewProtocolReportTemplateDto;
import ru.nabokovsg.gateway.dto.templates.reportProtocol.UpdateProtocolReportTemplateDto;
import ru.nabokovsg.gateway.dto.templates.sections.SectionDataTemplateDto;
import ru.nabokovsg.gateway.dto.templates.sections.UpdateSectionTemplateDto;
import ru.nabokovsg.gateway.dto.templates.subsections.NewSubsectionTemplateDto;
import ru.nabokovsg.gateway.dto.templates.subsections.UpdateSubsectionTemplateDto;
import ru.nabokovsg.gateway.dto.templates.tables.NewTableTemplateDto;
import ru.nabokovsg.gateway.dto.templates.tables.UpdateTableTemplateDto;
import ru.nabokovsg.gateway.enums.DocumentPartType;
import ru.nabokovsg.gateway.enums.TemplateType;

import java.util.List;

@Service
public class TemplateClient extends BaseClient {

    private static final String DELIMITER = "/";
    private static final String API_PREFIX_APPENDICES = "/appendices";
    private static final String API_PREFIX_CONCLUSION = "/conclusion";
    private static final String API_PREFIX_HEADER = "/header";
    private static final String API_PREFIX_TITLE_PAGE = "/title-page";
    private static final String API_PREFIX_REPORT_PROTOCOL = "/report/protocol";
    private static final String API_PREFIX_PROTOCOL = "/protocol";
    private static final String API_PREFIX_REPORT = "/report";
    private static final String API_PREFIX_SECTION = "/section";
    private static final String API_PREFIX_SUBSECTION = "/subsection";
    private static final String API_PREFIX_TABLE = "/table";

    public TemplateClient(@Value("${template-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> saveAppendices(NewAppendicesTemplateDto appendicesDto) {
        return post(API_PREFIX_APPENDICES, appendicesDto);
    }

    public Mono<Object> updateAppendices(UpdateAppendicesTemplateDto appendicesDto) {
        return patch(API_PREFIX_APPENDICES, appendicesDto);
    }

    public Mono<String> deleteAppendices(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_APPENDICES, String.valueOf(id)));
    }

    public Mono<Object> saveConclusion(DocumentPartType type, NewConclusionTemplateDto conclusionDto) {
        conclusionDto.setDocumentPartType(String.valueOf(type));
        return post(API_PREFIX_CONCLUSION, conclusionDto);
    }

    public Mono<Object> updateConclusion(UpdateConclusionTemplateDto conclusionDto) {
        return patch(API_PREFIX_CONCLUSION, conclusionDto);
    }

    public Mono<String> deleteConclusion(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_APPENDICES, String.valueOf(id)));
    }

    public Mono<Object> saveHeader(NewHeaderTemplateDto headerDto) {
        return post(API_PREFIX_HEADER, headerDto);
    }

    public Mono<Object> updateHeader(UpdateHeaderTemplateDto headerDto) {
        return patch(API_PREFIX_HEADER, headerDto);
    }

    public Mono<Object> getHeader(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_HEADER, String.valueOf(id)));
    }

    public Mono<String> deleteHeader(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_HEADER, String.valueOf(id)));
    }

    public Mono<Object> savePageTitle(NewPageTitleTemplateDto pageTitleDto) {
        return post(API_PREFIX_TITLE_PAGE, pageTitleDto);
    }

   public Mono<Object> updatePageTitle(UpdatePageTitleTemplateDto pageTitleDto) {
        return patch(API_PREFIX_TITLE_PAGE, pageTitleDto);
    }

    public Mono<Object>  saveProtocolReport(NewProtocolReportTemplateDto protocolDto) {
        return post(API_PREFIX_REPORT_PROTOCOL, protocolDto);
    }

    public Mono<Object>  updateProtocolReport(UpdateProtocolReportTemplateDto protocolDto) {
        return patch(API_PREFIX_REPORT_PROTOCOL, protocolDto);
    }

    public Mono<Object> getProtocolReport(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_REPORT_PROTOCOL, String.valueOf(id)));
    }

    public Flux<Object> getAllProtocolReport(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_REPORT_PROTOCOL, "/all/section/", String.valueOf(id)));
    }

    public Mono<String> deleteProtocolReport(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_REPORT_PROTOCOL, String.valueOf(id)));
    }

    public Mono<Object> saveProtocol(NewProtocolTemplateDto protocolDto) {
        return post(API_PREFIX_PROTOCOL, protocolDto);
    }

    public Mono<Object> updateProtocol(UpdateProtocolTemplateDto protocolDto) {
        return patch(API_PREFIX_PROTOCOL, protocolDto);
    }

    public Mono<Object> getProtocol(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_PROTOCOL, String.valueOf(id)));
    }

    public Flux<Object> getAllProtocols() {
        return getAll(String.join(DELIMITER, API_PREFIX_PROTOCOL, "/all"));
    }

    public Mono<Object> getReport(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_REPORT, String.valueOf(id)));
    }

    public Mono<Object> saveSection(SectionDataTemplateDto sectionsDto) {
        return post(API_PREFIX_SECTION, sectionsDto);
    }

    public Mono<Object> updateSection(List<UpdateSectionTemplateDto> sectionsDto) {
        return patch(API_PREFIX_SECTION, sectionsDto);
    }

    public Mono<Object> getSection(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_SECTION, String.valueOf(id)));
    }

    public Flux<Object> getAllSections(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_SECTION, "/all/report/", String.valueOf(id)));
    }

    public Mono<String>deleteSection(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_SECTION, String.valueOf(id)));
    }

    public Mono<Object> saveSubsection(TemplateType type, NewSubsectionTemplateDto subsectionDto) {
        subsectionDto.setTemplateType(String.valueOf(type));
        return post(API_PREFIX_SUBSECTION, subsectionDto);
    }

    public Mono<Object> updateSubsection(UpdateSubsectionTemplateDto subsectionDto) {
        return patch(API_PREFIX_SUBSECTION, subsectionDto);
    }

    public Mono<Object> getSubsection(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_SUBSECTION, String.valueOf(id)));
    }

    public Mono<String> deleteSubsection(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_SUBSECTION, String.valueOf(id)));
    }

    public Mono<Object> saveTable(NewTableTemplateDto tableDto) {
        return post(API_PREFIX_TABLE, tableDto);
    }

    public Mono<Object> updateTable(UpdateTableTemplateDto tableDto) {
        return patch(API_PREFIX_TABLE, tableDto);
    }

    public Mono<Object> getTable(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_TABLE, String.valueOf(id)));
    }


    public Mono<String> deleteTable(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_TABLE, String.valueOf(id)));
    }
}