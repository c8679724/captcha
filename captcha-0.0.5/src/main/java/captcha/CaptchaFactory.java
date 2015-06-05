package captcha;

import captcha.generator.ChineseGenerator;
import captcha.generator.ChineseIdiomGenerator;
import captcha.generator.ChooseImageGenerator;
import captcha.generator.ChooseImageNotch;
import captcha.generator.GifGenerator;
import captcha.generator.LetterAndNumberGenerator;
import captcha.generator.SelectFourFromSixGenerator;
import captcha.generator.SimpleLetterAndNumberGenerator;

/**
 * 创建验证码生成器对象的工厂。
 * 
 * @author Gerry
 * 
 */
public class CaptchaFactory {

    public enum CaptchaType {

        /** 常规4个字符验证码 **/
        LETTERANDNUMBER,

        /** 6选4验证码 **/
        SELECTFOURFROMSIX,

        /** 简洁版常规4个字符验证码 **/
        SIMPLELETTERANDNUMBER,

        /** 小学生字库4字验证码 **/
        CHINESE,

        /** 简洁版4字成语验证码 **/
        CHINESEIDIOM,

        /** gif动画验证码 **/
        GIF,

        /** 选择图片验证码 **/
        CHOOSEIMAGE,

        /** 选择图片模糊区域验证码 **/
        CHOOSEIMAGENOTCH
    }

    /**
     * 根据枚举类型构建对应的实现了{@link ICaptcha}接口的验证码生成器对象。
     * 
     * @param captchaType
     * @return
     */
    public static ICaptcha createCaptchaObject(CaptchaType captchaType) {

        ICaptcha captchaObject = null;

        switch (captchaType) {
        case LETTERANDNUMBER:
            captchaObject = LetterAndNumberGenerator.getInstance();
            break;
        case SELECTFOURFROMSIX:
            captchaObject = SelectFourFromSixGenerator.getInstance();
            break;
        case SIMPLELETTERANDNUMBER:
            captchaObject = SimpleLetterAndNumberGenerator.getInstance();
            break;
        case CHINESE:
            captchaObject = ChineseGenerator.getInstance();
            break;
        case CHINESEIDIOM:
            captchaObject = ChineseIdiomGenerator.getInstance();
            break;
        case GIF:
            captchaObject = GifGenerator.getInstance();
            break;
        case CHOOSEIMAGE:
            captchaObject = ChooseImageGenerator.getInstance();
            break;
        case CHOOSEIMAGENOTCH:
            captchaObject = ChooseImageNotch.getInstance();
            break;
        default:
            break;
        }

        return captchaObject;
    }
   
}
