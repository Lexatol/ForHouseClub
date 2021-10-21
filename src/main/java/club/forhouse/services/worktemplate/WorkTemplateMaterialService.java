package club.forhouse.services.worktemplate;

import club.forhouse.configuration.WorkTemplateMaterialMapper;
import club.forhouse.dto.worktemplate.WorkTemplateMaterialDto;
import club.forhouse.dto.worktemplate.WorkTemplateMaterialNewDto;
import club.forhouse.entities.worktemplate.WorkTemplateMaterial;
import club.forhouse.entities.worktemplate.WorkTemplateOperation;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.worktemplate.WorkTemplateMaterialRepository;
import club.forhouse.repositories.worktemplate.WorkTemplateOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkTemplateMaterialService {
    private final WorkTemplateMaterialRepository templateMaterialRepository;
    private final WorkTemplateOperationRepository operationRepository;
    private final WorkTemplateMaterialMapper modelMapper;

    public Page<WorkTemplateMaterialDto> getAll(int page, int size) {
        return templateMaterialRepository.findAll(PageRequest.of(page, size))
                .map(modelMapper::toDto);
    }

    @Transactional
    public WorkTemplateMaterialDto getById(Long id) {
        return templateMaterialRepository.findById(id)
                .map(modelMapper::toDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find Work Template Material with id: " + id));
    }

    @Transactional
    public List<WorkTemplateMaterialDto> getByOperationId(Long operationId) {

        WorkTemplateOperation operation = operationRepository.findById(operationId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Work Template Operation with id: " + operationId)
        );
        return templateMaterialRepository.findAllByOperationId(operation)
                .stream().map(modelMapper::toDto).collect(Collectors.toList());
    }

    public WorkTemplateMaterialDto addNew(WorkTemplateMaterialNewDto newDto) {
        return modelMapper.toDto(templateMaterialRepository.save(modelMapper.toEntity(newDto)));
    }

    public WorkTemplateMaterialDto save(WorkTemplateMaterialDto materialDto) {
        if (templateMaterialRepository.existsById(materialDto.getMaterialId())) {
            if (operationRepository.existsById(materialDto.getOperationId())) {
                WorkTemplateMaterial material = modelMapper.toEntity(materialDto);
                return modelMapper.toDto(templateMaterialRepository.save(material));
            } else {
                throw new ResourceNotFoundException("Unable to find Work Operation with id: " + materialDto.getOperationId());
            }
        } else {
            throw new ResourceNotFoundException("Unable to find Work Template Material with id: " + materialDto.getMaterialId());
        }
    }
}
