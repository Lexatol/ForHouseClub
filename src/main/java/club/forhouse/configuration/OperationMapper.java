package club.forhouse.configuration;

import club.forhouse.dto.operation.OperationDto;
import club.forhouse.entities.Operation;
import club.forhouse.entities.OperationCategory;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.OperationCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OperationMapper {
    private final OperationCategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Operation.class, OperationDto.class)
                .addMappings(m -> m.skip(OperationDto::setCategoryId))
                .addMappings(m -> m.skip(OperationDto::setCategory))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(OperationDto.class, Operation.class)
                .addMappings(m -> m.skip(Operation::setCategory)).setPostConverter(toEntityConverter());
    }


    public Operation toEntity(OperationDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Operation.class);
    }

    public OperationDto toDto(Operation material) {
        return Objects.isNull(material) ? null : mapper.map(material, OperationDto.class);
    }

    public Converter<OperationDto, Operation> toEntityConverter() {
        return context -> {
            OperationDto source = context.getSource();
            Operation destination = context.getDestination();
            mapCategory(source, destination);
            return context.getDestination();
        };
    }

    public Converter<Operation, OperationDto> toDtoConverter() {
        return context -> {
            Operation source = context.getSource();
            OperationDto destination = context.getDestination();
            mapCategory(source, destination);
            return context.getDestination();
        };
    }

    private void mapCategory(OperationDto source, Operation destination) {
        if (source.getCategoryId() != null) {
            OperationCategory category = categoryRepository.findById(source.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %d not exist", source.getCategoryId())));
            destination.setCategory(category);
        }
    }

    private void mapCategory(Operation source, OperationDto destination) {
        OperationCategory category = source.getCategory();
        if (category != null) {
            destination.setCategoryId(category.getCategoryId());
            destination.setCategory(category.getName());
        }
    }

}
