package model;

import java.util.Date;

public class Payments implements ModelClass {
    
    private int totalBill;
    private String description = null;
    private String manufacturerName = null;
    private int paymentAmount;
    private int balance;
    private Date date = null;
    public Payments() {

    }
    public int getTotalBill() {
        return totalBill;
    }
    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getManufacturerName() {
        return manufacturerName;
    }
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
    public int getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

   

}
