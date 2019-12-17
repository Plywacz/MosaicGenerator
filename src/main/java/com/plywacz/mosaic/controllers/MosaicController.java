package com.plywacz.mosaic.controllers;

import com.plywacz.mosaic.services.ImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/*
Author: BeGieU
Date: 15.12.2019
*/
@RestController
public class MosaicController {
    private final ImageService imageService;

    public MosaicController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/mosaic",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] generateMosaic(
            @RequestParam(value = "random", required = false, defaultValue = "0") Integer random,
            @RequestParam(value = "resolution", required = false, defaultValue = "2048x2048") String resolution,
            @RequestParam(value = "images") String images) {

        return imageService.generateMosaic(random, resolution, images);

    }

    @RequestMapping(value = "*")
    public String wrongUrlProvided(HttpServletRequest request) {

        return "you have provided wrong url" + request.getRequestURL();
    }
}
