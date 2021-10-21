package club.forhouse.configuration;

import club.forhouse.dto.material.MaterialDto;
import club.forhouse.dto.material.MaterialNewDto;
import club.forhouse.entities.material.Material;
import club.forhouse.entities.material.MaterialCategory;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.material.MaterialCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MaterialMapper {
    private final MaterialCategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Material.class, MaterialDto.class)
                .addMappings(m -> m.skip(MaterialDto::setCategoryId))
                .addMappings(m -> m.skip(MaterialDto::setCategory))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(MaterialDto.class, Material.class)
                .addMappings(m -> m.skip(Material::setCategory)).setPostConverter(toEntityConverter());

        mapper.createTypeMap(MaterialNewDto.class, Material.class)
                .addMappings(m -> m.skip(Material::setCategory)).setPostConverter(toNewEntityConverter())
                .addMappings(m -> m.skip(Material::setMaterialId));
    }


    public Material toEntity(MaterialDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Material.class);
    }

    public Material toEntity(MaterialNewDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Material.class);
    }

    public MaterialDto toDto(Material material) {
        return Objects.isNull(material) ? null : mapper.map(material, MaterialDto.class);
    }

    public Converter<MaterialDto, Material> toEntityConverter() {
        return context -> {
            MaterialDto source = context.getSource();
            Material destination = context.getDestination();
            mapCategory(source, destination);
            return context.getDestination();
        };
    }

    public Converter<MaterialNewDto, Material> toNewEntityConverter() {
        return context -> {
            MaterialNewDto source = context.getSource();
            Material destination = context.getDestination();
            mapCategory(source, destination);
            return context.getDestination();
        };
    }

    public Converter<Material, MaterialDto> toDtoConverter() {
        return context -> {
            Material source = context.getSource();
            MaterialDto destination = context.getDestination();
            mapCategory(source, destination);
            return context.getDestination();
        };
    }

    private void mapCategory(MaterialDto source, Material destination) {
        if (source.getCategoryId() != null) {
            MaterialCategory category = categoryRepository.findById(source.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %d not exist", source.getCategoryId())));
            destination.setCategory(category);
        } else {
            destination.setCategory(null);
        }
    }

    private void mapCategory(MaterialNewDto source, Material destination) {
        if (source.getCategoryId() != null) {
            MaterialCategory category = categoryRepository.findById(source.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %d not exist", source.getCategoryId())));
            destination.setCategory(category);
        } else {
            destination.setCategory(null);
        }
    }

    private void mapCategory(Material source, MaterialDto destination) {
        MaterialCategory category = source.getCategory();
        if (category != null) {
            destination.setCategoryId(category.getCategoryId());
            destination.setCategory(category.getName());
        }
    }
}
