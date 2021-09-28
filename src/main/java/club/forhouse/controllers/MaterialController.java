package club.forhouse.controllers;

import club.forhouse.dto.MaterialDto;
import club.forhouse.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/materials")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping
    public Page<MaterialDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        return materialService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public MaterialDto getById(@PathVariable Long id) {
        return materialService.getById(id);
    }
}
