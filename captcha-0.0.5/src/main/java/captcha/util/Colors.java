package captcha.util;

import java.awt.Color;

public class Colors {

    public Colors(Color bgColor, Color charColor) {

        this.bgColor = bgColor;
        this.charColor = charColor;
    }

    /** 背景色 **/
    private Color bgColor;

    /** 字符颜色 **/
    private Color charColor;

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public Color getCharColor() {
        return charColor;
    }

    public void setCharColor(Color charColor) {
        this.charColor = charColor;
    }

}
