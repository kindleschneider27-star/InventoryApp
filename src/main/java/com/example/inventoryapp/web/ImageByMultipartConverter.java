package com.example.inventoryapp.web;

import com.example.inventoryapp.domain.Image;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@Component
public class ImageByMultipartConverter implements Converter<MultipartFile, Image> {

    @Override
    public Image convert(MultipartFile file) {
        Image image = new Image();
        if (!file.isEmpty()) {
            image.setName(file.getOriginalFilename());
            image.setEncoding(file.getContentType());
            try{
                image.setContents(file.getBytes());
            }
            catch (IOException e){
                System.out.println("Error while converting image file");
            }
        }
        return image;
    }
}
