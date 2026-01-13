package com.ruoyi.common.core.domain.model;

public class RegisterBody extends LoginBody
{
    private static final long serialVersionUID = 1L;

    /** 用户昵称 */
    private String nickName;

    /** 手机号 */
    private String phonenumber;

    // ========== Getter / Setter ==========

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }
}
