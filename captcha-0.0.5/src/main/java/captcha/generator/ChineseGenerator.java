package captcha.generator;

import captcha.util.RandomUtil;
import captcha.util.RandomUtil.RandomStringType;

public class ChineseGenerator extends AbstractChineseGenerator {
    
    private static ChineseGenerator chineseGenerator;
    
    private ChineseGenerator() {

    }

    public static ChineseGenerator getInstance() {
        if (chineseGenerator == null) {
            chineseGenerator = new ChineseGenerator();
        }
        return chineseGenerator;
    }

    @Override
    protected String getRandomString(int size) {
        return RandomUtil.getRandomString(RandomStringType.CHINESE, size);
    }

}
