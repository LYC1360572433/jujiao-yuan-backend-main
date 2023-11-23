package com.lyc.jujiao.controller;

import com.lyc.jujiao.common.BaseResponse;
import com.lyc.jujiao.common.ErrorCode;
import com.lyc.jujiao.common.ResultUtil;
import com.lyc.jujiao.exception.BusinessException;
import com.lyc.jujiao.model.entity.User;
import com.lyc.jujiao.model.request.ChatRequest;
import com.lyc.jujiao.model.vo.MessageVo;
import com.lyc.jujiao.service.ChatService;
import com.lyc.jujiao.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.lyc.jujiao.contant.ChatConstant.*;

/**
 * @Description:
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private ChatService chatService;
    @Resource
    private UserService userService;

    /**
     * 获取好友间聊天记录
     * @param chatRequest
     * @param request
     * @return
     */
    @PostMapping("/privateChat")
    public BaseResponse<List<MessageVo>> getPrivateChat(@RequestBody ChatRequest chatRequest, HttpServletRequest request) {
        if (chatRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求有误");
        }
        User loginUser = userService.getLoginUser(request);
        List<MessageVo> privateChat = chatService.getPrivateChat(chatRequest, PRIVATE_CHAT, loginUser);
        return ResultUtil.success(privateChat);
    }

    /**
     * 获取大厅聊天记录
     * @param request
     * @return
     */
    @GetMapping("/hallChat")
    public BaseResponse<List<MessageVo>> getHallChat(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        List<MessageVo> hallChat = chatService.getHallChat(HALL_CHAT, loginUser);
        return ResultUtil.success(hallChat);
    }

    /**
     * 获取队伍聊天记录
     * @param chatRequest
     * @param request
     * @return
     */
    @PostMapping("/teamChat")
    public BaseResponse<List<MessageVo>> getTeamChat(@RequestBody ChatRequest chatRequest, HttpServletRequest request) {
        if (chatRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求有误");
        }
        User loginUser = userService.getLoginUser(request);
        List<MessageVo> teamChat = chatService.getTeamChat(chatRequest, TEAM_CHAT, loginUser);
        return ResultUtil.success(teamChat);
    }
}
