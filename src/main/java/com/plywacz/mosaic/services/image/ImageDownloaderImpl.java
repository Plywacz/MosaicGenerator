package com.plywacz.mosaic.services.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    private final ExecutorService executorService;

    public ImageDownloaderImpl(@Value("${threads_num}") int threadNum) {
        executorService = Executors.newFixedThreadPool(threadNum);
    }

    public List<BufferedImage> getImagesAsync(String urls) {
        var urlArr = splitUrls(urls);
        if (urlArr.length > 8 || urlArr.length < 1)
            throw new IllegalArgumentException("you have to provide number of urls to images, that is between 0 and 8");

        var imagesList = new ArrayList<BufferedImage>();
        var futureList = new ArrayList<Future<BufferedImage>>();
        try {
            for (String url : urlArr) {
                var futureImg = executorService.submit(
                        new ImageDownloadTask(url)
                );
                futureList.add(futureImg);
            }
            for (Future<BufferedImage> futureImg : futureList) {
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
