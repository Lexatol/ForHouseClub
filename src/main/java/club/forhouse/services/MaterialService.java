package club.forhouse.services;

import club.forhouse.configuration.MaterialMapper;
import club.forhouse.dto.MaterialDto;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    public Page<MaterialDto> getAll(int page, int size) {
        return materialRepository.findAll(PageRequest.of(page, size)).map(materialMapper::toDto);
    }

    public MaterialDto getById(long id) {
        return materialRepository.findById(id)
                .map(materialMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find Material with id:" + id));
    }
}
