package club.forhouse.controllers;

import club.forhouse.dto.operation.OperationDto;
import club.forhouse.services.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/operations")
public class OperationController {

    private final OperationService operationService;

    @GetMapping
    public Page<OperationDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        return operationService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public OperationDto getById(@PathVariable Long id) {
        return operationService.getById(id);
    }
}
