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
public class OrderDTO {
    private String orderID;
    private String email;
    private float totalPrice;
    private String orderDate;
    private boolean status;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String email, float totalPrice, String orderDate, boolean status) {
        this.orderID = orderID;
        this.email = email;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "orderID=" + orderID + ", email=" + email + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + ", status=" + status + '}';
    }
    
}
