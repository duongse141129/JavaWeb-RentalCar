/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CarDTO implements Serializable{
    private String carID;
    private String carName;
    private String color;
    private int quantity;
    private float price;
    private String nsx;
    private float rating;
    private String type;
    private boolean status;
    private String img;
    
    private String rentDate;
    private String returnDate;

    public CarDTO() {
    }

    public CarDTO(String carID, String carName, String color, int quantity, float price, String nsx, float rating, String type, boolean status, String img) {
        this.carID = carID;
        this.carName = carName;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.nsx = nsx;
        this.rating = rating;
        this.type = type;
        this.status = status;
        this.img = img;
    }

    

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "CarDTO{" + "carID=" + carID + ", carName=" + carName + ", color=" + color + ", quantity=" + quantity + ", price=" + price + ", nsx=" + nsx + ", rating=" + rating + ", type=" + type + ", status=" + status + ", img=" + img + '}';
    }
    
           
}
