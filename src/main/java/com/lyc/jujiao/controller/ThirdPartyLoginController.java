package com.lyc.jujiao.controller;

import com.lyc.jujiao.common.BaseResponse;
import com.lyc.jujiao.common.ErrorCode;
import com.lyc.jujiao.common.ResultUtil;
import com.lyc.jujiao.exception.BusinessException;
import com.lyc.jujiao.model.entity.User;
import com.lyc.jujiao.model.request.QQLoginRequest;
import com.lyc.jujiao.service.ThirdPartyLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.lyc.jujiao.contant.UserConstant.LOGIN_USER_STATUS;

/**
 * @Description:
 */
@RestController
@RequestMapping("login")
public class ThirdPartyLoginController {
    @Resource
    private ThirdPartyLoginService thirdPartyLoginService;

    @GetMapping("qq")
    public BaseResponse<String> qqLogin() throws IOException {
        String url = thirdPartyLoginService.qqLogin();
        return ResultUtil.success(url);
    }

    @PostMapping("loginInfo")
    public BaseResponse<User> saveLoginInfo(@RequestBody QQLoginRequest qqLoginRequest, HttpServletRequest request) throws IOException {
        if (qqLoginRequest == null || StringUtils.isBlank(qqLoginRequest.getCode())) {
            throw new BusinessException(ErrorCode.NOT_LOGIN, "请重新登录");
        }
        User user = thirdPartyLoginService.getLoginInfo(qqLoginRequest, request);
        request.getSession().setAttribute(LOGIN_USER_STATUS, user);
        return ResultUtil.success(user);
    }
}
