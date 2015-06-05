package captcha;

import java.awt.image.BufferedImage;

public class Captcha {

    private String captcha;

    private BufferedImage captchaImage;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public BufferedImage getCaptchaImage() {
        return captchaImage;
    }

    public void setCaptchaImage(BufferedImage captchaImage) {
        this.captchaImage = captchaImage;
    }

}
