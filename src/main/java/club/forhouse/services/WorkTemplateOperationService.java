package club.forhouse.services;

import club.forhouse.configuration.WorkTemplateOperationMapper;
import club.forhouse.dto.worktemplate.WorkTemplateOperationDto;
import club.forhouse.dto.worktemplate.WorkTemplateOperationNewDto;
import club.forhouse.entities.OperationCategory;
import club.forhouse.entities.WorkTemplate;
import club.forhouse.entities.WorkTemplateOperation;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.OperationCategoryRepository;
import club.forhouse.repositories.WorkTemplateOperationRepository;
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
public class WorkTemplateOperationService {
    private final WorkTemplateOperationRepository templateOperationRepository;
    private final WorkTemplateRepository templateRepository;
    private final WorkTemplateOperationMapper modelMapper;
    private final OperationCategoryRepository categoryRepository;

    public Page<WorkTemplateOperationDto> getAll(int page, int size) {
        return templateOperationRepository.findAll(PageRequest.of(page, size))
                .map(modelMapper::toDto);
    }

    @Transactional
    public WorkTemplateOperationDto getById(Long id) {
        return templateOperationRepository.findById(id)
                .map(modelMapper::toDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find Work Template Operation with id: " + id));
    }

    @Transactional
    public List<WorkTemplateOperationDto> getByTemplateId(Long templateId) {

        WorkTemplate template = templateRepository.findById(templateId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Work Template with id: " + templateId)
        );
        return templateOperationRepository.findAllByTemplateId(template)
                .stream().map(modelMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public List<WorkTemplateOperationDto> getByCategoryId(Long categoryId) {
        OperationCategory category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Operation Category with id: " + categoryId)
        );
        return templateOperationRepository.findAllByOperationCategory(category)
                .stream().map(modelMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public List<WorkTemplateOperationDto> getByCategoryIdAndTemplateId(Long categoryId, Long templateId) {
        OperationCategory category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Operation Category with id: " + categoryId)
        );

        WorkTemplate template = templateRepository.findById(templateId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Work Template with id: " + templateId)
        );
        return templateOperationRepository.findAllByOperationCategoryAndTemplate(category, template)
                .stream().map(modelMapper::toDto).collect(Collectors.toList());
    }

    public WorkTemplateOperationDto addNew(WorkTemplateOperationNewDto newDto) {
        return modelMapper.toDto(templateOperationRepository.save(modelMapper.toEntity(newDto)));
    }

    public WorkTemplateOperationDto save(WorkTemplateOperationDto operationDto) {
        if (templateOperationRepository.existsById(operationDto.getOperationId())) {
            if (templateRepository.existsById(operationDto.getTemplateId())) {
                WorkTemplateOperation operation = modelMapper.toEntity(operationDto);
                return modelMapper.toDto(templateOperationRepository.save(operation));
            } else {
                throw new ResourceNotFoundException("Unable to find Work Template with id: " + operationDto.getTemplateId());
            }
        } else {
            throw new ResourceNotFoundException("Unable to find Work Template Operation with id: " + operationDto.getOperationId());
        }
    }
}
