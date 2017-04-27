package com.example.administrator.dlwxnongxutong.bean;


/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name LoginBean$
 */
public class LoginBean {
    public int code;
    public String message;
    public Userinfo info;

    public class Userinfo{
        public String user_id;//用户id
        public String userphe;//用户手机号
        public String user_photo;//用户头像
        public String username;//用户昵称
        public String account;//账户余额
        public String adminid;//如果未绑定是0，绑定后为其他
        public int integral; //用户积分
    }

}
