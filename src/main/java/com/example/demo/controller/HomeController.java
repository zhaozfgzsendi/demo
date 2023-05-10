package com.example.demo.controller;

import com.example.demo.service.OAuthService;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private OAuthService oAuthService;

    @GetMapping("/")
    public String home(@RequestParam(name = "name", required = false, defaultValue = "world") String name, Model model) {
        System.out.println("home()");

        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code", required = false, defaultValue = "") String code, @RequestParam String state) {
        System.out.println("callback()");

        if ("".equals(code)) {
            // 获取code失败
            return "index";
        }

        String accessToken = oAuthService.getAccessToken(code);

        if (Strings.isNotBlank(accessToken)) {
            User user = oAuthService.getUserInfo(accessToken);
            log.info("获取到的用户信息：{}", user);
        }

        return "index";
    }
}
