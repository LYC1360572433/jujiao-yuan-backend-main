package com.lyc.jujiao.service;

import com.lyc.jujiao.model.entity.User;
import com.lyc.jujiao.model.request.QQLoginRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Date: 2023年04月24日 16:02
 * @Version: 1.0
 * @Description: 第三方登录
 */
public interface ThirdPartyLoginService {
    /**
     * 获取用户信息
     *
     * @param qqLoginRequest
     * @param request
     * @return
     * @throws IOException
     */
    User getLoginInfo(QQLoginRequest qqLoginRequest, HttpServletRequest request) throws IOException;

    /**
     * 获取QQ登录地址
     *
     * @return
     * @throws IOException
     */
    String qqLogin() throws IOException;
}
