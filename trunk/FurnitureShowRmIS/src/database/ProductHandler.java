package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import model.Product;

public class ProductHandler {
    private DbConnection db = null;

    public ProductHandler() {

    }

    public Product searchProduct(int id) throws Exception {
	Product product = null;
	db = DbConnection.getInstance();

	Connection conn = db.getConnection();

	PreparedStatement st = null;

	if (conn == null) {
	    throw new Exception(
		    "Unable to connect to the database. conection = null!");
	}
	try {
	    String query = "Select * from product where product_id = ?";
	    st = conn.prepareStatement(query);
	    st.setInt(1, id);
	    System.out.println("Executed Query: " + query + id);
	    ResultSet rs = st.executeQuery();
	    if (rs.next()) {
		product = new Product();
		product.setProductId(rs.getInt("product id"));
		product.setpName(rs.getString("P Name"));
		product.setDescription1(rs.getString("Description1"));
		product.setDescription2(rs.getString("Description2"));
		product.setManufacturerId(rs.getInt("Manufacturer Id"));
		product.setCategoryId(rs.getInt("Category Id"));
		product.setWarrantyId(rs.getInt("Warranty Id"));

	    }
	} catch (Exception e1) {
	    Logger.getGlobal().severe(
		    "Unable to retrieve product from the database: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception("Unable to retrieve product from the database!"
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
	return product;
    }

    public void saveProduct(int productId, String pName, String description1,
	    String description, int manufacturerId, int cetagoryId,
	    int warrantyId) throws Exception {

	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO PRODUCT(productId, pName, description1, description2, manufacturerId, cetagoryId, warrantyId) "
			    + " VALUES (?,?,?,?,?,?,?)");

	    stmt.setInt(1, productId);
	    stmt.setString(2, pName);
	    stmt.setString(3, description1);
	    stmt.setString(4, description);
	    stmt.setInt(5, manufacturerId);
	    stmt.setInt(6, cetagoryId);
	    stmt.setInt(7, warrantyId);

	    stmt.executeUpdate();

	    System.out.println("Executing Query: " + stmt.toString());
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while inserting product into product table: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Error occured while inserting product into product table: "
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

    public void saveProduct(Product p) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO PRODUCT(productId, pName, description1, description2, manufacturerId, cetagoryId, warrantyId) "
			    + " VALUES (?,?,?,?,?,?,?)");

	    stmt.setString(1, p.getpName());
	    stmt.setString(2, p.getDescription1());
	    stmt.setString(3, p.getDescription2());
	    stmt.setInt(4, p.getManufacturerId());
	    stmt.setInt(5, p.getCategoryId());
	    stmt.setInt(6, p.getWarrantyId());

	    stmt.executeUpdate();

	    System.out.println("Executing Query: " + stmt.toString());
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while inserting product into product table: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Error occured while inserting product into product table: "
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