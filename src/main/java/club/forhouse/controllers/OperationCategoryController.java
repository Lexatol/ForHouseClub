package club.forhouse.controllers;

import club.forhouse.dto.OperationCategoryDto;
import club.forhouse.services.OperationCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
