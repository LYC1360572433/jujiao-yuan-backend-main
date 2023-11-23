package com.lyc.jujiao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 */
@Data
public class UserUpdatePassword implements Serializable {

    private static final long serialVersionUID = -7620643864967860479L;
    long id;
    private String oldPassword;
    private String newPassword;
    private String checkPassword;
}
