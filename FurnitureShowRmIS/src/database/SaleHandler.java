package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

import model.Sale;

public class SaleHandler {
    private DbConnection db = null;

    public SaleHandler() {
    }

    public Vector<Integer> getAllSaleIds() throws Exception {
	Vector<Integer> ids = null;
	db = DbConnection.getInstance();

	Connection con = db.getConnection();

	Statement stmt = null;

	if (con == null) {
	    throw new Exception("Unable to connect to the database!");
	}
	try {
	    stmt = con.createStatement();
	    String query = "SELECT sale_id FROM sales";
	    System.out.println("Query Executed: " + query);
	    ResultSet rs = stmt.executeQuery(query);

	    if (rs != null) {
		ids = new Vector<Integer>();
		while (rs.next()) {
		    ids.add(rs.getInt("sale_id"));
		}
	    }
	} catch (Exception e1) {
	    Logger.getGlobal().severe(
		    "Unable to retrieve sales ids from the database. "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Unable to retrieve sales ids from the database!<p>"
			    + e1.getMessage());
	} finally {
	    try {
		DbConnection.closeConnection();
		if (stmt != null) {
		    stmt.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while closing the connection or statement: "
				+ e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
		throw new SQLException(
			"Error occured while closing the connection or statement.");
	    }
	}
	return ids;
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
	    String query = "Select * from Sales where Sale_id = ?";
	    st = conn.prepareStatement(query);
	    st.setInt(1, id);
	    System.out.println("Executed Query: " + query + id);
	    ResultSet rs = st.executeQuery();
	    if (rs.next()) {
		sale = new Sale();
		sale.setSaleId(id);
		sale.setName(rs.getString("itemname"));
		sale.setDescription1(rs.getString("Description1"));
		sale.setDescription2(rs.getString("Description2"));
		sale.setSalePrice(rs.getInt("saleprice"));
		sale.setPurchaseprice(rs.getInt("purchaseprice"));
		sale.setManufacturerName(rs.getString("Manufacturer"));
		sale.setQuantity(rs.getInt("Quantity"));
		sale.setTotalPrice(rs.getInt("totalprice"));
		sale.setTotalPurchasePrice(rs.getInt("totalpurchaseprice"));
		sale.setDate(rs.getDate("date"));
		/*
		 * itemname varchar(20), description1 varchar(60), description2
		 * varchar(60), saleprice double, date date, purchaseprice
		 * double, manufacturer varchar(20), quantity int(10),
		 * totalprice double
		 */
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

    public void saveSale(Sale s) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO sales(itemname, description1, description2, saleprice, date, purchaseprice, manufacturer, quantity, totalpurchaseprice, totalprice ) "
			    + " VALUES (?,?,?,?,?,?,?,?,?,?)");

	    stmt.setString(1, s.getName());
	    stmt.setString(2, s.getDescription1());
	    stmt.setString(3, s.getDescription2());
	    stmt.setDouble(4, s.getSalePrice());
	    stmt.setDate(5, (Date) s.getDate());
	    stmt.setDouble(6, s.getPurchasePrice());
	    stmt.setString(7, s.getManufacturerName());
	    stmt.setInt(8, s.getQuantity());
	    stmt.setDouble(9, s.getTotalPurchasePrice());
	    stmt.setDouble(10, s.getTotalprice());

	    /*
	     * itemname varchar(20), description1 varchar(60), description2
	     * varchar(60), saleprice double, date date, purchaseprice double,
	     * manufacturer varchar(20), quantity int(10), totalprice double
	     */
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

    public void deleteSale(int sId) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to get database connection");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("DELETE from sales where sale_id = '"
			    + sId + "';");

	    stmt = (PreparedStatement) conn
		    .prepareStatement("DELETE from sales where sale_id = "
			    + sId + ";");
	    stmt.executeUpdate();
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while deleting the sales: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception("Unable to delete sales: " + e1.getMessage());
	} finally {
	    try {
		DbConnection.getInstance();
		if (stmt != null) {
		    stmt.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while delete sales: " + e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
		throw new SQLException(
			"Error occured while closing the connection or statement.");
	    }
	}
    }

    public Vector<String> getManufacturerNames() {
	Vector<String> names = null;
	DbConnection db = DbConnection.getInstance();
	Connection conn = db.getConnection();

	String query = "Select manufacturer from sales;";
	Statement st = null;
	try {
	    st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    if (rs != null) {
		names = new Vector<String>();
		while (rs.next()) {
		    names.add(rs.getString("manufacturer"));
		}
	    }
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while retrieving manufacturer names: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    try {
		throw new SQLException(
			"Error occured while retrieving manufactrer names"
				+ e1.getMessage());
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	} finally {
	    try {
		DbConnection.closeConnection();
		if (st != null) {
		    st.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while closing the database connection: "
				+ e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
		try {
		    throw new SQLException(
			    "Error occured while closing the database connection"
				    + e1.getMessage());
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return names;
    }

    public void updateSale(Sale b) throws Exception {
	db = DbConnection.getInstance();
	Connection conn = db.getConnection();
	PreparedStatement stmt = null;
	Statement st = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database!");
	}
	try {
	    /*
	     * sale_id, itemname varchar(20), description1 varchar(60),
	     * description2 varchar(60), saleprice double, date date,
	     * purchaseprice double, manufacturer varchar(20), quantity int(10),
	     * totalpurchaseprice double, totalprice double
	     */
	    st = conn.createStatement();
	    java.util.Date d = b.getDate();
	    java.sql.Date date = new java.sql.Date(d == null ? null
		    : d.getTime());
	    String query = "Update sales set date = '" + date
		    + "', itemname = '" + b.getName() + "', description1 = '"
		    + b.getDescription1() + "', description2 = '"
		    + b.getDescription2() + "', saleprice = "
		    + b.getSalePrice() + ", purchaseprice = "
		    + b.getPurchasePrice() + ", manufacturer = '"
		    + b.getManufacturerName() + "', quantity = "
		    + b.getQuantity() + ", totalpurchaseprice = "
		    + b.getTotalPurchasePrice() + ", totalprice = "
		    + b.getTotalprice() + " where sale_id = " + b.getSaleId()
		    + ";";
	    System.out.println("Query Executed: " + query);
	    Logger.getGlobal().fine("Query Executed: " + query);
	    st.execute(query);
	} catch (SQLException e) {
	    Logger.getGlobal().severe(
		    "Error occured while updating - customer<p> "
			    + e.getMessage());
	    System.out.println("SQLException: " + e.getMessage());
	    e.printStackTrace();
	    throw new Exception("Error! Unable to update - customer. <p>"
		    + e.getMessage());
	} finally {
	    try {
		if (conn != null) {
		    conn.close();
		}
		if (stmt != null) {
		    stmt.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while updating - customer<p> "
				+ e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
		throw new Exception("Error . Unable to update - customer.<p> "
			+ e1.getMessage());
	    }
	}
    }
}