package ir.torshizi.upload_with_base64.object;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "picUrl", target = "picUrl")
    })
    public UserDto toUserDto (UserEntity userEntity);

    @InheritInverseConfiguration(name = "toUserDto")
    public UserEntity toUserEntity (UserDto userDto);
}
