package club.forhouse.configuration;

import club.forhouse.dto.worktemplate.WorkTemplateMaterialDto;
import club.forhouse.entities.Material;
import club.forhouse.entities.WorkTemplate;
import club.forhouse.entities.WorkTemplateMaterial;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.MaterialRepository;
import club.forhouse.repositories.WorkTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class WorkTemplateMaterialMapper {
    private final WorkTemplateRepository workTemplateRepository;
    private final MaterialRepository materialRepository;
    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(WorkTemplateMaterial.class, WorkTemplateMaterialDto.class)
                .addMappings(m -> m.skip(WorkTemplateMaterialDto::setTemplateId))
                .addMappings(m -> m.skip(WorkTemplateMaterialDto::setTemplate))
                .addMappings(m -> m.skip(WorkTemplateMaterialDto::setMaterialId))
                .addMappings(m -> m.skip(WorkTemplateMaterialDto::setMaterial))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(WorkTemplateMaterialDto.class, WorkTemplateMaterial.class)
                .addMappings(m -> m.skip(WorkTemplateMaterial::setTemplateId))
                .addMappings(m -> m.skip(WorkTemplateMaterial::setMaterialId))
                .setPostConverter(toEntityConverter());
    }


    public WorkTemplateMaterial toEntity(WorkTemplateMaterialDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, WorkTemplateMaterial.class);
    }

    public WorkTemplateMaterialDto toDto(WorkTemplateMaterial material) {
        return Objects.isNull(material) ? null : mapper.map(material, WorkTemplateMaterialDto.class);
    }

    public Converter<WorkTemplateMaterialDto, WorkTemplateMaterial> toEntityConverter() {
        return context -> {
            WorkTemplateMaterialDto source = context.getSource();
            WorkTemplateMaterial destination = context.getDestination();
            mapMaterial(source, destination);
            mapTemplate(source, destination);
            return context.getDestination();
        };
    }

    public Converter<WorkTemplateMaterial, WorkTemplateMaterialDto> toDtoConverter() {
        return context -> {
            WorkTemplateMaterial source = context.getSource();
            WorkTemplateMaterialDto destination = context.getDestination();
            mapMaterial(source, destination);
            mapTemplate(source, destination);
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

    private void mapTemplate(WorkTemplateMaterialDto source, WorkTemplateMaterial destination) {
        if (source.getTemplateId() != null) {
            WorkTemplate template = workTemplateRepository.findById(source.getTemplateId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("WorkTemplate with id %d not exist", source.getTemplateId())));
            destination.setTemplateId(template);
        }
    }

    private void mapMaterial(WorkTemplateMaterial source, WorkTemplateMaterialDto destination) {
        Material material = source.getMaterialId();
        if (material != null) {
            destination.setMaterial(material.getName());
            destination.setMaterialId(material.getMaterialId());
        }
    }

    private void mapTemplate(WorkTemplateMaterial source, WorkTemplateMaterialDto destination) {
        WorkTemplate template = source.getTemplateId();
        if (template != null) {
            destination.setTemplate(template.getName());
            destination.setTemplateId(template.getTemplateId());
        }
    }

}
