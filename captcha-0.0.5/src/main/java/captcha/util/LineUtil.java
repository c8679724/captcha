package captcha.util;

import java.awt.Color;
import java.awt.Graphics2D;

public class LineUtil {

    /**
     * 
     * 在验证码上画两条随机的干扰线. <br>
     * 两条干扰线中一条用背景色，一条用字符字体颜色。
     * 
     * @param g Graphics2D
     * @param isThick 是否需要加粗
     * @param lineSum 干扰线数量
     * @param height 验证码高度
     * @param bgColor 背景色
     * @param charColor 字符颜色
     * @return void
     * @author 成天华
     * @date 2015年1月16日 下午3:21:27
     */
    public static void draw2Line(Graphics2D g, boolean isThick, int lineSum, int height, Color bgColor, Color charColor) {

        // 画干扰线
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        boolean lineColor = RandomUtil.rand.nextBoolean();
        for (int j = 0; j < lineSum; j++) {
            x1 = 2;// 从第2个像素开始画干扰线
            y1 = height / 2 - 2 + j * 4;
            x2 = 2;
            y2 = height / 2 - 2 + j * 4;
            if (lineColor) {
                g.setColor(charColor);
                lineColor = false;
            } else {
                g.setColor(bgColor);
                lineColor = true;
            }
            for (int i = 0; i < 30; i++) {
                x1 = x2;
                x2 += 3;
                y1 = y2;
                if (RandomUtil.rand.nextBoolean()) {
                    y2 = y2 + RandomUtil.rand.nextInt(3);
                } else {
                    y2 = y2 - RandomUtil.rand.nextInt(3);
                }

                g.drawLine(x1, y1, x2, y2);

                if (isThick) {// 加粗干扰线
                    g.drawLine(x1, y1 - 1, x2, y2 - 1);
                }
            }
        }
    }
}
