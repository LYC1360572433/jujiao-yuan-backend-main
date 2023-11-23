package com.lyc.jujiao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 */
@Data
public class TransferTeamRequest implements Serializable {
    private static final long serialVersionUID = -6119912852151581286L;
    private String userAccount;
    private Long teamId;
}
