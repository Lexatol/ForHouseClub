package club.forhouse.services;

import club.forhouse.dto.OperationCategoryDto;
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
}
