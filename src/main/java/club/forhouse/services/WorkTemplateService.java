package club.forhouse.services;

import club.forhouse.dto.WorkTemplateDto;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.WorkTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class WorkTemplateService {
    private final WorkTemplateRepository workTemplateRepository;
    private final ModelMapper modelMapper;

    public Page<WorkTemplateDto> getAll(int page, int size) {
        return workTemplateRepository.findAll(PageRequest.of(page, size))
                .map(it -> modelMapper.map(it, WorkTemplateDto.class));
    }

    @Transactional
    public WorkTemplateDto getById(Long id) {
        return workTemplateRepository.findById(id)
                .map(it -> modelMapper.map(it, WorkTemplateDto.class))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find Work Template with id: " + id));
    }
}
