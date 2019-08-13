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
 * The Class ParentMethods for all the methods that required 
 * to extract(/update) data from(/in) the database for(/by) the methods from parent controller.
 * @author Adiel
 */
public class ParentMethods
{
	/**
	 * Get all the children from DB
	 * @param con the DB connection
	 * @param arr Parent ID
	 * @param client the current client
	 * @return the list of the parent children in ArrayList to the client.
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> GetSons(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		ArrayList<String> Temp = new ArrayList<String>();
		Result.add("GetSons");
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM parent_student;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
				{
					Temp.add(rs.getString(2));
					Temp.add(rs.getString(3));
				}
			}
			int i = 0;
			while(i < Temp.size())
			{
				rs = stmt.executeQuery("SELECT * FROM users;");
				while(rs.next())
				{
					if(rs.getString(1).equals(Temp.get(i)))
					{
						if(Temp.get(i + 1).equals("No"))
						{
							Result.add(Temp.get(i));
							Result.add(rs.getString(3) + " " + rs.getString(4));
							Result.add(Temp.get(i + 1));
						}
						else
						{
							Result.add(Temp.get(i));
							Result.add(rs.getString(3) + " " + rs.getString(4) + " (Blocked)");
							Result.add(Temp.get(i + 1));
						}
						break;
					}
				}
				i += 2;
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * get all the student info from DB
	 * @param con the DB connection
	 * @param arr student name and id
	 * @param client the current client
	 * @return the details of a specific son in ArrayList to the client.
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ParentShowStdInfo(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ParentShowStdInfo");
		String SemID = null;
		Statement stmt1, stmt2;
		boolean associated = false;
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
			rs1 = stmt1.executeQuery("SELECT * FROM users;");
			while(rs1.next())
			{
				if(rs1.getString(1).equals(((ArrayList<String>) arr).get(1)))
				{
					Result.add(rs1.getString(1)); // Student ID
					Result.add(rs1.getString(3)); // First Name
					Result.add(rs1.getString(4)); // Last Name
					Result.add(rs1.getString(9)); // PhoneNumber
					Result.add(rs1.getString(10)); // Address
					Result.add(rs1.getString(5)); // Birthdate
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM student_in_a_class;");
					while(rs2.next())
					{
						if(rs2.getString(1).equals(((ArrayList<String>) arr).get(1)) && rs2.getString(3).equals(SemID))
						{
							Result.add(rs2.getString(2)); // Class Name
							associated = true;
							break;
						}
					}
					rs2.close();
					if(!associated)
					{
						Result.add("Not Associated");
					}
					break;
				}
			}
			rs1.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * get all the homework information from DB
	 * @param con the DB connection
	 * @param arr student ID
	 * @param client the current client
	 * @return the homework list of the specific son in ArrayList to the client.
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> Parent_HWInfo(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("Parent_HWInfo");
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
	 * get the file homework from DB
	 * @param conn the DB connection
	 * @param arr file details
	 * @param client the current client
	 * @return the homework file from the server to the client
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Object> GetHWFile(Connection conn, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str1 = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		ArrayList<Object> content = new ArrayList<Object>();
		content.add("ParentFile");
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
		}catch(SQLException e){
			e.printStackTrace();
			}
		return content;		
	}
	
	/**
	 * get all submission info from DB
	 * @param conn student and homework details
	 * @param arr the message from client
	 * @param client the current client
	 * @return the submission information in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> GetSubInfo(Connection conn, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ParentSubInfo");
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
	 * get the submission file from DB
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
						if (i > 0)
						{
						    extension = file.toString().substring(i+1);
						}
						File file2 = new File(Server1Controller.SubPath+"\\test123."+extension);
						try 
						{
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
							content.add("ParentSubFile");
					    	content.add(bytesArray);
					    	content.add(extension);
							break;
						}catch(IOException e){
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
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return content;		
	}
	
	/**
	 * get the evaluation form file
	 * @param conn the DB connection
	 * @param arr file details
	 * @param client the current client
	 * @return the evaluation form from the server to the client
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Object> GetEVFile(Connection conn, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str1 = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		ArrayList<Object> content = new ArrayList<Object>();
		content.add("ParentEvaluationFile");
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
						try
						{
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
							
						}catch(IOException e){
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
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return content;		
	}
	
	/**
	 * get checked homework file
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
		content.add("ParentCheckedFile");
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
							
						}catch(IOException e){
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
			
		}catch(SQLException e){
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
	public static ArrayList<String> ParentViewStdCoursesList(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ParentViewStdCoursesList");
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
}
