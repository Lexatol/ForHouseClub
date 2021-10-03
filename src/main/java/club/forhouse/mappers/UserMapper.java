package club.forhouse.mappers;

import club.forhouse.dtoM.UserDtoM;
import club.forhouse.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "userName", target = "username")
    @Mapping(source = "userEmail", target = "email")
    @Mapping(source = "userPhone", target = "phone")
    UserDtoM toDto(User user);

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "userName", target = "username")
    @Mapping(source = "userEmail", target = "email")
    @Mapping(source = "userPhone", target = "phone")
    List<UserDtoM> toListDto(List<User> users);
}
