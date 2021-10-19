package club.forhouse.services.material;

import club.forhouse.dto.material.MaterialCategoryDto;
import club.forhouse.dto.material.MaterialCategoryNewDto;
import club.forhouse.entities.material.MaterialCategory;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.material.MaterialCategoryRepository;
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

    public MaterialCategoryDto addNew(MaterialCategoryNewDto categoryNewDto) {
        MaterialCategory materialCategory = categoryMapper.map(categoryNewDto, MaterialCategory.class);
        return categoryMapper.map(materialCategoryRepository.save(materialCategory), MaterialCategoryDto.class);
    }

    public MaterialCategoryDto save(MaterialCategoryDto categoryDto) {
        if (materialCategoryRepository.existsById(categoryDto.getCategoryId())) {
            MaterialCategory materialCategory = categoryMapper.map(categoryDto, MaterialCategory.class);
            return categoryMapper.map(materialCategoryRepository.save(materialCategory), MaterialCategoryDto.class);
        } else {
            throw new ResourceNotFoundException("Unable to find Material Category with id: " + categoryDto.getCategoryId());
        }
    }
}
