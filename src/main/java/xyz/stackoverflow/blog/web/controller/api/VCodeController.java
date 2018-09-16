package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/api")
public class VCodeController {

    @RequestMapping("/vcode")
    public void vcode(HttpServletResponse response, HttpSession session) {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String verifyCode = drawImg(output);

        session.setAttribute("vcode", verifyCode);

        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String drawImg(ByteArrayOutputStream output) {

        String code = "";
        for (int i = 0; i < 4; i++) {
            code += randomChar();
        }

        int width = 70;
        int height = 30;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bi.createGraphics();
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(new Color(160, 120, 80));
        g.setBackground(new Color(255, 255, 255));
        g.clearRect(0, 0, width, height);

        Random random = new Random();
        for(int i = 0;i < 50 ;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(15);
            int y1 = random.nextInt(15);
            g.drawLine(x,y,x+x1,y+y1);
        }

        g.setColor(new Color(80, 80, 180));
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int) x, (int) baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    private char randomChar() {
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }
}