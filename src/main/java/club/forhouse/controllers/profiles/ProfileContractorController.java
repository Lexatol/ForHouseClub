package club.forhouse.controllers.profiles;


import club.forhouse.dto.profiles.PriceListCompanyDto;
import club.forhouse.dto.profiles.ProfileContractorDto;
import club.forhouse.dto.operation.OperationDto;
import club.forhouse.services.profiles.PriceListCompanyService;
import club.forhouse.services.profiles.ProfileCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/profile_companies")
public class ProfileContractorController {
    private final ProfileCompanyService profileCompanyService;
    private final PriceListCompanyService priceListCompanyService;

    @GetMapping
    public List<ProfileContractorDto> findAll() {
        return profileCompanyService.findAll();
    }


    @GetMapping("/{id}")
    public ProfileContractorDto findProfileContractorById(@PathVariable Long id) {
        return profileCompanyService.findById(id);
    }

    @GetMapping("/company_info")
    public ProfileContractorDto findProfileContractorByGeneralManager(@RequestParam String email) {
        return profileCompanyService.findCompanyByGeneralManagerEmail(email);
    }

    @PostMapping("/save")
    public ProfileContractorDto saveOrUpdateProfile(@RequestBody ProfileContractorDto profileContractorDto) {
        return profileCompanyService.saveOrUpdate(profileContractorDto);
    }
    //TODO необходимо протестить, не проверял, уточнить возможность передачи двух requestbody
    @PostMapping("/add_operation")
    public ProfileContractorDto addNewOperation
            (@RequestBody ProfileContractorDto profileContractorDto,
             @RequestBody OperationDto operationDto) {
        PriceListCompanyDto priceList = priceListCompanyService.addNewOperation(profileContractorDto, operationDto);
        profileContractorDto.setPriceListCompany(priceList);
        return profileContractorDto;
    }
}
