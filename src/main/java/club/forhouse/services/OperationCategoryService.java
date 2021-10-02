package club.forhouse.services;

import club.forhouse.dto.operation.OperationCategoryDto;
import club.forhouse.dto.operation.OperationCategoryNewDto;
import club.forhouse.entities.OperationCategory;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.OperationCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationCategoryService {
    private final OperationCategoryRepository operationCategoryRepository;
    private final ModelMapper categoryMapper;

    public List<OperationCategoryDto> getAll() {
        return operationCategoryRepository.findAll()
                .stream().map(it -> categoryMapper.map(it, OperationCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public OperationCategoryDto getById(Long id) {
        return operationCategoryRepository.findById(id)
                .map(it -> categoryMapper.map(it, OperationCategoryDto.class))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find Operation Category with id: " + id));
    }


    public OperationCategoryDto addNew(OperationCategoryNewDto categoryNewDto) {
        OperationCategory category = categoryMapper.map(categoryNewDto, OperationCategory.class);
        return categoryMapper.map(operationCategoryRepository.save(category), OperationCategoryDto.class);
    }

    public OperationCategoryDto save(OperationCategoryDto categoryDto) {
        if (operationCategoryRepository.existsById(categoryDto.getCategoryId())) {
            OperationCategory materialCategory = categoryMapper.map(categoryDto, OperationCategory.class);
            return categoryMapper.map(operationCategoryRepository.save(materialCategory), OperationCategoryDto.class);
        } else {
            throw new ResourceNotFoundException("Unable to find Operation Category with id: " + categoryDto.getCategoryId());
        }
    }
}
