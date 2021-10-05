package club.forhouse.controllers;

import club.forhouse.dto.worktemplate.WorkTemplateOperationDto;
import club.forhouse.dto.worktemplate.WorkTemplateOperationNewDto;
import club.forhouse.services.WorkTemplateOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/work_template_operations")
public class WorkTemplateOperationController {

    private final WorkTemplateOperationService templateOperationService;

    @GetMapping
    public Page<WorkTemplateOperationDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        return templateOperationService.getAll(page - 1, size);
    }

    @GetMapping("/{id}")
    public WorkTemplateOperationDto getById(@PathVariable Long id) {
        return templateOperationService.getById(id);
    }

    @GetMapping("/find")
    public List<WorkTemplateOperationDto> getByTemplateId(@RequestParam(name = "tmpl") Long templateId) {
        return templateOperationService.getByTemplateId(templateId);
    }

    @PostMapping
    public WorkTemplateOperationDto addNew(@RequestBody WorkTemplateOperationNewDto operationNewDto) {
        return templateOperationService.addNew(operationNewDto);
    }

    @PutMapping
    public WorkTemplateOperationDto save(@RequestBody WorkTemplateOperationDto operationDto) {
        return templateOperationService.save(operationDto);
    }

}
