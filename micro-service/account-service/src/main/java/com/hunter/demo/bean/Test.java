package com.hunter.demo.bean;

/**
 * @author Hunter
 * @date 2022/02/22 12:00
 **/
public class Test {

    public static void main(String[] args) {
        User user = new User();

        user.setName("Hunter");
        user.setId(32);


        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);

        System.out.println(userDto.toString());
    }
}
