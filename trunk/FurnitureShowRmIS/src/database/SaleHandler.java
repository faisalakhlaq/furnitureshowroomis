package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import model.Sale;

public class SaleHandler {
    private DbConnection db = null;

    public SaleHandler() {
    }

    public Sale searchSale(int id) throws Exception {
	Sale sale = null;
	db = DbConnection.getInstance();

	Connection conn = db.getConnection();

	PreparedStatement st = null;

	if (conn == null) {
	    throw new Exception(
		    "Unable to connect to the database. conection = null!");
	}
	try {
	    String query = "Select * from Sale where Sale_id = ?";
	    st = conn.prepareStatement(query);
	    st.setInt(1, id);
	    System.out.println("Executed Query: " + query + id);
	    ResultSet rs = st.executeQuery();
	    if (rs.next()) {
		sale = new Sale();
		sale.setItem(rs.getString("Item"));
		sale.setDescription1(rs.getString("Description1"));
		sale.setDescription2(rs.getString("Description2"));
		sale.setSalePrice(rs.getInt("Sale Price"));
		sale.setPurchaseprice(rs.getInt("Purchase price"));
		sale.setManufacturer(rs.getString("Manufacturer"));
		sale.setQuantity(rs.getInt("Quantity"));
		sale.setTotalprice(rs.getInt("Total price"));
		sale.setDate(rs.getDate("date"));

	    }
	} catch (Exception e1) {
	    Logger.getGlobal().severe(
		    "Unable to retrieve sale from the database: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception("Unable to retrieve sale from the database!"
		    + e1.getMessage());
	} finally {
	    try {
		DbConnection.closeConnection();
		if (st != null) {
		    st.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while closing the connection or statement: "
				+ e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
		throw new SQLException(
			"Error occured while closing the connection or statement. "
				+ e1.getMessage());
	    }
	}
	return sale;
    }

    public void saveSales(String iName, String desc1, String desc2,
	    double sPrice, double pPrice, java.util.Date date, String manufact,
	    String quantity, String tPrice) throws Exception {

	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO sales(itemname, description1, description2, saleprice, purchaseprice, date, manufacturer, quantity, totalprice) "
			    + " VALUES (?,?,?,?,?,?,?,?,?)");

	    java.sql.Date d = new java.sql.Date(date.getTime());

	    stmt.setString(1, iName);
	    stmt.setString(2, desc1);
	    stmt.setString(3, desc2);
	    stmt.setDouble(4, sPrice);
	    stmt.setDouble(5, pPrice);
	    stmt.setDate(6, d);
	    stmt.setString(7, manufact);
	    stmt.setString(8, quantity);
	    stmt.setString(9, tPrice);

	    stmt.executeUpdate();

	    System.out.println("Executing Query: " + stmt.toString());
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while inserting sales into sale table: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Error occured while inserting sales into sale table: "
			    + e1.getMessage());
	} finally {
	    try {
		DbConnection.getInstance();
		if (stmt != null) {
		    stmt.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while closing the database conneciton: "
				+ e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
		throw new Exception(
			"Error occured while closing the database conneciton: "
				+ e1.getMessage());
	    }
	}
    }

    public void saveSale(Sale s) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO sales(itemname, description1, description2, saleprice, purchaseprice, date, manufacturer, quantity, totalprice) "
			    + " VALUES (?,?,?,?,?,?,?,?,?)");

	    stmt.setString(1, s.getItem());
	    stmt.setString(2, s.getDescription1());
	    stmt.setString(3, s.getDescription2());
	    stmt.setDouble(4, s.getSalePrice());
	    stmt.setDouble(5, s.getPurchaseprice());
	    stmt.setString(6, s.getManufacturer());
	    stmt.setInt(7, s.getQuantity());
	    stmt.setDouble(8, s.getTotalprice());
	    stmt.setDate(9, (Date) s.getDate());

	    stmt.executeUpdate();

	    System.out.println("Executing Query: " + stmt.toString());
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while inserting sale into sale table: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Error occured while inserting sale into sale table: "
			    + e1.getMessage());
	} finally {
	    try {
		DbConnection.getInstance();
		if (stmt != null) {
		    stmt.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while closing the database conneciton: "
				+ e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
		throw new Exception(
			"Error occured while closing the database conneciton: "
				+ e1.getMessage());
	    }
	}
    }
}