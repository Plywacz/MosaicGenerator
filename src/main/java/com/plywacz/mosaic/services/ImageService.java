package com.plywacz.mosaic.services;
/*
Author: BeGieU
Date: 15.12.2019
*/

public interface ImageService {
    byte[] generateMosaic(Integer random, String resolution, String images);
}
