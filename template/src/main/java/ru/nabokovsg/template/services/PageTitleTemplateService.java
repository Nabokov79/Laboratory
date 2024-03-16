package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.pageTitle.ResponsePageTitleTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.ShortResponsePageTitleTemplateDto;

import java.util.List;

public interface PageTitleTemplateService {

    ResponsePageTitleTemplateDto save(PageTitleTemplateDto pageTitleDto);

    ResponsePageTitleTemplateDto update(PageTitleTemplateDto pageTitleDto);

    List<ShortResponsePageTitleTemplateDto> getAll();
}