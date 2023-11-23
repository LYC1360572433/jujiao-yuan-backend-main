package com.lyc.jujiao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 */
@Data
public class QQLoginRequest implements Serializable {
    private static final long serialVersionUID = -2110552558438970973L;
    private String code;
}
