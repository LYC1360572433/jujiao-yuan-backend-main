package com.lyc.jujiao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 */
@Data
public class UserUpdateRequest implements Serializable {
    private static final long serialVersionUID = 2208508457574775689L;
    Integer id;
    String field;
    String editValue;
}
