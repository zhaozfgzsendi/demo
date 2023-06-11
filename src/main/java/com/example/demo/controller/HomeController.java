package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserInfo;
import com.example.demo.service.OAuthService;
import com.example.demo.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String home(@RequestParam(name = "name", required = false, defaultValue = "world") String name, Model model, HttpServletRequest request) {
        System.out.println("home()");

        Cookie[] cookies = request.getCookies();
        cookies = cookies == null ? new Cookie[]{} : cookies;
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                UserInfo userInfo = userMapper.findByToken(token);
                name = userInfo.getName();
                if (userInfo != null) {
                    request.getSession().setAttribute("user", userInfo);
                    log.info("成功获取用户信息：{}", userInfo);
                }
                break;
            }
        }

        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code", required = false, defaultValue = "") String code,
                           @RequestParam String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        System.out.println("callback()");

        if ("".equals(code)) {
            // 获取code失败
            return "index";
        }

        String accessToken = oAuthService.getAccessToken(code);

        if (Strings.isNotBlank(accessToken)) {
            User user = oAuthService.getUserInfo(accessToken);
            log.info("获取到的用户信息：{}", user);
            if (user != null && user.getId() != null) {
                String token = UUID.randomUUID().toString();
                UserInfo userInfo = new UserInfo();
                userInfo.setToken(token);
                userInfo.setName(user.getName());
                userInfo.setAccountId(String.valueOf(user.getId()));
                userInfo.setGmtCreate(System.currentTimeMillis());
                userInfo.setGmtModified(userInfo.getGmtCreate());

                // 写入数据库
                userMapper.insert(userInfo);
                response.addCookie(new Cookie("token", token));

                return "redirect:/";
            } else {
                String errmsg = "github登录获取用户信息失败：" + accessToken + user;
                log.error(errmsg);
                request.getSession().setAttribute("errmsg",errmsg);
            }
        }

        return "index";
    }
}
