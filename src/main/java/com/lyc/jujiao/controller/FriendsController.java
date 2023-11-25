package com.lyc.jujiao.controller;

import com.lyc.jujiao.common.BaseResponse;
import com.lyc.jujiao.common.ErrorCode;
import com.lyc.jujiao.common.ResultUtil;
import com.lyc.jujiao.exception.BusinessException;
import com.lyc.jujiao.model.entity.User;
import com.lyc.jujiao.model.request.FriendAddRequest;
import com.lyc.jujiao.model.vo.FriendsRecordVO;
import com.lyc.jujiao.service.FriendsService;
import com.lyc.jujiao.service.UserService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 */
@RestController
@RequestMapping("/friends")
public class FriendsController {
    @Resource
    private FriendsService friendsService;

    @Resource
    private UserService userService;

    @PostMapping("add")
    public BaseResponse<Boolean> addFriendRecords(@RequestBody FriendAddRequest friendAddRequest, HttpServletRequest request) {
        if (friendAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求有误");
        }
        User loginUser = userService.getLoginUser(request);
        boolean addStatus = friendsService.addFriendRecords(loginUser, friendAddRequest);
        return ResultUtil.success(addStatus, "申请成功");
    }

    /**
     * 获取当前用户收到的申请记录
     * @param request
     * @return
     */
    @GetMapping("getRecords")
    public BaseResponse<List<FriendsRecordVO>> getRecords(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        List<FriendsRecordVO> friendsList = friendsService.obtainFriendApplicationRecords(loginUser);
        return ResultUtil.success(friendsList);
    }

    /**
     * 获取通知数量
     * @param request
     * @return
     */
    @GetMapping("getRecordCount")
    public BaseResponse<Integer> getRecordCount(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        int recordCount = friendsService.getRecordCount(loginUser);
        return ResultUtil.success(recordCount);
    }

    /**
     * 获取当前用户的申请记录
     * @param request
     * @return
     */
    @GetMapping("getMyRecords")
    public BaseResponse<List<FriendsRecordVO>> getMyRecords(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        List<FriendsRecordVO> myFriendsList = friendsService.getMyRecords(loginUser);
        return ResultUtil.success(myFriendsList);
    }

    /**
     * 同意好友申请
     * @param fromId
     * @param request
     * @return
     */
    @PostMapping("agree/{fromId}")
    public BaseResponse<Boolean> agreeToApply(@PathVariable("fromId") Long fromId, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        boolean agreeToApplyStatus = friendsService.agreeToApply(loginUser, fromId);
        return ResultUtil.success(agreeToApplyStatus);
    }

    /**
     * 拒绝好友申请
     * @param fromId
     * @param request
     * @return
     */
    @PostMapping("reject/{fromId}")
    public BaseResponse<Boolean> rejectToApply(@PathVariable("fromId") Long fromId, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        boolean rejectToApplyStatus = friendsService.rejectToApply(loginUser, fromId);
        return ResultUtil.success(rejectToApplyStatus);
    }

    @PostMapping("canceledApply/{id}")
    public BaseResponse<Boolean> canceledApply(@PathVariable("id") Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求有误");
        }
        User loginUser = userService.getLoginUser(request);
        boolean canceledApplyStatus = friendsService.canceledApply(id, loginUser);
        return ResultUtil.success(canceledApplyStatus);
    }

    /**
     * 将未读信息标记为已读
     * @param ids
     * @param request
     * @return
     */
    @GetMapping("/read")
    public BaseResponse<Boolean> toRead(@RequestParam(required = false) Set<Long> ids, HttpServletRequest request) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResultUtil.success(false);
        }
        User loginUser = userService.getLoginUser(request);
        boolean isRead = friendsService.toRead(loginUser, ids);
        return ResultUtil.success(isRead);
    }
}
