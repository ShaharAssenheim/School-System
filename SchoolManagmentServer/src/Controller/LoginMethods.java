package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import OCSF.ConnectionToClient;

/**
 * The Class LoginMethods to connect the user to the system,logout the user from system
 * and reset password.
 */
public class LoginMethods
{
	
	/**  flag. */
	public static int flag = 0;
	
	/**
	 * connect the user to the system
	 * @param con the DB connection
	 * @param arr the message from client
	 * @param client the current client
	 * @return the answer if connection Succeeded or failed
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> Login(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("Login");
		Statement stmt;
		String[] str = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			if(str[1].equals("") && str[2].equals(""))
				Result.add("Enter username and password");
			else
				while(rs.next())
				{
					if(str[1].equals(rs.getString(1)))
						if(str[2].equals(rs.getString(6)))
							if(rs.getString(7).equals("0"))
							{
								PreparedStatement updateUnit = con.prepareStatement( "UPDATE users " +  "SET Connected = ? " + "WHERE UID = ?");
								updateUnit.setString(1,"1");
								updateUnit.setString(2,((ArrayList<String>) arr).get(1));
								updateUnit.executeUpdate();
								Result.add("Pass");
								Result.add(rs.getString(2));
								Result.add(rs.getString(3));
								Server2Controller t = (Server2Controller)Server2Controller.last;
							    t.AddDisplayDevice(client);
								t.PrintToLog(client + " logged-in to the system");
								break;
							}
							else
							{
								Result.add("Logged-in in another device");
								break;
							}
						else
						{
							Result.add("Wrong Password");
							break;
						}
				}
			if(Result.size() == 1)
			{
				Result.add("Invalid username");
			}
			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * logout the user from system
	 * @param con the DB connection
	 * @param arr the message from client
	 * @param client the current client
	 */
	@SuppressWarnings("unchecked")
	public static void Logout(Connection con, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(str[1].equals(rs.getString(1)) && rs.getString(7).equals("1"))
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE users " +  "SET Connected = ? " + "WHERE UID = ?");
					updateUnit.setString(1,"0");
					updateUnit.setString(2,((ArrayList<String>) arr).get(1));
					updateUnit.executeUpdate();
					Server2Controller t = (Server2Controller)Server2Controller.last;
				    t.DelDisplayDevice(client);
					t.PrintToLog(client + " logged-out from the system");
					break;
				}
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
	}
	
	/**
	 * get the email address of the user from DB, to send the password to email
	 * @param con the DB connection
	 * @param arr the message from client
	 * @param client the current client
	 * @return the user name and password
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ForgotPass(Connection con, Object arr, ConnectionToClient client)
	{
		int flag=0;
		Statement stmt;
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ForgotPass");
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE UID = "+((ArrayList<String>)arr).get(1)+" AND Email IS NOT NULL");
			while(rs.next())
			{
					Result.add(rs.getString(11));//email
					Result.add(rs.getString(6));//password
					Result.add(rs.getString(3));//name
					flag=1;
			}
			rs.close();
			if(flag==0)
				Result.add("no mail");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
}
