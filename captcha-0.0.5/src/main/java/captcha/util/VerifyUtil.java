package captcha.util;

import captcha.CaptchaFactory.CaptchaType;

public class VerifyUtil {

    /**
     * 
     * 校验验证码的正确性. <br>
     * 
     * @param captchaType 验证码类型
     * @param code1 服务端保存的验证码
     * @param code2 客户提交的验证码
     * @return boolean 校验结果
     * @throws 校验失败
     * @author sky
     * @date 2015年5月13日 下午5:53:22
     */
    public static boolean verify(CaptchaType captchaType, String code1, String code2) throws Exception {

        switch (captchaType) {
        case CHOOSEIMAGE:
            if (code1.equalsIgnoreCase(code2)) {
                return true;
            } else {
                return false;
            }
        case CHOOSEIMAGENOTCH:

            if (code1.charAt(code1.length() - 1) == ';') {
                code1 = code1.substring(0, code1.length() - 1);
            }
            if (code2.charAt(code2.length() - 1) == ';') {
                code2 = code2.substring(0, code2.length() - 1);
            }

            double[][] serverCode = null;
            double[][] clientCode = null;
            String[] temp = code1.split(";");
            String[] temp1 = null;
            serverCode = new double[temp.length][4];
            for (int i = 0; i < temp.length; i++) {
                temp1 = null;
                temp1 = temp[i].split(",");
                for (int j = 0; j < temp1.length; j++) {

                    serverCode[i][j] = Integer.parseInt(temp1[j]);
                }
            }

            temp = code2.split(";");
            clientCode = new double[temp.length][2];
            for (int i = 0; i < temp.length; i++) {
                temp1 = null;
                temp1 = temp[i].split(",");
                for (int j = 0; j < temp1.length; j++) {
                    clientCode[i][j] = Double.parseDouble(temp1[j]);
                }
            }

            for (int i = 0; i < clientCode.length; i++) {

                boolean a = clientCode[i][0] >= serverCode[0][0] && clientCode[i][0] <= serverCode[0][2]
                        && clientCode[i][1] >= serverCode[0][1] && clientCode[i][1] <= serverCode[0][3];

                boolean b = clientCode[i][0] >= serverCode[1][0] && clientCode[i][0] <= serverCode[1][2]
                        && clientCode[i][1] >= serverCode[1][1] && clientCode[i][1] <= serverCode[1][3];

                if (!(a || b)) {
                    return false;
                }
            }

        default:
            break;
        }
        return true;
    }
}
