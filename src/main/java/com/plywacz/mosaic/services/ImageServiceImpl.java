package com.plywacz.mosaic.services;
/*
Author: BeGieU
Date: 15.12.2019
*/

import com.plywacz.mosaic.services.image.ImageDownloader;
import com.plywacz.mosaic.services.mosaic.ImageResizer;
import com.plywacz.mosaic.services.mosaic.MosaicAssembler;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    private static final int AVG_WIDTH_INDEX = 0;
    private static final int AVG_HEIGHT_INDEX = 1;

    private final ImageDownloader imageDownloader;
    private final MosaicAssembler mosaicAssembler;
    private final ImageResizer imageResizer;


    public ImageServiceImpl(ImageDownloader imageDownloader,
                            MosaicAssembler mosaicAssembler,
                            ImageResizer imageResizer) {
        this.imageDownloader = imageDownloader;
        this.mosaicAssembler = mosaicAssembler;
        this.imageResizer = imageResizer;
    }

    @Override
    public byte[] generateMosaic(Integer random, String resolutionStr, String imagesUrls) {
        var imageList = imageDownloader.getImagesAsync(imagesUrls);

        var avgImagesDimensionsArr = getAvgWidthAndHeight(imageList);
        var resizedImgsList = imageResizer.scaleImagesList(
                imageList,
                avgImagesDimensionsArr[AVG_WIDTH_INDEX],
                avgImagesDimensionsArr[AVG_HEIGHT_INDEX]);

        if(random.equals(1)) //if random parameter is set to 1 that means  client wants mosaic in random order
            Collections.shuffle(resizedImgsList);
        var mosaic = mosaicAssembler.assembleMosaic(resizedImgsList);

        var mosaicResolutionArr = getResolution(resolutionStr);
        var resizedMosaic = imageResizer.scaleImage(
                mosaic,
                mosaicResolutionArr[0],
                mosaicResolutionArr[1]);

        return toByteArr(resizedMosaic);

    }

    private int[] getResolution(String resolutionStr) {
        if (!resolutionStr.contains("x"))
            throw new IllegalArgumentException("wrong resolution format provided. Correct format: XxY where X nad Y is int value");

        var splitedStr = resolutionStr.split("x");

        try {
            var result = new int[2];
            result[0] = Integer.parseInt(splitedStr[0]);
            result[1] = Integer.parseInt(splitedStr[1]);
            return result;
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("wrong resolution format provided. Correct format: XxY where X nad Y is int value+\n" +
                    "provided X or Y is not numeric value");
        }
    }

    /*
     * first returned elem in arr is avg width second avg height of images in the list
     */
    private int[] getAvgWidthAndHeight(List<BufferedImage> images) {
        var sumWidth = 0;
        var sumHeight = 0;
        for (BufferedImage bi : images) {
            sumWidth += bi.getWidth();
            sumHeight += bi.getHeight();
        }

        return new int[]{
                sumWidth / images.size(),  //avg width
                sumHeight / images.size() //avg height
        };
    }


    private byte[] toByteArr(BufferedImage image) {
        Optional<byte[]> imageBytes;
        try (var byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            imageBytes = Optional.of(byteArrayOutputStream.toByteArray());
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return imageBytes.orElseThrow(() -> new RuntimeException("couldnt Generate byte arr from img"));
    }
}
