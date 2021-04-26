package com.han.gp.controller.student;

import com.github.pagehelper.PageInfo;
import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.domain.User;
import com.han.gp.domain.enums.RoleEnum;
import com.han.gp.domain.enums.UserStatusEnum;
import com.han.gp.service.AuthenticationService;
import com.han.gp.service.UserService;
import com.han.gp.vo.student.user.UserRegister;
import com.han.gp.vo.student.user.UserResponse;
import com.han.gp.vo.student.user.UserUpdate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController("StudentUserController")
@RequestMapping(value = "/api/student/user")
public class UserController extends BaseApiController {

    private final UserService userService;
//    private final MessageService messageService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService, AuthenticationService authenticationService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }




    @RequestMapping(value = "/current", method = RequestMethod.POST)
    public RestResponse<UserResponse> current() {
        User user = getCurrentUser();
        UserResponse userVm = UserResponse.from(user);
        return RestResponse.ok(userVm);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RestResponse register(@RequestBody @Valid UserRegister model) {
        User existUser = userService.getUserByUserName(model.getUserName());
        if (null != existUser) {
            return new RestResponse<>(2, "用户已存在");
        }
        User user = modelMapper.map(model, User.class);
        String encodePwd = authenticationService.pwdEncode(model.getPassword());
        user.setPassword(encodePwd);
        user.setRole(RoleEnum.STUDENT.getCode());
        user.setStatus(UserStatusEnum.Enable.getCode()?1:0);
        user.setLastActiveTime(new Date());
        user.setCreateTime(new Date());
        user.setDeleted(false);
        userService.insertSelective(user);
        return RestResponse.ok();
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RestResponse update(@RequestBody @Valid UserUpdate model) {
        if (StringUtils.isBlank(model.getBirthDay())) {
            model.setBirthDay(null);
        }
        User user = userService.selectById(getCurrentUser().getId());
        modelMapper.map(model, user);
        user.setModifyTime(new Date());
        userService.updateByPrimaryKeySelective(user);
        return RestResponse.ok();
    }

/*    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public RestResponse<List<UserEventLogVM>> log() {
        User user = getCurrentUser();
        List<UserEventLog> userEventLogs = userEventLogService.getUserEventLogByUserId(user.getId());
        List<UserEventLogVM> userEventLogVMS = userEventLogs.stream().map(d -> {
            UserEventLogVM vm = modelMapper.map(d, UserEventLogVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(d.getCreateTime()));
            return vm;
        }).collect(Collectors.toList());
        return RestResponse.ok(userEventLogVMS);
    }*/

/*    @RequestMapping(value = "/message/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<MessageResponseVM>> messagePageList(@RequestBody MessageRequestVM messageRequestVM) {
        messageRequestVM.setReceiveUserId(getCurrentUser().getId());
        PageInfo<MessageUser> messageUserPageInfo = messageService.studentPage(messageRequestVM);
        List<Integer> ids = messageUserPageInfo.getList().stream().map(d -> d.getMessageId()).collect(Collectors.toList());
        List<Message> messages = ids.size() != 0 ? messageService.selectMessageByIds(ids) : null;
        PageInfo<MessageResponseVM> page = PageInfoHelper.copyMap(messageUserPageInfo, e -> {
            MessageResponseVM vm = modelMapper.map(e, MessageResponseVM.class);
            messages.stream().filter(d -> e.getMessageId().equals(d.getId())).findFirst().ifPresent(message -> {
                vm.setTitle(message.getTitle());
                vm.setContent(message.getContent());
                vm.setSendUserName(message.getSendUserName());
            });
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }*/
/*

    @RequestMapping(value = "/message/unreadCount", method = RequestMethod.POST)
    public RestResponse unReadCount() {
        Integer count = messageService.unReadCount(getCurrentUser().getId());
        return RestResponse.ok(count);
    }

    @RequestMapping(value = "/message/read/{id}", method = RequestMethod.POST)
    public RestResponse read(@PathVariable Integer id) {
        messageService.read(id);
        return RestResponse.ok();
    }
*/

}
