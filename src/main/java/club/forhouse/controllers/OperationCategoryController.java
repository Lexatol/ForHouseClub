package club.forhouse.controllers;

import club.forhouse.dto.operation.OperationCategoryDto;
import club.forhouse.dto.operation.OperationCategoryNewDto;
import club.forhouse.services.OperationCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/operation_categories")
public class OperationCategoryController {

    private final OperationCategoryService operationCategoryService;

    @GetMapping
    public List<OperationCategoryDto> getAll() {
        return operationCategoryService.getAll();
    }

    @GetMapping("/{id}")
    public OperationCategoryDto getById(@PathVariable Long id) {
        return operationCategoryService.getById(id);
    }

    @PostMapping
    public OperationCategoryDto addNew(@RequestBody OperationCategoryNewDto newCategory) {
        return operationCategoryService.addNew(newCategory);
    }

    @PutMapping
    public OperationCategoryDto save(@RequestBody OperationCategoryDto newCategory) {
        return operationCategoryService.save(newCategory);
    }

}
