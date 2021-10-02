package club.forhouse.controllers;

import club.forhouse.dto.worktemplate.WorkTemplateMaterialDto;
import club.forhouse.dto.worktemplate.WorkTemplateMaterialNewDto;
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
        return templateMaterialService.getAll(page - 1, size);
    }

    @GetMapping("/{id}")
    public WorkTemplateMaterialDto getById(@PathVariable Long id) {
        return templateMaterialService.getById(id);
    }

    @GetMapping("/find")
    public List<WorkTemplateMaterialDto> getByTemplateId(@RequestParam(name = "tmplt") Long templateId) {
        return templateMaterialService.getByTemplateId(templateId);
    }

    @PostMapping
    public WorkTemplateMaterialDto addNew(@RequestBody WorkTemplateMaterialNewDto materialNewDto) {
        return templateMaterialService.addNew(materialNewDto);
    }

    @PutMapping
    public WorkTemplateMaterialDto save(@RequestBody WorkTemplateMaterialDto materialDto) {
        return templateMaterialService.save(materialDto);
    }
}
