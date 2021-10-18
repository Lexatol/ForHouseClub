package club.forhouse.mappers;

import club.forhouse.dto.pricelist.OperDto;
import club.forhouse.entities.operation.Operation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OperMapper {
    OperDto toDto(Operation operation);

    List<OperDto> toListDto(List<Operation> operationsList);
}
