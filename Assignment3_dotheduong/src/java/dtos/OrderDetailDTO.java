/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Admin
 */
public class OrderDetailDTO {
    private String detailID;
    private String orderID;
    private String carID;
    private String carName;
    private int amount;
    private String rentDate;
    private String returnDate;
    private float rate;
    private float price;
    private boolean status;
    
    private boolean statusCancel;
    private boolean statusRate;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String detailID, String orderID, String carID, String carName, int amount, String rentDate, String returnDate, float rate, float price, boolean status) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.carID = carID;
        this.carName = carName;
        this.amount = amount;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.rate = rate;
        this.price = price;
        this.status = status;
    }
    

    public OrderDetailDTO(String detailID, String orderID, String carID, int amount, String rentDate, String returnDate, float rate, float price, boolean status) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.carID = carID;
        this.amount = amount;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.rate = rate;
        this.price = price;
        this.status = status;
    }

    

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }



    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatusCancel() {
        return statusCancel;
    }

    public void setStatusCancel(boolean statusCancel) {
        this.statusCancel = statusCancel;
    }

    public boolean isStatusRate() {
        return statusRate;
    }

    public void setStatusRate(boolean statusRate) {
        this.statusRate = statusRate;
    }
    
    
    @Override
    public String toString() {
        return "OrderDetailDTO{" + "detailID=" + detailID + ", orderID=" + orderID + ", carID=" + carID + ", amount=" + amount + ", rentDate=" + rentDate + ", returnDate=" + returnDate + ", rate=" + rate + ", price=" + price + ", status=" + status + '}';
    }
    
    
}
