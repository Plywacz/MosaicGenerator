package com.plywacz.mosaic.services;
/*
Author: BeGieU
Date: 15.12.2019
*/

import com.plywacz.mosaic.services.image.ImageDownloader;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageDownloader imageDownloader;


    public ImageServiceImpl(ImageDownloader imageDownloader) {
        this.imageDownloader = imageDownloader;
    }

    @Override
    public byte[] generateMosaic(Long random, String resolution, String images) {
        //todo change this code, it is only for postman test
        var imageList = imageDownloader.getImagesAsync(images);
        var mosaic = assembleMosaic(imageList);

    }

    private BufferedImage assembleMosaic(List<BufferedImage> imageList) {
        return null;
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
