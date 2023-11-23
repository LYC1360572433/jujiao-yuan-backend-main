package com.lyc.jujiao.model.request;

import com.lyc.jujiao.common.PageRequest;
import lombok.Data;

/**
 * @Description:
 */
@Data
public class UserSearchRequest extends PageRequest {
    private static final long serialVersionUID = 5579195046213219475L;
    private String username;
}
