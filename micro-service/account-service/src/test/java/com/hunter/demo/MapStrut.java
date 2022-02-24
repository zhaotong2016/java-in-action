package com.hunter.demo;

import com.hunter.demo.bean.User;
import com.hunter.demo.bean.UserDto;
import com.hunter.demo.bean.UserMapper;
import org.junit.Test;

/**
 * @author Hunter
 * @date 2022/02/22 12:10
 **/
public class MapStrut {


    @Test
    public void userToUserDto(){
        User user = new User();

        user.setName("Hunter");
        user.setId(32);


        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);

        System.out.println(userDto.toString());
    }

}
