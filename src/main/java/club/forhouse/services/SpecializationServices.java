package club.forhouse.services;

import club.forhouse.dto.SpecializationDto;
import club.forhouse.entities.Specialization;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.SpecializationMapper;
import club.forhouse.repositories.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecializationServices {
    private final SpecializationRepository specializationRepository;

    public SpecializationDto findById(Long id) {
        Specialization spec = specializationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find specialization with id: " + id));
        return new SpecializationDto(spec);
    }

    public List<SpecializationDto> findAll() {
        List<Specialization> listSpec = specializationRepository.findAll();
        return listSpec.stream().map(SpecializationDto::new).collect(Collectors.toList());
    }

}
