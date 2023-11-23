package com.lyc.jujiao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 */
@Data
public class KickOutUserRequest implements Serializable {
    private static final long serialVersionUID = 5733340462955698625L;
    Long teamId;
    Long userId;
}
