package model;

public class Product implements ModelClass {
    private int productId;
    private String pName = null;
    private String description1 = null;
    private String description2 = null;
    private int manufacturerId;
    private int categoryId;
    private int warrantyId;

    public Product() {

    }

    public int getProductId() {
	return productId;
    }

    public void setProductId(int productId) {
	this.productId = productId;
    }

    public String getpName() {
	return pName;
    }

    public void setpName(String pName) {
	this.pName = pName;
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

    public int getManufacturerId() {
	return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
	this.manufacturerId = manufacturerId;
    }

    public int getCategoryId() {
	return categoryId;
    }

    public void setCategoryId(int cetagoryId) {
	this.categoryId = cetagoryId;
    }

    public int getWarrantyId() {
	return warrantyId;
    }

    public void setWarrantyId(int warrantyId) {
	this.warrantyId = warrantyId;
    }
}