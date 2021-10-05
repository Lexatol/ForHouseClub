package club.forhouse.mappers;

import club.forhouse.dto.LkUserDto;
import club.forhouse.entities.LkUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LkUserMapper {
    LkUserDto toDto(LkUser lkUser);
    List<LkUserDto> toListDto(List<LkUser> lkUserList);
}
