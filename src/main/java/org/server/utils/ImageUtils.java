package org.server.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public final class ImageUtils {

    public static byte[] getBytes(BufferedImage bufferedImage, String format) {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, format, bos);
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ImageUtils() {

    }
}