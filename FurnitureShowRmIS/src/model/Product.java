package model;

public class Product implements ModelClass {
    private int productId;
    private String productName = null;
    private String description1 = null;
    private String description2 = null;
    private String manufacturerName;
    private String categoryName;
    private int warranty;

    public Product() {

    }

    public int getProductId() {
	return productId;
    }

    public void setProductId(int productId) {
	this.productId = productId;
    }

    public String getProductName() {
	return productName;
    }

    public void setProductName(String pName) {
	this.productName = pName;
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

    public String getManufacturerName() {
	return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
	this.manufacturerName = manufacturerName;
    }

    public String getCategoryName() {
	return categoryName;
    }

    public void setCategoryName(String cetagoryName) {
	this.categoryName = cetagoryName;
    }

    public int getWarranty() {
	return warranty;
    }

    public void setWarranty(int warranty) {
	this.warranty = warranty;
    }
}