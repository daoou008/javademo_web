package system.login;

import system.login.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginSuccess")
public class loginSuccess extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置页面编码
        response.setContentType("text/html;charset=utf-8");
        //获取用户信息
        User user = (User)request.getAttribute("user");
        //输出登录成功内容
        try {
            response.getWriter().write("登录成功!" + user.getUsername() + ",欢迎您");
        } catch (Exception e) {
            response.getWriter().write("登录成功!欢迎回来");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
