package captcha;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import captcha.CaptchaFactory.CaptchaType;
import captcha.util.FontUtil;

public class TestCaptchaFactory2 {

    private final static Logger logger = LoggerFactory.getLogger(TestCaptchaFactory2.class);

    public static void main(String[] args) {

        ICaptcha icaptcha = CaptchaFactory.createCaptchaObject(CaptchaType.CHINESEIDIOM);

        File dir = new File(FontUtil.classPath + "../verify/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        OutputStream os = null;
        for (int i = 0; i < 30; i++) {
            
            try {
                os = new FileOutputStream(dir.getPath() + "/" + i + ".png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
            Captcha captcha = icaptcha.generateCaptcha(os);
            System.out.println("验证码:" + captcha.getCaptcha());
        }
    }
}
