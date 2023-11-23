package com.lyc.jujiao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 */
@Data
public class TeamJoinRequest implements Serializable {
    private static final long serialVersionUID = 4439560746192023859L;
    /**
     * 队伍id
     */
    private Long teamId;
    /**
     * 队伍密码
     */
    private String password;
}
