package club.forhouse.configuration;

import club.forhouse.dto.worktemplate.WorkTemplateMaterialDto;
import club.forhouse.dto.worktemplate.WorkTemplateMaterialNewDto;
import club.forhouse.entities.material.Material;
import club.forhouse.entities.worktemplate.WorkTemplateMaterial;
import club.forhouse.entities.worktemplate.WorkTemplateOperation;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.material.MaterialRepository;
import club.forhouse.repositories.worktemplate.WorkTemplateOperationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class WorkTemplateMaterialMapper {
    private final WorkTemplateOperationRepository operationRepository;
    private final MaterialRepository materialRepository;
    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(WorkTemplateMaterial.class, WorkTemplateMaterialDto.class)
                .addMappings(m -> m.skip(WorkTemplateMaterialDto::setOperationId))
                .addMappings(m -> m.skip(WorkTemplateMaterialDto::setOperation))
                .addMappings(m -> m.skip(WorkTemplateMaterialDto::setMaterialId))
                .addMappings(m -> m.skip(WorkTemplateMaterialDto::setMaterial))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(WorkTemplateMaterialDto.class, WorkTemplateMaterial.class)
                .addMappings(m -> m.skip(WorkTemplateMaterial::setOperationId))
                .addMappings(m -> m.skip(WorkTemplateMaterial::setMaterialId))
                .setPostConverter(toEntityConverter());

        mapper.createTypeMap(WorkTemplateMaterialNewDto.class, WorkTemplateMaterial.class)
                .addMappings(m -> m.skip(WorkTemplateMaterial::setOperationId))
                .addMappings(m -> m.skip(WorkTemplateMaterial::setMaterialId))
                .addMappings(m -> m.skip(WorkTemplateMaterial::setRowId))
                .setPostConverter(toNewEntityConverter());
    }

    public WorkTemplateMaterial toEntity(WorkTemplateMaterialDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, WorkTemplateMaterial.class);
    }

    public WorkTemplateMaterial toEntity(WorkTemplateMaterialNewDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, WorkTemplateMaterial.class);
    }

    public WorkTemplateMaterialDto toDto(WorkTemplateMaterial material) {
        return Objects.isNull(material) ? null : mapper.map(material, WorkTemplateMaterialDto.class);
    }

    private Converter<WorkTemplateMaterialDto, WorkTemplateMaterial> toEntityConverter() {
        return context -> {
            WorkTemplateMaterialDto source = context.getSource();
            WorkTemplateMaterial destination = context.getDestination();
            mapMaterial(source, destination);
            mapOperation(source, destination);
            return context.getDestination();
        };
    }

    private Converter<WorkTemplateMaterialNewDto, WorkTemplateMaterial> toNewEntityConverter() {
        return context -> {
            WorkTemplateMaterialNewDto source = context.getSource();
            WorkTemplateMaterial destination = context.getDestination();
            mapMaterial(source, destination);
            mapOperation(source, destination);
            return context.getDestination();
        };
    }

    private Converter<WorkTemplateMaterial, WorkTemplateMaterialDto> toDtoConverter() {
        return context -> {
            WorkTemplateMaterial source = context.getSource();
            WorkTemplateMaterialDto destination = context.getDestination();
            mapMaterial(source, destination);
            mapOperation(source, destination);
            return context.getDestination();
        };
    }

    private void mapMaterial(WorkTemplateMaterialDto source, WorkTemplateMaterial destination) {
        if (source.getMaterialId() != null) {
            Material material = materialRepository.findById(source.getMaterialId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Material with id %d not exist", source.getMaterialId())));
            destination.setMaterialId(material);
        }
    }

    private void mapMaterial(WorkTemplateMaterialNewDto source, WorkTemplateMaterial destination) {
        if (source.getMaterialId() != null) {
            Material material = materialRepository.findById(source.getMaterialId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Material with id %d not exist", source.getMaterialId())));
            destination.setMaterialId(material);
        }
    }

    private void mapOperation(WorkTemplateMaterialDto source, WorkTemplateMaterial destination) {
        if (source.getOperationId() != null) {
            WorkTemplateOperation operation = operationRepository.findById(source.getOperationId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("WorkTemplateOperation with id %d not exist", source.getOperationId())));
            destination.setOperationId(operation);
        }
    }

    private void mapOperation(WorkTemplateMaterialNewDto source, WorkTemplateMaterial destination) {
        if (source.getOperationId() != null) {
            WorkTemplateOperation operation = operationRepository.findById(source.getOperationId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("WorkTemplateOperation with id %d not exist", source.getOperationId())));
            destination.setOperationId(operation);
        }
    }

    private void mapMaterial(WorkTemplateMaterial source, WorkTemplateMaterialDto destination) {
        Material material = source.getMaterialId();
        destination.setMaterial(material.getName());
        destination.setMaterialId(material.getMaterialId());
    }

    private void mapOperation(WorkTemplateMaterial source, WorkTemplateMaterialDto destination) {
        WorkTemplateOperation operation = source.getOperationId();
        destination.setOperation(operation.getOperationId().getName());
        destination.setOperationId(operation.getRowId());
    }

}
