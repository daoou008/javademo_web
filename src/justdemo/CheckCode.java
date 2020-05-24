package justdemo;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCode")
public class CheckCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 创建对象，在内存中画图
        int width = 80;
        int height = 30;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2. 美化图片，加入干扰信息
        Graphics gra = img.getGraphics();
        //2.1 背景色为粉色的长方形
        gra.setColor(Color.pink);
        gra.fillRect(0, 0, width, height);
        //2.2 蓝色边框
        gra.setColor(Color.blue);
        gra.drawRect(0, 0, width - 1, height - 1);
        //2.3 设置图片中的验证码
        String codeOri = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random r = new Random();
        StringBuilder checkCode = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int charIndex = r.nextInt(codeOri.length());
            char codeEle = codeOri.charAt(charIndex);
            gra.drawString(String.valueOf(codeEle), width / 8 + (width / 4 * i), height / 2);
            checkCode.append(codeEle);
        }

        HttpSession session = request.getSession();

        if (null != checkCode) {
            session.setAttribute("checkCode", checkCode.toString());
        }

        //2.4 画干扰线
        gra.setColor(Color.green);
        for (int i = 0; i < 5; i++) {
            int x1 = r.nextInt(width);      //生成随机点
            int x2 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int y2 = r.nextInt(height);
            gra.drawLine(x1, y1, x2, y2);
        }

        //3. 输出验证码图片
        ImageIO.write(img, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
