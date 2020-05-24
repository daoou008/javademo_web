package justdemo.session;

import justdemo.login.domain.User;
import justdemo.servlet.LoginCheck;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置当前页面编码
        request.setCharacterEncoding("utf-8");

        boolean checkCodeCorrect = false;
        boolean userPasswordCorrect = false;
        //获取验证码
        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        if (null != session) {
            Object o = session.getAttribute("checkCode");
            if (null != o && checkCode.equalsIgnoreCase((String)o)) {
                checkCodeCorrect = true;
            }
        }
        session.removeAttribute("checkCode");
        //如果验证码验证失败，直接跳转回login页面，并提示验证码失败
        if (!checkCodeCorrect) {
            request.setAttribute("checkCodeError", "验证码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        else {
            //获取当前请求参数
            Map<String, String[]> pars = request.getParameterMap();
            User loginUser = new User();
            //使用BeanUtils封装user对象
            try {
                //BeanUtils.populate(loginUser, pars);
                loginUser.setUsername(pars.get("username")[0]);
                loginUser.setPassword(pars.get("password")[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //调用login方法
            User user = new LoginCheck().login(loginUser);
            //判断是否成功
            if (null == user) {
                //登录失败,返回到登录页面
                request.setAttribute("userError", "用户名或密码错误!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                //登录成功,转发到登录成功页面
                //request.setAttribute("user", user);
                //request.getRequestDispatcher("/success.jsp").forward(request, response);
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/success.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
