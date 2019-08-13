package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import OCSF.ConnectionToClient;

/**
 * The Class UserMethods for all the methods that required 
 * to extract(/update) data from(/in) the database for(/by) the  shared methods for all the users.
 */
public class UserMethods
{
	
	/**
	 * get the user info from DB
	 * @param con the DB connection
	 * @param arr the details for getting user info
	 * @param client the current client
	 * @return the user info
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> UserInfo(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("UserInfo");
		Statement stmt;
		String Role = "";
		String[] str = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(str[1].equals(rs.getString(1)))// If the teacher was found in the database update it's teaching unit.
				{
					Result.add(rs.getString(1)); // User ID
					Role = rs.getString(2); // Getting Role ID
					Result.add(rs.getString(3)); // First Name
					Result.add(rs.getString(4)); // Last Name
					Result.add(rs.getString(6)); // Password
					Result.add(rs.getString(9)); // Phone Number
					Result.add(rs.getString(10)); // Address
					Result.add(rs.getString(5)); // Birth Date
					break;
				}
			}
			rs.close();
			ResultSet rsrole = stmt.executeQuery("SELECT * FROM roles;");
			while(rsrole.next())
			{
				if(rsrole.getString(1).equals(Role))
				{
					Result.add(rsrole.getString(2)); // Role Name
					break;
				}
			}
			rsrole.close();
			if(Role.equals("Teacher"));
			{
				ResultSet rshours = stmt.executeQuery("SELECT * FROM teachers;");
				while(rshours.next())
				{
					if(rshours.getString(1).equals(Result.get(1)))
					{
						Result.add(rshours.getString(2)); // Max Hours for Teacher
						break;
					}
				}
				rshours.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * get student info from data base, check if the id is an student id
	 * @param con the DB connection
	 * @param arr the details for getting student info
	 * @param client the current client
	 * @return the student info
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> StudentInfo(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("StudentInfo");
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			boolean exist = false;
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
				{
					if(rs.getString(2).equals("5"))
					{
						Result.add(rs.getString(1)); // User ID
						Result.add(rs.getString(3)); // First Name
						Result.add(rs.getString(4)); // Last Name
						Result.add(rs.getString(6)); // Password
						Result.add(rs.getString(9)); // Phone Number
						Result.add(rs.getString(10)); // Address
						Result.add(rs.getString(5)); // Birth Date
						exist = true;
						break;
					}
					else
					{
						Result.add(rs.getString(3) + " "  + rs.getString(4) + " is not a student");
						exist = true;
						break;
					}
				}
			}
			rs.close();
			if(!exist)
				Result.add(((ArrayList<String>) arr).get(1) + " doesn't exist in the database");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * change user password
	 * @param con the DB connection
	 * @param arr the details of the user
	 * @param client the current client
	 * @return the message of success or fail
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ChangePass(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ChangePass");
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
					if(rs.getString(6).equals(((ArrayList<String>) arr).get(2)))
					{
						PreparedStatement updateUnit = con.prepareStatement( "UPDATE users SET Password = ? WHERE UID = ?");
						updateUnit.setString(1,((ArrayList<String>) arr).get(3));
						updateUnit.setString(2,((ArrayList<String>) arr).get(1));
						updateUnit.executeUpdate();
						updateUnit.close();
						Result.add("Password Changed Successfully");
					}
					else
						Result.add("Current Password is Wrong");
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
}
