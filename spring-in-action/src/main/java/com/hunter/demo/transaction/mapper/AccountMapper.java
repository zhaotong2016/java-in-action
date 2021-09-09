package com.hunter.demo.transaction.mapper;

import com.hunter.demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AccountMapper {

    @Select("SELECT * FROM account WHERE id = #{userId}")
    User getUser(@Param("userId") Integer userId);
}
