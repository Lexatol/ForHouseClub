package club.forhouse.services;

import club.forhouse.dto.MaterialCategoryDto;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.MaterialCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialCategoryService {
    private final MaterialCategoryRepository materialCategoryRepository;
    private final ModelMapper categoryMapper;

    public List<MaterialCategoryDto> getAll() {
        return materialCategoryRepository.findAll()
                .stream().map(it -> categoryMapper.map(it, MaterialCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public MaterialCategoryDto getById(Long id) {
        return materialCategoryRepository.findById(id)
                .map(it -> categoryMapper.map(it, MaterialCategoryDto.class))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find Material Category with id: " + id));
    }
}
