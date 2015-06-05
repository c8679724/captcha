package captcha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import captcha.CaptchaFactory.CaptchaType;
import captcha.util.FontUtil;

public class TestChooseImageNotchCaptchaFactory2 {

    private final static Logger logger = LoggerFactory.getLogger(TestChooseImageNotchCaptchaFactory2.class);

    public static void main(String[] args) {

        ICaptcha icaptcha = CaptchaFactory.createCaptchaObject(CaptchaType.CHOOSEIMAGENOTCH);

        File dir = new File(FontUtil.classPath + "../verify/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (int i = 0; i < 30; i++) {
            Captcha captcha = icaptcha.generateCaptcha();
            System.out.println("验证码:" + captcha.getCaptcha());
            try {
                ImageIO.write(captcha.getCaptchaImage(), "gif", new FileOutputStream(dir.getPath() + "/" + i + ".gif"));
            } catch (FileNotFoundException e) {
                logger.warn("", e);
            } catch (IOException e) {
                logger.warn("", e);
            }
        }
    }
}
