package club.forhouse.controllers.estimates;

import club.forhouse.dto.worktemplate.WorkTemplateOperationDto;
import club.forhouse.dto.worktemplate.WorkTemplateOperationNewDto;
import club.forhouse.services.worktemplate.WorkTemplateOperationService;
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
    public List<WorkTemplateOperationDto> getByTemplateId(@RequestParam(name = "tmpl", required = false) Long templateId,
                                                          @RequestParam(name = "category", required = false) Long categoryId) {

        if (templateId != null && categoryId != null) {
            return templateOperationService.getByCategoryIdAndTemplateId(categoryId, templateId);
        } else if (templateId == null && categoryId != null) {
            return templateOperationService.getByCategoryId(categoryId);
        } else if (templateId != null) {
            return templateOperationService.getByTemplateId(templateId);
        } else {
            throw new RuntimeException("Invalid parameters");
        }
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
