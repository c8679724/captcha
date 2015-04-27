package captcha;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import captcha.Captcha;
import captcha.CaptchaFactory;
import captcha.CaptchaFactory.CaptchaType;
import captcha.ICaptcha;
import captcha.util.FontUtil;

public class TestCaptchaFactory {

    private final static Logger logger = LoggerFactory.getLogger(TestCaptchaFactory.class);

    public static void main(String[] args) {

        ICaptcha icaptcha = CaptchaFactory.createCaptchaObject(CaptchaType.CHINESEIDIOM);

        File dir = new File(FontUtil.classPath + "../verify/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (int i = 0; i < 30; i++) {
            Captcha captcha = icaptcha.generateCaptcha();
            System.out.println("验证码:" + captcha.getCaptcha());
            try {
                ImageIO.write(captcha.getCaptchaImage(), "png", new FileOutputStream(dir.getPath() + "/" + i + ".png"));
            } catch (FileNotFoundException e) {
                logger.warn("", e);
            } catch (IOException e) {
                logger.warn("", e);
            }
        }
    }
}
