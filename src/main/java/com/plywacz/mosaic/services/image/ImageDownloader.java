package com.plywacz.mosaic.services.image;
/*
Author: BeGieU
Date: 15.12.2019
*/

import java.awt.image.BufferedImage;
import java.util.List;

public interface ImageDownloader {
    List<BufferedImage> getImagesAsync(String urls) ;


}
