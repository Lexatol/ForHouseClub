package club.forhouse.controllers.estimates;

import club.forhouse.dto.material.MaterialCategoryDto;
import club.forhouse.dto.material.MaterialCategoryNewDto;
import club.forhouse.services.material.MaterialCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/material_categories")
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

    @PostMapping
    public MaterialCategoryDto addNew(@RequestBody MaterialCategoryNewDto newCategory) {
        return materialCategoryService.addNew(newCategory);
    }

    @PutMapping
    public MaterialCategoryDto save(@RequestBody MaterialCategoryDto newCategory) {
        return materialCategoryService.save(newCategory);
    }

}
