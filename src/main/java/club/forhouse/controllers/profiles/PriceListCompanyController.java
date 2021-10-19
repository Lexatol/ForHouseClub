package club.forhouse.controllers.profiles;


import club.forhouse.dto.pricelist.PriceListCompanyDto;
import club.forhouse.dto.profiles.ProfileContractorDto;
import club.forhouse.services.profiles.PriceListCompanyService;
import club.forhouse.services.profiles.ProfileCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/pl-company")
public class PriceListCompanyController {
    private final PriceListCompanyService priceListCompanyService;


    @GetMapping("/{id}")
    public PriceListCompanyDto findProfileContractorById(@PathVariable Long id) {
        return priceListCompanyService.findById(id);
    }
}