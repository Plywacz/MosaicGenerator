package com.plywacz.mosaic.services.image;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
Author: BeGieU
Date: 15.12.2019
*/
@Service
class ImageDownloaderImpl implements ImageDownloader {
    private static final int MAX_IMG_NUMBER = 8;
    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_IMG_NUMBER);

    public List<BufferedImage> getImagesAsync(String urls) {
        var urlArr = splitUrls(urls);
        var imagesList = new ArrayList<BufferedImage>();

        var futureSet = new ArrayList<Future<BufferedImage>>();
        try {
            for (String url : urlArr) {
                var futureImg = executorService.submit(
                        new ImageDownloadTask(url)
                );
                futureSet.add(futureImg);
            }
            for (Future<BufferedImage> futureImg : futureSet) {
                imagesList.add(
                        futureImg.get()
                );
            }
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return imagesList;
    }

    private String[] splitUrls(String urls) {
        return urls.split(",");
    }
}
