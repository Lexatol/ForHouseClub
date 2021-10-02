package club.forhouse.services;

import club.forhouse.configuration.WorkTemplateMaterialMapper;
import club.forhouse.dto.worktemplate.WorkTemplateMaterialDto;
import club.forhouse.dto.worktemplate.WorkTemplateMaterialNewDto;
import club.forhouse.entities.WorkTemplate;
import club.forhouse.entities.WorkTemplateMaterial;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.WorkTemplateMaterialRepository;
import club.forhouse.repositories.WorkTemplateRepository;
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
    private final WorkTemplateRepository templateRepository;
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
    public List<WorkTemplateMaterialDto> getByTemplateId(Long templateId) {

        WorkTemplate template = templateRepository.findById(templateId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Work Template with id: " + templateId)
        );
        return templateMaterialRepository.findAllByTemplateId(template)
                .stream().map(modelMapper::toDto).collect(Collectors.toList());
    }

    public WorkTemplateMaterialDto addNew(WorkTemplateMaterialNewDto newDto) {
        return modelMapper.toDto(templateMaterialRepository.save(modelMapper.toEntity(newDto)));
    }

    public WorkTemplateMaterialDto save(WorkTemplateMaterialDto materialDto) {
        if (templateMaterialRepository.existsById(materialDto.getMaterialId())) {
            if (templateRepository.existsById(materialDto.getTemplateId())) {
                WorkTemplateMaterial material = modelMapper.toEntity(materialDto);
                return modelMapper.toDto(templateMaterialRepository.save(material));
            } else {
                throw new ResourceNotFoundException("Unable to find Work Template with id: " + materialDto.getTemplateId());
            }
        } else {
            throw new ResourceNotFoundException("Unable to find Work Template Material with id: " + materialDto.getMaterialId());
        }
    }
}
