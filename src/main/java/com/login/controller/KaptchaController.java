package com.login.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.login.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@CrossOrigin
@Controller
public class KaptchaController {
    /**
     * 验证码工具
     */
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte[] captcha = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // 将生成的验证码保存在session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("rightCode", createText);
            String rightCode = (String) request.getSession().getAttribute("rightCode");
            System.out.println(rightCode);
            BufferedImage bi = defaultKaptcha.createImage(createText);
            ImageIO.write(bi, "jpg", out);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        captcha = out.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream sout = response.getOutputStream();
        sout.write(captcha);
        sout.flush();
        sout.close();
    }

//    /**
//     * 校对验证码
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/log", method = RequestMethod.POST)
//    public ModelAndView imgvrifyControllerDefaultKaptcha(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView model = new ModelAndView();
//        String rightCode = (String) request.getSession().getAttribute("rightCode");
//        String tryCode = request.getParameter("Vcode");
//        System.out.println("rightCode:" + rightCode + " ———— tryCode:" + tryCode);
//        if (!rightCode.equals(tryCode)) {
//            model.addObject("info", "验证码错误,请再输一次!");
//            model.setViewName("error");
//        } else {
//            model.addObject("info", "登陆成功");
//            model.setViewName("index");
//        }
//        return model;
//    }
    @PostMapping("/login")
    @ResponseBody
    public Result add(HttpServletRequest request){
        String rightCode = (String) request.getSession().getAttribute("rightCode");
        String tryCode = request.getParameter("Vcode");
        System.out.println("rightCode:" + rightCode + " ———— tryCode:" + tryCode);
        if (!rightCode.equals(tryCode)) {
            return Result.error("200","验证码错误");
        } else {
            return Result.success();
        }

    }

    /**
     * 返回首页
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("login");
    }
}