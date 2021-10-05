package club.forhouse.mappers;

import club.forhouse.dto.SpecializationDto;
import club.forhouse.entities.Specialization;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface SpecializationMapper {
    SpecializationDto toDTO(Specialization specialization);
    List<SpecializationDto> toListDto(List<Specialization> specializations);
}
