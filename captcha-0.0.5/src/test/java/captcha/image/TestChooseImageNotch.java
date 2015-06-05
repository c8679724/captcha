package captcha.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Ignore;
import org.junit.Test;
import captcha.util.RandomUtil;

public class TestChooseImageNotch {

    private static final int zoneNumber = 3;
    private static final int zoneWidth = 60;
    private static final int zoneHeigth = 60;

    @Test
    @Ignore
    public void testName() {

        File imageFile = new File("H:/workspace/test/captcha-0.0.3/src/test/resources/image/杯子/49b1OOOPICbd.jpg");
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int height = bImage.getHeight();
        int width = bImage.getWidth();
        int[][] matrix = new int[3][3];
        int[] values = new int[9];
        int[][] randomZone = getRandomZone(width, height, zoneNumber);
        for (int h = 0; h < randomZone.length; h++) {
            for (int i = randomZone[h][0]; i < (randomZone[h][0] + zoneWidth - 3); i++) {
                for (int j = randomZone[h][1]; j < (randomZone[h][1] + zoneHeigth - 3); j++) {
                    readPixel(bImage, i, j, values);
                    fillMatrix(matrix, values);
                    bImage.setRGB(i, j, avgMatrix(matrix));
                }
            }
        }
        try {
            ImageIO.write(bImage, "png", new FileOutputStream("H:/data/test.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[][] getRandomZone(int width, int height, int zoneNumber) {

        int[][] result = new int[zoneNumber][2];
        for (int i = 0; i < zoneNumber; i++) {
            result[i][0] = RandomUtil.rand.nextInt(width - zoneWidth);
            result[i][1] = RandomUtil.rand.nextInt(height - zoneHeigth);
            boolean s = true;

            // 处理随机块重叠交叉的问题
            while (s && i > 0) {
                for (int j = 0; j < i; j++) {
                    System.out.println(Math.abs(result[i][0] - result[j][0]) + "," + Math.abs(result[i][1] - result[j][1]));
                    if ((Math.abs(result[i][0] - result[j][0]) < (zoneWidth + 3))
                            && (Math.abs(result[i][1] - result[j][1]) < (zoneHeigth + 3))) {

                        result[i][0] = RandomUtil.rand.nextInt(width - zoneWidth);
                        result[i][1] = RandomUtil.rand.nextInt(height - zoneHeigth);
                        System.out.println("发现交叉了:" + result[i][0] + "," + result[i][1]);
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
