package justdemo.cookie;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieDemo")
public class CookieDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*Cookie cookie = new Cookie("msg", "你好");
        //设置同域名下共享cookie
        cookie.setDomain(".sjgyh.com");
        *//*同服务器下共享cookie
        cookie.setPath("/");*//*
        response.addCookie(cookie);*/

        //设置页面编码
        response.setContentType("text/html;charset=utf-8");

        String cookAccessName = "accessTime";
        String accessTime = null;
        Cookie[] cookies = request.getCookies();
        boolean accessed_flag = false;
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookAccessName.equals(cookie.getName())) {
                    accessed_flag = true;
                    accessTime = URLDecoder.decode(cookie.getValue(), "utf-8");
                    break;
                }
            }
        }

        if (accessed_flag) {
            response.getWriter().write("<h1>欢迎回来，您上次的访问时间为：" + accessTime + "</h1>");
        } else {
            response.getWriter().write("<h1>您好，欢迎您首次访问</h1>");
        }
        String currentTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
        currentTime = URLEncoder.encode(currentTime, "utf-8");
        //记录当前访问时间
        Cookie cookie = new Cookie(cookAccessName , currentTime);
        cookie.setMaxAge(500);
        response.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
