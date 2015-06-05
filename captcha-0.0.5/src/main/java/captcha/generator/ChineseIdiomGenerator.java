package captcha.generator;

import captcha.util.RandomUtil;
import captcha.util.RandomUtil.RandomStringType;

public class ChineseIdiomGenerator extends AbstractChineseGenerator {
    
    private static ChineseIdiomGenerator chineseIdiomGenerator;
    
    private ChineseIdiomGenerator() {

    }

    public static ChineseIdiomGenerator getInstance() {
        if (chineseIdiomGenerator == null) {
            chineseIdiomGenerator = new ChineseIdiomGenerator();
        }
        return chineseIdiomGenerator;
    }

    @Override
    protected String getRandomString(int size) {
        return RandomUtil.getRandomString(RandomStringType.CHINESEIDIOM, size);
    }

}
