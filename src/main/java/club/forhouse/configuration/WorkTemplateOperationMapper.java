package club.forhouse.configuration;

import club.forhouse.dto.worktemplate.WorkTemplateOperationDto;
import club.forhouse.dto.worktemplate.WorkTemplateOperationNewDto;
import club.forhouse.entities.operation.Operation;
import club.forhouse.entities.worktemplate.WorkTemplate;
import club.forhouse.entities.worktemplate.WorkTemplateOperation;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.operation.OperationRepository;
import club.forhouse.repositories.worktemplate.WorkTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class WorkTemplateOperationMapper {
    private final WorkTemplateRepository workTemplateRepository;
    private final OperationRepository operationRepository;
    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(WorkTemplateOperation.class, WorkTemplateOperationDto.class)
                .addMappings(m -> m.skip(WorkTemplateOperationDto::setTemplateId))
                .addMappings(m -> m.skip(WorkTemplateOperationDto::setTemplate))
                .addMappings(m -> m.skip(WorkTemplateOperationDto::setOperationId))
                .addMappings(m -> m.skip(WorkTemplateOperationDto::setOperation))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(WorkTemplateOperationDto.class, WorkTemplateOperation.class)
                .addMappings(m -> m.skip(WorkTemplateOperation::setTemplateId))
                .addMappings(m -> m.skip(WorkTemplateOperation::setOperationId))
                .setPostConverter(toEntityConverter());

        mapper.createTypeMap(WorkTemplateOperationNewDto.class, WorkTemplateOperation.class)
                .addMappings(m -> m.skip(WorkTemplateOperation::setTemplateId))
                .addMappings(m -> m.skip(WorkTemplateOperation::setOperationId))
                .addMappings(m -> m.skip(WorkTemplateOperation::setRowId))
                .setPostConverter(toNewEntityConverter());
    }

    public WorkTemplateOperation toEntity(WorkTemplateOperationDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, WorkTemplateOperation.class);
    }

    public WorkTemplateOperation toEntity(WorkTemplateOperationNewDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, WorkTemplateOperation.class);
    }

    public WorkTemplateOperationDto toDto(WorkTemplateOperation material) {
        return Objects.isNull(material) ? null : mapper.map(material, WorkTemplateOperationDto.class);
    }

    public Converter<WorkTemplateOperationDto, WorkTemplateOperation> toEntityConverter() {
        return context -> {
            WorkTemplateOperationDto source = context.getSource();
            WorkTemplateOperation destination = context.getDestination();
            mapOperation(source, destination);
            mapTemplate(source, destination);
            return context.getDestination();
        };
    }

    public Converter<WorkTemplateOperationNewDto, WorkTemplateOperation> toNewEntityConverter() {
        return context -> {
            WorkTemplateOperationNewDto source = context.getSource();
            WorkTemplateOperation destination = context.getDestination();
            mapOperation(source, destination);
            mapTemplate(source, destination);
            return context.getDestination();
        };
    }

    public Converter<WorkTemplateOperation, WorkTemplateOperationDto> toDtoConverter() {
        return context -> {
            WorkTemplateOperation source = context.getSource();
            WorkTemplateOperationDto destination = context.getDestination();
            mapOperation(source, destination);
            mapTemplate(source, destination);
            return context.getDestination();
        };
    }

    private void mapOperation(WorkTemplateOperationDto source, WorkTemplateOperation destination) {
        if (source.getOperationId() != null) {
            Operation operation = operationRepository.findById(source.getOperationId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Operation with id %d not exist", source.getOperationId())));
            destination.setOperationId(operation);
        }
    }

    private void mapOperation(WorkTemplateOperationNewDto source, WorkTemplateOperation destination) {
        if (source.getOperationId() != null) {
            Operation operation = operationRepository.findById(source.getOperationId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Operation with id %d not exist", source.getOperationId())));
            destination.setOperationId(operation);
        }
    }

    private void mapTemplate(WorkTemplateOperationDto source, WorkTemplateOperation destination) {
        if (source.getTemplateId() != null) {
            WorkTemplate template = workTemplateRepository.findById(source.getTemplateId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("WorkTemplate with id %d not exist", source.getTemplateId())));
            destination.setTemplateId(template);
        }
    }

    private void mapTemplate(WorkTemplateOperationNewDto source, WorkTemplateOperation destination) {
        if (source.getTemplateId() != null) {
            WorkTemplate template = workTemplateRepository.findById(source.getTemplateId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("WorkTemplate with id %d not exist", source.getTemplateId())));
            destination.setTemplateId(template);
        }
    }

    private void mapOperation(WorkTemplateOperation source, WorkTemplateOperationDto destination) {
        Operation operation = source.getOperationId();
        destination.setOperation(operation.getName());
        destination.setOperationId(operation.getOperationId());
    }

    private void mapTemplate(WorkTemplateOperation source, WorkTemplateOperationDto destination) {
        WorkTemplate template = source.getTemplateId();
        destination.setTemplate(template.getName());
        destination.setTemplateId(template.getTemplateId());
    }
}
