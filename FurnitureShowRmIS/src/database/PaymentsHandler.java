package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JComboBox;

import model.Category;

public class PaymentsHandler {
    private DbConnection db = null;

    public PaymentsHandler() {

    }

//
//	public Vector<String> getmanufactureNames() throws SQLException {
//		Vector<String> names = null;
//		DbConnection db = DbConnection.getInstance();
//		Connection conn = db.getConnection();
//
//		String query = "Select m_name from PAYMENTS;";
//		Statement st = null;
//		try {
//			st = conn.createStatement();
//			ResultSet rs = st.executeQuery(query);
//			if (rs != null) {
//				names = new Vector<String>();
//				while (rs.next()) {
//					names.add(rs.getString("m_name"));
//				}
//			}
//		} catch (SQLException e1) {
//			Logger.getGlobal().severe(
//					"Error occured while retrieving manufacturer names: "
//							+ e1.getMessage());
//			System.out.println("SQLException: " + e1.getMessage());
//			e1.printStackTrace();
//			throw new SQLException(
//					"Error occured while retrieving manufactrer names"
//							+ e1.getMessage());
//		} finally {
//			try {
//				DbConnection.closeConnection();
//				if (st != null) {
//					st.close();
//				}
//			} catch (SQLException e1) {
//				Logger.getGlobal().severe(
//						"Error occured while closing the database connection: "
//								+ e1.getMessage());
//				System.out.println("SQLException: " + e1.getMessage());
//				e1.printStackTrace();
//				throw new SQLException(
//						"Error occured while closing the database connection"
//								+ e1.getMessage());
//			}
//		}
//		return names;
//	}


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
		
}
