package captcha;

import org.junit.Test;
import captcha.CaptchaFactory.CaptchaType;
import captcha.util.VerifyUtil;

public class TestVerify {

    @Test
    public void test() {

        String code1 = "62,150,107,195;126,91,171,136;";
        String code2 = "149,115;149,103;135,116;134,105;66,171;86,168;86,156;66,156;";
        boolean verifyResult = false;
        try {
            verifyResult = VerifyUtil.verify(CaptchaType.CHOOSEIMAGENOTCH, code1, code2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(verifyResult);
    }
}
