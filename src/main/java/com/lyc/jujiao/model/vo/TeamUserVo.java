package com.lyc.jujiao.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description: 队伍和用户信息封装类（脱敏）
 */
@Data
public class TeamUserVo implements Serializable {
    private static final long serialVersionUID = 4408963399165943029L;

    private Set<TeamVo> teamSet;
}
