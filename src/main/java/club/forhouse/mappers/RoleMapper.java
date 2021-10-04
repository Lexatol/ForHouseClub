package club.forhouse.mappers;

import club.forhouse.dto.RoleDto;
import club.forhouse.entities.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleDto toDto(Role role);
    List<RoleDto> toListDto(List<Role> roleList);
}
