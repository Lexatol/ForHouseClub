package club.forhouse.controllers.estimates;

import club.forhouse.dto.operation.OperationDto;
import club.forhouse.dto.operation.OperationNewDto;
import club.forhouse.services.operation.OperationService;
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
        return operationService.getAll(page - 1, size);
    }

    @GetMapping("/{id}")
    public OperationDto getById(@PathVariable Long id) {
        return operationService.getById(id);
    }

    @PostMapping
    public OperationDto addNew(@RequestBody OperationNewDto newDto) {
        return operationService.addNew(newDto);
    }

    @PutMapping
    public OperationDto save(@RequestBody OperationDto operationDto) {
        return operationService.save(operationDto);
    }

}
