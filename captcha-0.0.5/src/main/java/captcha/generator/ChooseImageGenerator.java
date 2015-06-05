package captcha.generator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import captcha.Captcha;
import captcha.ICaptcha;
import captcha.util.ColorUtil;
import captcha.util.Colors;
import captcha.util.FontUtil;
import captcha.util.RandomUtil;

public class ChooseImageGenerator implements ICaptcha {

    private final static Logger logger = LoggerFactory.getLogger(ChooseImageGenerator.class);

    // 默认验证码位数
    private final int SIZE = 4;
    // 默认验证码宽度
    private final int WIDTH = 290;
    // 默认验证码高度
    private final int HEIGHT = 200;
    // 默认验证码图片总数
    private final static int COUNT = 8;
    // 默认验证码有效图片数
    private final static int VALID = 2;
    // 默认字体大小
    private final float FONTSIZE = 15;

    public static String[] imageDirPaths = null;

    private static String classPath = "";

    private static Map<String, String[]> images = new HashMap<String, String[]>();

    private static ChooseImageGenerator chooseImageGenerator;

    private ChooseImageGenerator() {

    }

    public static ChooseImageGenerator getInstance() {
        if (chooseImageGenerator == null) {
            chooseImageGenerator = new ChooseImageGenerator();
        }
        return chooseImageGenerator;
    }

    static {

        new FontUtil();
        File imageDir = new File(FontUtil.tmpDir + "/image/chooseImageGenerator/");
        imageDirPaths = imageDir.list();

        for (int i = 0; i < imageDirPaths.length; i++) {
            images.put(imageDirPaths[i], new File(FontUtil.tmpDir + "/image/chooseImageGenerator/" + imageDirPaths[i]).list());
        }
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
    public Captcha generateCaptcha(int size, int width, int height, float fontSize) {
        // 获取本次有效的图片类型
        int validDir = 0;
        validDir = RandomUtil.rand.nextInt(imageDirPaths.length);
        String validDir_ = imageDirPaths[validDir];
        // 从有效的图片类型中获取具体的随机图片和获取用来混淆的无效随机图片
        String[] imagePaths = getRandImagePath(validDir);
        String[] imagePaths2 = new String[COUNT];
        int[] validImageIndex = new int[VALID];
        // 把有效的图片进行随机排列
        for (int i = 0; i < VALID; i++) {
            validImageIndex[i] = RandomUtil.rand.nextInt(COUNT);
            for (int j = i; j > 0; j--) {
                if (j > 0) {
                    while (validImageIndex[i] == validImageIndex[j - 1]) {
                        validImageIndex[i] = RandomUtil.rand.nextInt(COUNT);
                    }
                }
            }
        }
        // 把有效的图片按照随机到的顺序放到数组中
        for (int i = 0; i < VALID; i++) {
            imagePaths2[validImageIndex[i]] = imagePaths[i];
        }
        // 其他用来混淆的无效的图片放到数组中
        int index = VALID;
        for (int i = 0; i < COUNT; i++) {

            if (imagePaths2[i] == null) {
                imagePaths2[i] = imagePaths[index];
                index++;
            }
        }

        // 设置用来输出的图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        Font charFont = FontUtil.charFont3.deriveFont(0, fontSize);
        Colors colors = ColorUtil.getRandomColor();
        Color bgColor = colors.getBgColor();
        Color charColor = colors.getCharColor();
        // 设置背景
        g.setColor(bgColor);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // 合并所有的图片
        try {
            mergeImage(g, imagePaths2);
        } catch (IOException e1) {
            logger.warn("验证码图片组合失败:", e1);
        }
        // 写文字
        g.setColor(charColor);
        g.setFont(charFont);
        g.drawString("请点击下图中", 5, 21);

        g.setColor(Color.RED);
        g.drawString("所有的 ", 96, 21);

        g.setColor(charColor);
        g.drawString(validDir_, 141, 21);
        // 画中间的间隔线
        g.setColor(Color.GRAY);
        g.drawLine(3, 31, 287, 31);
        // 生成验证码
        Captcha captcha = new Captcha();
        captcha.setCaptchaImage(null);
        // 把有效图片的顺序进行排序，得到验证码
        Arrays.sort(validImageIndex);
        String c = "";
        for (int i = 0; i < VALID; i++) {
            imagePaths2[validImageIndex[i]] = imagePaths[i];
            c += validImageIndex[i] + "";

        }
        captcha.setCaptcha(c);
        captcha.setCaptchaImage(image);
        return captcha;
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int size, int width, int height, float fontSize) {

        Captcha captcha = generateCaptcha(0, width, height, fontSize);
        try {
            ImageIO.write(captcha.getCaptchaImage(), "png", os);
        } catch (IOException e) {
            logger.warn("验证码写入输出流发生异常:", e);
            return null;
        }

        captcha.setCaptchaImage(null);
        return captcha;
    }

    public static void main(String[] args) {

    }

    /**
     * 
     * 获取随机的有效的图片路径. <br>
     * 
     * @param validDir 本次随机到的目录
     * @return String[]
     * @author sky
     * @date 2015年3月24日 上午10:25:08
     */
    public static String[] getRandImagePath(int validDir) {

        String[] imagePath = new String[COUNT];
        int[] validImageIndex = null;

        classPath = FontUtil.tmpDir;
        if (imageDirPaths != null) {
            // 取出有效的随机图片路径
            // 取出有效的图片目录
            // 取出目录下随机的valid个图片路径
            String[] imagePaths = new File(classPath + "image/chooseImageGenerator/" + imageDirPaths[validDir]).list();
            validImageIndex = new int[VALID];
            for (int i = 0; i < VALID; i++) {
                validImageIndex[i] = RandomUtil.rand.nextInt(imagePaths.length);
                for (int j = i; j > 0; j--) {
                    if (j > 0) {
                        while (validImageIndex[i] == validImageIndex[j - 1]) {
                            validImageIndex[i] = RandomUtil.rand.nextInt(imagePaths.length);
                        }
                    }
                }
                imagePath[i] = classPath + "image/chooseImageGenerator/" + imageDirPaths[validDir] + "/" + imagePaths[validImageIndex[i]];
            }

            // 取出干扰的随机图片路径
            // 取出剩下路径下的所有图片的路径
            String[] otherImages = getUnValidPath(imageDirPaths[validDir]);
            // 随机取出count-valid个图片路径
            validImageIndex = new int[COUNT - VALID];
            for (int i = 0; i < COUNT - VALID; i++) {
                validImageIndex[i] = RandomUtil.rand.nextInt(otherImages.length);
                for (int j = i; j > 0; j--) {
                    if (j > 0) {
                        while (validImageIndex[i] == validImageIndex[j - 1]) {
                            validImageIndex[i] = RandomUtil.rand.nextInt(otherImages.length);
                        }
                    }
                }
                imagePath[i + VALID] = otherImages[validImageIndex[i]];
            }
            // 合并取出的所有图片路径
        }
        return imagePath;
    }

    /**
     * 
     * 获取非有效的所有图片的路径. <br>
     * 
     * @param validDir 本次的有效图片的路径
     * @return String[]
     * @author sky
     * @date 2015年3月24日 上午10:23:31
     */
    public static String[] getUnValidPath(String validDir) {

        List<String> images_ = new ArrayList<String>();

        String[] im = null;
        for (String key : images.keySet()) {
            if (!key.equals(validDir)) {
                im = images.get(key);
                for (int j = 0; j < im.length; j++) {
                    images_.add(classPath + "image/chooseImageGenerator/" + key + "/" + im[j]);
                }
            }
        }

        String[] images__ = new String[images_.size()];
        return images_.toArray(images__);
    }

    /**
     * 
     * 合并所有图片.
     * 
     * @param g
     * @param imagePaths
     * @throws IOException
     * @return void
     * @author sky
     * @date 2015年3月24日 上午10:24:26
     */
    public static void mergeImage(Graphics2D g, String[] imagePaths) throws IOException {

        for (int i = 0; i < imagePaths.length; i++) {

            BufferedImage bImage = ImageIO.read(new File(imagePaths[i]));
            if (i > imagePaths.length / 2 - 1) {
                g.drawImage(bImage, (70 + 2) * (i - imagePaths.length / 2) + 2, 122, 70, 80, null);
            } else {
                g.drawImage(bImage, (70 + 2) * i + 2, 40, 70, 80, null);
            }
        }
    }

}
