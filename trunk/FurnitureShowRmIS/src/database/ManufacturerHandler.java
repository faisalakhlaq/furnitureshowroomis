package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import model.Manufacturer;

public class ManufacturerHandler {
    private DbConnection db = null;

    public ManufacturerHandler() {

    }

    public Manufacturer searchManufacturer(int id) throws Exception {
	Manufacturer manufacturer = null;
	db = DbConnection.getInstance();

	Connection conn = db.getConnection();

	PreparedStatement st = null;

	if (conn == null) {
	    throw new Exception(
		    "Unable to connect to the database. conection = null!");
	}
	try {
	    String query = "Select * from manufacturer where manufacturer_id = ?";
	    st = conn.prepareStatement(query);
	    st.setInt(1, id);
	    System.out.println("Executed Query: " + query + id);
	    ResultSet rs = st.executeQuery();

	    if (rs.next()) {
		manufacturer = new Manufacturer();
		manufacturer.setmId(rs.getInt("manufacturer_id"));
		manufacturer.setmName(rs.getString("M_name"));
		manufacturer.setContactPerson1(rs.getString("Contact_Person1"));
		manufacturer.setContactPerson2(rs.getString("Contact_Person2"));
		manufacturer.settNumber(rs.getInt("T_number"));
		manufacturer.setCellNumber(rs.getInt("Cell_number"));
		manufacturer.setAddress(rs.getString("Address"));
		manufacturer.setWeb(rs.getString("Web"));
		manufacturer.setAccountNumber(rs.getString("Account_number"));
	    }
	} catch (Exception e1) {
	    Logger.getGlobal().severe(
		    "Unable to retrieve manufacturer from the database: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Unable to retrieve manufacturer from the database!"
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
	return manufacturer;
    }

    public void saveManufacturer(int mId, String mName, String contactPerson1,
	    String contactPerson2, int tNumber, int cellNumber, String address,
	    String web, String accountNumber) throws Exception {

	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO MANUFACTURER(mId, mName, contactPerson1, contactPerson2, tNumber, cellNumber, address, web, accountNumber) "
			    + " VALUES (?,?,?,?,?,?,?,?,?)");

	    stmt.setInt(1, mId);
	    stmt.setString(2, mName);
	    stmt.setString(3, contactPerson1);
	    stmt.setString(4, contactPerson2);
	    stmt.setInt(5, tNumber);
	    stmt.setInt(6, cellNumber);
	    stmt.setString(7, address);
	    stmt.setString(8, web);
	    stmt.setString(9, accountNumber);

	    stmt.executeUpdate();

	    System.out.println("Executing Query: " + stmt.toString());
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while inserting manufacturer into manufacturer table: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Error occured while inserting manufacturer into manufacturer table: "
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

    public void saveManufacturer(Manufacturer m) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO MANUFACTURER(m_name, contact_person1, contact_person2, t_number, cell_number, address, web, account_number) "
			    + " VALUES (?,?,?,?,?,?,?,?)");

	    stmt.setString(1, m.getmName());
	    stmt.setString(2, m.getContactPerson1());
	    stmt.setString(3, m.getContactPerson2());
	    stmt.setInt(4, m.gettNumber());
	    stmt.setInt(5, m.getCellNumber());
	    stmt.setString(6, m.getAddress());
	    stmt.setString(7, m.getWeb());
	    stmt.setString(8, m.getAccountNumber());

	    stmt.executeUpdate();

	    System.out.println("Executing Query: " + stmt.toString());
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while inserting manufacturer into manufacturer table: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Error occured while inserting manufacturer into manufacturer table: "
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