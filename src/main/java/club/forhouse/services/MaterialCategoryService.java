package club.forhouse.services;

import club.forhouse.dto.MaterialCategoryDto;
import club.forhouse.entities.MaterialCategory;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.MaterialCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialCategoryService {
    private final MaterialCategoryRepository materialCategoryRepository;
    private final ModelMapper mapper;

    public List<MaterialCategoryDto> getAll() {
        return materialCategoryRepository.findAll()
                .stream().map(it -> mapper.map(it, MaterialCategoryDto.class))
                .collect(Collectors.toList());
    }

    public MaterialCategoryDto getById(Long id) {
        MaterialCategory byId = materialCategoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Material Category with id: " + id));
        return mapper.map(byId, MaterialCategoryDto.class);
    }
}
