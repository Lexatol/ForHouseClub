package club.forhouse.controllers;

import club.forhouse.dto.WorkTemplateDto;
import club.forhouse.services.WorkTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/work_templates")
public class WorkTemplateController {

    private final WorkTemplateService workTemplateService;

    @GetMapping
    public Page<WorkTemplateDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "5") int size) {
        return workTemplateService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public WorkTemplateDto getById(@PathVariable Long id) {
        return workTemplateService.getById(id);
    }
}
