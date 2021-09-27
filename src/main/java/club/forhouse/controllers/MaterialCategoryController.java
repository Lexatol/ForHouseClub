package club.forhouse.controllers;

import club.forhouse.dto.MaterialCategoryDto;
import club.forhouse.services.MaterialCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/material_category")
public class MaterialCategoryController {

    private final MaterialCategoryService materialCategoryService;

    @GetMapping
    public List<MaterialCategoryDto> getAll() {
        return materialCategoryService.getAll();
    }

    @GetMapping("/{id}")
    public MaterialCategoryDto getById(@PathVariable Long id) {
        return materialCategoryService.getById(id);
    }
}
