package com.alvis.exam.configuration.spring.security;

import com.alvis.exam.base.SystemCode;
import com.alvis.exam.domain.UserEventLog;
import com.alvis.exam.event.UserEvent;
import com.alvis.exam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 登录成功返回
 *
 * @author alvis
 */
@Component
@AllArgsConstructor
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ApplicationEventPublisher eventPublisher;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User springUser = (User) authentication.getPrincipal();
        com.alvis.exam.domain.User user = userService.getUserByUserName(springUser.getUsername());
        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        userEventLog.setContent(user.getUserName() + " 登录了车管考试系统");
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        com.alvis.exam.domain.User newUser = new com.alvis.exam.domain.User();
        newUser.setId(user.getId());
        newUser.setUserUuid(user.getUserUuid());
        newUser.setUserName(user.getUserName());
        newUser.setRealName(user.getRealName());
        newUser.setAge(user.getAge());
        newUser.setSex(user.getSex());
        newUser.setBirthDay(user.getBirthDay());
        newUser.setUserLevel(user.getUserLevel());
        newUser.setPhone(user.getPhone());
        newUser.setRole(user.getRole());
        newUser.setStatus(user.getStatus());
        newUser.setImagePath(user.getImagePath());
        newUser.setDeleted(user.getDeleted());
        newUser.setWxOpenId(user.getWxOpenId());
        RestUtil.response(response, SystemCode.OK.getCode(), SystemCode.OK.getMessage(), newUser);
    }
}
