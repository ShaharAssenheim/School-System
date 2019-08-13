package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import OCSF.ConnectionToClient;

/**
 * The Class TeacherMethods for all the methods that required 
 * to extract(/update) data from(/in) the database for(/by) the methods from teacher controller.
 * @author Eliran Abergel
 */
public class TeacherMethods
{
	
	/**
	 * Uploaded file and update DB.
	 *
	 * @param conn the DB connection
	 * @param arr the file details
	 * @param client the current client
	 * @return the string if file uploaded successfully or not
	 */
	@SuppressWarnings({ "unchecked", "unused", "resource" })
	public static String UploadedFileData(Connection conn, Object arr, ConnectionToClient client)
	{
		String Semester = null;
		String OK = "OK";
		String OK2 = "OK2";
		String Error = "Error";
		String Error2 = "Error2";
		File file = null;
		int flag = 0;
		int flag2 = 0;
		String temp = null;
		try {
			Statement stmt3;
			stmt3 = conn.createStatement();
			ResultSet rs3;		
			rs3 = stmt3.executeQuery("SELECT * FROM classes;");
			while(rs3.next())
			{
				temp = rs3.getString(3)+" "+rs3.getString(4);
				if(temp.equals(((String) ((ArrayList<Object>) arr).get(4))))
				{
					break;
				}
			}	
			
			Statement stmt5;
			stmt5 = conn.createStatement();			
			ResultSet rs5 = stmt5.executeQuery("SELECT * FROM semesters;");
			while(rs5.next())
			{
				if(rs5.getString(6).equals("1"))
				{
					Semester = rs5.getString(1);
					break;
				}
			}
			rs5.close();
			
			Statement stmt2;
			stmt2 = conn.createStatement();
			ResultSet rs2;		
			rs2 = stmt2.executeQuery("SELECT * FROM teacher_in_class_course;");
			while(rs2.next())
			{
				if(rs2.getString(2).equals(temp) && rs2.getString(3).equals(((String) ((ArrayList<Object>) arr).get(5))) && rs2.getString(1).equals(((String) ((ArrayList<Object>) arr).get(6))))
				{
					flag = 2;
					break;
				}
			}
			rs2.close();
				
		
			if(flag == 2)
			{
				if(((ArrayList<Object>) arr).size()==7)
				{
				Statement stmt;
				stmt = conn.createStatement();
				ResultSet rs;		
				rs = stmt.executeQuery("SELECT * FROM homeworks;");
				while(rs.next())
				{
					if(rs.getString(2).equals(((String) ((ArrayList<Object>) arr).get(1))) && rs.getString(6).equals(((String) ((ArrayList<Object>) arr).get(5))) && rs.getString(7).equals(Semester))
					{
						flag = 1;
						break;
					}
				}
				rs.close();
				}
			
			if(flag == 2)	
			{
			String Class = (String) ((ArrayList<Object>) arr).get(4);
			String Course = (String) ((ArrayList<Object>) arr).get(5);
			
			
			
			try {
				file = new File(Server1Controller.HWPath+"\\"+Semester+"\\"+Course+"\\"+Class+"\\"+((ArrayList<Object>) arr).get(1)+"."+((ArrayList<Object>) arr).get(2));
				if(file.exists() && !file.isDirectory())
				{
					flag2++;
				}
				file.getParentFile().mkdirs();
				FileWriter writer = new FileWriter(file);
				FileOutputStream fos = new FileOutputStream(file);
				((ArrayList<Object>) arr).add(Semester);
				fos.write((byte[])((ArrayList<Object>) arr).get(0));
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			if(flag2==1)
			{
				PreparedStatement updateUnit4 = conn.prepareStatement("UPDATE homeworks SET DeadLineDate = ? WHERE Attachments = ? ");				
				updateUnit4.setDate(1, (Date)((ArrayList<Object>)arr).get(3));
				updateUnit4.setString(2, file.toString());
				updateUnit4.executeUpdate();
				updateUnit4.close();
			}
			else
			{
			Statement stmt4;
			stmt4 = conn.createStatement();
			ResultSet rs4;		
			rs4 = stmt4.executeQuery("SELECT * FROM homeworks;");	
			int cnt = 1;
			while(rs4.next())
				{
				if(Integer.parseInt(rs4.getString(1))>=cnt)
					cnt = 1 + Integer.parseInt(rs4.getString(1));
				}
			rs4.close();
			PreparedStatement updateUnit = conn.prepareStatement("INSERT INTO homeworks (HWID, Name, DeadLineDate, Attachments, ClassName, CourseName, Semester) VALUES (?, ?, ?, ?, ?, ?, ?)");				
			updateUnit.setString(1, String.valueOf(cnt));
			updateUnit.setString(2, ((String) ((ArrayList<Object>) arr).get(1)));
			updateUnit.setDate(3, (Date) ((ArrayList<Object>) arr).get(3));
			updateUnit.setString(4, file.toString());
			updateUnit.setString(5, (String) ((ArrayList<Object>) arr).get(4));
			updateUnit.setString(6, (String) ((ArrayList<Object>) arr).get(5));
			updateUnit.setString(7, (String) ((ArrayList<Object>) arr).get(7));
			updateUnit.executeUpdate();
			updateUnit.close();	
			
			
			PreparedStatement updateUnit2 = conn.prepareStatement("INSERT INTO class_hws (ClassName, HWID) VALUES (?, ?)");				
			updateUnit2.setString(1, ((String) ((ArrayList<Object>) arr).get(4)));
			updateUnit2.setString(2, String.valueOf(cnt));
			updateUnit2.executeUpdate();
			updateUnit2.close();	
			
			PreparedStatement updateUnit3 = conn.prepareStatement("INSERT INTO teacher_hws (UID, HWID) VALUES (?, ?)");				
			updateUnit3.setString(1, ((String) ((ArrayList<Object>) arr).get(6)));
			updateUnit3.setString(2, String.valueOf(cnt));
			updateUnit3.executeUpdate();
			updateUnit3.close();	
			}
			}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(flag2==1) return OK2;
			else if(flag == 1) return Error;
			else if(flag != 2) return Error2;
			else return OK;
	}
	
	
	
	/**
	 * Gets the student data.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding student
	 * @param client the current client
	 * @return the student data
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> GetStudentData(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SID");
		boolean exist = false;
		Statement stmt;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(((ArrayList<String>)arr).get(1).equals(rs.getString(1)))
				{
					if((rs.getString(2).equals("5")))
					{
							Result.add(rs.getString(3)); // first Name	
							Result.add(rs.getString(4)); // last Name
							Result.add(rs.getString(9)); // phone
							Result.add(rs.getString(10)); // address
							Result.add(rs.getString(5)); // birthday
							exist = true;
							break;
					}
					else
					{
						Result.add(rs.getString(3) + " " + rs.getString(4) + " is not a student");
						exist = true;
						break;
					}
				}
			}
			if(!exist)
				Result.add(((ArrayList<String>)arr).get(1) + " doesn't exist in the database");
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	
	/**
	 * Find Students from chosen class.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding students from chosen class
	 * @param client the current client
	 * @return the students data from chosen class
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> StudentsFromClass(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		ArrayList<String> Result2 = new ArrayList<String>();
		Statement stmt;
		String Semester=null;
		try{
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
		
		ResultSet rs4 = stmt.executeQuery("SELECT * FROM student_in_a_class;");
		while(rs4.next())
		{
			if(rs4.getString(2).equals(((ArrayList<String>)arr).get(2)) && Semester.equals(rs4.getString(3)))
			{
				Result.add(rs4.getString(1)); // student ID	
			}
		}
		rs4.close();
		
		Result2.add("StudentInfo2");
		ResultSet rs2 = stmt.executeQuery("SELECT * FROM users;");
		while(rs2.next())
		{
			for(int i=0;i<Result.size();i++)
			{
				if(Result.get(i).equals(rs2.getString(1)))
				{
					Result2.add(rs2.getString(1)); // UID
					Result2.add(rs2.getString(3)); // first Name	
					Result2.add(rs2.getString(4)); // last Name
					Result2.add(rs2.getString(5)); // birthday
					Result2.add(rs2.getString(9)); // phone
					Result2.add(rs2.getString(10)); // address
					Result2.add(((ArrayList<String>)arr).get(2)); //class name
				}
			}
		}
		rs2.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return Result2;
	}
	
	
	/**
	 * Find Students from chosen course.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding students from chosen course
	 * @param client the current client
	 * @return the students data from chosen course
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> StudentsFromCourse(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		ArrayList<String> Result2 = new ArrayList<String>();
		Statement stmt;
		String Semester = null;
		try{
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
		ResultSet rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
		while(rs.next())
		{
			if(((ArrayList<String>)arr).get(2).equals(rs.getString(2)) && Semester.equals(rs.getString(4)))
				Result.add(rs.getString(1)); // student ID	
		}
		rs.close();
		Result2.add("StudentInfo2");
		Result2.add("StudentsFromCourse");
		ResultSet rs2 = stmt.executeQuery("SELECT * FROM users;");
		while(rs2.next())
		{
			for(int i=0;i<Result.size();i++)
			{
				if(Result.get(i).equals(rs2.getString(1)))
				{
					Result2.add(rs2.getString(1)); // UID
					Result2.add(rs2.getString(3)); // first Name	
					Result2.add(rs2.getString(4)); // last Name
					Result2.add(rs2.getString(5)); // birthday
					Result2.add(rs2.getString(9)); // phone
					Result2.add(rs2.getString(10)); // address
					Result2.add(((ArrayList<String>)arr).get(2)); // course
				}
			}
		}
		rs2.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return Result2;
	}
	
	
	/**
	 * Find Students names.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding students names
	 * @param client the current client
	 * @return the students names
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> StudentsNames(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		ArrayList<String> Result2 = new ArrayList<String>();
		ArrayList<String> Result3 = new ArrayList<String>();
		Result3.add("StName");
		String HWID = null;
		int flag = 0;
		String Semester = null;
		Statement stmt,stmt3;
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM homeworks;");
			while(rs.next())
			{
				if(((ArrayList<String>)arr).get(2).equals(rs.getString(2)) && ((ArrayList<String>)arr).get(1).equals(rs.getString(6)) && rs.getString(7).equals(Semester))
				{
					HWID = rs.getString(1);
					break;
				}
			}
			rs.close();
			stmt = con.createStatement();
			ResultSet rs2 = stmt.executeQuery("SELECT * FROM class_hws;");
			while(rs2.next())
			{
				if(HWID.equals(rs2.getString(2)))
					Result.add(rs2.getString(1));
			}
			rs2.close();
			
			stmt3 = con.createStatement();
			ResultSet rs4 = stmt3.executeQuery("SELECT * FROM student_in_a_class;");
			while(rs4.next())
			{
				for(int i=0;i<Result.size();i++)
				{
					if(Result.get(i).equals(rs4.getString(2)) && rs4.getString(3).equals(Semester))
						Result2.add(rs4.getString(1));
				}
			}
			rs4.close();
			
			
			
			stmt = con.createStatement();
			ResultSet rs3 = stmt.executeQuery("SELECT * FROM users;");
			while(rs3.next())
			{
				for(int i=0;i<Result2.size();i++)
				{
					if(Result2.get(i).equals(rs3.getString(1)))
					{
						Result3.add(rs3.getString(1));
						Result3.add(rs3.getString(3));
						Result3.add(rs3.getString(4));
						
						stmt = con.createStatement();
						Result3.add("No");
						ResultSet rsA = stmt.executeQuery("SELECT * FROM submissions;");
						while(rsA.next())
						{
							if(((ArrayList<String>)arr).get(2).equals(rsA.getString(5)) && ((ArrayList<String>)arr).get(1).equals(rsA.getString(6)) && Semester.equals(rsA.getString(7)))
								{
								stmt = con.createStatement();
								ResultSet rsB = stmt.executeQuery("SELECT * FROM student_submissions;");
								while(rsB.next())
									{
										if(rsA.getString(1).equals(rsB.getString(2)) && rs3.getString(1).equals(rsB.getString(1)))
										{
											Result3.remove(Result3.size()-1);
											Result3.add(rsB.getString(4));
											flag++;
											break;
										}
									}
								rsB.close();
								if(flag==1) break;
								}
						}
						flag=0;
						rsA.close();
					}
				}
			}
			rs3.close();
			
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result3;
	}
	
	
	/**
	 * Find HW names.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding HW names
	 * @param client the current client
	 * @return the HW names
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> HWName(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("TeacherHWName");
		Statement stmt,stmt2;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM homeworks;");
			while(rs.next())
			{
				if(((ArrayList<String>)arr).get(1).equals(rs.getString(6)))
				{
					stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM teacher_hws;");
					while(rs2.next())
					{
						if(rs.getString(1).equals(rs2.getString(2)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(2)))
						{
							Result.add(rs.getString(2)); // HW Name
						}
					}
					rs2.close();
				}
			}
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Find Classes and courses names.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding classes and courses names
	 * @param client the current client
	 * @return the classes and courses names
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ClassAndCourse(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		ArrayList<String> Class = new ArrayList<String>();
		ArrayList<String> Course = new ArrayList<String>();
		Result.add("Courses and Classes");
		Result.add(((ArrayList<String>)arr).get(2));
		Statement stmt,stmt2,stmt3;
		String Semester = null;
		try{
			Statement stmt7;
			stmt7 = con.createStatement();			
			ResultSet rs7 = stmt7.executeQuery("SELECT * FROM semesters;");
			while(rs7.next())
			{
				if(rs7.getString(6).equals("1"))
				{
					Semester = rs7.getString(1);
					break;
				}
			}
			rs7.close();
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM courses");
		while(rs.next())
		{
			stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT * FROM teacher_in_class_course");
			while(rs2.next())
			{
				if(!Course.contains(rs.getString(2)) && rs.getString(2).equals(rs2.getString(3)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(3)) && Semester.equals(rs2.getString(4)))
				{
					Result.add(rs.getString(2)); // Course Name
					Course.add(rs.getString(2));
					break;
				}
			}
			rs2.close();
		}
		rs.close();
		Result.add("stop");
		stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery("SELECT * FROM classes;");
		while(rs3.next())
		{
			stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT * FROM teacher_in_class_course");
			while(rs2.next())
			{
				String classes = rs3.getString(3)+" "+rs3.getString(4);
				if(!Class.contains(classes) && classes.equals(rs2.getString(2)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(3)))
				{			
					Result.add(classes); // Class Name
					Class.add(classes);
					break;
				}
			}
			rs2.close();
		}
		rs3.close();		
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return Result;
	}
	
	
	
	/**
	 * Find Classes names.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding classes names
	 * @param client the current client
	 * @return the classes names
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> TClasses(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		ArrayList<String> C = new ArrayList<String>();
		Result.add("TClasses");
		Statement stmt2,stmt3;
		String Semester = null;
		try{
			Statement stmt7;
			stmt7 = con.createStatement();			
			ResultSet rs7 = stmt7.executeQuery("SELECT * FROM semesters;");
			while(rs7.next())
			{
				if(rs7.getString(6).equals("1"))
				{
					Semester = rs7.getString(1);
					break;
				}
			}
			rs7.close();
		stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery("SELECT * FROM classes;");
		while(rs3.next())
		{
			stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT * FROM teacher_in_class_course");
			while(rs2.next())
			{
				String classes = rs3.getString(3)+" "+rs3.getString(4);
				if(!(C.contains(classes)) && classes.equals(rs2.getString(2)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(1)) && Semester.equals(rs2.getString(4)))
				{			
					Result.add(classes); // Class Name
					C.add(classes);
					break;
				}
			}
			rs2.close();
		}
		rs3.close();		
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return Result;
	}
	
	
	
	/**
	 * Find courses names.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding courses names
	 * @param client the current client
	 * @return the courses names
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> TCourses(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("TCourses");
		Statement stmt,stmt2;
		String Semester = null;
		try{
			Statement stmt7;
			stmt7 = con.createStatement();			
			ResultSet rs7 = stmt7.executeQuery("SELECT * FROM semesters;");
			while(rs7.next())
			{
				if(rs7.getString(6).equals("1"))
				{
					Semester = rs7.getString(1);
					break;
				}
			}
			rs7.close();
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM courses");
		while(rs.next())
		{
			stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT * FROM teacher_in_class_course");
			while(rs2.next())
			{
				if(rs2.getString(2).equals(((ArrayList<String>)arr).get(2)) &&   rs.getString(2).equals(rs2.getString(3)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(1)) && Semester.equals(rs2.getString(4)))
				{
					Result.add(rs.getString(2)); // Course Name
					break;
				}
			}
			rs2.close();
		}
		rs.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return Result;
	}
	
	
	
	/**
	 * Find HW info.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding chosen HW info
	 * @param client the current client
	 * @return the chosen HW info
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> FindHW(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("FindHW");
		int flag=0;
		Statement stmt,stmt2;
		String Semester = null;
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM homeworks;");
			while(rs.next())
			{
				stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM teacher_hws;");
				while(rs2.next())
				{
					if(Semester.equals(rs.getString(7)) && rs.getString(5).equals(((ArrayList<String>)arr).get(3)) && rs.getString(6).equals(((ArrayList<String>)arr).get(4)) && ((ArrayList<String>)arr).get(2).equals(rs2.getString(1)) && rs.getString(1).equals(rs2.getString(2)))
					{
						if(((ArrayList<String>)arr).get(1).equals(rs.getString(2)))
						{
							Result.add(rs.getString(4)); // Attachment
							Date date = rs.getDate(3);
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							String reportDate = df.format(date);
							Result.add(reportDate); // DeadLine	
							Result.add(rs.getString(5)); // Class	
							Result.add(rs.getString(6)); // Course	
							flag++;
							break;
						}
					}
				}
				rs2.close();
				if(flag==1) break;
			}
			rs.close();
			if(flag==0) Result.add("Homework name doesn't exist");
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * Find courses names.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding courses names
	 * @param client the current client
	 * @return the courses names
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> TCourseName(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("TCourseName");
		Result.add(((ArrayList<String>)arr).get(1));
		Statement stmt,stmt2;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses");
			while(rs.next())
			{
				stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM teacher_in_class_course");
				while(rs2.next())
				{
					if(rs.getString(2).equals(rs2.getString(3)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(2)))
					{
						Result.add(rs.getString(2)); // Course Name
						break;
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
	 * Get chosen HW info.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding chosen HW info
	 * @param client the current client
	 * @return the chosen HW info
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> HWInfo2(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("HWInfo2");
		int flag=0;
		Statement stmt;
		Statement stmt2;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM homeworks;");
			while(rs.next())
			{
				stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM student_in_a_course;");
				while(rs2.next())
				{
				if(rs.getString(6).equals(((ArrayList<String>)arr).get(3)) && rs.getString(2).equals(((ArrayList<String>)arr).get(2)))
					{
						Date date = rs.getDate(3);
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						String reportDate = df.format(date);
						Result.add(reportDate); // DeadLine	
						Result.add(rs.getString(5)); // Class	
						break;
					}
				}
				rs2.close();
			}
			rs.close();
			
			
			stmt = con.createStatement();
			ResultSet rs3 = stmt.executeQuery("SELECT * FROM submissions");
			while(rs3.next())
			{
					if(rs3.getString(5).equals(((ArrayList<String>)arr).get(2)) && rs3.getString(6).equals(((ArrayList<String>)arr).get(3)))
					{
						stmt2 = con.createStatement();
						ResultSet rs2 = stmt2.executeQuery("SELECT * FROM student_submissions");
						while(rs2.next())
						{
							if(rs3.getString(1).equals(rs2.getString(2)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(1)))
							{
								Result.add(rs3.getString(2)); //sub name
								Result.add(rs3.getString(3)); //sub date
								if(!(rs3.getString(3)==null))
									Result.add(rs2.getString(3)); //grade
								flag++;
								break;
							}
						}
						rs2.close();
					}
					if(flag>0) break;
			}
			rs3.close();
			if(flag==0)Result.add("/");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	
	
	/**
	 * Gets the HW file for download.
	 *
	 * @param conn the DB connection
	 * @param arr the details for finding the HW file for download
	 * @param client the current client
	 * @return the HW file for download
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Object> GetHWFile(Connection conn, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str1 = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		ArrayList<Object> content = new ArrayList<Object>();
		content.add("TeacherFile");
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM homeworks;");
			while(rs.next())
			{
				if(str1[1].equals(rs.getString(2)))
				{
					File file = new File(rs.getString(4));
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
	 * Gets the submitted file for download.
	 *
	 * @param conn the DB connection
	 * @param arr the details for finding the submitted file for download
	 * @param client the current client
	 * @return the submitted file for download
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
						content.add("TeacherSubFile");
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
	 * Update grade, evaluation form file and grade sheet file for student submission.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding student submission for update
	 * @param file the grade sheet file
	 * @param file2 the evaluation form file
	 * @param client the current client
	 * @return the string "UpdateGrade" for update approval
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> UpdateGrade(Connection con,Object arr,File file,File file2, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("UpdateGrade");
		Statement stmt2,stmt5,stmt6;
		String SID = null;
		String GSID = null;
		String EFID = null;
		String Semester = null;
		int Grade = 0;
		int gradeCnt = 0;
		try 
		{
			Statement stmt7;
			stmt7 = con.createStatement();			
			ResultSet rs7 = stmt7.executeQuery("SELECT * FROM semesters;");
			while(rs7.next())
			{
				if(rs7.getString(6).equals("1"))
				{
					Semester = rs7.getString(1);
					break;
				}
			}
			rs7.close();
			
			if(((ArrayList<String>)arr).get(10).equals("-"))
			{
				Statement stmt3;
				stmt3 = con.createStatement();
				ResultSet rs;		
				rs = stmt3.executeQuery("SELECT * FROM submissions;");
				int cnt = 1;
				while(rs.next())
					{
					if(Integer.parseInt(rs.getString(1))>=cnt)
						cnt = 1 + Integer.parseInt(rs.getString(1));
					}
				rs.close();
				stmt3.close();
				PreparedStatement updateUnit = con.prepareStatement("INSERT INTO submissions (SID, Name, SubmissionDate, Attachments, HWName, CourseName, Semester) VALUES (?, ?, ?, ?, ?, ?, ?)");				
				updateUnit.setString(1, String.valueOf(cnt));
				SID = String.valueOf(cnt);
				updateUnit.setString(2, "-");
		
				updateUnit.setString(3, "-");
				updateUnit.setString(4, "-");
				updateUnit.setString(5, (String) ((ArrayList<Object>) arr).get(3));
				updateUnit.setString(6, (String) ((ArrayList<Object>) arr).get(4));
				updateUnit.setString(7, Semester);
				updateUnit.executeUpdate();
				updateUnit.close();	
				
				PreparedStatement updateUnit2 = con.prepareStatement("INSERT INTO student_submissions (UID, SID, Grade) VALUES (?, ?, ?)");
				updateUnit2.setString(1, (((ArrayList<String>)arr).get(2)));
				updateUnit2.setString(2,SID);
				updateUnit2.setString(3,null);
				updateUnit2.executeUpdate();
				updateUnit2.close();	
			}
			
			int flag = 0;
			Statement stmt8 = con.createStatement();
			ResultSet rs8 = stmt8.executeQuery("SELECT * FROM submissions");			
			while(rs8.next())
			{
				stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM student_submissions");
				if(rs8.getString(5).equals(((ArrayList<String>)arr).get(3)) && rs8.getString(6).equals(((ArrayList<String>)arr).get(4)) && Semester.equals(rs8.getString(7)))
					while(rs2.next())
					{
						if(rs8.getString(1).equals(rs2.getString(2)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(2)))
						{
							SID = rs8.getString(1);
							
							flag++;
							break;
						}
					}
					rs2.close();
					if(flag>0)break;		
			}
			rs8.close();

				PreparedStatement updateUnit6 = con.prepareStatement("UPDATE student_submissions SET Grade = ? ,Checked = ? WHERE UID = ? AND SID = ? ");				
				updateUnit6.setString(1, ((ArrayList<String>)arr).get(1));
				updateUnit6.setString(2, "Yes");
				updateUnit6.setString(3, ((ArrayList<String>)arr).get(2));
				updateUnit6.setString(4, SID);
				updateUnit6.executeUpdate();
				updateUnit6.close();	


			
			stmt5 = con.createStatement();
			ResultSet rs5 = stmt5.executeQuery("SELECT * FROM student_submissions");
			
			while(rs5.next())
			{
				stmt6 = con.createStatement();
				ResultSet rs6 = stmt6.executeQuery("SELECT * FROM submissions");
				while(rs6.next())
				{
					if(rs5.getString(1).equals(((ArrayList<String>)arr).get(2)) && rs5.getString(2).equals(rs6.getString(1)) && rs6.getString(6).equals(((ArrayList<String>)arr).get(4)) && Semester.equals(rs6.getString(7)) && rs5.getString(4).equals("Yes"))
					{
						Grade = Grade + Integer.parseInt(rs5.getString(3));
						gradeCnt++;
						break;
					}
				}
				rs6.close();
			}
			rs5.close();
			
			PreparedStatement updateUnit3 = con.prepareStatement("UPDATE student_in_a_course SET Grade = ? WHERE UID = ? AND CourseName = ? AND SemID = ?");	
			updateUnit3.setInt(1, Grade/gradeCnt);
			updateUnit3.setString(2, ((ArrayList<String>)arr).get(2));
			updateUnit3.setString(3, ((ArrayList<String>)arr).get(4));
			updateUnit3.setString(4, Semester);
			updateUnit3.executeUpdate();
			updateUnit3.close();	
			
			
			
			Statement stmt3;
			stmt3 = con.createStatement();
			ResultSet rs3;		
			rs3 = stmt3.executeQuery("SELECT * FROM grade_sheets;");
			
			int cnt = 1;
			while(rs3.next())
				{
				if(Integer.parseInt(rs3.getString(1))>=cnt)
					cnt = 1 + Integer.parseInt(rs3.getString(1));
				}
			rs3.close();
			stmt3.close();
			
			if(((ArrayList<String>)arr).get(10).equals("-"))
			{
			PreparedStatement updateUnit = con.prepareStatement("INSERT INTO grade_sheets (GSID, Name, HWName, SubName, Attachments, Semester) VALUES (?, ?, ?, ?, ?, ?)");				
			updateUnit.setString(1, String.valueOf(cnt));
			GSID = String.valueOf(cnt);
			updateUnit.setString(2,"-");
			updateUnit.setString(3, ((String) ((ArrayList<Object>) arr).get(3)));
			updateUnit.setString(4, "-");
			updateUnit.setString(5, "-");
			updateUnit.setString(6, Semester);
			updateUnit.executeUpdate();
			updateUnit.close();		
			}
			else
			{
			PreparedStatement updateUnit = con.prepareStatement("INSERT INTO grade_sheets (GSID, Name, HWName, SubName, Attachments, Semester) VALUES (?, ?, ?, ?, ?, ?)");				
			updateUnit.setString(1, String.valueOf(cnt));
			GSID = String.valueOf(cnt);
			updateUnit.setString(2,((String) ((ArrayList<Object>) arr).get(2))+ "-CheckedHW-"+((String) ((ArrayList<Object>) arr).get(9)));
			updateUnit.setString(3, ((String) ((ArrayList<Object>) arr).get(3)));
			updateUnit.setString(4, ((String) ((ArrayList<Object>) arr).get(9)));
			updateUnit.setString(5, file.toString());
			updateUnit.setString(6, Semester);
			updateUnit.executeUpdate();
			updateUnit.close();	
			}
			
			PreparedStatement updateUnit2 = con.prepareStatement("INSERT INTO student_grade_sheets (UID, GSID) VALUES (?, ?)");
			updateUnit2.setString(1, (((ArrayList<String>)arr).get(2)));
			updateUnit2.setString(2,GSID);
			updateUnit2.executeUpdate();
			updateUnit2.close();	
			
			
			
			
			Statement stmt4;
			stmt4 = con.createStatement();
			ResultSet rs4;		
			rs4 = stmt4.executeQuery("SELECT * FROM evaluation_forms;");
			
			int cnt2 = 1;
			while(rs4.next())
				{
				if(Integer.parseInt(rs4.getString(1))>=cnt2)
					cnt2 = 1 + Integer.parseInt(rs4.getString(1));
				}
			rs4.close();
			stmt4.close();
			PreparedStatement updateUnit4 = con.prepareStatement("INSERT INTO evaluation_forms (EFID, Name, HWName, SubName, Attachments, Semester) VALUES (?, ?, ?, ?, ?, ?)");				
			updateUnit4.setString(1, String.valueOf(cnt2));
			EFID = String.valueOf(cnt2);
			updateUnit4.setString(2, ((String) ((ArrayList<Object>) arr).get(2))+"-Evaluation-"+((String) ((ArrayList<Object>) arr).get(9)));
			updateUnit4.setString(3, ((String) ((ArrayList<Object>) arr).get(3)));
			updateUnit4.setString(4, ((String) ((ArrayList<Object>) arr).get(9)));
			updateUnit4.setString(5, file2.toString());
			updateUnit4.setString(6, Semester);
			updateUnit4.executeUpdate();
			updateUnit4.close();	
			
			PreparedStatement updateUnit5 = con.prepareStatement("INSERT INTO student_evaluation_forms (UID, EFID) VALUES (?, ?)");
			updateUnit5.setString(1, (((ArrayList<String>)arr).get(2)));
			updateUnit5.setString(2,EFID);
			updateUnit5.executeUpdate();
			updateUnit5.close();	
		
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	
	
	/**
	 * find all teacher courses and classes appointing for current semester.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding all teacher courses and classes appointing for current semester.
	 * @param client the current client
	 * @return the teacher courses and classes appointing for current semester.
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> DisplayAppointings(Connection con,Object arr,ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("DisplayAppointings");
		Statement stmt;
		String Semester=null;
		try {
			Statement stmt7;
			stmt7 = con.createStatement();			
			ResultSet rs7 = stmt7.executeQuery("SELECT * FROM semesters;");
			while(rs7.next())
			{
				if(rs7.getString(6).equals("1"))
				{
					Semester = rs7.getString(1);
					break;
				}
			}
			rs7.close();
			stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course;");
			 while(rs.next())
			 {
				 if(rs.getString(1).equals(((ArrayList<String>)arr).get(1)) && Semester.equals(rs.getString(4)))
						 {
					 		Result.add(rs.getString(2));
					 		Result.add(rs.getString(3));
					 		Statement stmt2;
					 		stmt2 = con.createStatement();
							 ResultSet rs2 = stmt2.executeQuery("SELECT * FROM courses;");
							 while(rs2.next())
							 {
								 if(rs.getString(3).equals(rs2.getString(2)))
								 {
									Result.add(rs2.getString(4));
								 	Result.add(rs2.getString(3)); 
								 	break;
								 }
							 }
							 rs2.close();
						 }
			 }
			 rs.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Result;
		
	}
	
}
