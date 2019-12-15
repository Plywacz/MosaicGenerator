package com.plywacz.mosaic.services.image;
/*
Author: BeGieU
Date: 15.12.2019
*/

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.concurrent.Callable;

class ImageDownloadTask implements Callable<BufferedImage> {
    private final String imageUrl;

    public ImageDownloadTask(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public BufferedImage call() throws Exception {
        return ImageIO.read(new URL(imageUrl));
    }
}
