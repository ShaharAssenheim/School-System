package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import OCSF.ConnectionToClient;

/**
 * The Class SchoolDirectorMethods for all the methods that required 
 * to extract(/update) data from(/in) the database for(/by) the methods from school director controller.
 * @author shachar & Mohamad
 */
public class SchoolDirectorMethods
{
	
	/**
	 * gets student info.
	 *
	 * @param con the DB connection
	 * @param arr student ID
	 * @param client the current client
	 * @return the student details in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> SDViewStdInfo(Connection con, Object arr, ConnectionToClient client)
	{
		String SemID = "";
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SDViewStdInfo");
		boolean exist = false, student = false, associated = false, notastudent = false;
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
					SemID = rs.getString(1);
			}
			rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
				{
					exist = true;
					if(rs.getString(2).equals("5"))
					{
						student = true;
						Result.add(rs.getString(1)); // User ID
						Result.add(rs.getString(3)); // First Name
						Result.add(rs.getString(4)); // Last Name
						Result.add(rs.getString(9)); // Phone Number
						Result.add(rs.getString(10)); // Address
						Result.add(rs.getString(5)); // Birth Date
					}
					else
					{
						notastudent = true;
						Result.add(rs.getString(3) + " " + rs.getString(4) + " is not a student");
					}
					break;
				}
			}
			if(student)
			{
				rs = stmt.executeQuery("SELECT * FROM student_in_a_class;");
				while(rs.next())
				{
					if(rs.getString(1).equals(Result.get(1)) && rs.getString(3).equals(SemID))
					{
						associated = true;
						Result.add(rs.getString(2));
					}
				}
			}
			if(!exist)
			{
				Result.add(((ArrayList<String>) arr).get(1) + " not exist in the database");
			}
			else if(!associated && !notastudent)
				Result.add("Not Associated");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets the classes names.
	 *
	 * @param con the DB connection
	 * @param arr the chosen semester
	 * @param client the current client
	 * @return the opened classes in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> GetClasses(Connection con, Object arr, ConnectionToClient client)
	{
		boolean flag = false;
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("GetClasses");
		Statement stmt;
		try 
		{
			String Semester = ((ArrayList<String>)arr).get(1);
			String SemID = "";
	    	String SemYear = "";	
	    	char SemNum = 0;
			int i = Semester.indexOf(",");
			if (i > 0) {
				SemYear = Semester.substring(0,i-1);
			}
			i = Semester.indexOf(".");
			if (i > 0) {
				SemNum = Semester.charAt(i-1);
			}
			SemID = SemID.concat(SemYear) + SemNum;
			
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			
			rs = stmt.executeQuery("SELECT * FROM classes;");
			while(rs.next())
				if(rs.getString(2).equals(SemID))
				{
					flag = true;
					Result.add(rs.getString(1));
				}
			rs.close();
			if(!flag)
				Result.add("No Classes Are Found");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Access block.
	 *
	 * @param con the DB connection
	 * @param arr parent, student and check box details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> AccessBlock(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("AccessBlock");
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM parent_student;");
			while(rs.next())
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)) && rs.getString(2).equals(((ArrayList<String>) arr).get(2)))
				{
					if(((ArrayList<String>) arr).get(3).equals("1"))
						if(rs.getString(3).equals("No"))
						{
							PreparedStatement updateUnit = con.prepareStatement( "UPDATE parent_student SET Blocked = ?  WHERE PID = ? AND SID = ?");
							updateUnit.setString(1,"Yes");
							updateUnit.setString(2,((ArrayList<String>) arr).get(1));
							updateUnit.setString(3,((ArrayList<String>) arr).get(2));
							updateUnit.executeUpdate();
							updateUnit.close();
							Result.add("User Blocked");
						}
						else
							Result.add("User Already Blocked");
					else
						if(((ArrayList<String>) arr).get(3).equals("0"))
							if(rs.getString(3).equals("No"))
								Result.add("User Already Unblocked");
							else
							{
								PreparedStatement updateUnit = con.prepareStatement( "UPDATE parent_student SET Blocked = ?  WHERE PID = ? AND SID = ?");
								updateUnit.setString(1,"No");
								updateUnit.setString(2,((ArrayList<String>) arr).get(1));
								updateUnit.setString(3,((ArrayList<String>) arr).get(2));
								updateUnit.executeUpdate();
								updateUnit.close();
								Result.add("User Unblocked");
							}	
				}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets the last 10 semesters.
	 *
	 * @param con the DB connection
	 * @param client the current client
	 * @return the last 10 semesters from the database in ArrayList to the client
	 */
	public static ArrayList<String> GetLast10Semesters(Connection con, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("GetLast10Semesters");
		Statement stmt;
		boolean flag = true;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			int i = 10;
			if(!rs.last())
				flag = false;
			while(flag && i > 0)
			{
				Result.add(rs.getString(5));
				Result.add(rs.getString(2));
				i--;
				if(!rs.previous())
					flag = false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Update exceptional student registration.
	 *
	 * @param con the DB connection
	 * @param client the current client
	 * @return the Exceptional Registration Requests in ArrayList to the client
	 */
	public static ArrayList<String> ViewExceptionalStudentRegistration(Connection con, ConnectionToClient client)
	{

		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewExceptionalStudentRegistration");
		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<String> details = new ArrayList<String>();
		String SemID="";
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
				{
					SemID = rs.getString(1);
					break;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM regrequests WHERE SemID = " + SemID);
			while(rs.next())
			{
				ids.add(rs.getString(5));
			}
			rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				for(int i=0;i<ids.size();i++)
				{
					if(ids.get(i).equals(rs.getString(1)))
					{
						details.add(rs.getString(1));
						details.add(rs.getString(3));
						details.add(rs.getString(4));
					}
				}
			}
			rs = stmt.executeQuery("SELECT * FROM regrequests WHERE SemID = " + SemID);
			while(rs.next())
			{
				for(int i=0;i<details.size();i=i+3)
				{
					if(details.get(i).equals(rs.getString(5)))
					{
						Result.add(rs.getString(1)); //Reqid
						Result.add(details.get(i));//studentid
						Result.add(details.get(i+1));//studentname
						Result.add(details.get(i+2));//studentlastname
						Result.add(rs.getString(2)); //CourseName
						Result.add(rs.getString(3)); //classname
						Result.add(rs.getString(4)); //reqtype
					    Result.add(rs.getString(6)); //status
					    break;
					}
				}
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Update appointing request.
	 *
	 * @param con the DB connection
	 * @param client the current client
	 * @return the Teacher Appointing Requests in ArrayList to the client
	 */
	public static ArrayList<String> ViewAppointREQ(Connection con, ConnectionToClient client)
	{

		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewAppointREQ");
		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<String> details = new ArrayList<String>();
		String SemID="";
		Statement stmt;
		try 
		{
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
				{
					SemID = rs.getString(1);
					break;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM changerequests WHERE SemID = " + SemID);
			while(rs.next())
			{
				ids.add(rs.getString(2));
			}
			
			rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				for(int i=0;i<ids.size();i++)
				{
					if(ids.get(i).equals(rs.getString(1)))
					{
						details.add(rs.getString(1));//uid
						details.add(rs.getString(3));//first name
						details.add(rs.getString(4));//last name
					}
				}
			}
			rs = stmt.executeQuery("SELECT * FROM changerequests WHERE SemID = " + SemID);
			while(rs.next())
			{
				for(int i=0;i<details.size();i=i+3)
				{
					if(details.get(i).equals(rs.getString(2)))
					{
						Result.add(rs.getString(1)); //Reqid
						Result.add(details.get(i));//teacherid
						Result.add(details.get(i+1));//teachername
						Result.add(details.get(i+2));//teacherlastname
						Result.add(rs.getString(3)); //classname
						Result.add(rs.getString(4)); //CourseName
						Result.add(rs.getString(5)); //teachingUnit
						Result.add(rs.getString(6)); //newteacher
					    Result.add(rs.getString(7)); //status
					    break;
					}
				}
					
				
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * update Rejected exceptional registration request.
	 *
	 * @param con the DB connection
	 * @param arr exceptional registration details
	 * @param client the current client
	 * @return alert message message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> RejectExpReg(Connection con, Object arr, ConnectionToClient client)
	{

		ArrayList<String> Result = new ArrayList<String>();
		Result.add("RejectExpReg");
		String SemID="";
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
				{
					SemID = rs.getString(1);
					break;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM regrequests WHERE SemID = " + SemID);
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(1))&&rs.getString(3).equals(((ArrayList<String>) arr).get(2))
						&&rs.getString(4).equals(((ArrayList<String>) arr).get(3))&&rs.getString(5).equals(((ArrayList<String>) arr).get(4)))
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE regrequests SET Status = ?  WHERE CourseName = ? AND ClassName = ? AND ReqType = ? AND StudentID = ? ");
					updateUnit.setString(1,"Reject");
					updateUnit.setString(2,((ArrayList<String>) arr).get(1));//courseName
					updateUnit.setString(3,((ArrayList<String>) arr).get(2));//classname
					updateUnit.setString(4,((ArrayList<String>) arr).get(3));//reqtype
					updateUnit.setString(5,((ArrayList<String>) arr).get(4));//studentid
					updateUnit.executeUpdate();
					updateUnit.close();
				}
			}
			Result.add("The Request Rejected");
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * update Approved exceptional registration request.
	 *
	 * @param con the DB connection
	 * @param arr exceptional registration details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ApproveExpReg(Connection con, Object arr, ConnectionToClient client)
	{
		String SemID="";
		String SemID11="";
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ApproveExpReg");
		Statement stmt;
		try 
		{
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
				{
					SemID11 = rs.getString(1);
					break;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM classes WHERE SemID = " + SemID11);
			while(rs.next())
			{
				
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(2)))
				{
					
					SemID=rs.getString(2);
					break;
				}
			}
			 rs = stmt.executeQuery("SELECT * FROM regrequests;");
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(1))&&rs.getString(3).equals(((ArrayList<String>) arr).get(2))
						&&rs.getString(4).equals(((ArrayList<String>) arr).get(3))&&rs.getString(5).equals(((ArrayList<String>) arr).get(4))&&rs.getString(7).equals(SemID))
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE regrequests SET Status = ?  WHERE CourseName = ? AND ClassName = ? AND ReqType = ? AND StudentID = ? ");
					updateUnit.setString(1,"Approve");
					updateUnit.setString(2,((ArrayList<String>) arr).get(1));//courseName
					updateUnit.setString(3,((ArrayList<String>) arr).get(2));//classname
					updateUnit.setString(4,((ArrayList<String>) arr).get(3));//reqtype
					updateUnit.setString(5,((ArrayList<String>) arr).get(4));//studentid
					updateUnit.executeUpdate();
					updateUnit.close();
				}
			}
			
			
			if(((ArrayList<String>) arr).get(3).equals("Deletion"))
			{
				rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
				while(rs.next())
				{
					if(rs.getString(1).equals(((ArrayList<String>) arr).get(4))&&
							rs.getString(2).equals(((ArrayList<String>) arr).get(1)))
					{
						PreparedStatement updateUnit = con.prepareStatement( "DELETE FROM student_in_a_course WHERE UID = ? AND CourseName = ?;");
						updateUnit.setString(1, ((ArrayList<String>)arr).get(4));
						updateUnit.setString(2, ((ArrayList<String>)arr).get(1));
						updateUnit.executeUpdate();
						updateUnit.close();
					}
				}
			}
			else if(((ArrayList<String>) arr).get(3).equals("Registration"))
			{
				PreparedStatement updateUnit = con.prepareStatement( "INSERT INTO student_in_a_course ( UID, CourseName, Grade, SemID, ClassID, ExceptionalReg) VALUES(?, ?, ?, ?, ?, ?)");
				  updateUnit.setString(1,((ArrayList<String>)arr).get(4));//student id
				  updateUnit.setString(2,((ArrayList<String>) arr).get(1));//coursname
				  updateUnit.setInt(3, 0);//grade
				  updateUnit.setString(4,SemID);//semester
				  updateUnit.setString(5,((ArrayList<String>) arr).get(2));//classname
				  updateUnit.setString(6,"1");//exceptional
				  updateUnit.executeUpdate();
				  updateUnit.close();
			}
			
			Result.add("The Request Approved");
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Update Rejected appointing change request.
	 *
	 * @param con the DB connection
	 * @param arr appointing change details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> RejectAppChng(Connection con, Object arr, ConnectionToClient client)
	{
		String SemID="";
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("RejectAppChng");
		Statement stmt;
		try 
		{
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
				{
					SemID = rs.getString(1);
					break;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM changerequests WHERE SemID = " + SemID);
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(1))&&rs.getString(3).equals(((ArrayList<String>) arr).get(2))
						&&rs.getString(4).equals(((ArrayList<String>) arr).get(3))&&rs.getString(6).equals(((ArrayList<String>) arr).get(4)))
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE changerequests SET Status = ?  WHERE OldTeacher = ? AND ClassName = ? AND CourseName = ? AND NewTeacher = ? AND SemID = ? ");
					updateUnit.setString(1,"Reject");
					updateUnit.setString(2,((ArrayList<String>) arr).get(1));//oldteacher
					updateUnit.setString(3,((ArrayList<String>) arr).get(2));//classname
					updateUnit.setString(4,((ArrayList<String>) arr).get(3));//coursename
					updateUnit.setString(5,((ArrayList<String>) arr).get(4));//newteacher
					updateUnit.setString(6,SemID);//newteacher
					updateUnit.executeUpdate();
					updateUnit.close();
				}
			}
			Result.add("The Request Rejected");
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Update Approved appointing change request.
	 *
	 * @param con the DB connection
	 * @param arr appointing change details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public static ArrayList<String> ApproveAppChng(Connection con, Object arr, ConnectionToClient client)
	{
		String SemID="";
		String SemID11="";
		int CourseWeekHours=0;
		int TactualHoursNEW=0;
		int TmaxWeekHoursNEW=0;
		int TactualHoursOLD=0;
		int TmaxWeekHoursOLD=0;
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ApproveAppChng");
		Statement stmt;
		try 
		{
			
			stmt = con.createStatement();
			if(((ArrayList<String>) arr).get(6).equals("Approve"))
			{
				Result.add("The Request  Allready Approved");
				return Result;
			}
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
				{
					SemID11 = rs.getString(1);
					break;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM classes WHERE SemID = " + SemID11);
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(2)))
				{
					
					SemID=rs.getString(2);
					break;
				}
			}
			 rs = stmt.executeQuery("SELECT * FROM changerequests WHERE SemID = " + SemID);
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(1))&&rs.getString(3).equals(((ArrayList<String>) arr).get(2))
						&&rs.getString(4).equals(((ArrayList<String>) arr).get(3))&&rs.getString(5).equals(((ArrayList<String>) arr).get(5))
						&&rs.getString(6).equals(((ArrayList<String>) arr).get(4)))
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE changerequests SET Status = ?  WHERE OldTeacher = ? AND ClassName = ? AND CourseName = ? AND TeachingUnit = ? AND NewTeacher = ? AND SemID = ? ");
					updateUnit.setString(1,"Approve");
					updateUnit.setString(2,((ArrayList<String>) arr).get(1));//oldteacher
					updateUnit.setString(3,((ArrayList<String>) arr).get(2));//classname
					updateUnit.setString(4,((ArrayList<String>) arr).get(3));//coursename
					updateUnit.setString(5,((ArrayList<String>) arr).get(5));//teachingunit
					updateUnit.setString(6,((ArrayList<String>) arr).get(4));//newteacher
					updateUnit.setString(7,SemID);//newteacher
					updateUnit.executeUpdate();
					updateUnit.close();
				}
			}
			rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course WHERE TID = " + ((ArrayList<String>) arr).get(1) + " AND SemID = "+ SemID);
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1))&&rs.getString(2).equals(((ArrayList<String>) arr).get(2))
						&&rs.getString(3).equals(((ArrayList<String>) arr).get(3))&&rs.getString(4).equals(SemID))
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE teacher_in_class_course SET TID = ?  WHERE TID = ? AND ClassID = ? AND CourseName = ? AND SemID = ?");
					updateUnit.setString(1,((ArrayList<String>) arr).get(4));//NEWTEACHER
					updateUnit.setString(2,((ArrayList<String>) arr).get(1));//oldteacher
					updateUnit.setString(3,((ArrayList<String>) arr).get(2));//classname
					updateUnit.setString(4,((ArrayList<String>) arr).get(3));//coursename
					updateUnit.setString(5,SemID);//SEMESTER
					updateUnit.executeUpdate();
					updateUnit.close();
					
				}
			}
			rs = stmt.executeQuery("SELECT * FROM courses;");
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(3)))
				{
					CourseWeekHours= Integer.parseInt(rs.getString(3));
				}
			}
			rs = stmt.executeQuery("SELECT * FROM teachers;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(4)))//newTeacher
				{
					TactualHoursNEW = Integer.parseInt(rs.getString(3));
					TmaxWeekHoursNEW = Integer.parseInt(rs.getString(2));
				}
				else if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))//oldTeacher
				{
					TactualHoursOLD = Integer.parseInt(rs.getString(3));
					TmaxWeekHoursOLD = Integer.parseInt(rs.getString(2));
				}
			}
			TactualHoursNEW=TactualHoursNEW+CourseWeekHours;
			TactualHoursOLD=TactualHoursOLD-CourseWeekHours;
			
			rs = stmt.executeQuery("SELECT * FROM teachers;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(4)))//newTeacher
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE teachers SET ActualHours = ?  WHERE UID = ? ");
					updateUnit.setString(1,String.valueOf(TactualHoursNEW));//NEWTEACHER
					updateUnit.setString(2,((ArrayList<String>) arr).get(4));//NEWTEACHER
					updateUnit.executeUpdate();
					updateUnit.close();
				}
				else if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))//oldTeacher
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE teachers SET ActualHours = ?  WHERE UID = ? ");
					updateUnit.setString(1,String.valueOf(TactualHoursOLD));//OldTEACHER
					updateUnit.setString(2,((ArrayList<String>) arr).get(1));//Oldteacher
					updateUnit.executeUpdate();
					updateUnit.close();
				}
			}
			
			Result.add("The Request Approved");
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets details for report Generation.
	 *
	 * @param con the DB connection
	 * @param arr report details
	 * @param client the current client
	 * @return the report information in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> GenerateReport(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		ArrayList<String> Result2 = new ArrayList<String>();
		ArrayList<String> Result3 = new ArrayList<String>();
		ArrayList<String> Semesters = new ArrayList<String>();
		int Grade = 0, GradeCnt = 0;
		int flag = 0, size = Integer.parseInt(((ArrayList<String>)arr).get(4));
	
		try 
		{
			Statement stmt5;
			stmt5 = con.createStatement();			
			ResultSet rs5 = stmt5.executeQuery("SELECT * FROM semesters;");
			while(rs5.next())
			{
				if(rs5.getString(1).equals(((ArrayList<String>)arr).get(1)))
				{
					Semesters.add(rs5.getString(1));
					int year = Integer.parseInt(rs5.getString(5));
					int semNum = Character.getNumericValue(rs5.getString(5).charAt(0));
					for(int i=0;i<size;i++)
					{
						if(semNum==2)
						{
							semNum = 1;
							String Sem = String.valueOf(year)+String.valueOf(semNum);
							Semesters.add(Sem);
						}
						else
						{
							semNum = 2;
							year--;
							String Sem = String.valueOf(year)+String.valueOf(semNum);
							Semesters.add(Sem);
						}
					}
					break;
				}
			}
			rs5.close();
			
			
			
			if(((ArrayList<String>)arr).get(2).equals("0"))
			{
				Statement stmt3;
				stmt3 = con.createStatement();			
				ResultSet rs3 = stmt3.executeQuery("SELECT * FROM teachers;");
				while(rs3.next())
				{
					if(rs3.getString(1).equals(((ArrayList<String>)arr).get(3)) && !(((ArrayList<String>)arr).get(3).equals("0")))
					{
						flag++;
						break;
					}
				}
				rs3.close();
						
				if(flag == 1)
				{
					Result.add("GenerateReport1");
					Statement stmt2;
					stmt2 = con.createStatement();			
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM teacher_in_class_course;");
					while(rs2.next())
					{
						String CourseNClass = rs2.getString(3)+" ("+rs2.getString(2)+")";
						if((!(Result.contains(CourseNClass))) && Semesters.contains(rs2.getString(4)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(3)))
						{
							Result.add(CourseNClass);
							Result3.add(rs2.getString(2)); //class
							Result3.add(rs2.getString(3)); //course
						}
					}
					rs2.close();
					
					for(int i=0;i<Result3.size();i=i+2)
					{
						Statement stmt;
						stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
						while(rs.next())
						{
							if(rs.getString(3)!=null && rs.getString(2).equals(Result3.get(i+1)) && rs.getString(5).equals(Result3.get(i)) &&  Semesters.contains(rs.getString(4)))
							{
								Grade = Grade + Integer.parseInt(rs.getString(3));
								GradeCnt++;
							}
						}
						if(GradeCnt!=0)Result2.add(String.valueOf(Grade/GradeCnt));
						else Result2.add("0");
						rs.close();
						Grade = 0;
						GradeCnt = 0;
					}
				}
				else
				{
					Result.add("GenerateReportError");
				}
			}
			else if(((ArrayList<String>)arr).get(2).equals("1"))
			{
				Result.add("GenerateReport2");
				Statement stmt2;
				stmt2 = con.createStatement();			
				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM teacher_in_class_course;");
				while(rs2.next())
				{
					String TIDNCourse = rs2.getString(1)+","+rs2.getString(3);
					if((!(Result3.contains(TIDNCourse))) && Semesters.contains(rs2.getString(4)) && rs2.getString(2).equals(((ArrayList<String>)arr).get(3)) && !(rs2.getString(1).equals("0")))
					{
						if(!(Result.contains(rs2.getString(1)))) Result.add(rs2.getString(1)); //TID
						Result3.add(TIDNCourse); //TID & course
					}
				}
				rs2.close();
				
				for(int i=1;i<Result.size();i++)
				{
					for(int j=0;j<Result3.size();j++)
					{
						Statement stmt;
						stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
						while(rs.next())
						{
							String ID = "" , Course = "";
							int k = Result3.get(j).indexOf(",");
		    				if (k > 0) {
		    					ID = Result3.get(j).substring(0,k);
		    				}
		    				k = Result3.get(j).indexOf(",");
		    				if (k > 0) {
		    					Course = Result3.get(j).substring(k+1);
		    				}
							if(rs.getString(3)!=null && ID.equals(Result.get(i)) && Course.equals(rs.getString(2)) &&   Semesters.contains(rs.getString(4)) && rs.getString(5).equals(((ArrayList<String>)arr).get(3)))
							{
								Grade = Grade + Integer.parseInt(rs.getString(3));
								GradeCnt++;
							}
						}
						rs.close();
					}
					if(GradeCnt!=0)Result2.add(String.valueOf(Grade/GradeCnt));
					else Result2.add("0");
					Grade = 0;
					GradeCnt = 0;
				}
				
				
				
			}
			else if(((ArrayList<String>)arr).get(2).equals("2"))
			{
				Result.add("GenerateReport3");
				Statement stmt2;
				stmt2 = con.createStatement();			
				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM teacher_in_class_course;");
				while(rs2.next())
				{
					if((!(Result.contains(rs2.getString(3)))) && Semesters.contains(rs2.getString(4)) && rs2.getString(2).equals(((ArrayList<String>)arr).get(3)) && !(rs2.getString(1).equals("0")))
					{
						Result.add(rs2.getString(3));
					}
				}
				rs2.close();
				
				for(int i=1;i<Result.size();i++)
				{
					Statement stmt;
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
					while(rs.next())
					{
						if(rs.getString(3)!=null && rs.getString(2).equals(Result.get(i)) &&  Semesters.contains(rs.getString(4)) && rs.getString(5).equals(((ArrayList<String>)arr).get(3)))
						{
							Grade = Grade + Integer.parseInt(rs.getString(3));
							GradeCnt++;
						}
					}
					if(GradeCnt!=0)Result2.add(String.valueOf(Grade/GradeCnt));
					else Result2.add("0");
					Grade = 0;
					GradeCnt = 0;
					rs.close();
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		Result.addAll(Result2);
		return Result;
	}
	
	/**
	 * gets blocking parents info.
	 *
	 * @param con the DB connection
	 * @param arr parent and student IDs
	 * @param client the current client
	 * @return message to show the blocking check box in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewBlockingCheckBox(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewBlockingCheckBox");
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM parent_student;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)) && rs.getString(2).equals(((ArrayList<String>) arr).get(2)))
				{
					Result.add(rs.getString(3));
					break;
				}
			}
			if(Result.size() == 1)
				Result.add(((ArrayList<String>) arr).get(1) + " is not " + ((ArrayList<String>) arr).get(2) + " parent");
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets student homework list.
	 *
	 * @param con the DB connection
	 * @param arr student ID
	 * @param client the current client
	 * @return the student homework list in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> SDViewStdHomeworkList(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SDViewStdHomeworkList");
		String Semester = null;
		Statement stmt,stmt2, stmt3, stmt4,stmt5;
		try 
		{
			Statement stmt6;
			stmt6 = con.createStatement();			
			ResultSet rs6 = stmt6.executeQuery("SELECT * FROM semesters;");
			while(rs6.next())
			{
				if(rs6.getString(6).equals("1"))
				{
					Semester = rs6.getString(1);
					break;
				}
			}
			rs6.close();
			
			stmt = con.createStatement();
			stmt2 = con.createStatement();
			stmt3 = con.createStatement();
			stmt4 = con.createStatement();
			stmt5 = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM homeworks;");
			while(rs.next())
			{
				String SID = null;
				int flag = 0;
				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM student_in_a_course;");
				while(rs2.next())
				{ //if
				if(rs.getString(6).equals(rs2.getString(2)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(1)) && rs.getString(7).equals(Semester) && rs2.getString(4).equals(Semester))
					{
						ResultSet rs5 = stmt5.executeQuery("SELECT * FROM student_in_a_class;");
						while(rs5.next())
						{
						if(rs.getString(5).equals(rs5.getString(2)) && rs5.getString(1).equals(((ArrayList<String>)arr).get(1)) && rs5.getString(3).equals(Semester))
						{
						Result.add(rs.getString(1)); // HWID
						Result.add(rs.getString(2)); // HW Name
						Date date = rs.getDate(3);
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						String reportDate = df.format(date);
						Result.add(reportDate); // DeadLine	
						Result.add(rs.getString(6)); // Course
						Result.add(rs.getString(5)); // Class	
						
						int flag3 = 0;
						ResultSet rs3 = stmt3.executeQuery("SELECT * FROM submissions;");
						while(rs3.next())
						{							
							if(rs.getString(2).equals(rs3.getString(5)) && rs.getString(6).equals(rs3.getString(6)) && Semester.equals(rs3.getString(7)))
							{
								Statement stmt7 = con.createStatement();
								ResultSet rs7 = stmt7.executeQuery("SELECT * FROM student_submissions;");
								while(rs7.next())
								{
									if(rs3.getString(1).equals(rs7.getString(2)) && rs7.getString(1).equals(((ArrayList<String>)arr).get(1)))
									{
										SID = rs3.getString(1);
										flag3++;
										break;
									}
								}
								rs7.close();
							}
							if(flag3>0)break;
						}
						rs3.close();
						ResultSet rs4 = stmt4.executeQuery("SELECT * FROM student_submissions;");
						while(rs4.next())
						{
							if(rs4.getString(1).equals(((ArrayList<String>)arr).get(1)) && rs4.getString(2).equals(SID))
									{
										flag++;
										if(rs4.getString(3)==null)
										{
										Result.add(""); // Grade
										Result.add("No"); // Checked
										}
										else
										{
										Result.add(rs4.getString(3)); // Grade
										Result.add(rs4.getString(4)); // Checked
										}
										break;
									}
						}
						if(flag==0)
						{
							Result.add(""); // Grade
							Result.add("No"); // Checked
						}
						rs4.close();
						
					}					
					}
					rs5.close();
				}
				}
				rs2.close();
			}
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets the submitted file info.
	 *
	 * @param conn the DB connection
	 * @param arr student ID and homework details
	 * @param client the current client
	 * @return the submission information in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> GetSubInfo(Connection conn, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SchDirSubInfo");
		int flag = 0;
		Statement stmt,stmt2;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM submissions");
			while(rs.next())
			{
					if(rs.getString(5).equals(((ArrayList<String>)arr).get(2)) && rs.getString(6).equals(((ArrayList<String>)arr).get(3)))
					{
						stmt2 = conn.createStatement();
						ResultSet rs2 = stmt2.executeQuery("SELECT * FROM student_submissions");
						while(rs2.next())
						{
							if(rs.getString(1).equals(rs2.getString(2)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(1)))
							{
								Result.add(rs.getString(2));
								Result.add(rs.getString(3));
								flag++;
								break;
							}
						}
						rs2.close();
					}
					if(flag>0) break;
			}
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets the Checked HW file.
	 *
	 * @param conn the DB connection
	 * @param arr file details
	 * @param client the current client
	 * @return the checked file from the server to the client
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Object> GetCHFile(Connection conn, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str1 = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		ArrayList<Object> content = new ArrayList<Object>();
		content.add("SchDirCheckedFile");
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM grade_sheets;");
			while(rs.next())
			{
				if(str1[1].equals(rs.getString(2)))
				{
					File file = new File(rs.getString(5));
					String path = rs.getString(5);
					if(file.exists())
					{
			    	String extension = "";		
					int i = file.toString().lastIndexOf('.');
					if (i > 0) {
					    extension = file.toString().substring(i+1);
					}
					File file2 = new File(Server1Controller.CHWPath+"\\test123."+extension);
					try {
						FileChannel src = null;
						FileChannel dest = null;
						src = new FileInputStream(file).getChannel();
						dest = new FileOutputStream(file2).getChannel();	
						dest.transferFrom(src, 0, src.size());
						src.close();
						dest.close();
						
						byte[] bytesArray = new byte[(int) file2.length()];
						FileInputStream fis = new FileInputStream(file2);							
						fis.read(bytesArray); //read file into bytes[]
				        fis.close();
						file2.delete();
						
				    	content.add(bytesArray);
				    	content.add(extension);
						break;
						
					} catch (IOException e) {
						e.printStackTrace();
					}											
				}
					else
						{
						PreparedStatement st = conn.prepareStatement("DELETE FROM grade_sheets WHERE Attachments = ?");
						st.setString(1,path);
						st.executeUpdate(); 
						st.close();
						byte[] bytesArray = new byte[1];
						content.add(bytesArray);
						content.add("Empty");
						break;
						}
				}
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return content;		
	}
	
	/**
	 * Gets the Evaluation form file.
	 *
	 * @param conn the DB connection
	 * @param arr file details
	 * @param client the current client
	 * @return the evaluation file from the server to the client
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Object> GetEVFile(Connection conn, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str1 = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		ArrayList<Object> content = new ArrayList<Object>();
		content.add("SchDirEvaluationFile");
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM evaluation_forms;");
			while(rs.next())
			{
				if(str1[1].equals(rs.getString(2)))
				{
					File file = new File(rs.getString(5));
					String path = rs.getString(5);
					if(file.exists())
					{
			    	String extension = "";		
					int i = file.toString().lastIndexOf('.');
					if (i > 0) {
					    extension = file.toString().substring(i+1);
					}
					File file2 = new File(Server1Controller.EvPath+"\\test123."+extension);
					try {
						FileChannel src = null;
						FileChannel dest = null;
						src = new FileInputStream(file).getChannel();
						dest = new FileOutputStream(file2).getChannel();	
						dest.transferFrom(src, 0, src.size());
						src.close();
						dest.close();
						
						byte[] bytesArray = new byte[(int) file2.length()];
						FileInputStream fis = new FileInputStream(file2);							
						fis.read(bytesArray); //read file into bytes[]
				        fis.close();
						file2.delete();
						
				    	content.add(bytesArray);
				    	content.add(extension);
						break;
						
					} catch (IOException e) {
						e.printStackTrace();
					}											
				}
					else
						{
						PreparedStatement st = conn.prepareStatement("DELETE FROM evaluation_forms WHERE Attachments = ?");
						st.setString(1,path);
						st.executeUpdate(); 
						st.close();
						byte[] bytesArray = new byte[1];
						content.add(bytesArray);
						content.add("Empty");
						break;
						}
				}
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return content;		
	}
	
	/**
	 * Gets the HW file.
	 *
	 * @param conn the DB connection
	 * @param arr file details
	 * @param client the current client
	 * @return the homework file from the server to the client
	 */
	@SuppressWarnings({ "unchecked", "resource", "unused" })
	public static ArrayList<Object> GetHWFile(Connection conn, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str1 = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		ArrayList<Object> content = new ArrayList<Object>();
		content.add("SchDirFile");
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM homeworks;");
			while(rs.next())
			{
				if(str1[1].equals(rs.getString(2)))
				{
					File file = new File(rs.getString(4));
					String path = rs.getString(4);
					if(file.exists())
					{
			    	String extension = "";		
					int i = file.toString().lastIndexOf('.');
					if (i > 0) {
					    extension = file.toString().substring(i+1);
					}
					File file2 = new File(Server1Controller.HWPath+"\\test123."+extension);
					try {
						FileChannel src = null;
						FileChannel dest = null;
						src = new FileInputStream(file).getChannel();
						dest = new FileOutputStream(file2).getChannel();	
						dest.transferFrom(src, 0, src.size());
						src.close();
						dest.close();
						
						byte[] bytesArray = new byte[(int) file2.length()];
						FileInputStream fis = new FileInputStream(file2);							
						fis.read(bytesArray); //read file into bytes[]
				        fis.close();
						file2.delete();
						
				    	content.add(bytesArray);
				    	content.add(extension);
						break;
						
					} catch (IOException e) {
						e.printStackTrace();
					}											
				}
					else
						{
						byte[] bytesArray = new byte[1];
						content.add(bytesArray);
						content.add("Empty");
						break;
						}
				}
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return content;		
	}
	
	/**
	 * Gets the submitted file.
	 *
	 * @param conn the DB connection
	 * @param arr file details
	 * @param client the current client
	 * @return the submission file from the server to the client
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Object> GetSubFile(Connection conn, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str1 = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		ArrayList<Object> content = new ArrayList<Object>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM submissions;");
			while(rs.next())
			{
				if(str1[1].equals(rs.getString(2)))
				{
					File file = new File(rs.getString(4));
					String path = rs.getString(4);
					if(file.exists())
					{
			    	String extension = "";		
					int i = file.toString().lastIndexOf('.');
					if (i > 0) {
					    extension = file.toString().substring(i+1);
					}
					File file2 = new File(Server1Controller.SubPath+"\\test123."+extension);
					try {
						FileChannel src = null;
						FileChannel dest = null;
						src = new FileInputStream(file).getChannel();
						dest = new FileOutputStream(file2).getChannel();	
						dest.transferFrom(src, 0, src.size());
						src.close();
						dest.close();
						
						byte[] bytesArray = new byte[(int) file2.length()];
						FileInputStream fis = new FileInputStream(file2);							
						fis.read(bytesArray); //read file into bytes[]
				        fis.close();
						file2.delete();
						content.add("SchDirSubFile");
				    	content.add(bytesArray);
				    	content.add(extension);
						break;
						
					} catch (IOException e) {
						e.printStackTrace();
					}											
				}
					else
						{
						PreparedStatement st = conn.prepareStatement("DELETE FROM submittions WHERE Attachments = ?");
						st.setString(1,path);
						st.executeUpdate(); 
						st.close();
						byte[] bytesArray = new byte[1];
						content.add(bytesArray);
						content.add("Empty");
						break;
						}
				}
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return content;		
	}
	
	/**
	 * Gets courses info by student info.
	 *
	 * @param con the DB connection
	 * @param arr student ID
	 * @param client the current client
	 * @return the student current semester courses list in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> SDViewStdCoursesList(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SDViewStdCoursesList");
		String SemID = null;
		try 
		{	
			Statement stmt1 = con.createStatement();
			Statement stmt2 = con.createStatement();
			Statement stmt3 = con.createStatement();
			Statement stmt4 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM semesters;");
			while(rs1.next())
			{
				if(rs1.getString(6).equals("1"))
				{
					SemID = rs1.getString(1);
					break;
				}
			}
			rs1 = stmt1.executeQuery("SELECT * FROM student_in_a_course;");
			while(rs1.next())
			{
				if(rs1.getString(1).equals(((ArrayList<String>) arr).get(1)) && rs1.getString(4).equals(SemID))
				{
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM courses;");
					while(rs2.next())
					{
						if(rs2.getString(2).equals(rs1.getString(2)))
						{
							Result.add(rs2.getString(1));
							Result.add(rs2.getString(2));
							Result.add(rs2.getString(3));
							ResultSet rs3 = stmt3.executeQuery("SELECT * FROM teacher_in_class_course;");
							while(rs3.next())
							{
								if(rs3.getString(2).equals(rs1.getString(5)) && rs3.getString(4).equals(SemID) && rs3.getString(3).equals(rs1.getString(2)))
								{
									if(!rs3.getString(1).equals("0"))
									{
										ResultSet rs4 = stmt4.executeQuery("SELECT * FROM users;");
										while(rs4.next())
										{
										
											if(rs4.getString(1).equals(rs3.getString(1)))
											{
												Result.add(rs4.getString(3) + " " + rs4.getString(4));
												break;
											}
										}
										rs4.close();
									}
									else
									{
										Result.add("");
									}
									break;
								}
							}
							rs3.close();
							break;
						}	
					}
					rs2.close();
					Result.add(rs1.getString(6));
				}
			}
			rs1.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
		
	}
	
	/**
	 * Gets parent info by student info.
	 *
	 * @param con the DB connection
	 * @param arr parent ID
	 * @param client the current client
	 * @return the parent information in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> SDViewParInfo(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SDViewParInfo");
		boolean exist = false;
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
				{
					exist = true;
					if(rs.getString(2).equals("6"))
					{
						Result.add(rs.getString(1)); // User ID
						Result.add(rs.getString(3)); // First Name
						Result.add(rs.getString(4)); // Last Name
						Result.add(rs.getString(9)); // Phone Number
						Result.add(rs.getString(10)); // Address
						Result.add(rs.getString(5)); // Birth Date
					}
					else
						Result.add(rs.getString(3) + " " + rs.getString(4) + " is not a parent");
					break;
				}
			}
			if(!exist)
			{
				Result.add(((ArrayList<String>) arr).get(1) + " not exist in the database");
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets students info by parent info.
	 *
	 * @param con the DB connection
	 * @param arr parent ID
	 * @param client the current client
	 * @return the parent children details in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewParChildren(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewParChildren");
		String SemID = null, ClassName = null;
		Statement stmt1, stmt2;
		try 
		{
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM semesters;");
			while(rs1.next())
			{
				if(rs1.getString(6).equals("1"))
				{
					SemID = rs1.getString(1);
					break;
				}
			}
			rs1 = stmt1.executeQuery("SELECT * FROM parent_student;");
			while(rs1.next())
			{
				if(rs1.getString(1).equals(((ArrayList<String>) arr).get(1)))
				{
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM users;");
					while(rs2.next())
					{
						if(rs2.getString(1).equals(rs1.getString(2)))
						{
							Result.add(rs2.getString(1)); // Student ID
							Result.add(rs2.getString(3) + " " + rs2.getString(4)); // Name
							Result.add(rs2.getString(5)); // Birth Date
							Result.add(rs2.getString(9)); // Phone Number
							break;
						}
					}
					rs2 = stmt2.executeQuery("SELECT * FROM student_in_a_class;");
					while(rs2.next())
					{
						if(rs2.getString(1).equals(Result.get(Result.size() - 4)) && rs2.getString(3).equals(SemID))
						{
							Result.add(rs2.getString(2));
							ClassName = rs2.getString(2);
							break;
						}
					}
					if(ClassName == null)
					{
						Result.add("Not Associated");
					}
					ClassName = null;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets teacher info.
	 *
	 * @param con the DB connection
	 * @param arr teacher ID
	 * @param client the current client
	 * @return the teacher information in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> SDViewTeaInfo(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SDViewTeaInfo");
		boolean exist = false;
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
				{
					exist = true;
					if(rs.getString(2).equals("4"))
					{
						Result.add(rs.getString(1)); // User ID
						Result.add(rs.getString(3)); // First Name
						Result.add(rs.getString(4)); // Last Name
						Result.add(rs.getString(9)); // Phone Number
						Result.add(rs.getString(10)); // Address
						Result.add(rs.getString(5)); // Birth Date
					}
					else
						Result.add(rs.getString(3) + " " + rs.getString(4) + " is not a teacher");
					break;
				}
			}
			if(exist)
			{
				rs = stmt.executeQuery("SELECT * FROM teachers;");
				while(rs.next())
				{
					if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
					{
						Result.add(rs.getString(2)); // Max Hours
						Result.add(rs.getString(3)); // Actual Hours
						break;
					}
				}
			}
			else
				Result.add(((ArrayList<String>) arr).get(1) + " not exist in the database");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets teacher homework list.
	 *
	 * @param con the DB connection
	 * @param arr teacher ID
	 * @param client the current client
	 * @return the teacher current semester homework list in ArrayList<String> to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewTeaHomeworks(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewTeaHomeworks");
		String SemID = null;
		Statement stmt1,stmt2;
		try 
		{
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM semesters;");
			while(rs1.next())
			{
				if(rs1.getString(6).equals("1"))
				{
					SemID = rs1.getString(1);
					break;
				}
			}
			rs1 = stmt1.executeQuery("SELECT * FROM teacher_hws;");
			while(rs1.next())
			{
				if(rs1.getString(1).equals(((ArrayList<String>) arr).get(1)))
				{
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM homeworks;");
					while(rs2.next())
					{
						if(rs2.getString(1).equals(rs1.getString(2)) && rs2.getString(7).equals(SemID))
						{
							Result.add(rs2.getString(1)); // HW ID
							Result.add(rs2.getString(2)); // HW Name
							Date date = rs2.getDate(3);
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							String reportDate = df.format(date);
							Result.add(reportDate); // DeadLine	
							Result.add(rs2.getString(6)); // Course
							Result.add(rs2.getString(5)); // Class
							break;
						}
					}
					rs2.close();
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets courses info by teacher info.
	 *
	 * @param con the DB connection
	 * @param arr teacher ID
	 * @param client the current client
	 * @return the teacher current semester courses in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewTeaCourses(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewTeaCourses");
		String SemID = null;
		try 
		{	
			Statement stmt1 = con.createStatement();
			Statement stmt2 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM semesters;");
			while(rs1.next())
			{
				if(rs1.getString(6).equals("1"))
				{
					SemID = rs1.getString(1);
					break;
				}
			}
			rs1 = stmt1.executeQuery("SELECT * FROM teacher_in_class_course;");
			while(rs1.next())
			{
				if(rs1.getString(1).equals(((ArrayList<String>) arr).get(1)) && rs1.getString(4).equals(SemID))
				{
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM courses;");
					while(rs2.next())
					{
						if(rs2.getString(2).equals(rs1.getString(3)))
						{
							Result.add(rs2.getString(1)); // Course Number
							break;
						}
					}
					rs2.close();
					Result.add(rs1.getString(3)); // Course Name
					Result.add(rs1.getString(6)); // WeekHours
					Result.add(rs1.getString(2)); // Class Name
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets Last 10 semesters.
	 *
	 * @param con the DB connection
	 * @param client the current client
	 * @return the last 10 semesters in ArrayList to the client
	 */
	public static ArrayList<String> Last10SemestersYears(Connection con, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("Last10SemestersYears");
		Statement stmt;
		boolean flag = true;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			int i = 10;
			if(!rs.last())
				flag = false;
			while(flag && i > 0)
			{
				if(!Result.contains(rs.getString(5)))
					Result.add(rs.getString(5));
				i--;
				if(!rs.previous())
					flag = false;
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets Last 10 semesters.
	 *
	 * @param con the DB connection
	 * @param arr semester year
	 * @param client the current client
	 * @return the last semester of the certain year in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> Last10SemestersNums(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("Last10SemestersNums");
		Statement stmt;
		boolean flag = true;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			int i = 10;
			if(!rs.last())
				flag = false;
			while(flag && i > 0)
			{
				if(((ArrayList<String>) arr).contains(rs.getString(5)))
				{
					Result.add(rs.getString(2));
					Result.add(rs.getString(3));
					Result.add(rs.getString(4));
				}
				i--;
				if(!rs.previous())
					flag = false;
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets the semester info.
	 *
	 * @param con the DB connection
	 * @param arr semester ID
	 * @param client the current client
	 * @return the semester information in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> GetSemesterInfo(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("GetSemesterInfo");
		Statement stmt;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>)arr).get(1)))
				{
					Result.add(rs.getString(1));
					Result.add(rs.getString(3));
					Result.add(rs.getString(4));
					break;
				}
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets courses info by semester.
	 *
	 * @param con the DB connection
	 * @param arr semester ID
	 * @param client the current client
	 * @return the semester courses schedule in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewSemesterCourses(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewSemesterCourses");
		Statement stmt1, stmt2;
		String SemID = ((ArrayList<String>) arr).get(1);
		try
		{
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM teacher_in_class_course;");
			while(rs1.next())
			{
				if(rs1.getString(4).equals(SemID))
				{
					Result.add(rs1.getString(3));
					Result.add(rs1.getString(5));
					Result.add(rs1.getString(2));
					if(!rs1.getString(1).equals("0"))
					{
						ResultSet rs2 = stmt2.executeQuery("SELECT * FROM users;");
						while(rs2.next())
							if(rs2.getString(1).equals(rs1.getString(1)))
							{
								Result.add(rs2.getString(3) + " " + rs2.getString(4));
								break;
							}
						rs2.close();
					}
					else
						Result.add("");
					Result.add(rs1.getString(6));
				}
			}
			rs1.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Gets courses info.
	 *
	 * @param con the DB connection
	 * @param client the current client
	 * @return the courses list in ArrayList to the client
	 */
	public static ArrayList<String> SchDirViewCourses(Connection con, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SchDirViewCourses");
		Statement stmt1, stmt2;
		String PreCourses = "";
		try
		{
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM courses;");
			while(rs1.next())
			{
				Result.add(rs1.getString(1)); // Course Number
				Result.add(rs1.getString(2)); // Course Name
				Result.add(rs1.getString(4)); // TeachingUnit
				Result.add(rs1.getString(3)); // Week Hours
				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM course_pre;");
				while(rs2.next())
				{
					if(rs2.getString(1).equals(rs1.getString(1)) && rs2.getString(2).equals(rs1.getString(2)))
						if(!PreCourses.equals(""))
							PreCourses = PreCourses + ", " + rs2.getString(3);
						else
							PreCourses = rs2.getString(3);
				}
				rs2.close();
				Result.add(PreCourses);
				PreCourses = "";
			}
			rs1.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
}
