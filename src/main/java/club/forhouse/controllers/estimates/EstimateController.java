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

    @PostMapping
    public EstimateDto createNew(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String usernameFromToken = tokenService.getUsernameFromToken(token);
        UserDto user = userService.findByUserEmailDto(usernameFromToken);
        CompanyDto company = companyService.findByUser(user);
        return estimateService.createNew(user, company);
    }

    @GetMapping
    public Page<EstimateDto> getAll(@RequestHeader("Authorization") String authorizationHeader,
                                    @RequestParam(name = "page", defaultValue = "1") int page) {
        String token = authorizationHeader.replace("Bearer ", "");
        String usernameFromToken = tokenService.getUsernameFromToken(token);
        UserDto user = userService.findByUserEmailDto(usernameFromToken);
        CompanyDto company = companyService.findByUser(user);
        return estimateService.findAll(user, company, --page);
    }

}
