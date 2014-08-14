package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

import model.Product;

public class ProductHandler {
    private DbConnection db = null;

    public ProductHandler() {

    }

    public Vector<Integer> getAllProductsIds() throws Exception {
	Vector<Integer> ids = null;
	db = DbConnection.getInstance();

	Connection con = db.getConnection();

	Statement stmt = null;

	if (con == null) {
	    throw new Exception("Unable to connect to the database!");
	}
	try {
	    stmt = con.createStatement();
	    String query = "SELECT PRODUCT_ID FROM PRODUCT";
	    System.out.println("Query Executed: " + query);
	    ResultSet rs = stmt.executeQuery(query);

	    if (rs != null) {
		ids = new Vector<Integer>();
		while (rs.next()) {
		    ids.add(rs.getInt("PRODUCT_ID"));
		}
	    }
	} catch (Exception e1) {
	    Logger.getGlobal().severe(
		    "Unable to retrieve Category ids from the database. "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Unable to retrieve Category ids from the database!<p>"
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
		product.setProductId(rs.getInt("PRODUCT_ID"));
		product.setProductName(rs.getString("P_NAME"));
		

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

    public void deleteCategory(int pId) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to get database connection");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("DELETE from PRODUCT where PRODUCT_ID = '"
			    + pId + "';");

	    stmt = (PreparedStatement) conn
		    .prepareStatement("DELETE from PRODUCT where PRODUCT_ID = "
			    + pId + ";");
	    stmt.executeUpdate();
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while deleting the Product: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception("Unable to delete Product: " + e1.getMessage());
	} finally {
	    try {
		DbConnection.getInstance();
		if (stmt != null) {
		    stmt.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while delete Product: "
				+ e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
		throw new SQLException(
			"Error occured while closing the connection or statement.");
	    }
	}
    }

    // public void saveProduct(int productId, String pName, String description1,
    // String description, int manufacturerId, int cetagoryId,
    // int warrantyId) throws Exception {
    //
    // DbConnection db = DbConnection.getInstance();
    // Connection conn = (Connection) db.getConnection();
    // PreparedStatement stmt = null;
    //
    // if (conn == null) {
    // throw new Exception("Unable to connect to the database");
    // }
    // try {
    // stmt = (PreparedStatement) conn
    // .prepareStatement("INSERT INTO PRODUCT(productId, pName, description1, description2, manufacturerId, cetagoryId, warrantyId) "
    // + " VALUES (?,?,?,?,?,?,?)");
    //
    // stmt.setInt(1, productId);
    // stmt.setString(2, pName);
    // stmt.setString(3, description1);
    // stmt.setString(4, description);
    // stmt.setInt(5, manufacturerId);
    // stmt.setInt(6, cetagoryId);
    // stmt.setInt(7, warrantyId);
    //
    // stmt.executeUpdate();
    //
    // System.out.println("Executing Query: " + stmt.toString());
    // } catch (SQLException e1) {
    // Logger.getGlobal().severe(
    // "Error occured while inserting product into product table: "
    // + e1.getMessage());
    // System.out.println("SQLException: " + e1.getMessage());
    // e1.printStackTrace();
    // throw new Exception(
    // "Error occured while inserting product into product table: "
    // + e1.getMessage());
    // } finally {
    // try {
    // DbConnection.getInstance();
    // if (stmt != null) {
    // stmt.close();
    // }
    // } catch (SQLException e1) {
    // Logger.getGlobal().severe(
    // "Error occured while closing the database conneciton: "
    // + e1.getMessage());
    // System.out.println("SQLException: " + e1.getMessage());
    // e1.printStackTrace();
    // throw new Exception(
    // "Error occured while closing the database conneciton: "
    // + e1.getMessage());
    // }
    // }
    // }

    public void saveProduct(Product p) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO PRODUCT(p_name, description1, description2, manufacturer_name, cetagory_name, warranty) "
			    + " VALUES (?,?,?,?,?,?)");

	    stmt.setString(1, p.getProductName());
	    stmt.setString(2, p.getDescription1());
	    stmt.setString(3, p.getDescription2());
	    stmt.setString(4, p.getManufacturerName());
	    stmt.setString(5, p.getCategoryName());
	    stmt.setInt(6, p.getWarranty());

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

    public void deleteProduct(int productID) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to get database connection");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("DELETE from product where product_ID = "
			    + productID + ";");

	    stmt.executeUpdate();
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while deleting the Category: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception("Unable to delete category: " + e1.getMessage());
	} finally {
	    try {
		DbConnection.getInstance();
		if (stmt != null) {
		    stmt.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while delete Category: "
				+ e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
		throw new SQLException(
			"Error occured while closing the connection or statement.");
	    }
	}
    }

    public Vector<String> getProductNames() throws Exception {
	Vector<String> names = null;
	db = DbConnection.getInstance();

	Connection con = db.getConnection();

	Statement stmt = null;

	if (con == null) {
	    throw new Exception("Unable to connect to the database!");
	}
	try {
	    stmt = con.createStatement();
	    String query = "SELECT p_name FROM PRODUCT";
	    System.out.println("Query Executed: " + query);
	    ResultSet rs = stmt.executeQuery(query);

	    if (rs != null) {
		names = new Vector<String>();
		while (rs.next()) {
		    names.add(rs.getString("p_name"));
		}
	    }
	} catch (Exception e1) {
	    Logger.getGlobal().severe(
		    "Unable to retrieve Category ids from the database. "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Unable to retrieve Category ids from the database!<p>"
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
	return names;
    }
}