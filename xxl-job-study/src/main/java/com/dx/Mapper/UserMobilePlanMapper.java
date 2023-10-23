package com.dx.Mapper;

import com.dx.entity.UserMobilePlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMobilePlanMapper {
    @Select("select * from t_user_mobile_plan")
    List<UserMobilePlan> selectAll();
}