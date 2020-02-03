# MosaicGenerator

## General
  App generates mosaic for given collections of images  which are download from given urls in link. Images are downloaded asynchronously.
  
  Endpoint:  
   [GET]  
  http://localhost:8080/mosaic?random=Z&resolution=XxY&images=URL1,URL2,URL3  
  where:   
   - random is optional parametr. It means random images order  in mosaic. If not specified it is set on 0 that means order like in the given request. Value 1 in parameter means random order of images    
   - resolution defines resolution of output mosaic image. Optional, default resolution is 2048x2048. 
   - images urls to images to be included in mosaic. Required, you should specify between 1 to 8 images.  
   * example request: http://localhost:8080/mosaic?random=1&resolution=1000x600&images=https://upload.wikimedia.org/wikipedia/commons/3/3f/JPEG_example_flower.jpg,https://upload.wikimedia.org/wikipedia/commons/2/23/Lake_mapourika_NZ.jpeg,https://pyx-project.org/examples/bitmap/jpeg.png,https://i.stack.imgur.com/nB9zHm.jpg  
   
## Main used technologies:
  - Project set up on Spring Boot
  - java threads pool to get images from different urls  parallel  

## Testing with postman
  - Download project from github
  - run in Intellij IDEA
  - then run with example request, that is written above on postman app.
