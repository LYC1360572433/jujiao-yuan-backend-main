package com.lyc.jujiao.controller;

import com.lyc.jujiao.common.BaseResponse;
import com.lyc.jujiao.common.ErrorCode;
import com.lyc.jujiao.common.ResultUtil;
import com.lyc.jujiao.exception.BusinessException;
import com.lyc.jujiao.model.entity.User;
import com.lyc.jujiao.model.request.*;
import com.lyc.jujiao.model.vo.TeamUserVo;
import com.lyc.jujiao.model.vo.TeamVo;
import com.lyc.jujiao.service.TeamService;
import com.lyc.jujiao.service.UserService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @Description:
 */
@RestController
@RequestMapping("/team")
public class TeamController {
    @Resource
    private TeamService teamService;

    @Resource
    private UserService userService;

    /**
     * 查看队伍信息
     * @param teamId
     * @param request
     * @return
     */
    @GetMapping("/{teamId}")
    public BaseResponse<TeamVo> getUsersByTeamId(@PathVariable("teamId") Long teamId, HttpServletRequest request) {
        if (teamId == null || teamId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该用户暂未加入队伍");
        }
        TeamVo teams = teamService.getUsersByTeamId(teamId, request);
        return ResultUtil.success(teams);
    }

    /**
     * 获取队伍列表
     * @return
     */
    @GetMapping("/teams")
    public BaseResponse<TeamUserVo> getTeams() {
        TeamUserVo teams = teamService.getTeams();
        return ResultUtil.success(teams);
    }

    /**
     * 解散队伍
     * @param teamId
     * @param request
     * @return
     */
    @PostMapping("/{teamId}")
    public BaseResponse<Boolean> dissolutionByTeamId(@PathVariable("teamId") Long teamId, HttpServletRequest request) {
        if (teamId == null || teamId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该用户暂未加入队伍");
        }
        boolean dissolutionTeam = teamService.dissolutionTeam(teamId, request);
        return ResultUtil.success(dissolutionTeam);
    }

    /**
     * 退出队伍
     * @param teamId
     * @param request
     * @return
     */
    @PostMapping("/quit/{teamId}")
    public BaseResponse<Boolean> quitTeam(@PathVariable("teamId") Long teamId, HttpServletRequest request) {
        if (teamId == null || teamId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该用户暂未加入队伍");
        }
        boolean quitTeam = teamService.quitTeam(teamId, request);
        return ResultUtil.success(quitTeam);
    }

    /**
     * 查看已加入的队伍
     * @param teamId
     * @param request
     * @return
     */
    @GetMapping("/teamsByIds")
    public BaseResponse<TeamUserVo> getTeamListByTeamIds(@RequestParam(required = false) Set<Long> teamId, HttpServletRequest request) {
        if (CollectionUtils.isEmpty(teamId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该用户暂未加入队伍");
        }
        TeamUserVo teams = teamService.getTeamListByTeamIds(teamId, request);
        return ResultUtil.success(teams);
    }

    /**
     * 加入队伍
     * @param joinTeam
     * @param request
     * @return
     */
    @PostMapping("/join")
    public BaseResponse<User> joinTeam(@RequestBody TeamJoinRequest joinTeam, HttpServletRequest request) {
        if (joinTeam == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "加入队伍失败");
        }
        User loginUser = userService.getLoginUser(request);
        User joinUser = teamService.joinTeam(joinTeam, loginUser);
        return ResultUtil.success(joinUser);
    }

    /**
     * 创建队伍
     * @param teamCreateRequest
     * @param request
     * @return
     */
    @PostMapping("/createTeam")
    public BaseResponse<Boolean> createTeam(@RequestBody TeamCreateRequest teamCreateRequest, HttpServletRequest request) {
        if (teamCreateRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "创建队伍失败");
        }
        User loginUser = userService.getLoginUser(request);
        Boolean team = teamService.createTeam(teamCreateRequest, loginUser);
        return ResultUtil.success(team);
    }

    /**
     * 搜索队伍
     * @param teamQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/search")
    public BaseResponse<TeamUserVo> teamQuery(@RequestBody TeamQueryRequest teamQueryRequest, HttpServletRequest request) {
        if (teamQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍不存在");
        }
        TeamUserVo teams = teamService.teamQuery(teamQueryRequest, request);
        return ResultUtil.success(teams);
    }

    /**
     * 更新队伍信息
     * @param teamUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTeam(@RequestBody TeamUpdateRequest teamUpdateRequest, HttpServletRequest request) {
        if (teamUpdateRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "修改队伍失败");
        }
        User loginUser = userService.getLoginUser(request);
        Boolean team = teamService.updateTeam(teamUpdateRequest, loginUser);
        return ResultUtil.success(team);
    }

    /**
     * 踢出用户
     * @param kickOutUserRequest
     * @param request
     * @return
     */
    @PostMapping("/kickOutUser")
    public BaseResponse<Boolean> kickOutTeamByUserId(@RequestBody KickOutUserRequest kickOutUserRequest, HttpServletRequest request) {
        if (kickOutUserRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "该用户不存在");
        }
        User loginUser = userService.getLoginUser(request);
        Boolean kickOut = teamService.kickOutTeamByUserId(kickOutUserRequest, loginUser);
        return ResultUtil.success(kickOut);
    }

    /**
     * 转让队伍
     * @param transferTeamRequest
     * @param request
     * @return
     */
    @PostMapping("/transfer")
    public BaseResponse<Boolean> transferTeam(@RequestBody TransferTeamRequest transferTeamRequest, HttpServletRequest request) {
        if (transferTeamRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "操作失败");
        }
        User loginUser = userService.getLoginUser(request);
        Boolean transferTeam = teamService.transferTeam(transferTeamRequest, loginUser);
        return ResultUtil.success(transferTeam);
    }
}
