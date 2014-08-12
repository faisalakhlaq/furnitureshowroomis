package model;

public class Manufacturer implements ModelClass {
    private int mId;
    private String mName = null;
    private String contactPerson1 = null;
    private String contactPerson2 = null;
    private int tNumber;
    private int cellNumber;
    private String address = null;
    private String web = null;
    private String accountNumber = null;

    public Manufacturer() {
    }

    public int getmId() {
	return mId;
    }

    public void setmId(int mId) {
	this.mId = mId;
    }

    public String getmName() {
	return mName;
    }

    public void setmName(String mName) {
	this.mName = mName;
    }

    public String getContactPerson1() {
	return contactPerson1;
    }

    public void setContactPerson1(String contactPerson1) {
	this.contactPerson1 = contactPerson1;
    }

    public String getContactPerson2() {
	return contactPerson2;
    }

    public void setContactPerson2(String contactPerson2) {
	this.contactPerson2 = contactPerson2;
    }

    public int gettNumber() {
	return tNumber;
    }

    public void settNumber(int tNumber) {
	this.tNumber = tNumber;
    }

    public int getCellNumber() {
	return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
	this.cellNumber = cellNumber;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getWeb() {
	return web;
    }

    public void setWeb(String web) {
	this.web = web;
    }

    public String getAccountNumber() {
	return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
    }

}
