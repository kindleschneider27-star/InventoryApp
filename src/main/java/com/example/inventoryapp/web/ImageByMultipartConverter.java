package com.example.inventoryapp.web;

import com.example.inventoryapp.domain.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@Component
public class ImageByMultipartConverter implements Converter<MultipartFile, Image> {
    private static final Logger logger = LoggerFactory.getLogger(ImageByMultipartConverter.class);

    @Override
    public Image convert(MultipartFile file) {
        Image image = new Image();
        if (!file.isEmpty()) {
            image.setImageName(file.getOriginalFilename());
            image.setEncoding(file.getContentType());
            try{
                image.setContents(file.getBytes());
            }
            catch (IOException e){
                System.out.println("Error while converting image file");
                logger.error("Error while converting image file");
            }
        }
        return image;
    }
}
