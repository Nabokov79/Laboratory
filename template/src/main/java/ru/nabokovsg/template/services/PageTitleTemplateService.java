package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.pageTitle.FullPageTitleTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.ShortPageTitleTemplateDto;

import java.util.List;

public interface PageTitleTemplateService {

    FullPageTitleTemplateDto save(PageTitleTemplateDto pageTitleDto);

    FullPageTitleTemplateDto update(PageTitleTemplateDto pageTitleDto);

    List<ShortPageTitleTemplateDto> getAll();
}