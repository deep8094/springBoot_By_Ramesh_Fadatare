package com.brar.springBoot_By_Ramesh_Fadatare.mapper;

import com.brar.springBoot_By_Ramesh_Fadatare.dto.UserDto;
import com.brar.springBoot_By_Ramesh_Fadatare.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class); //use this to call the below methods, since its an interface.

    //mapConstruct will automatically create implementations for them at compile time.

    @Mapping(source = "email", target = "emailAddress")
    UserDto mapUserToUserDto(User user);
    @Mapping(source = "emailAddress", target = "email")
    User mapUserDtoToUser(UserDto userDto);
}
