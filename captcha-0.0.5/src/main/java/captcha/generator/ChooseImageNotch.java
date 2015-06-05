package captcha.generator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
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
import captcha.util.RandomUtil;

public class ChooseImageNotch implements ICaptcha {

    private final static Logger logger = LoggerFactory.getLogger(ChooseImageNotch.class);

    // 默认验证码位数
    private final int SIZE = 4;
    // 默认验证码宽度
    private final int WIDTH = 290;
    // 默认验证码高度
    private final int HEIGHT = 200;
    // 默认字体大小
    private final float FONTSIZE = 15;
    // 默认高斯模糊块数量
    private static final int zoneNumber = 2;
    // 默认高斯模糊块宽度
    private static final int zoneWidth = 45;
    // 默认高斯模糊块高度
    private static final int zoneHeigth = 45;

    public static String[] imageDirPaths = null;

    private static String imageTmpDir = "";

    private static ChooseImageNotch chooseImageNotch;

    private ChooseImageNotch() {

    }

    public static ChooseImageNotch getInstance() {
        if (chooseImageNotch == null) {
            chooseImageNotch = new ChooseImageNotch();
        }
        return chooseImageNotch;
    }

    static {

        new FontUtil();
        imageTmpDir = FontUtil.tmpDir + "/image/chooseImageNotch/";
        File imageDir = new File(imageTmpDir);
        if (imageDir.isDirectory()) {
            imageDirPaths = imageDir.list();
        }

        for (int i = 0; i < imageDirPaths.length; i++) {
            imageDirPaths[i] = imageTmpDir + imageDirPaths[i];
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

        // 获取一个随机的背景图片
        int imageIndex = RandomUtil.rand.nextInt(imageDirPaths.length);

        // 画背景图片
        File imageFile = new File(imageDirPaths[imageIndex]);
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 把背景图片画到画布上
        g.drawImage(bImage, 2, 40, WIDTH - 4, HEIGHT - 42, null);

        // 在背景图片上选择zoneNumber个随机的位置进行高斯模糊
        int height1 = HEIGHT - 42;
        int width1 = WIDTH - 4;
        int[][] matrix = new int[3][3];
        int[] values = new int[9];
        int[][] randomZone = getRandomZone(width1, height1, zoneNumber);
        for (int h = 0; h < randomZone.length; h++) {
            for (int i = randomZone[h][0]; i < randomZone[h][0] + zoneWidth; i++) {
                for (int j = randomZone[h][1]; j < randomZone[h][1] + zoneHeigth; j++) {
                    readPixel(image, i, j, values);
                    fillMatrix(matrix, values);
                    image.setRGB(i, j, avgMatrix(matrix));
                }
            }
        }

        // 写文字
        g.setColor(charColor);
        g.setFont(charFont);
        g.drawString("点击图片中两处模糊的位置", 5, 21);

        // 画中间的间隔线
        g.setColor(Color.GRAY);
        g.drawLine(3, 31, 287, 31);

        // 产生验证码
        Captcha captcha = new Captcha();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < randomZone.length; i++) {
            stringBuffer.append(randomZone[i][0] - 2).append(",").append(randomZone[i][1] - 40).append(",")
                    .append(randomZone[i][0] + zoneWidth - 2).append(",").append(randomZone[i][1] + zoneHeigth - 40).append(";");
        }

        captcha.setCaptcha(stringBuffer.toString());
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

    /**
     * 
     * 生成随机模糊块. <br>
     * 
     * @param width
     * @param height
     * @param zoneNumber
     * @return int[][]
     * @author sky
     * @date 2015年5月14日 下午5:46:03
     */
    private int[][] getRandomZone(int width, int height, int zoneNumber) {

        int[][] result = new int[zoneNumber][2];
        for (int i = 0; i < zoneNumber; i++) {
            result[i][0] = RandomUtil.rand.nextInt(width - zoneWidth) + 2;
            result[i][1] = RandomUtil.rand.nextInt(height - zoneHeigth) + 40;
            boolean s = true;

            // 处理随机块重叠交叉的问题
            while (s && i > 0) {
                for (int j = 0; j < i; j++) {
                    logger.trace(Math.abs(result[i][0] - result[j][0]) + "," + Math.abs(result[i][1] - result[j][1]));
                    if ((Math.abs(result[i][0] - result[j][0]) < (zoneWidth + 3))
                            && (Math.abs(result[i][1] - result[j][1]) < (zoneHeigth + 3))) {

                        result[i][0] = RandomUtil.rand.nextInt(width - zoneWidth) + 2;
                        result[i][1] = RandomUtil.rand.nextInt(height - zoneHeigth) + 40;
                        logger.trace("发现交叉了:" + result[i][0] + "," + result[i][1]);
                        // 如果发现交叉，退出for循环，重新检查数据
                        break;
                    }

                    // 如果是最后一个且没有区域没有发生交叉，退出while循环
                    if (j == (i - 1)) {
                        s = false;
                    }
                }

            }
        }
        return result;
    }

    private static void readPixel(BufferedImage img, int x, int y, int[] pixels) {

        int xStart = x - 1;
        int yStart = y - 1;
        int current = 0;

        for (int i = xStart; i < 3 + xStart; i++) {
            for (int j = yStart; j < 3 + yStart; j++) {
                int tx = i;
                if (tx < 0) {
                    tx = -tx;
                } else if (tx >= img.getWidth()) {
                    tx = x;
                }
                int ty = j;
                if (ty < 0) {
                    ty = -ty;
                } else if (ty >= img.getHeight()) {
                    ty = y;
                }
                pixels[current++] = img.getRGB(tx, ty);
            }
        }
    }

    private static void fillMatrix(int[][] matrix, int... values) {

        int filled = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                x[j] = values[filled++];
            }
        }
    }

    private static int avgMatrix(int[][] matrix) {
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                if (j == 1) {
                    continue;
                }
                Color c = new Color(x[j]);
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
            }
        }
        return new Color(r / 8, g / 8, b / 8).getRGB();
    }
}
