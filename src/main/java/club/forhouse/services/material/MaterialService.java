package club.forhouse.services.material;

import club.forhouse.configuration.MaterialMapper;
import club.forhouse.dto.material.MaterialDto;
import club.forhouse.dto.material.MaterialNewDto;
import club.forhouse.entities.material.Material;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.material.MaterialRepository;
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

    public MaterialDto addNew(MaterialNewDto newDto) {
        Material material = materialMapper.toEntity(newDto);
        return materialMapper.toDto(materialRepository.save(material));
    }

    public MaterialDto save(MaterialDto materialDto) {
        if (materialRepository.existsById(materialDto.getMaterialId())) {
            Material material = materialMapper.toEntity(materialDto);
            return materialMapper.toDto(materialRepository.save(material));
        } else {
            throw new ResourceNotFoundException("Unable to find Material  with id: " + materialDto.getMaterialId());
        }
    }

}
