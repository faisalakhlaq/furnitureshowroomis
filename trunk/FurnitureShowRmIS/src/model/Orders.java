package model;

import java.sql.Date;

import javax.swing.JLabel;

public class Orders implements ModelClass {
    private String description = null;
    private String status = null;
    private String borrow = null;
    private Date orderdate = null;
    private Date deliveryDate= null;
    public Orders() {

    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getBorrow() {
        return borrow;
    }
    public void setBorrow(String borrow) {
        this.borrow = borrow;
    }
    public Date getOrderDate() {
        return orderdate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderdate = orderDate;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    
}
