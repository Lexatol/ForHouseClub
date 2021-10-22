package club.forhouse.controllers.estimates;

import club.forhouse.dto.worktemplate.WorkCategoryDto;
import club.forhouse.dto.worktemplate.WorkTemplateBaseDto;
import club.forhouse.dto.worktemplate.WorkTemplateDto;
import club.forhouse.dto.worktemplate.WorkTemplateNewDto;
import club.forhouse.services.worktemplate.WorkTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/work_templates")
public class WorkTemplateController {

    private final WorkTemplateService workTemplateService;

    @GetMapping
    public Page<WorkTemplateDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "5") int size) {
        return workTemplateService.getAll(page - 1, size);
    }

    @GetMapping("/categories")
    public List<WorkCategoryDto> getCategories() {
        return workTemplateService.getCategories();
    }

    @GetMapping("/list")
    public List<WorkTemplateBaseDto> getList(@RequestParam(name = "category", required = false) Long categoryId) {
        if (categoryId == null) {
            return workTemplateService.getList();
        } else {
            return workTemplateService.getListByCategory(categoryId);
        }
    }

    @GetMapping("/{id}")
    public WorkTemplateDto getById(@PathVariable Long id) {
        return workTemplateService.getById(id);
    }

    @PostMapping
    public WorkTemplateDto addNew(@RequestBody WorkTemplateNewDto newDto) {
        return workTemplateService.addNew(newDto);
    }
}
