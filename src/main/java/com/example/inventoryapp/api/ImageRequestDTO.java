package com.example.inventoryapp.api;

import com.example.inventoryapp.domain.Image;

import java.util.Base64;

public class ImageRequestDTO {
    private String ImageName;
    private String encoding;
    private String contents;

    public ImageRequestDTO() {}

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
