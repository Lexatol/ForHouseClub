package club.forhouse.mappers;

import club.forhouse.entities.Specialization;
import club.forhouse.dtoM.SpecializationDtoM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SpecializationMapper {

    SpecializationMapper INSTANCE = Mappers.getMapper(SpecializationMapper.class);

    @Mapping(source = "specializationId", target = "id")
    @Mapping(source = "specializationTitle", target = "title")
    SpecializationDtoM toDTO(Specialization specialization);

    @Mapping(source = "specializationId", target = "id")
    @Mapping(source = "specializationTitle", target = "title")
    List<SpecializationDtoM> toListDto(List<Specialization> specializations);
}
