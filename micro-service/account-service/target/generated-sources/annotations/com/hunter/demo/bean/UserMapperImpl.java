package com.hunter.demo.bean;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-22T12:23:21+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_41 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserName( user.getName() );
        if ( user.getId() != null ) {
            userDto.setId( String.valueOf( user.getId() ) );
        }

        return userDto;
    }
}
