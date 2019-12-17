package com.plywacz.mosaic.services.mosaic;
/*
Author: BeGieU
Date: 16.12.2019
*/

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.List;

/**
 * Mosaic assembler for list of images that has same width and height
 * Assembled Mosaic always has 2 rows
 */
@Service
public class MosaicAssemblerImpl implements MosaicAssembler {
    private static final int ROW_NUMBER = 2;

    /*
     *firstRowImagesAmount need to be round  up,
     *  because i want first row to be longer than the second when images amount is odd,
     * therefore imageListSize is casted to double then divided by row number,
     */

    /**
     * @param imageList that contains one sized images
     * @return assembled mosaic in same order it was in the given list,
     * mosaic has 2 rows and if number of images in given list is odd
     * then first row is longer by one than second row
     */
    @Override
    public BufferedImage assembleMosaic(List<BufferedImage> imageList) {
        var imageListSize = imageList.size();
        var firstRowImagesAmount = (int) Math.round((double) imageListSize / ROW_NUMBER);

        var mosaicWidth = getMosaicWidth(imageList, firstRowImagesAmount);
        var mosaicHeight = getMosaicHeight(imageList);

        return generateMosaic(imageList, mosaicWidth, mosaicHeight);
    }

    private BufferedImage generateMosaic(List<BufferedImage> imageList, int width, int height) {
        var mosaic = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        var graphics2d = mosaic.createGraphics();

        final var imgWidth = imageList.get(0).getWidth();
        final var imgHeight = imageList.get(0).getHeight();

        var xCord = 0;
        var yCord = 0;

        for (BufferedImage bi : imageList) {
            graphics2d.drawImage(bi, xCord, yCord, null);
            xCord += imgWidth;
            if (xCord >= mosaic.getWidth()) {
                xCord = 0;
                yCord += imgHeight;
            }
        }
        return mosaic;
    }

    private int getMosaicWidth(List<BufferedImage> imageList, int firstRowImagesAmount) {
        return imageList.get(0).getWidth() * firstRowImagesAmount;
    }

    private int getMosaicHeight(List<BufferedImage> bufferedImageList) {
        return bufferedImageList.get(0).getHeight() * ROW_NUMBER;
    }
}
