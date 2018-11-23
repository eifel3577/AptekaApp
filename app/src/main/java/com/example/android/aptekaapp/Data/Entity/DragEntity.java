package com.example.android.aptekaapp.Data.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Drag Entity используется в Data слое
 */

@Entity(tableName = "dragentity")
public class DragEntity {

    @PrimaryKey(autoGenerate = true)
    public long primaryKey;


    private String groupName;

    @SerializedName("drag_url")
    private String dragUrl;

    @SerializedName("drag_name")
    private String dragName;

    @SerializedName("drag_photo")
    private String dragPhoto;

    @SerializedName("drag_price")
    private String dragPrice;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDragPhoto() {return dragPhoto;}

    public void setDragPhoto(String dragPhoto) {this.dragPhoto = dragPhoto;}

    public String getDragUrl() {return dragUrl;}

    public void setDragUrl(String dragUrl) {this.dragUrl = dragUrl;}
}