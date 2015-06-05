package captcha.generator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import captcha.Captcha;
import captcha.ICaptcha;
import captcha.util.ColorUtil;
import captcha.util.Colors;
import captcha.util.FontUtil;
import captcha.util.LineUtil;
import captcha.util.RandomUtil;

/**
 * 英文和数字混合的验证码生成器。
 * 
 * @author Gerry
 * 
 */
public class LetterAndNumberGenerator implements ICaptcha {

    private static LetterAndNumberGenerator letterAndNumberGenerator;

    private final static Logger logger = LoggerFactory.getLogger(LetterAndNumberGenerator.class);

    // 默认验证码位数
    private final int SIZE = 4;
    // 默认验证码宽度
    private final int WIDTH = 80;
    // 默认验证码高度
    private final int HEIGHT = 28;
    // 默认字体大小
    private final float FONTSIZE = 20;

    private LetterAndNumberGenerator() {

    }

    public static LetterAndNumberGenerator getInstance() {
        if (letterAndNumberGenerator == null) {
            letterAndNumberGenerator = new LetterAndNumberGenerator();
        }
        return letterAndNumberGenerator;
    }

    @Override
    public Captcha generateCaptcha() {
        return generateCaptcha(this.SIZE, this.WIDTH, this.HEIGHT, this.FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(int width, int height) {
        return generateCaptcha(this.SIZE, width, height, this.FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(int size, int width, int height) {
        return generateCaptcha(size, width, height, this.FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(int width, int height, float fontSize) {
        return generateCaptcha(this.SIZE, width, height, fontSize);
    }
    
    @Override
    public Captcha generateCaptcha(OutputStream os) {
        return generateCaptcha(os, null, SIZE, WIDTH, HEIGHT, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha) {
        return generateCaptcha(os, null, SIZE, WIDTH, HEIGHT, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int width, int height) {
        return generateCaptcha(os, null, SIZE, width, height, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int size, int width, int height) {

        return generateCaptcha(os, null, size, width, height, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int width, int height, float fontSize) {
        return generateCaptcha(os, null, SIZE, width, height, fontSize);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int size, int width, int height, float fontSize) {

        Captcha captcha = generateCaptcha(size, width, height, fontSize);
        captcha.setCaptchaImage(null);
        try {
            ImageIO.write(captcha.getCaptchaImage(), "png", os);
        } catch (IOException e) {
            logger.warn("验证码写入输出流发生异常:", e);
            return null;
        }
        return captcha;
    }

    @Override
    public Captcha generateCaptcha(int size, int width, int height, float fontSize) {
        Graphics2D g = null;
        String value = "";
        BufferedImage bimage = null;

        try {

            value = RandomUtil.getRandomString(size);

            bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            g = bimage.createGraphics();
            // 设置随机背景色和字符颜色

            Colors colors = ColorUtil.getRandomColor();
            Color bgColor = colors.getBgColor();
            Color charColor = colors.getCharColor();
            // 填充深色背景
            g.setColor(bgColor);
            g.fillRect(0, 0, width, height);

            // 设置字体
            Font charFont = FontUtil.charFont;
            Font charFont2 = FontUtil.charFont2;
            int y = height;
            int xp = 3;

            // 设置文字颜色
            g.setColor(charColor);
            // 每个文字都变形
            for (int c = 0; c < value.length(); c++) {
                // 获取文字变形设置
                AffineTransform fontAT = FontUtil.getAffineTransform(25);
                charFont = charFont.deriveFont(RandomUtil.rand.nextInt(5), fontSize + RandomUtil.rand.nextInt(6)).deriveFont(
                        fontAT);
                charFont2 = charFont2.deriveFont(RandomUtil.rand.nextInt(5), fontSize + RandomUtil.rand.nextInt(6)).deriveFont(
                        fontAT);

                if (RandomUtil.rand.nextBoolean()) {
                    g.setFont(charFont2);
                } else {
                    g.setFont(charFont);
                }

                String ch = String.valueOf(value.charAt(c));
                int ht = RandomUtil.rand.nextInt(3);

                // 打印字符并移动位置
                int xp_ = xp;
                switch (c) {
                case 0:
                    xp_ = xp;
                    break;
                case 1:
                    xp_ = xp - 2;
                    break;
                case 2:
                    xp_ = xp - 2;
                    break;
                case 3:
                    xp_ = xp - 4;
                    break;
                }
                g.drawString(ch, xp_, y - 9 + (RandomUtil.rand.nextBoolean() ? -ht : ht));
                // 移动指针给下一个字符.
                xp += g.getFontMetrics().stringWidth(ch);

            }

            LineUtil.draw2Line(g, true, 2, height, bgColor, charColor);
        } catch (Exception e) {
            logger.warn("验证码生成失败:", e);
        } finally {
            if (g != null) {
                g.dispose();
            }
        }

        Captcha captcha = new Captcha();
        captcha.setCaptchaImage(bimage);
        captcha.setCaptcha(value);
        return captcha;
    }

}
