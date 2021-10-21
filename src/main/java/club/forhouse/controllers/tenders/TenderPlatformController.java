package club.forhouse.controllers.tenders;

import club.forhouse.dto.tenders.SystemTenderDto;
import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.dto.tenders.TenderPlatformDto;
import club.forhouse.services.tenders.TenderPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/platform")
public class TenderPlatformController {
    private final TenderPlatformService tendersPlatformService;

    @GetMapping("id/{id}")
    public TenderPlatformDto findById(@PathVariable Long id) {
        return tendersPlatformService.findById(id);
    }

    @GetMapping
    public List<TenderPlatformDto> findAll() {
        return tendersPlatformService.findAll();
    }

    @GetMapping("/add")
    public TenderPlatformDto add(@RequestBody SystemTenderDto systemTenderDto) {
        return tendersPlatformService.addTender(systemTenderDto);
    }


}