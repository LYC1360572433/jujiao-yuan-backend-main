package com.lyc.jujiao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 */
@Data
public class UserQueryRequest implements Serializable {

    private static final long serialVersionUID = 8245489531033247232L;
    /**
     * 查询用户
     */
    private String searchText;
}
