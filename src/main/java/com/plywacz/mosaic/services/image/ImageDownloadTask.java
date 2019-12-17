package com.plywacz.mosaic.services.image;
/*
Author: BeGieU
Date: 15.12.2019
*/

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;

class ImageDownloadTask implements Callable<BufferedImage> {
    private final String imageUrl;

    public ImageDownloadTask(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public BufferedImage call() throws Exception {
        try {
            return ImageIO.read(new URL(imageUrl));
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("couldn't fetch given image from url: " + imageUrl, e);
        }
    }
}
