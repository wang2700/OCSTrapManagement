package com.example.jerry.ocstrapmanagement;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jerry on 3/15/2018.
 */

public class Borrower implements Serializable {

    private String name;
    private String email;
    private String phone;
    private HashMap<String,Trap> borrowedTraps;

    public Borrower() {
    }

    public Borrower(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Borrower(String name, String email, String phone, DatabaseReference userRef) {
        this.name = name;
        this.email = email;
        this.phone = phone;
//        this.borrowedTraps = new HashMap<>();
        Trap trap1 = new Trap("111111", "987654");
        Trap trap2 = new Trap("222222", "876543");
        this.borrowedTraps = new HashMap<>();
        this.borrowedTraps.put("123456", trap1);
        this.borrowedTraps.put("456789", trap2);
        addToDatabase(userRef);
    }


    private void addToDatabase(DatabaseReference userRef) {
        userRef.child(name).child("email").setValue(email);
        userRef.child(name).child("phone").setValue(phone);
        userRef.child(name).child("Borrowed Traps").setValue(borrowedTraps);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean equals(Borrower obj) {
        return name.equals(obj.getName()) && email.equals(obj.getEmail());
    }

    public void checkOutTrap(String barcode, String timeStamp){
        borrowedTraps.put(barcode, new Trap(timeStamp, barcode));
    }

    public void checkInTrap (String barcode, String timeStamp){
        borrowedTraps.get(barcode).checkIn(timeStamp);
    }
}
