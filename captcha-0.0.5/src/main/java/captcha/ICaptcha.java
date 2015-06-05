package captcha;

import java.io.OutputStream;

/**
 * 创建验证码接口。
 * 
 * @author Gerry
 * 
 */
public interface ICaptcha {
    /**
     * 生成常规验证码.
     * 
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha();

    /**
     * 生成常规验证码.
     * 
     * @param width 验证码图片宽度。
     * @param height 验证码图片高度。
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha(int width, int height);

    /**
     * 生成常规验证码.
     * 
     * @param size 验证码位数。
     * @param width 验证码图片宽度。
     * @param height 验证码图片高度。
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha(int size, int width, int height);

    /**
     * 生成常规验证码.
     * 
     * @param width 验证码图片宽度。
     * @param height 验证码图片高度。
     * @param fontSize 字体大小
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha(int width, int height, float fontSize);

    /**
     * 生成常规验证码.
     * 
     * @param size 验证码位数。
     * @param width 验证码图片宽度。
     * @param height 验证码图片高度。
     * @param fontSize 字体大小
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha(int size, int width, int height, float fontSize);

    /**
     * 生成常规验证码和GIF动画验证码.
     * 
     * @param os 输出流(os输出流在方法内部处理完成后默认会关闭)
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha(OutputStream os);

    /**
     * 生成常规验证码和GIF动画验证码.
     * 
     * @param os 输出流(os输出流在方法内部处理完成后默认会关闭)
     * @param iCaptcha gif动画里面的每一帧常规验证码的种类，非gif填写这个参数无意义
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha);

    /**
     * 生成常规验证码和GIF动画验证码.
     * 
     * @param os 输出流(os输出流在方法内部处理完成后默认会关闭)
     * @param iCaptcha gif动画里面的每一帧常规验证码的种类，非gif填写这个参数无意义
     * @param width 验证码图片宽度。
     * @param height 验证码图片高度。
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int width, int height);

    /**
     * 生成常规验证码和GIF动画验证码.
     * 
     * @param os 输出流(os输出流在方法内部处理完成后默认会关闭)
     * @param iCaptcha gif动画里面的每一帧常规验证码的种类，非gif填写这个参数无意义
     * @param size 验证码位数。
     * @param width 验证码图片宽度。
     * @param height 验证码图片高度。
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int size, int width, int height);

    /**
     * 生成常规验证码和GIF动画验证码.
     * 
     * @param os 输出流(os输出流在方法内部处理完成后默认会关闭)
     * @param iCaptcha gif动画里面的每一帧常规验证码的种类，非gif填写这个参数无意义
     * @param width 验证码图片宽度。
     * @param height 验证码图片高度。
     * @param fontSize 字体大小
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int width, int height, float fontSize);

    /**
     * 生成常规验证码和GIF动画验证码.
     * 
     * @param os 输出流(os输出流在方法内部处理完成后默认会关闭)
     * @param iCaptcha gif动画里面的每一帧常规验证码的种类，非gif填写这个参数无意义
     * @param size 验证码位数。
     * @param width 验证码图片宽度。
     * @param height 验证码图片高度。
     * @param fontSize 字体大小
     * @return {@link Captcha} 验证码实体
     */
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int size, int width, int height, float fontSize);

}
