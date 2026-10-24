package com.example.inventoryapp.api;

import com.example.inventoryapp.domain.Image;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import java.util.Base64;

public class ImageResponseDTO {
    private String ImageName;
    private String encoding;
    private String contents;

    public ImageResponseDTO() {}

    public static ImageResponseDTO from(Image image) {
        if (image == null || image.getContents() == null || image.getContents().length == 0) {
            return null;
        }

        ImageResponseDTO response = new ImageResponseDTO();
        response.setImageName(image.getImageName());
        response.setEncoding(image.getEncoding());
        response.setContents(Base64.getEncoder().encodeToString(image.getContents()));

        return response;
    }

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
