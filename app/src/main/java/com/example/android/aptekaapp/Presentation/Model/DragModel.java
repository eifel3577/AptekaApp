package com.example.android.aptekaapp.Presentation.Model;

public class DragModel {
    private String dragName;
    private String dragPrice;
    private String dragPhoto;
    private String dragUrl;

    public String getDragName() {
        return dragName;
    }

    public void setDragName(String dragName) {
        this.dragName = dragName;
    }

    public String getDragPrice() {
        return dragPrice;
    }

    public void setDragPrice(String dragPrice) {
        this.dragPrice = dragPrice;
    }

    public String getDragPhoto() {
        return dragPhoto;
    }

    public void setDragPhoto(String dragPhoto) {
        this.dragPhoto = dragPhoto;
    }

    public String getDragUrl() {return dragUrl;}

    public void setDragUrl(String dragUrl) {this.dragUrl = dragUrl;}
}