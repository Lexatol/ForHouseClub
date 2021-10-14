package club.forhouse.controllers;

import club.forhouse.dto.estimate.EstimateDto;
import club.forhouse.services.EstimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/estimates")
public class EstimateController {

    private final EstimateService estimateService;

    @PostMapping
    public EstimateDto createNew(@RequestParam Long userId, Long companyId) {
        return estimateService.createNew(userId, companyId);
    }
}
