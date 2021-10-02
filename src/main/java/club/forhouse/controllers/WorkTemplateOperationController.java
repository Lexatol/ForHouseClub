package club.forhouse.controllers;

import club.forhouse.dto.worktemplate.WorkTemplateOperationDto;
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
        return templateOperationService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public WorkTemplateOperationDto getById(@PathVariable Long id) {
        return templateOperationService.getById(id);
    }

    @GetMapping("/by_template/{templateId}")
    public List<WorkTemplateOperationDto> getByTemplateId(@PathVariable Long templateId) {
        return templateOperationService.getByTemplateId(templateId);
    }
}
