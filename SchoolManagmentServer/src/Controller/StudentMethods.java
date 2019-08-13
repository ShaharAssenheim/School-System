package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
 * The Class StudentMethods for all the methods that required 
 * to extract(/update) data from(/in) the database for(/by) the methods from student controller.
 * @author Eliran Abergel
 */
public class StudentMethods
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
		String OK = "StuOK";
		String Error = "StuError";
		String SID;
		int flag=0;
		File file = null;
		try
		{
			String Semester = null;
			String Course = (String) ((ArrayList<Object>) arr).get(2);
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
			
			
			if(((ArrayList<Object>) arr).size()==8)
			{
				Statement stmt1,stmt2;
				stmt1 = conn.createStatement();			
				ResultSet rs1 = stmt1.executeQuery("SELECT * FROM submissions;");
				stmt2 = conn.createStatement();			
				while(rs1.next())
				{
					if(rs1.getString(5).equals(((ArrayList<Object>) arr).get(3)) && rs1.getString(6).equals(((ArrayList<Object>) arr).get(2)) && rs1.getString(7).equals(Semester))
					{
						ResultSet rs2 = stmt2.executeQuery("SELECT * FROM student_submissions;");
						while(rs2.next())
						{
							if(rs2.getString(1).equals(((ArrayList<Object>) arr).get(7)) && rs2.getString(2).equals(rs1.getString(1)))
							{
								flag=2;
								break;
							}
						}
						rs2.close();
						if(flag==2) break;
					}
				}
				rs1.close();
			}
			
			
			if(flag==0)
			{
			try {
				file = new File(Server1Controller.SubPath+"\\"+Semester+"\\"+Course+"\\"+((ArrayList<Object>) arr).get(7)+"-"+((ArrayList<Object>) arr).get(4)+"."+((ArrayList<Object>) arr).get(6));
				if(file.exists() && !file.isDirectory())
				{
					flag++;
				}
				file.getParentFile().mkdirs();
				FileWriter writer = new FileWriter(file);
				FileOutputStream fos = new FileOutputStream(file);
				fos.write((byte[])((ArrayList<Object>) arr).get(1));
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			if(flag == 1)
			{
				PreparedStatement updateUnit4 = conn.prepareStatement("UPDATE submissions SET SubmissionDate = ? WHERE Attachments = ? ");		
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				updateUnit4.setString(1, dateFormat.format(date));
				updateUnit4.setString(2, file.toString());
				updateUnit4.executeUpdate();
				updateUnit4.close();
			}
			else
			{	
			Statement stmt;
			stmt = conn.createStatement();
			ResultSet rs;		
				rs = stmt.executeQuery("SELECT * FROM submissions;");	
			int cnt = 1;
			while(rs.next())
				{
				if(Integer.parseInt(rs.getString(1))>=cnt)
					cnt = 1 + Integer.parseInt(rs.getString(1));
				}
			rs.close();
			stmt.close();
			PreparedStatement updateUnit = conn.prepareStatement("INSERT INTO submissions (SID, Name, SubmissionDate, Attachments, HWName, CourseName, Semester) VALUES (?, ?, ?, ?, ?, ?, ?)");				
			updateUnit.setString(1, String.valueOf(cnt));
			SID = String.valueOf(cnt);
			updateUnit.setString(2, ((ArrayList<Object>) arr).get(7)+"-"+((String) ((ArrayList<Object>) arr).get(4)));
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			
			updateUnit.setString(3, dateFormat.format(date));
			updateUnit.setString(4, file.toString());
			updateUnit.setString(5, (String) ((ArrayList<Object>) arr).get(3));
			updateUnit.setString(6, (String) ((ArrayList<Object>) arr).get(2));
			updateUnit.setString(7, Semester);
			updateUnit.executeUpdate();
			updateUnit.close();	
			
			PreparedStatement updateUnit2 = conn.prepareStatement("INSERT INTO student_submissions (UID, SID, Grade) VALUES (?, ?, ?)");
			updateUnit2.setString(1, (((ArrayList<String>)arr).get(7)));
			updateUnit2.setString(2,SID);
			updateUnit2.setString(3,null);
			updateUnit2.executeUpdate();
			updateUnit2.close();	
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag==2) return Error;
		else return OK;
	}
	
	
	
	/**
	 * Gets the HW file details for download.
	 *
	 * @param conn the DB connection
	 * @param arr the HW file details
	 * @param client the current client
	 * @return the HW file or "Empty" string for error
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Object> GetHWFile(Connection conn, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str1 = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		ArrayList<Object> content = new ArrayList<Object>();
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
	 * Gets the sub file.
	 *
	 * @param conn the DB connection
	 * @param arr the submitted file details
	 * @param client the current client
	 * @return the submitted file or "Empty" string for error
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
						content.add("SubFile");
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
	 * Student HW info.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding HW
	 * @param client the current client
	 * @return the student HW info
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> Student_HWInfo(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("Student_HWInfo");
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
	 * HW name.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding HW name
	 * @param client the current client
	 * @return the HW name and deadline date
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> HWName(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		ArrayList<String> HW = new ArrayList<String>();
		Result.add("HWName");
		Statement stmt,stmt2,stmt3;
		String Semester=null;
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
				
				if(((ArrayList<String>)arr).get(1).equals(rs.getString(6)) && Semester.equals(rs.getString(7)))
				{
					stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM class_hws;");
					while(rs2.next())
					{
						if(rs.getString(1).equals(rs2.getString(2)) && rs2.getString(1).equals(rs.getString(5)))
						{
							stmt3 = con.createStatement();
							ResultSet rs3 = stmt3.executeQuery("SELECT * FROM student_in_a_class;");
							while(rs3.next())
							{
								if(!HW.contains(rs.getString(2)) && rs2.getString(1).equals(rs3.getString(2)) && rs3.getString(1).equals(((ArrayList<String>)arr).get(2)) && Semester.equals(rs3.getString(3)))
								{
								HW.add(rs.getString(2));
								Result.add(rs.getString(2)); // HW Name
								Date date = rs.getDate(3);
								DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
								String reportDate = df.format(date);
								Result.add(reportDate); // DLD
								}
							}
							rs3.close();
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
	 * Course name.
	 *
	 * @param con the DB connection
	 * @param arr the details for finding course name
	 * @param client the current client
	 * @return the course name
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> CourseName(Connection con,Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("CourseName");
		Result.add(((ArrayList<String>)arr).get(1));
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses");
			while(rs.next())
			{
				stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM student_in_a_course");
				while(rs2.next())
				{
					if(rs.getString(2).equals(rs2.getString(2)) && rs2.getString(1).equals(((ArrayList<String>)arr).get(2)) && Semester.equals(rs2.getString(4)))
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
	 * Gets the sub info.
	 *
	 * @param conn the DB connection
	 * @param arr the details for finding  submitted file info
	 * @param client the current client
	 * @return the submitted file info
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> GetSubInfo(Connection conn, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SubInfo");
		int flag = 0;
		Statement stmt,stmt2;
		String Semester = null;
		try 
		{
			Statement stmt6;
			stmt6 = conn.createStatement();			
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
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM submissions");
			while(rs.next())
			{
					if(rs.getString(5).equals(((ArrayList<String>)arr).get(2)) && rs.getString(6).equals(((ArrayList<String>)arr).get(3)) && Semester.equals(rs.getString(7)))
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
	 * Gets the EV file.
	 *
	 * @param conn the DB connection
	 * @param arr the evaluation file details
	 * @param client the current client
	 * @return the evaluation file or "Empty" string for error
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Object> GetEVFile(Connection conn, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str1 = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		ArrayList<Object> content = new ArrayList<Object>();
		content.add("EvaluationFile");
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM evaluation_forms;");
			while(rs.next())
			{
				if(str1[1].equals(rs.getString(2)))
				{
					File file = new File(rs.getString(5));
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
	 * Gets the CH file.
	 *
	 * @param conn the DB connection
	 * @param arr the checked HW file details
	 * @param client the current client
	 * @return the checked HW file or "Empty" string for error
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Object> GetCHFile(Connection conn, Object arr, ConnectionToClient client)
	{
		Statement stmt;
		String[] str1 = ((ArrayList<String>) arr).toArray(new String[((ArrayList<String>) arr).size()]);
		ArrayList<Object> content = new ArrayList<Object>();
		content.add("CheckedFile");
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM grade_sheets;");
			while(rs.next())
			{
				if(str1[1].equals(rs.getString(2)))
				{
					File file = new File(rs.getString(5));
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
	
}
