package com.plywacz.mosaic.services.mosaic;
/*
Author: BeGieU
Date: 17.12.2019
*/

import java.awt.image.BufferedImage;
import java.util.List;

public interface ImageResizer {
    List<BufferedImage> scaleImagesList(List<BufferedImage> images, int newWidth, int newHeight);

    BufferedImage scaleImage(BufferedImage image, int newWidth, int newHeight);
}
