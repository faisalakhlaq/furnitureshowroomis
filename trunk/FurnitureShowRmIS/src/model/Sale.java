package model;

import java.util.Date;

public class Sale implements ModelClass {
    private int saleId;
    private String name = null;
    private String description1 = null;
    private String description2 = null;
    private double salePrice;
    private Date date = null;
    private double purchasePrice;
    private String manufacturerName = null;
    private int quantity;
    private double totalPrice;
    private double totalPurchasePrice;

    public Sale() {

    }

    public int getSaleId() {
	return saleId;
    }

    public void setSaleId(int saleId) {
	this.saleId = saleId;
    }

    public String getName() {
	return name;
    }

    public void setName(String item) {
	this.name = item;
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

    public double getPurchasePrice() {
	return purchasePrice;
    }

    public void setPurchaseprice(double purchaseprice) {
	this.purchasePrice = purchaseprice;
    }

    public String getManufacturerName() {
	return manufacturerName;
    }

    public void setManufacturerName(String manufacturer) {
	this.manufacturerName = manufacturer;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public double getTotalprice() {
	return totalPrice;
    }

    public void setTotalPrice(double totalprice) {
	this.totalPrice = totalprice;
    }

    public double getTotalPurchasePrice() {
	return totalPurchasePrice;
    }

    public void setTotalPurchasePrice(double totalPurchasePrice) {
	this.totalPurchasePrice = totalPurchasePrice;
    }

    // @Override
    // public boolean equals(Object obj) {
    // boolean equal = false;
    // if (!(obj instanceof Sale))
    // return equal;
    //
    // Sale c = (Sale) obj;
    //
    // // if(this.hashCode() == c.hashCode()) // TODO check if hashcode needs
    // // to be checked here
    // if (this.saleId == c.getSaleId() && this.date.equals(c.getDate())
    // && this.name.equals(c.getName())
    // && this.description1.equals(c.getDescription1())
    // && this.description2 == c.getDescription2()
    // && this.salePrice == (c.getSalePrice())
    // && this.purchasePrice == c.getPurchasePrice()
    // && this.manufacturerName.equals(c.getManufacturerName())
    // && this.quantity == c.getQuantity()
    // && this.totalPrice == c.getTotalprice()
    // && this.totalPurchasePrice == c.getTotalPurchasePrice()) {
    // equal = true;
    // }
    // return equal;
    // }
}