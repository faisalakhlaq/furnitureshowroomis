package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JComboBox;

import model.Category;
import model.Payments;

public class PaymentsHandler {
    private DbConnection db = null;

    public PaymentsHandler() {

    }

    //
    // public Vector<String> getmanufactureNames() throws SQLException {
    // Vector<String> names = null;
    // DbConnection db = DbConnection.getInstance();
    // Connection conn = db.getConnection();
    //
    // String query = "Select m_name from PAYMENTS;";
    // Statement st = null;
    // try {
    // st = conn.createStatement();
    // ResultSet rs = st.executeQuery(query);
    // if (rs != null) {
    // names = new Vector<String>();
    // while (rs.next()) {
    // names.add(rs.getString("m_name"));
    // }
    // }
    // } catch (SQLException e1) {
    // Logger.getGlobal().severe(
    // "Error occured while retrieving manufacturer names: "
    // + e1.getMessage());
    // System.out.println("SQLException: " + e1.getMessage());
    // e1.printStackTrace();
    // throw new SQLException(
    // "Error occured while retrieving manufactrer names"
    // + e1.getMessage());
    // } finally {
    // try {
    // DbConnection.closeConnection();
    // if (st != null) {
    // st.close();
    // }
    // } catch (SQLException e1) {
    // Logger.getGlobal().severe(
    // "Error occured while closing the database connection: "
    // + e1.getMessage());
    // System.out.println("SQLException: " + e1.getMessage());
    // e1.printStackTrace();
    // throw new SQLException(
    // "Error occured while closing the database connection"
    // + e1.getMessage());
    // }
    // }
    // return names;
    // }

    public Vector<String> getManufacturerNames() {
	Vector<String> names = null;
	DbConnection db = DbConnection.getInstance();
	Connection conn = db.getConnection();

	String query = "Select m_name from PAYMENTS;";
	Statement st = null;
	try {
	    st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    if (rs != null) {
		names = new Vector<String>();
		while (rs.next()) {
		    names.add(rs.getString("m_name"));
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
		// TODO Auto-generated catch block
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
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
	return names;
    }

    public void savePayments(Payments p) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO PAYMENTS(m_Name,DESCRIPTION, total_Bill, AMOUNT_PAID,   BALANCE, date ) "
			    + " VALUES (?,?,?,?,?,? )");
	    java.sql.Date d = new java.sql.Date(p.getDate().getTime());
	    stmt.setString(1, p.getManufacturerName());
	    stmt.setString(2, p.getDescription());
	    stmt.setDouble(3, p.getTotalBill());
	    stmt.setDouble(4, p.getPaymentAmount());

	    stmt.setDouble(5, p.getBalance());
	    stmt.setDate(6, d);

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

}
