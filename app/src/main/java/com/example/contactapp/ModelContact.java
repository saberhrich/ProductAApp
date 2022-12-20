package com.example.contactapp;

public class ModelContact {

    private String id,libelle,image,code,price,radioGroup,addedTime,updatedTime;

    // create constructor

    public ModelContact(String id, String libelle, String image, String code, String price, String radioGroup, String addedTime, String updatedTime) {
        this.id = id;
        this.libelle = libelle;
        this.image = image;
        this.code = code;
        this.price = price;
        this.radioGroup = radioGroup;
        this.addedTime = addedTime;
        this.updatedTime = updatedTime;
    }

    // create getter and setter method

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRadioGroup() {
        return radioGroup;
    }

    public void setNote(String note) {
        this.radioGroup = radioGroup;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
