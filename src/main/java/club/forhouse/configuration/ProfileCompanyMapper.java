package club.forhouse.configuration;

import club.forhouse.dto.profiles.ProfileContractorDto;
import club.forhouse.dto.profiles.SpecializationDto;
import club.forhouse.entities.profiles.ProfileCompany;
import club.forhouse.entities.profiles.Specialization;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.profiles.ProfileCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProfileCompanyMapper {
    private final ProfileCompanyRepository profileCompanyRepository;
    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ProfileCompany.class, ProfileContractorDto.class)
                //.addMappings(m -> m.skip(ProfileContractorDto::setSpecializations))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(ProfileContractorDto.class, ProfileCompany.class)
                //.addMappings(m -> m.skip(ProfileCompany::setSpecializations))
                .setPostConverter(toEntityConverter());
    }

    public ProfileCompany toEntity(ProfileContractorDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, ProfileCompany.class);
    }

    public ProfileContractorDto toDto(ProfileCompany profileCompany) {
        return Objects.isNull(profileCompany) ? null : mapper.map(profileCompany, ProfileContractorDto.class);
    }

    private Converter<ProfileCompany, ProfileContractorDto> toDtoConverter() {
        return context -> {
            ProfileCompany source = context.getSource();
            ProfileContractorDto destination = context.getDestination();
            //mapSpecializations(source, destination);
            return context.getDestination();
        };
    }

    // toDto
    private void mapSpecializations(ProfileCompany source, ProfileContractorDto destination) {
        List<Specialization> specializations = source.getSpecializations();
        if (specializations != null){
            for (int i = 0; i < specializations.size(); i++) {
                destination.getSpecializations().add(new SpecializationDto(
                        specializations.get(i).getSpecializationId(),
                        specializations.get(i).getSpecializationTitle()
                ));
            }
        }
    }

    private Converter<ProfileContractorDto, ProfileCompany> toEntityConverter() {
        return context -> {
            ProfileContractorDto source = context.getSource();
            ProfileCompany destination = context.getDestination();
            //mapSpecializations(source, destination);
            return context.getDestination();
        };
    }

    // toEntity
    private void mapSpecializations(ProfileContractorDto source, ProfileCompany destination) {
        if (source.getProfileId() != null) {
            ProfileCompany category = profileCompanyRepository.findById(source.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Profile with id %d not exist", source.getProfileId())));
            destination.setSpecializations(category.getSpecializations());
        } else {
            destination.setSpecializations(null);
        }
    }
}
