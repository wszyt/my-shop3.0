package com.zyt.my.shop.web.ui.api;

public class API {
    //主机地址
    public static final String HOST = "http://localhost:8081/api/v1";

    //内容查询接口 - 幻灯片
    public static final String API_CONTENTS = HOST + "/contents/ppt";

    //内容查询接口 - 中幻灯片
    public static final String API_CONTENTS_MPPT = HOST + "/contents/mppt";

    //用户会员管理接口 - 登录
    public static final String API_USERS_LOGIN = HOST + "/users/login";

    //用户会员管理接口 - 注册
    public static final String API_USER_REGISTER = HOST + "/users/register";

}

