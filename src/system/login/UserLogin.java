package system.login;

import org.apache.commons.beanutils.BeanUtils;
import system.login.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/userLogin")
public class UserLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码，防止乱码
        request.setCharacterEncoding("utf-8");

        /*//获取请求参数
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        //创建用户对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);*/

        //获取所有请求参数
        Map<String, String[]> pars = request.getParameterMap();
        User loginUser = new User();
        //使用BeanUtils封装user对象
        try {
            BeanUtils.populate(loginUser, pars);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用login方法
        User user = new LoginCheck().login(loginUser);
        //判断是否成功
        if (null == user) {
            //登录失败,转发到登录失败页面
            request.getRequestDispatcher("/loginFailed").forward(request, response);
        } else {
            //登录成功,转发到登录成功页面
            request.setAttribute("user", user);
            request.getRequestDispatcher("/loginSuccess").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
