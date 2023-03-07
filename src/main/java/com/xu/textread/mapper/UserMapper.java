package com.xu.textread.mapper;

import com.xu.textread.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @Author xyc
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-01-26 22:12:04
* @Entity com.xu.textread.model.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




