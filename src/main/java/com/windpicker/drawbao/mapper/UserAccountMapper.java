package com.windpicker.drawbao.mapper;

import com.windpicker.drawbao.model.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAccountMapper {

    UserAccount findByWxid(@Param("wxid") String wxid);

    int insert(UserAccount user);

    int update(UserAccount user);
}

