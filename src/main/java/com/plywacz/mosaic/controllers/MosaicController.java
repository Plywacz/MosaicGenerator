package com.plywacz.mosaic.controllers;

import com.plywacz.mosaic.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

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
            params = {"random", "resolution", "images"},
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] generateMosaic(
            @RequestParam(value = "random", required = false) Long random,
            @RequestParam(value = "resolution", required = false) String resolution,
            @RequestParam(value = "images") String images) {

        var img = imageService.generateMosaic(random, resolution, images);
        return img;

    }

    @RequestMapping(value = "*")
    public String wrongUrlProvided(HttpServletRequest request) {

        return "you have provided wrong url" + request.getRequestURL();
    }
}
