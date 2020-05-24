package justdemo.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo5")
public class ServletDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求方式
        String method = request.getMethod();
        System.out.println(method);
        System.out.println("=============================================");
        //获取虚拟目录
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        System.out.println("=============================================");
        //获取Servlet路径
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        System.out.println("=============================================");
        //获取get方式请求参数
        String queryString = request.getQueryString();
        System.out.println(queryString);
        System.out.println("=============================================");
        //获取请求URI
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        System.out.println("=============================================");
        //获取协议及版本
        String protocol = request.getProtocol();
        System.out.println(protocol);
        System.out.println("=============================================");
        //获取客户机IP地址
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
    }
}
