package club.forhouse.controllers;

import club.forhouse.dto.WorkTemplateMaterialDto;
import club.forhouse.services.WorkTemplateMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/work_template_materials")
public class WorkTemplateMaterialController {

    private final WorkTemplateMaterialService templateMaterialService;

    @GetMapping
    public Page<WorkTemplateMaterialDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "5") int size) {
        return templateMaterialService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public WorkTemplateMaterialDto getById(@PathVariable Long id) {
        return templateMaterialService.getById(id);
    }

    @GetMapping("/by_template/{templateId}")
    public List<WorkTemplateMaterialDto> getByTemplateId(@PathVariable Long templateId) {
        return templateMaterialService.getByTemplateId(templateId);
    }
}
