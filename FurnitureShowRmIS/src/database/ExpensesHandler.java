package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import model.Expenses;

public class ExpensesHandler {
    private DbConnection db = null;

    public ExpensesHandler() {
    }

    public Vector<Integer> getAllExpensesIds() throws Exception {
	Vector<Integer> ids = null;
	db = DbConnection.getInstance();

	Connection con = db.getConnection();

	Statement stmt = null;

	if (con == null) {
	    throw new Exception("Unable to connect to the database!");
	}
	try {
	    stmt = con.createStatement();
	    String query = "SELECT EXPENSE_ID FROM expenses";
	    System.out.println("Query Executed: " + query);
	    ResultSet rs = stmt.executeQuery(query);

	    if (rs != null) {
		ids = new Vector<Integer>();
		while (rs.next()) {
		    ids.add(rs.getInt("EXPENSE_ID"));
		}
	    }
	} catch (Exception e1) {
	    Logger.getGlobal().severe(
		    "Unable to retrieve expenses ids from the database. "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Unable to retrieve expenses ids from the database!<p>"
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

    public Expenses searchExpenses(int id) throws Exception {
	Expenses expense = null;
	db = DbConnection.getInstance();

	Connection conn = db.getConnection();

	PreparedStatement st = null;

	if (conn == null) {
	    throw new Exception(
		    "Unable to connect to the database. conection = null!");
	}
	try {
	    String query = "Select * from expenses where expense_id = ?";
	    st = conn.prepareStatement(query);
	    st.setInt(1, id);
	    System.out.println("Executed Query: " + query + id);
	    ResultSet rs = st.executeQuery();

	    if (rs.next()) {
		expense = new Expenses();
		expense.setId(id);
		expense.setTitle(rs.getString("title"));
		expense.setDescription(rs.getString("description"));
		expense.setAmount(rs.getInt("amount"));
		expense.setDate(rs.getDate("date"));
	    }
	} catch (Exception e1) {
	    Logger.getGlobal().severe(
		    "Unable to retrieve expenses from the database: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Unable to retrieve expenses from the database!"
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
	return expense;
    }

    @SuppressWarnings("null")
    public void saveExpenses(String title, String desc, String amount,
	    java.util.Date date) {

	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	JOptionPane inserted = null;

	if (conn == null) {
	    inserted.setValue("Cannot get a connection to the database");
	    return;
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO expenses(title, description, amount,date) "
			    + "VALUES (?,?,?,?)");

	    java.sql.Date d = new java.sql.Date(date.getTime());

	    stmt.setString(1, title);
	    stmt.setString(2, desc);
	    stmt.setString(3, amount);

	    stmt.setDate(4, d);

	    stmt.executeUpdate();
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while adding the area code: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    inserted.setValue("Unable to add area code . " + e1.getMessage());
	} finally {
	    try {
		DbConnection.getInstance();
		if (stmt != null) {
		    stmt.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while inserting the area code: "
				+ e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
	    }
	}
    }

    public void deleteExpenses(int eId) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to get database connection");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("DELETE from expenses where EXPENSE_ID = '"
			    + eId + "';");

	    stmt = (PreparedStatement) conn
		    .prepareStatement("DELETE from expenses where EXPENSE_ID = "
			    + eId + ";");
	    stmt.executeUpdate();
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while deleting the expenses: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception("Unable to delete expenses: " + e1.getMessage());
	} finally {
	    try {
		DbConnection.getInstance();
		if (stmt != null) {
		    stmt.close();
		}
	    } catch (SQLException e1) {
		Logger.getGlobal().severe(
			"Error occured while delete expenses: "
				+ e1.getMessage());
		System.out.println("SQLException: " + e1.getMessage());
		e1.printStackTrace();
		throw new SQLException(
			"Error occured while closing the connection or statement.");
	    }
	}
    }

    public void saveExpenses(Expenses e) throws Exception {
	DbConnection db = DbConnection.getInstance();
	Connection conn = (Connection) db.getConnection();
	PreparedStatement stmt = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database");
	}
	try {
	    stmt = (PreparedStatement) conn
		    .prepareStatement("INSERT INTO expenses(title, description, amount,date) "
			    + "VALUES (?,?,?,?)");

	    stmt.setString(1, e.getTitle());
	    stmt.setString(2, e.getDescription());
	    stmt.setInt(3, e.getAmount());
	    stmt.setDate(4, (Date) e.getDate());

	    stmt.executeUpdate();

	    System.out.println("Executing Query: " + stmt.toString());
	} catch (SQLException e1) {
	    Logger.getGlobal().severe(
		    "Error occured while inserting expenses into Expenses table: "
			    + e1.getMessage());
	    System.out.println("SQLException: " + e1.getMessage());
	    e1.printStackTrace();
	    throw new Exception(
		    "Error occured while inserting expenses into expenses table: "
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

    public void updateExpenses(Expenses b) throws Exception {
	db = DbConnection.getInstance();
	Connection conn = db.getConnection();
	PreparedStatement stmt = null;
	Statement st = null;

	if (conn == null) {
	    throw new Exception("Unable to connect to the database!");
	}
	try {
	    st = conn.createStatement();
	    java.util.Date d = b.getDate();
	    java.sql.Date date = new java.sql.Date(d == null ? null
		    : d.getTime());
	    String query = "Update sales set date = '" + date + "', title = '"
		    + b.getTitle() + "', description = '" + b.getDescription()
		    + "', amount = '" + b.getAmount() + "', EXPENSE_ID = "
		    + b.getId() + ",  = "

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
