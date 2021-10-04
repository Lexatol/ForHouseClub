package club.forhouse.mappers;

import club.forhouse.dto.UserDto;
import club.forhouse.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDto toDto(User user);
    List<UserDto> toListDto(List<User> users);
}
