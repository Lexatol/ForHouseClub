package ForHouseClub.controllers;


import ForHouseClub.dto.ProfileContractorDto;
import ForHouseClub.entities.ProfileContractor;
import ForHouseClub.exceptions.ResourceNotFoundException;
import ForHouseClub.services.ProfileContractorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/profile_contractor")
public class ProfileContractorController {
    private final ProfileContractorServices profileContractorServices;

    @GetMapping
    public List<ProfileContractorDto> findAll() {
        return profileContractorServices.findAll()
                .stream().map(ProfileContractorDto::new).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ProfileContractorDto findProfileContractorById(@PathVariable Long id) {
        ProfileContractor pfC = profileContractorServices.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find profile with id: " + id));
        return new ProfileContractorDto(pfC);
    }
}
