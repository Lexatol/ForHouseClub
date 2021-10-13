package club.forhouse.services;

import club.forhouse.dto.SpecializationDto;
import club.forhouse.entities.Specialization;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.SpecializationMapper;
import club.forhouse.repositories.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationServices {
    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper;

    public SpecializationDto findById(Long id) {
        Specialization spec = specializationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find specialization with id: " + id));
        return specializationMapper.toDTO(spec);
    }

    public Specialization findSpecById(Long id) {
        Specialization spec = specializationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find specialization with id: " + id));
        return spec;
    }

    public List<SpecializationDto> findAll() {
        return specializationMapper.toListDto(specializationRepository.findAll());
    }

}
