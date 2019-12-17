package com.plywacz.mosaic.services.mosaic;
/*
Author: BeGieU
Date: 17.12.2019
*/

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageResizerImpl implements ImageResizer {
    @Override
    public List<BufferedImage> scaleImagesList(List<BufferedImage> images, int newWidth, int newHeight) {
        var scaledImagesList = new ArrayList<BufferedImage>();
        for (BufferedImage inputImage : images) {
            var outputImg = scale(inputImage, newWidth, newHeight);
            scaledImagesList.add(outputImg);
        }

        return scaledImagesList;
    }

    @Override
    public BufferedImage scaleImage(BufferedImage image, int newWidth, int newHeight) {
        return scale(image, newWidth, newHeight);
    }

    private BufferedImage scale(BufferedImage inputImage, int newWidth, int newHeight) {
        var outputImg = new BufferedImage(
                newWidth,
                newHeight,
                inputImage.getType());
        var graphics2d = outputImg.createGraphics();

        graphics2d.drawImage(inputImage,
                0, 0,
                newWidth, newHeight,
                null);
        graphics2d.dispose();

        return outputImg;
    }
}
