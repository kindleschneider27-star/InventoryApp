package com.example.inventoryapp.domain;

import java.util.Base64;

public class Image {
    private String name;
    private String encoding;
    private byte[] contents;

    public Image(String name, String encoding, byte[] contents) {
        this.name = name;
        this.encoding = encoding;
        this.contents = contents;
    }

    public Image() {
        name = "";
        encoding = "";
        contents = new byte[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
