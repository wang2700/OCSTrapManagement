package com.example.jerry.ocstrapmanagement;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.Serializable;

/**
 * Created by jerry on 3/15/2018.
 */

public class Trap implements Serializable {

    private String checkInTime;
    private String checkOutTime;
    private Boolean isReturned = false;
    private String barcode;

    public Trap() {
    }

    public Trap(String checkOutTime, String barcode) {
        this.checkOutTime = checkOutTime;
        this.checkInTime = "";
        this.barcode = barcode;
    }

    public void checkIn(String timeStamp) {
        checkInTime = timeStamp;
        isReturned = true;
    }

    public boolean equals(String barcode){
        return this.barcode.equals(barcode);
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public Boolean getReturned() {
        return isReturned;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
