package captcha.util;

import java.util.Random;
import captcha.util.wordStock.ChineseWordStock;

public class RandomUtil {

    public static final Random rand = new Random(System.currentTimeMillis());

    private static final String randString = "123456789ABCDEFGHJKMNPQRSUVWXY";// 随机产生的字符串

    public enum RandomStringType {

        CHINESE,

        CHINESEIDIOM,

        LETTERANDNUMBER
    }

    /**
     * 
     * 获取指定长度的随机字符串. <br>
     * 获取指定长度的随机字符串，该字符串已删除'OZLTI'这几个字符，避免与数字形似。
     * 
     * @param size 字符个数
     * @return String 随机字符串
     * @author 成天华
     * @date 2015年1月16日 下午3:33:51
     */
    public static String getRandomString(int size) {
        StringBuffer value_ = new StringBuffer();
        for (int i = 0; i < size; i++) {
            value_.append(randString.charAt((rand.nextInt(randString.length() - 1))));
        }
        return value_.toString();
    }

    /**
     * 
     * 获取指定长度的随机字符串. <br>
     * 获取指定长度的随机字符串，该字符串已删除'OZLTI'这几个字符，避免与数字形似。
     * 
     * @param type 字库类型 CHINESE：《小学生第一册》中的随机单字;CHINESEIDIOM:中文褒义成语
     *            LETTERANDNUMBER:普通数字英文组合
     * @param size 字符个数
     * 
     * @return String 随机字符串
     * @author 成天华
     * @date 2015年2月27日 下午3:43:11
     */
    public static String getRandomString(RandomStringType type, int size) {

        return getValue(type, size);
    }

    public static String getValue(RandomStringType type, int size) {
        StringBuffer value_ = new StringBuffer();

        switch (type) {
        case CHINESE:
            for (int i = 0; i < size; i++) {
                value_.append(ChineseWordStock.wordStock1.charAt((rand.nextInt(ChineseWordStock.wordStock1.length() - 1))));
            }
            break;
        case LETTERANDNUMBER:
            for (int i = 0; i < size; i++) {
                value_.append(randString.charAt((rand.nextInt(randString.length() - 1))));
            }
            break;
        case CHINESEIDIOM:
            value_.append(ChineseWordStock.wordStock2.get(rand.nextInt(ChineseWordStock.wordStock2.size())));

        }
        return value_.toString();
    }

    /**
     * 
     * 随机获取一定范围内不重复的索引. <br>
     * 
     * @param count 总个数
     * @param size 索引个数
     * @return int[]
     * @author 成天华
     * @date 2015年1月16日 下午5:08:01
     */
    public static int[] getRandomIndex(int count, int size) {

        int[] randomIndex = new int[size];

        for (int i = 0; i < randomIndex.length; i++) {
            randomIndex[i] = rand.nextInt(count);
            if (i > 0) {
                for (int j = 1; j <= i; j++) {
                    while (randomIndex[j] == randomIndex[j - 1]) {
                        randomIndex[j] = rand.nextInt(count);
                    }
                }
            }
        }
        return randomIndex;
    }

    // 随机生成字符,根据截取的位数决定产生的数字
    // value = UUID.randomUUID().toString().replace("-",
    // "").substring(0, 4);
}
