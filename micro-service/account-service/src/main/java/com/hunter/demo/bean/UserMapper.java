package com.hunter.demo.bean;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class );


    @ToEntity
    @Mapping(source = "name", target = "userName")
    @Mapping(source = "id", target = "id")
    UserDto userToUserDto(User user);
}
