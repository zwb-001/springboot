package com.demo.controller;

import com.demo.pojo.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    //验证码宽度，验证码高度
    @Autowired
    private UserService userService;

    //首页面
    @RequestMapping("/")
    public void index(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        HttpSession session;
        //解决乱码问题
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        //创建并且获取保存用户信息的session对象
        session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("userInfo");
        if (user == null) {
            out.print("<h1>您好</h1>\n" + "<h4>您还没登录</h4>\n" + "<a href='/login'>请登录</a>");
        } else {
            out.print("<h2>您已经登陆登录</h2>\n<a href=\"/person>查看信息界面</a>");
        }
        //创建cookie存放session的标识号
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    //从首页跳转到登录页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //从用户个人信息详情页返回用户首页
    @RequestMapping("/user")
    public String person() {
        return "user";
    }

    //退出系统
    @RequestMapping("/logout")
    public String loginout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        //将session对象移除
        httpServletRequest.getSession().removeAttribute("username");
        return "login";
    }

    @RequestMapping("userBack")
    public String user() {
        return "user";
    }

    @RequestMapping("logining")
    //登录功能
    public String logining(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
            , Map<String, Object> map, HttpSession session) throws Exception {
        User userInfo = this.userService.findByUsernameAndPassword(username, password);
        session.setAttribute("username", username);
        if (userInfo != null) {
            //信息正确，跳转到个人页面
            return "user";
        } else {
            //两种方式一种直接显示，一种弹出窗口显示
            map.put("message", "登陆失败,请检查用户名和密码!");
            return "login";
        }

    }

    @RequestMapping("register")
    public String register() {
        return "register";
    }

    //注册功能
    @RequestMapping("/registering")
    public String registering(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "QQ") String QQ,
            @RequestParam(name = "sex") String sex,
            @RequestParam(name = "birthday") String birthday,
            @RequestParam(name = "myself") String myself,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        List<User> usernames = this.userService.findByUsername(username);
        if (CollectionUtils.isEmpty(usernames)) {
            //用户名还没有被注册
            this.userService.save(username, password, QQ, sex, birthday, myself);
            out.print("<script type=\"text/javascript\">alert('注册成功,请您登录!!!')</script>");
            return "login";
        } else {

            out.print("<p style='color=red'>用户名已注册!请更改用户名!</p>");
            return "register";
        }

    }

    //    查看个人信息
    @RequestMapping("/userInfo{username}")
    public ModelAndView getPersonInfo(@RequestParam String username) {
        List<User> user = this.userService.findByUsername(username);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userInfo");
        //thymleaf模板太复杂了，我这个菜鸟学起来好吃力
        String Username = user.get(0).getUsername();
        String Sex = user.get(0).getSex();
        String Birthday = user.get(0).getBirthday();
        String QQ = user.get(0).getQQ();
        String Myself = user.get(0).getMyself();
        mv.addObject("Username", Username);
        mv.addObject("Sex", Sex);
        mv.addObject("Birthday", Birthday);
        mv.addObject("QQ", QQ);
        mv.addObject("Myself", Myself);
        return mv;
    }

    //修改个人信息
    @RequestMapping("/change{username}")
    public String updateinfo(HttpServletResponse httpServletResponse,
                             @RequestParam(value = "username") String username
            , Map<String, Object> map) {
        List<User> users = this.userService.findByUsername(username);
        return "redirect:changeInfo";
    }


}
