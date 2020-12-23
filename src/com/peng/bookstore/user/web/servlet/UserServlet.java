package com.peng.bookstore.user.web.servlet;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import com.peng.bookstore.cart.domain.Cart;
import com.peng.bookstore.user.domain.User;
import com.peng.bookstore.user.domain.UserException;
import com.peng.bookstore.user.service.UserService;
import com.peng.bookstore.utils.BaseServlet;
import com.peng.bookstore.utils.BeanUtils;
import com.peng.bookstore.utils.UUIDUtils;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();

    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User form = BeanUtils.getBean(User.class, map);
//        System.out.println("form" + form);
        String password = request.getParameter("password");

        String username = request.getParameter("username");
        Map<String, String> errors = new HashMap<>();
         if (username == null || username.trim().isEmpty()) {
             errors.put("username", "用户名不能为空");
        }
        if (username.length() < 2 || username.length() > 10) {
            errors.put("username", "用户名长度必须在2到10之间");
        }

        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "密码不能为空");
        }
        if (password.length() < 3 || password.length() > 10) {
            errors.put("password", "密码长度必须在3到10之间");
        }

        String email = request.getParameter("email");
        String regex = "\\w+@\\w+\\.com";
        if (!email.matches(regex)){
            errors.put("email", "邮箱格式不正确");
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            request.setAttribute("unuser", form);
//            System.out.println("unuser " + form);
            request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request, response);
        }



        form.setUid(UUIDUtils.getUUID());
        form.setCode(UUIDUtils.getUUID() + UUIDUtils.getUUID());
        form.setState(false);
        form.setPassword(password);
//        System.out.println(form);

        try{
//            System.out.println(form);
            userService.regist(form);
            sendMail(form);
            request.setAttribute("msg", "您的账号可以注册，请前往注册邮箱完成激活！");

        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("unuser", form);
            request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request, response);
        }
        return "/jsps/msg.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        User form = BeanUtils.getBean(User.class, map);
        try {
            User user = userService.login(form);
            request.getSession().setAttribute("loginUser", user);
            request.getSession().setAttribute("cart", new Cart()); //用户登录成功后，立马为用户设置一个购物车
        } catch (UserException e) {
            System.out.println(e.getMessage());
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("unuser", form);
            return "f:/jsps/user/login.jsp";
        }
        return "/index.jsp";
    }

    public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        return "index.jsp";
    }

    public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        try {
            userService.active(code);
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
        }

        request.setAttribute("msg", "注册完成，请前往登录");
        return "/jsps/msg.jsp";
    }



    public void sendMail(User user){
        InputStream rs = this.getClass().getClassLoader().getResourceAsStream("mail.properties");
        Properties properties = new Properties();
        try {
            properties.load(rs);
            String host = properties.getProperty("host");
            String uname = properties.getProperty("uname");
            String pwd = properties.getProperty("pwd");
            String sender = properties.getProperty("sender");
            String subject = properties.getProperty("subject");
            String content = properties.getProperty("content");
            content = MessageFormat.format(content, user.getCode());  //将占位符替换为真实的激活码

            Session session = MailUtils.createSession(host, uname, pwd);
            Mail mail = new Mail(sender, user.getEmail(), subject, content);
            MailUtils.send(session, mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
