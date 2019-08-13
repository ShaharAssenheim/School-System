package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import OCSF.ConnectionToClient;

/**
 * The Class SystemManagerMethods for all the methods that required 
 * to extract(/update) data from(/in) the database for(/by) the methods from system manager controller.
 * responsible for add and edit course in DB.
 * @author shachar & Mohamad
 */
public class SystemManagerMethods
{
	
	/**
	 * get all the teaching units from DB
	 * @param con the DB connection
	 * @param arr the message from client
	 * @param client the current client
	 * @return the message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> Teachunit(Connection con, Object arr, ConnectionToClient client)//ViewTeachingUnit
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add(((ArrayList<String>)arr).get(0));
		Statement stmt;
		try {
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM teaching_units;");
			
			while(rs.next())
			{
				Result.add(rs.getString(1));
				Result.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Result;
	}
	
	/**
	 * add course to DB
	 * @param con the DB connection
	 * @param arr the message from client
	 * @param client the current client
	 * @return the message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String>AddCourse(Connection con, Object arr, ConnectionToClient client)//AddCourse
	{
		int flag=0;
		Statement stmt;
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("AddCourse");
		String Pre="";
		Pre=((ArrayList<String>) arr).get(5);
		int j=0,flag2=0;
		List<String> PreCourse = new ArrayList<String>(Arrays.asList(Pre.split(",")));
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses;");
			while(rs.next())
			{
				if((((ArrayList<String>) arr).get(1)).equals(rs.getString(1))||(((ArrayList<String>) arr).get(2)).toUpperCase().equals(rs.getString(2).toUpperCase()))
				{
					Result.add("The Course Name Or Number Already Exist!!!");
					flag=1;
					break;
				}
			}
			rs.close();
			if(flag==0)
			{
				PreparedStatement updateUnit = con.prepareStatement( "INSERT INTO courses ( cnum ,CourseName ,teachunit, WeakHours )VALUES(?,? ,?, ?)");
				updateUnit.setString(1,((ArrayList<String>) arr).get(1));
				updateUnit.setString(2,((ArrayList<String>) arr).get(2));
				updateUnit.setString(3,((ArrayList<String>) arr).get(3));
				updateUnit.setString(4,((ArrayList<String>) arr).get(4));
				updateUnit.executeUpdate();
				updateUnit.close();	
				Result.add("The Course Added Succefuly!!!");
				flag2=1;
			}
				if(flag2==1&&!((ArrayList<String>) arr).get(5).equals("No-Pre"))
				{
					for(j=0;j<PreCourse.size();j++)
					{
						PreparedStatement updateUnit = con.prepareStatement( "INSERT INTO course_pre ( cnum, CourseName ,PreCourse)VALUES(?,?,?)");
						updateUnit.setString(1,((ArrayList<String>) arr).get(1));
						updateUnit.setString(2,((ArrayList<String>) arr).get(2));
						updateUnit.setString(3,PreCourse.get(j));
						updateUnit.executeUpdate();
						updateUnit.close();	
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Result;	
	}
	
	/**
	 * get all the course details from DB
	 * @param con the DB connection
	 * @param arr the message from client
	 * @param client the current client
	 * @return the message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewCourse(Connection con, Object arr, ConnectionToClient client)//ViewCourse
	{
		ArrayList<String> Result = new ArrayList<String>();
		Statement stmt;
		int flag=0;
		try {	
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses;");
			Result.clear();
			Result.add("ViewCourse1");
			while(rs.next())
			{
				if(((ArrayList<String>) arr).size() == 5)//Num and Number in search
				{
						if((((ArrayList<String>) arr).get(2)).equals(rs.getString(1)) && (((ArrayList<String>) arr).get(4)).toUpperCase().equals(rs.getString(2).toUpperCase()))
						{
							Result.add(rs.getString(1));
							Result.add(rs.getString(2));
							Result.add(rs.getString(3));
							Result.add(rs.getString(4));
							flag=1;
							break;
						}		
				}
				else
					if((((ArrayList<String>) arr).get(2)).equals(rs.getString(1)) && (((ArrayList<String>) arr).get(1)).equals("Num"))
					{
						Result.add(rs.getString(1));
						Result.add(rs.getString(2));
						Result.add(rs.getString(3));
						Result.add(rs.getString(4));
						//Result.add(rs.getString(5));
						flag=1;
						break;
					}
					else
						if((((ArrayList<String>) arr).get(2)).toUpperCase().equals(rs.getString(2).toUpperCase()) && (((ArrayList<String>) arr).get(1)).equals("Name"))
					{
						Result.add(rs.getString(1));
						Result.add(rs.getString(2));
						Result.add(rs.getString(3));
						Result.add(rs.getString(4));
						//Result.add(rs.getString(5));
						flag=1;
						break;
					}		
			}
			if(flag==0)
			{
				Result.add("Course Not Found In DB!!!");
			}
			else if(flag==1)
			{
				String Course="";
				String PreCourse="";
				Course=rs.getString(2);
				rs = stmt.executeQuery("SELECT * FROM course_pre;");
				while(rs.next())
				{
					if(rs.getString(2).equals(Course))
					{
						PreCourse+=rs.getString(3)+",";
					}
				}
				if(!PreCourse.isEmpty())
					Result.add(PreCourse.substring(0, PreCourse.length()-1));
				else
					Result.add("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Result;
	}
	
	/**
	 * update course details
	 * @param con the DB connection
	 * @param arr the message from client
	 * @param client the current client
	 * @return the message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> UpdateCourse(Connection con, Object arr, ConnectionToClient client)
	{
		int flag=0;
		Statement stmt;
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("UpdateCourse");
		String Pre="";
		Pre=((ArrayList<String>) arr).get(7);
		int j=0,flag2=0;
		List<String> PreCourse = new ArrayList<String>(Arrays.asList(Pre.split(",")));
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses;");
			while(rs.next())
			{
				if(((ArrayList<String>) arr).get(2).equals(rs.getString(1))&&!(((ArrayList<String>) arr).get(2).equals(((ArrayList<String>) arr).get(1))))
				{
					flag=1;
					Result.add("The Course Number is Used!!!");
					break;
				}
			}
			 rs = stmt.executeQuery("SELECT * FROM courses;");
			while(rs.next()&&flag==0)
			{
				if(((ArrayList<String>) arr).get(4).toUpperCase().equals(rs.getString(2).toUpperCase())&&!(((ArrayList<String>) arr).get(3).equals(((ArrayList<String>) arr).get(4))))
				{
					flag=1;
					Result.add("The Course Name is Used!!!");
					break;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM courses;");
			while(rs.next()&&flag==0)
			{
					if(((ArrayList<String>) arr).get(1).equals(rs.getString(1)))
					{
						PreparedStatement updateUnit = con.prepareStatement( "UPDATE courses SET cnum  = ? ,CourseName = ?, WeakHours = ?, teachunit = ? WHERE cnum = ? ");
						updateUnit.setString(1,((ArrayList<String>) arr).get(2));
						updateUnit.setString(2,((ArrayList<String>) arr).get(4));
						updateUnit.setString(3,((ArrayList<String>) arr).get(6));
						updateUnit.setString(4,((ArrayList<String>) arr).get(5));
						updateUnit.setString(5,((ArrayList<String>) arr).get(1));
						updateUnit.executeUpdate();
						Result.add("The Course Updated Succefuly!!!");
						flag2=1;
						break;
					}
			}
			if(flag2==1)
			{
				rs = stmt.executeQuery("SELECT * FROM course_pre WHERE cnum = "  + ((ArrayList<String>) arr).get(1));
				while(rs.next())
				{
					PreparedStatement updateUnit = con.prepareStatement(" DELETE FROM course_pre WHERE cnum = "  + ((ArrayList<String>) arr).get(1));
					updateUnit.executeUpdate();
					updateUnit.close();	
				}
				if(!((ArrayList<String>) arr).get(7).equals("No-Pre"))
				for(j=0;j<PreCourse.size();j++)
				{
					PreparedStatement updateUnit = con.prepareStatement( "INSERT INTO course_pre ( cnum, CourseName ,PreCourse)VALUES(?,?,?)");
					updateUnit.setString(1,((ArrayList<String>) arr).get(2));
					updateUnit.setString(2,((ArrayList<String>) arr).get(4));
					updateUnit.setString(3,PreCourse.get(j));
					updateUnit.executeUpdate();
					updateUnit.close();	
				}
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Result;
	}
}
