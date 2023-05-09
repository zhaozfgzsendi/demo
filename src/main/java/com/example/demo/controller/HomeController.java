package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@RequestParam(name = "name", required = false, defaultValue = "world") String name, Model model) {
        System.out.println("home()");

        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code", required = false, defaultValue = "") String code) {
        System.out.println("callback()");

        if ("".equals(code)) {
            // 获取code失败
            return "index";
        }

        System.out.println(code);

        return "index";
    }
}
