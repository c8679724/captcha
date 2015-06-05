package captcha.generator;

import java.io.OutputStream;
import captcha.Captcha;
import captcha.CaptchaFactory;
import captcha.CaptchaFactory.CaptchaType;
import captcha.ICaptcha;
import captcha.gifAPI.AnimatedGifEncoder;
import captcha.util.RandomUtil;

public class GifGenerator implements ICaptcha {

    private static GifGenerator gifGenerator;

    // 默认验证码位数
    private final static int SIZE = 4;
    // 默认验证码宽度
    private final static int WIDTH = 80;
    // 默认验证码高度
    private final static int HEIGHT = 28;
    // 默认字体大小
    private final static float FONTSIZE = 20;

    private GifGenerator() {

    }

    public static GifGenerator getInstance() {
        if (gifGenerator == null) {
            gifGenerator = new GifGenerator();
        }
        return gifGenerator;
    }

    @Override
    public Captcha generateCaptcha() {
        return null;
    }

    @Override
    public Captcha generateCaptcha(int width, int height) {
        return null;
    }

    @Override
    public Captcha generateCaptcha(int size, int width, int height) {
        return null;
    }

    @Override
    public Captcha generateCaptcha(int width, int height, float fontSize) {
        return null;
    }

    @Override
    public Captcha generateCaptcha(int size, int width, int height, float fontSize) {
        return null;
    }

    @Override
    public Captcha generateCaptcha(OutputStream os) {
        return generateCaptcha(os, CaptchaFactory.createCaptchaObject(CaptchaType.SIMPLELETTERANDNUMBER), SIZE, WIDTH, HEIGHT,
                FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha) {
        return generateCaptcha(os, iCaptcha, SIZE, WIDTH, HEIGHT, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int width, int height) {
        return generateCaptcha(os, iCaptcha, SIZE, width, height, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int size, int width, int height) {

        return generateCaptcha(os, iCaptcha, size, width, height, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int width, int height, float fontSize) {
        return generateCaptcha(os, iCaptcha, SIZE, width, height, fontSize);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int size, int width, int height, float fontSize) {

        Captcha Captcha = new Captcha();

        AnimatedGifEncoder gif = new AnimatedGifEncoder();
        int r = RandomUtil.rand.nextInt(10);
        gif.setRepeat(0);
        gif.start(os);
        for (int i = 0; i < 10; i++) {

            Captcha captcha = iCaptcha.generateCaptcha(size, width, height, fontSize);

            if (i == r) {
                gif.setDelay(5000);
                Captcha.setCaptcha(captcha.getCaptcha());
            } else {
                gif.setDelay(5);
            }
            gif.addFrame(captcha.getCaptchaImage());

        }
        gif.finish();

        return Captcha;
    }

}
