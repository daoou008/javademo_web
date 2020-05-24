package justdemo.servlet;

import sjgyh.utils.DownloadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download")
public class download extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求的文件格式，防止乱码
        request.setCharacterEncoding("utf-8");
        //获取文件名称
        String filename = request.getParameter("filename");
        //获取文件的全路径
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/downloads/" + filename);
        //生成字节流
        FileInputStream fis = new FileInputStream(realPath);

        //生成响应信息
        //对应MIME type，防止乱码
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type", mimeType);

        //为了解决中文乱码的问题，用DownloadUtils工具类，获取转码后的文件名
        String agent = request.getHeader("user-agent");
        filename = DownloadUtils.getFileName(agent, filename);
        //设置打开方式，必须都是下载方式打开(浏览器某些文件格式可以解码，避免浏览器自己解析这些文件)
        response.setHeader("content-disposition", "attachment;filename=" + filename);

        //输出需要下载的文件
        ServletOutputStream os = response.getOutputStream();
        byte[] buffer = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }

        fis.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
