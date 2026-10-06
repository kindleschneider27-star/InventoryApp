package com.example.inventoryapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;

import java.util.Base64;

@Embeddable
public class Image {
    private String ImageName;
    private String encoding;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] contents;

    public Image(String name, String encoding, byte[] contents) {
        this.ImageName = name;
        this.encoding = encoding;
        this.contents = contents;
    }

    public Image() {
        ImageName = "";
        encoding = "";
        contents = new byte[0];
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        this.ImageName = imageName;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public String getDataUri(){
        if (contents == null || contents.length == 0){
            return null;
        }
        return "data:" + encoding + ";base64," + Base64.getEncoder().encodeToString(contents);
    }
}
