package club.forhouse.services;

import club.forhouse.dto.ProfileContractorDto;
import club.forhouse.entities.ProfileCompany;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.ProfileContractorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileContractorService {
    private final ProfileContractorRepository profileContractorRepository;

    public List<ProfileContractorDto> findAll() {
        return profileContractorRepository.findAll().stream().map(ProfileContractorDto::new).collect(Collectors.toList());
    }

    public ProfileContractorDto findById(Long id) {
        ProfileCompany pfC = profileContractorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find profile with id: " + id));
        return new ProfileContractorDto(pfC);
    }

}
