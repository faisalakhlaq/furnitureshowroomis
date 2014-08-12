package model;

import java.util.Date;

public class Sale implements ModelClass {
    private String item = null;
    private String description1 = null;
    private String description2 = null;
    private double salePrice;
    private Date date = null;
    private double purchaseprice;
    private String manufacturer = null;
    private int quantity;
    private double totalprice;

    public Sale() {

    }

    public String getItem() {
	return item;
    }

    public void setItem(String item) {
	this.item = item;
    }

    public String getDescription1() {
	return description1;
    }

    public void setDescription1(String description1) {
	this.description1 = description1;
    }

    public String getDescription2() {
	return description2;
    }

    public void setDescription2(String description2) {
	this.description2 = description2;
    }

    public double getSalePrice() {
	return salePrice;
    }

    public void setSalePrice(double salePrice) {
	this.salePrice = salePrice;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public double getPurchaseprice() {
	return purchaseprice;
    }

    public void setPurchaseprice(double purchaseprice) {
	this.purchaseprice = purchaseprice;
    }

    public String getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public double getTotalprice() {
	return totalprice;
    }

    public void setTotalprice(double totalprice) {
	this.totalprice = totalprice;
    }

}
