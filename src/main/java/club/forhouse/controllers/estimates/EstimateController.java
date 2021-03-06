package club.forhouse.controllers.estimates;

import club.forhouse.configuration.JwtTokenUtil;
import club.forhouse.dto.estimate.EstimateDto;
import club.forhouse.dto.profiles.CompanyDto;
import club.forhouse.dto.profiles.UserDto;
import club.forhouse.services.estimate.EstimateService;
import club.forhouse.services.profiles.CompanyService;
import club.forhouse.services.profiles.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/estimates")
public class EstimateController {

    private final EstimateService estimateService;
    private final JwtTokenUtil tokenService;
    private final UserService userService;
    private final CompanyService companyService;

    @PostMapping("/new")
    public EstimateDto createNew(@RequestHeader("Authorization") String authorizationHeader) {
        UserDto user = getUserFromToken(authorizationHeader);
        CompanyDto company = companyService.findByUser(user);
        return estimateService.createNew(user, company);
    }

    @PostMapping("/save")
    public EstimateDto save(@RequestBody EstimateDto estimateDto) {
        return estimateService.save(estimateDto);
    }

    @GetMapping
    public Page<EstimateDto> getAll(@RequestHeader("Authorization") String authorizationHeader,
                                    @RequestParam(name = "page", defaultValue = "1") int page) {
        UserDto user = getUserFromToken(authorizationHeader);
        CompanyDto company = companyService.findByUser(user);
        return estimateService.findAll(user, company, --page);
    }

    private UserDto getUserFromToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String usernameFromToken = tokenService.getUsernameFromToken(token);
        return userService.findByUserEmailDto(usernameFromToken);
    }

    @GetMapping("/{id}")
    public EstimateDto getById(@RequestHeader("Authorization") String authorizationHeader,
                               @PathVariable(name = "id") Long estimateId) {
        UserDto user = getUserFromToken(authorizationHeader);
        CompanyDto company = companyService.findByUser(user);
        return estimateService.findByCompanyAndId(company, estimateId);
    }


}
