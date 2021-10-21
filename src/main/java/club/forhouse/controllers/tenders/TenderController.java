package club.forhouse.controllers.tenders;

import club.forhouse.dto.tenders.SystemTenderDto;
import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.services.tenders.TendersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tenders")
public class TenderController {
    private final TendersService tendersService;

    @GetMapping("id/{id}")
    public TenderDto findById(@PathVariable Long id) {
        return tendersService.findById(id);
    }

    @GetMapping
    public List<TenderDto> findAll() {
        return tendersService.findAll();
    }

    @PutMapping("/add")
    public TenderDto save(@RequestBody SystemTenderDto systemTenderDto) {
        return tendersService.save(systemTenderDto);
    }
}
