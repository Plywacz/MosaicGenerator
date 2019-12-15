package com.plywacz.mosaic.services;
/*
Author: BeGieU
Date: 15.12.2019
*/

import java.awt.image.BufferedImage;

public interface ImageService {
    byte[] generateMosaic(Long random, String resolution, String images);
}
