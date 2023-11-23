package com.lyc.jujiao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 */
@Data
public class TeamQueryRequest implements Serializable {
    private static final long serialVersionUID = -1968358494521098572L;

    /**
     * 查询队伍
     */
    private String searchText;
}
