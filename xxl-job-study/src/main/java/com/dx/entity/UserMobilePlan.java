package com.dx.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserMobilePlan {
    private Long id;//主键
    private String username;//用户名
    private String nickname;//昵称
    private String phone;//手机号码
    private String info;//备注
}