package model;

import java.sql.Date;



public class Payments implements ModelClass {
    
    private int totalBill;
    private String description = null;
    private String manufacturerName = null;
    private int paymentAmount;
    private double balance;
    private java.util.Date date = null;
    public Payments() {

    }
    public double getTotalBill() {
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
    public double getBalance() {
        return balance;
    }
    public void setBalance(double d) {
        this.balance = d;
    }
    public Date getDate() {
        return (Date) date;
    }
    public void setDate(java.util.Date date2) {
        this.date = date2;
    }

   

}
