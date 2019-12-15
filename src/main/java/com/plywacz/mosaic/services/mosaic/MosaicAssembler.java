package com.plywacz.mosaic.services.mosaic;
/*
Author: BeGieU
Date: 15.12.2019
*/

import java.awt.image.BufferedImage;
import java.util.List;

public interface MosaicAssembler {
    BufferedImage assembleMosaic(List<BufferedImage> imageList);
}
