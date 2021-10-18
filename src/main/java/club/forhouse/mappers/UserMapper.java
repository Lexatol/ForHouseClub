package club.forhouse.mappers;

import club.forhouse.dto.profiles.UserDto;
import club.forhouse.entities.profiles.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto user);

    List<UserDto> toListDto(List<User> users);
}
