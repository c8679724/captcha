package captcha.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import captcha.util.io.ZipUtils;

public class FontUtil {

    private static final Logger logger = LoggerFactory.getLogger(FontUtil.class);

    /**
     * 缓存目录
     */
    public static String tmpDir = System.getProperty("java.io.tmpdir") + "/captchaResources/";

    public static final String classPath = FontUtil.class.getClassLoader().getResource("").getPath();
    public static File fontFile = new File(tmpDir + "/font/方正水柱简体.ttf");
    public static File fontFile2 = new File(tmpDir + "/font/王汉宗魏碑体.ttf");
    public static File fontFile3 = new File(tmpDir + "/font/simsun.ttc");
    public static Font charFont = new Font("arial", 5, 30);
    public static Font charFont2 = new Font("arial", 5, 30);
    public static Font charFont3 = new Font("新宋体", 5, 30);

    static {

        String pathString = FontUtil.class.getResource("/font/simsun.ttc").getPath();
        if (pathString.contains(".jar!")) {
            pathString = pathString.split(".jar!")[0].replace("file:", "") + ".jar";

            File captchaResourcesDir = new File(tmpDir);
            try {
                FileUtils.deleteDirectory(captchaResourcesDir);
            } catch (IOException e1) {
                logger.warn("", e1);
            }
            captchaResourcesDir.mkdirs();
            try {
                ZipUtils.decompress(new File(pathString), captchaResourcesDir);
            } catch (Exception e) {
                logger.warn("", e);
            }

            File file = new File(tmpDir + "/captcha");
            try {
                FileUtils.deleteDirectory(file);
            } catch (IOException e1) {
                logger.warn("", e1);
            }
        } else {
            tmpDir = classPath;
            fontFile = new File(tmpDir + "/font/方正水柱简体.ttf");
            fontFile2 = new File(tmpDir + "/font/王汉宗魏碑体.ttf");
            fontFile3 = new File(tmpDir + "/font/simsun.ttc");
        }

        if (fontFile.exists() || fontFile2.exists() || fontFile3.exists()) {
            try {
                charFont = Font.createFont(0, fontFile);
            } catch (FontFormatException e) {
                logger.warn("", e);
            } catch (IOException e) {
                logger.warn("", e);
            }

            try {
                charFont2 = Font.createFont(0, fontFile2);
            } catch (FontFormatException e) {
                logger.warn("", e);
            } catch (IOException e) {
                logger.warn("", e);
            }

            try {
                charFont3 = Font.createFont(0, fontFile3);
            } catch (FontFormatException e) {
                logger.warn("", e);
            } catch (IOException e) {
                logger.warn("", e);
            }
        }
    }

    /**
     * 
     * 获取文字变形. <br>
     * 
     * @return AffineTransform
     * @author 成天华
     * @date 2015年1月16日 下午4:16:51
     */
    public static AffineTransform getAffineTransform(int max) {
        AffineTransform fontAT = new AffineTransform();
        // 产生弧度
        int rotate = RandomUtil.rand.nextInt(max);
        fontAT.rotate(RandomUtil.rand.nextBoolean() ? Math.toRadians(rotate) : -Math.toRadians(rotate / 2));
        return fontAT;
    }

    /**
     * 
     * 获取文字变形. <br>
     * 
     * @return AffineTransform
     * @author 成天华
     * @date 2015年1月16日 下午4:16:51
     */
    public static AffineTransform getAffineTransform(int min, int max) {
        AffineTransform fontAT = new AffineTransform();
        // 产生弧度
        int rotate = RandomUtil.rand.nextInt(max) % (max - min + 1) + min;
        fontAT.rotate(RandomUtil.rand.nextBoolean() ? Math.toRadians(rotate) : -Math.toRadians(rotate));
        return fontAT;
    }
}
