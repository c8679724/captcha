package captcha.util;

import java.awt.Color;

public class ColorUtil {

    /**
     * 
     * 获取统一搭配的背景色和字符颜色. <br>
     * 背景色和文字颜色的随机并统一搭配
     * 
     * @return Colors
     * @author 成天华
     * @date 2015年1月16日 下午3:46:42
     */
    public static Colors getRandomColor() {

        int color = RandomUtil.rand.nextInt(3);
        /** 红绿蓝三色的色值 **/
        int bgR = 0, bgG = 0, bgB = 0;
        int charR = 0, charG = 0, charB = 0;
        int maxR = 0, maxG = 0, maxB = 0;
        int minR = 0, minG = 0, minB = 0;
        if (color == 0) {// 红
            maxR = 255;
            maxG = 245;
            maxB = 245;

            minR = 250;
            minG = 235;
            minB = 235;
            bgR = RandomUtil.rand.nextInt(maxR) % (maxR - minR + 1) + minR;
            bgG = RandomUtil.rand.nextInt(maxG) % (maxG - minG + 1) + minG;
            bgB = RandomUtil.rand.nextInt(maxB) % (maxB - minB + 1) + minB;

            maxR = 210;
            maxG = 70;
            maxB = 20;

            minR = 200;
            minG = 60;
            minB = 5;
            charR = RandomUtil.rand.nextInt(maxR) % (maxR - minR + 1) + minR;
            charG = RandomUtil.rand.nextInt(maxG) % (maxG - minG + 1) + minG;
            charB = RandomUtil.rand.nextInt(maxB) % (maxB - minB + 1) + minB;
        } else if (color == 1) {// 绿
            maxR = 245;
            maxG = 255;
            maxB = 245;

            minR = 235;
            minG = 250;
            minB = 235;
            bgR = RandomUtil.rand.nextInt(maxR) % (maxR - minR + 1) + minR;
            bgG = RandomUtil.rand.nextInt(maxG) % (maxG - minG + 1) + minG;
            bgB = RandomUtil.rand.nextInt(maxB) % (maxB - minB + 1) + minB;
            maxR = 70;
            maxG = 210;
            maxB = 20;

            minR = 60;
            minG = 200;
            minB = 5;
            charR = RandomUtil.rand.nextInt(maxR) % (maxR - minR + 1) + minR;
            charG = RandomUtil.rand.nextInt(maxG) % (maxG - minG + 1) + minG;
            charB = RandomUtil.rand.nextInt(maxB) % (maxB - minB + 1) + minB;
        } else {// 蓝
            maxR = 245;
            maxG = 245;
            maxB = 255;

            minR = 235;
            minG = 235;
            minB = 250;
            bgR = RandomUtil.rand.nextInt(maxR) % (maxR - minR + 1) + minR;
            bgG = RandomUtil.rand.nextInt(maxG) % (maxG - minG + 1) + minG;
            bgB = RandomUtil.rand.nextInt(maxB) % (maxB - minB + 1) + minB;

            maxR = 20;
            maxG = 70;
            maxB = 210;

            minR = 5;
            minG = 60;
            minB = 200;
            charR = RandomUtil.rand.nextInt(maxR) % (maxR - minR + 1) + minR;
            charG = RandomUtil.rand.nextInt(maxG) % (maxG - minG + 1) + minG;
            charB = RandomUtil.rand.nextInt(maxB) % (maxB - minB + 1) + minB;
        }

        Color bgColor = new Color(bgR, bgG, bgB);
        Color charColor = new Color(charR, charG, charB);

        return new Colors(bgColor, charColor);
    }

}
