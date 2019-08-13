package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Collectors;

import OCSF.ConnectionToClient;

/**
 * The Class SecretaryMethods for all the methods that required 
 * to extract(/update) data from(/in) the database for(/by) the methods from secretary controller.
 * @author shachar & Mohamad
 */
public class SecretaryMethods
{
	
	/**
	 * Save classes to course.
	 * @param con the DB connection
	 * @param arr class and course details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> SaveClassesToCourse(Connection con, Object arr, ConnectionToClient client)
	{
		int flag=0;
		int flag44=0;
		int flag55=0;
		String ClassID;
		String SemID="";
		String SemID11="";
		String TU="";
		String WeekHours="";
		String Course="";
		String Std="";
		ArrayList<String> Result = new ArrayList<String>();
		ArrayList<String> Students = new ArrayList<String>();
		ArrayList<String> Students2 = new ArrayList<String>();
		ArrayList<String> PreCourse = new ArrayList<String>();
		Result.add("SaveClassesToCourse");
		Statement stmt;
		ClassID = ((ArrayList<String>)arr).get(1) + " " + ((ArrayList<String>)arr).get(2);
		
		try {
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
		    rs = stmt.executeQuery("SELECT * FROM classes;");
			while(rs.next())
			{
				if(rs.getString(1).equals(ClassID)&&rs.getString(2).equals(SemID11))
				{
					SemID=rs.getString(2);
					break;
				}
			}
			
			rs = stmt.executeQuery("SELECT * FROM courses;");
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>)arr).get(3)))
				{
					TU=rs.getString(4);
					WeekHours=rs.getString(3);
					Course=rs.getString(2);
					break;
				}
			}
			
			rs = stmt.executeQuery("SELECT * FROM course_pre;");
			while(rs.next())
			{
				if(rs.getString(2).equals(Course))
				{
					PreCourse.add(rs.getString(3));
				}
			}
			
			if(PreCourse.isEmpty())
			{
				flag55=1;
			}
			
			rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course;");
			while(rs.next())
			{
				if(rs.getString(2).equals(ClassID)&& rs.getString(3).equals(((ArrayList<String>)arr).get(3))&&rs.getString(4).equals(SemID))//if the class already set to the course
				{
					Result.add("1");
					Result.add("The Class "+ClassID+" Already Appoint To "+((ArrayList<String>)arr).get(3)+" Course");
					flag=1;
				}
			}
			
			rs = stmt.executeQuery("SELECT * FROM student_in_a_class;");
			while(rs.next())
			{
				if(rs.getString(2).equals(ClassID)&&rs.getString(3).equals(SemID))//if the student is in the class
				{
					Students.add(rs.getString(1));
				}
			}
			
			rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
			while(rs.next())//delete from the course student without precourses
			{
				for(int i=0;i<Students.size();i++)
				{
					if(rs.getString(1).equals(Students.get(i)))
						for(int j=0;j<PreCourse.size();j++)
						{
							if(rs.getString(2).equals(PreCourse.get(j)))
							{
								
								Students2.add(Students.get(i));
								
							}
						}
				}
			}
			
			if(Students2.size()!=Students.size()*PreCourse.size()||flag55==1)
			{
				flag44=1;
			}
			
			if(flag44==0)
			{
				Students2.clear();
				rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
				while(rs.next())//delete from the course student without precourses
				{
					for(int i=0;i<Students.size();i++)
					{
						if(rs.getString(1).equals(Students.get(i)))
							for(int j=0;j<PreCourse.size();j++)
							{
								if(rs.getString(2).equals(PreCourse.get(j))&&rs.getInt(3)<55)
								{
								
									Students2.add(Students.get(i));
									Students.remove(i);
								}
							}
					}
				}
			}
			
			int flag88=0;
			if(Students2.isEmpty()&&flag44==0)
			{
				flag88=1;
			}
			
			if(flag==0)
			{
				PreparedStatement updateUnit = con.prepareStatement( "INSERT INTO teacher_in_class_course (TID, ClassID, CourseName, SemID, TeachingUnit, WeeklyHours) VALUES(?,?, ?, ?, ?, ?)");
				  updateUnit.setString(1,"0");
				  updateUnit.setString(2,ClassID);
				  updateUnit.setString(3,((ArrayList<String>) arr).get(3));
				  updateUnit.setString(4,SemID);
				  updateUnit.setString(5,TU);
				  updateUnit.setString(6,WeekHours);
				  updateUnit.executeUpdate();
				  updateUnit.close();
				  Result.add("2");
				  Std= String.join("\n", Students2);
				  if(Students2.isEmpty()&&flag44==1&&flag55==1||flag88==1)
				  {
					  Result.add("Class "+ClassID+" Appoint Succefuly To "+((ArrayList<String>)arr).get(3)+" Course");
				  }
				  else if(flag44==0&&flag55==0)
				  {
					  Students2 = (ArrayList<String>) Students2.stream().distinct().collect(Collectors.toList());
					  Std= String.join("\n", Students2);
					  Result.add("Class "+ClassID+" Appoint Succefuly To "+((ArrayList<String>)arr).get(3)+" Course" + 
							  "\n This Student Dont Have The PreCourse And Deleted From The Course:"
							  + "\n"+Std);
				  }
				  else 
				  {
					  Students = (ArrayList<String>) Students.stream().distinct().collect(Collectors.toList());
					  Std= String.join("\n", Students);
					  Result.add("Class "+ClassID+" Appoint Succefuly To "+((ArrayList<String>)arr).get(3)+" Course" + 
							  "\n This Student Dont Have The PreCourse And Deleted From The Course:"
							  + "\n"+Std);
					  Students.clear();
				  }
				  
				  for(int i=0;i<Students.size();i++)
				  {
					  PreparedStatement updateUnit2 = con.prepareStatement( "INSERT INTO student_in_a_course ( UID, CourseName, Grade,SemID, ClassID, ExceptionalReg) VALUES(?, ?, ?, ?, ?, ?)");
					  updateUnit2.setString(1,Students.get(i));
					  updateUnit2.setString(2,Course);
					  updateUnit2.setInt(3, 000);
					  updateUnit2.setString(4,SemID);
					  updateUnit2.setString(5,ClassID);
					  updateUnit2.setString(6,"0");
					  updateUnit2.executeUpdate();
					  updateUnit2.close();
				  }
			}
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * GET FROM DB ALL THE CLASSES AND THEIR COURSES ONLY FROM CURRENT SEMSTER
	 * @param con the DB connection
	 * @param client the current client
	 * @return the semester classes list in ArrayList to the client
	 */
	public static ArrayList<String> NewAppointingClasses(Connection con, ConnectionToClient client)
	{
		String semID="";
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("NewAppointingClasses");
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
				{
					semID=rs.getString(1);
					break;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course WHERE SemID = "+semID);
			while(rs.next())
			{
				if(rs.getString(4).equals(semID))
				{
					Result.add(rs.getString(2));
					Result.add(rs.getString(3));
				}
			}
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * APPOINTING TEACHER TO A CLASS
	 * @param con the DB connection
	 * @param arr New Appointing details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> AddNewApp(Connection con, Object arr, ConnectionToClient client)
	{
		int CourseWeekHours=0;
		int TactualHours=0;
		int TmaxWeekHours=0;
		String actualHours="";
		String TU="";
		int flag=0;
		int flag2=0;
		int flag3=0;
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("AddNewApp");
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses;");
			while(rs.next())//get the course teaching unit and weekHours
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(3)))
				{
						TU=rs.getString(4);
						CourseWeekHours=Integer.parseInt(rs.getString(3));
						break;
				}
				
			}
			
			 rs = stmt.executeQuery("SELECT * FROM teachers;");
			  while(rs.next())//get the teacher max hours and actual hours
			  {
				  if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
				  {
					  TactualHours=Integer.parseInt(rs.getString(3));
					  TmaxWeekHours=Integer.parseInt(rs.getString(2));
					  break;
				  }
			  }
			
			rs = stmt.executeQuery("SELECT * FROM teacher_tunit;");
			while(rs.next())//check if teacher have the course teaching unit
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1))&&rs.getString(2).equals(TU))
				{
					flag2=1;
				}
							
			}
			if(flag2==0)
			{
				Result.add("3");
				return Result;
			}
				
			if(TactualHours+CourseWeekHours<=TmaxWeekHours)
				flag3=1;
			
			if(flag3==0)
			{
				Result.add("4");
				return Result;
			}
			
			rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course WHERE TID = 0");
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(2))&&
						rs.getString(3).equals(((ArrayList<String>) arr).get(3))&&flag2==1&&flag3==1)
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE teacher_in_class_course SET TID = ?  WHERE ClassID = ? AND CourseName = ?");
					updateUnit.setString(1,((ArrayList<String>) arr).get(1));
					updateUnit.setString(2,((ArrayList<String>) arr).get(2));
					updateUnit.setString(3,((ArrayList<String>) arr).get(3));
					updateUnit.executeUpdate();
					updateUnit.close();
					Result.add("2");
					flag=1;
				}
			}
			if(flag==1)//update the teacher actual hours
			{
				rs = stmt.executeQuery("SELECT * FROM teachers;");
				while(rs.next())
				{
					if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
					{
						TactualHours=TactualHours+CourseWeekHours;
						actualHours=String.valueOf(TactualHours);
						PreparedStatement updateUnit = con.prepareStatement( "UPDATE teachers SET ActualHours = ?  WHERE UID = ? ");
						updateUnit.setString(1,actualHours);
						updateUnit.setString(2,((ArrayList<String>) arr).get(1));
						updateUnit.executeUpdate();
						updateUnit.close();
					}
				}
			}
			rs.close();
			if(flag==0)
				Result.add("1");//class already have a teacher
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * VIEW ALL TEACHER CLASSES OFF THE CURRENT SEMSTER
	 * @param con the DB connection
	 * @param arr teacher details
	 * @param client the current client
	 * @return the teacher courses list in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewTeaAppointing(Connection con, Object arr, ConnectionToClient client)
	{
		String SemID="";
		ArrayList<String> Result = new ArrayList<String>();
		int flag=0;
		Result.add("ViewTeaAppointing");
		Statement stmt;
		try {
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
			rs = stmt.executeQuery("SELECT * FROM users WHERE UID = " + ((ArrayList<String>) arr).get(1));
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1))&&rs.getString(2).equals("4"))
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				Result.add("There Is No Teacher With That ID");
				return Result;	
			}
			flag=0;
			rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course WHERE TID = " + ((ArrayList<String>) arr).get(1) + " AND SemID = " + SemID);
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				Result.add("The Teacher Dont Have Any Classes");
				return Result;	
			}
				
			rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course WHERE TID = " + ((ArrayList<String>) arr).get(1) + " AND SemID = " + SemID);
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(1)))
				{
					Result.add(rs.getString(1));//TEACHER ID
					Result.add(rs.getString(2));//CLASS ID
					Result.add(rs.getString(3));//COURSE NAME
					Result.add(rs.getString(5));//TEACHING UNIT
					Result.add(rs.getString(6));//WEEK HOURS
				}
				
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * open new semester
	 * @param con the DB connection
	 * @param arr new semester details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> NewSem(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("NewSem");
		String ID = ((ArrayList<String>)arr).get(1) + ((ArrayList<String>)arr).get(2).substring(0, 1);
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM teachers;");
			while(rs.next())
			{
				PreparedStatement updateUnit = con.prepareStatement( "UPDATE teachers SET ActualHours = ?");
				updateUnit.setString(1,"0");
				updateUnit.executeUpdate();
				updateUnit.close();
			}
			rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(1).equals(ID))
				{
					Result.add("Semester already exists.");
					return Result;
				}	
			}
			rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				PreparedStatement updateUnit = con.prepareStatement( "UPDATE semesters SET Current  = ?");
				updateUnit.setString(1,"0");
				updateUnit.executeUpdate();
				updateUnit.close();
			}
			rs.close();
			PreparedStatement updateUnit = con.prepareStatement( "INSERT INTO semesters ( SemID, SemesterNum, StartingDate, EndingDate, Year, Current) VALUES(?, ?, ?, ?, ?, ?)");
			updateUnit.setString(1,ID);
			updateUnit.setString(2,((ArrayList<String>) arr).get(2));
			updateUnit.setString(3,((ArrayList<String>) arr).get(3));
			updateUnit.setString(4,((ArrayList<String>) arr).get(4));
			updateUnit.setString(5,((ArrayList<String>) arr).get(1));
			updateUnit.setString(6, "1");
			updateUnit.executeUpdate();
			updateUnit.close();
			Result.add("Semester Added.");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * VIEW ALL CURRENT SEMSTER DETAILS
	 * @param con the DB connection
	 * @param arr existed semester details
	 * @param client the current client
	 * @return the semester information in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewSem(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewSem");
		String ID;
		if(((ArrayList<String>)arr).get(2).equals("1. Winter"))
			ID = ((ArrayList<String>)arr).get(1) + "1";
		else if(((ArrayList<String>)arr).get(2).equals("2. Spring"))
				ID = ((ArrayList<String>)arr).get(1) + "2";
			else
				ID = ((ArrayList<String>)arr).get(1) + "3";
		Statement stmt;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(1).equals(ID))
				{
					Result.add(ID);
					Result.add(rs.getString(3));
					Result.add(rs.getString(4));
					return Result;
				}	
			}
			Result.add("Semester Doesn't Exist.");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * CHECK CURRENT SEMSTER
	 * @param con the DB connection
	 * @param arr semester details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> CheckCur(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("CheckCur");
		Statement stmt;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>)arr).get(1)))
				{
					if(rs.getString(6).equals("1"))
					{
						Result.add("Move to Edit.");
						return Result;
					}
					else
					{
						Result.add("Cann't Edit.");
						return Result;
					}
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * EDIT SEMSTER DETAILS.
	 * @param con the DB connection
	 * @param arr existed semester new details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> EditSem(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("EditSem");
		String ID;
		if(((ArrayList<String>)arr).get(3).equals("1. Winter"))
			ID = ((ArrayList<String>)arr).get(2) + "1";
		else if(((ArrayList<String>)arr).get(3).equals("2. Spring"))
				ID = ((ArrayList<String>)arr).get(2) + "2";
			else
				ID = ((ArrayList<String>)arr).get(2) + "3";
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>)arr).get(1)))
				{
					
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE semesters SET SemID = ? ,SemesterNum = ? ,StartingDate = ? ,EndingDate = ? ,Year = ? WHERE SemID = ?");
					updateUnit.setString(1,ID);
					updateUnit.setString(2,((ArrayList<String>)arr).get(3));
					updateUnit.setString(3,((ArrayList<String>)arr).get(4));
					updateUnit.setString(4,((ArrayList<String>)arr).get(5));
					updateUnit.setString(5,((ArrayList<String>)arr).get(2));
					updateUnit.setString(6,((ArrayList<String>)arr).get(1));
					updateUnit.executeUpdate();
					updateUnit.close();
				}	
			}
			rs.close();
			Result.add("Edit Succeed.");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	} 
	
	/**
	 * GET COURSES AND CLASSES DETAILS
	 * @param con the DB connection
	 * @param arr the message from client
	 * @param client the current client
	 * @return the courses names in ArrayList to the client
	 */
	public static ArrayList<String>CoursesClasses(Connection con, ConnectionToClient client)//AddCourse
	{
		Statement stmt;
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("CoursesClassType");
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses;");
			while(rs.next())
			{
				Result.add(rs.getString(2));	
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * GET COURSES AND CLASSES DETAILS
	 * @param con the DB connection
	 * @param arr course details
	 * @param client the current client
	 * @return class name in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String>CourseClassesType11(Connection con, Object arr, ConnectionToClient client)//AddCourse
	{
		String Semid="";
		Statement stmt;
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("CourseClassesType11");
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
					Semid=rs.getString(1);	
			}
		    rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course;");
			while(rs.next())
			{
				if(rs.getString(3).equals(((ArrayList<String>) arr).get(1))&&rs.getString(4).equals(Semid))
					Result.add(rs.getString(2));	
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * GET COURSES DETAILS
	 * @param con the DB connection
	 * @param arr course details
	 * @param client the current client
	 * @return the courses list in ArrayLst to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String>Courses(Connection con, Object arr, ConnectionToClient client)//AddCourse
	{
		Statement stmt;
		ArrayList<String> Result = new ArrayList<String>();
		if(((ArrayList<String>) arr).get(0).equals("CoursesSet"))
			Result.add("CoursesSet");
		else
			Result.add("CoursesSet2");
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses;");
			while(rs.next())
			{
				Result.add(rs.getString(2));	
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * OPEN NEW REQUSET FOR EXCEPTIONAL STUDENT
	 * @param con the DB connection
	 * @param arr new exceptional registration request details 
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String>NewRegRequest(Connection con, Object arr, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("NewRegRequest");
		String ID="1000";
		String Course="";
		String SemID="";
		//int flag=0;
		int flag2=0;
		Course=((ArrayList<String>) arr).get(1);
		int id=0;
		
		Statement stmt;
		try {
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
			rs = stmt.executeQuery("SELECT * FROM regrequests;");
			while(rs.next())
			{
				ID=rs.getString(1);
			}
			id=Integer.parseInt(ID);
			id++;
			ID=String.valueOf(id);
			rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(4))&&rs.getString(2).equals("5"))
				{
					flag2=1;
				}
			}
			if(flag2==0)
			{
				Result.add("There Is No Student With This ID");
				return Result;
			}
		    rs = stmt.executeQuery("SELECT * FROM regrequests;");
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(1))&&rs.getString(3).equals(((ArrayList<String>) arr).get(2))
						&&rs.getString(4).equals(((ArrayList<String>) arr).get(3))&&rs.getString(5).equals(((ArrayList<String>) arr).get(4))&&rs.getString(7).equals(SemID))
				{
					Result.add("The request is already sent");
					return Result;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
			while(rs.next())//check if the student have the precourses
			{
				if(rs.getString(1).equals(((ArrayList<String>) arr).get(4))&&((ArrayList<String>) arr).get(3).equals("Registration"))
						if(rs.getString(2).equals(Course)&&rs.getString(4).equals(SemID))
							{
								Result.add("The Student Already Regist to " + Course +" Course");
								return Result;
							}
							
			}
			
			PreparedStatement updateUnit = con.prepareStatement( "INSERT INTO regrequests ( ReqID, CourseName, ClassName, ReqType, StudentID, Status, SemID) VALUES(?, ?, ?, ?, ?, ?, ?)");
			updateUnit.setString(1,ID);
			updateUnit.setString(2,((ArrayList<String>) arr).get(1));
			updateUnit.setString(3,((ArrayList<String>) arr).get(2));
			updateUnit.setString(4,((ArrayList<String>) arr).get(3));
			updateUnit.setString(5,((ArrayList<String>) arr).get(4));
			updateUnit.setString(6,((ArrayList<String>) arr).get(5));
			updateUnit.setString(7,SemID);
			updateUnit.executeUpdate();
			updateUnit.close();
			Result.add("Request Added.");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * VIE REQUSET FOR EXCEPTIONAL STUDENT
	 * @param con the DB connection
	 * @param arr existed exceptional registration request details
	 * @param client the current client
	 * @return the request status in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String>ViewRegStatus(Connection con, Object arr, ConnectionToClient client)//Add
	{
		int flag=0;
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewRegStatus");
		String SemID="";
		Statement stmt;
		try {
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
			rs = stmt.executeQuery("SELECT * FROM regrequests;");
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(2))&&rs.getString(5).equals(((ArrayList<String>) arr).get(1))&&rs.getString(7).equals(SemID))
				{
					Result.add(rs.getString(3));
					Result.add(rs.getString(4));
					Result.add(rs.getString(6));
					flag=1;
				}
			}
			if(flag==0)
				Result.add("There is no request with this details!!!");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * VIEW COURSE DETAILS
	 * @param con the DB connection
	 * @param arr course name
	 * @param client the current client
	 * @return the course details in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String>ViewCourse(Connection con, Object arr, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewCourse2");
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses;");
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(1)))
				{
					Result.add(rs.getString(1));
					Result.add(rs.getString(3));
					Result.add(rs.getString(4));
					break;
				}
			}
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
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * OPEN NEW CLASS
	 * @param con the DB connection
	 * @param client the current client
	 * @return the current semester details in ArrayList to the client
	 */
	public static ArrayList<String> OpenNewClassP(Connection con, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("OpenNewClassP");
		Statement stmt;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
				{
					Result.add(rs.getString(5));
					Result.add(rs.getString(2));
					break;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * ADD NEW CLASS TO CURRENT SEMESTER
	 * @param con the DB connection
	 * @param arr class and semester details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> AddNewClass(Connection con, Object arr, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("AddNewClass");
		Statement stmt;
		String SemID;
		if(((ArrayList<String>)arr).get(2).equals("1. Winter"))
			SemID = ((ArrayList<String>)arr).get(1) + "1";
		else if(((ArrayList<String>)arr).get(2).equals("2. Spring"))
				SemID = ((ArrayList<String>)arr).get(1) + "2";
			else
				SemID = ((ArrayList<String>)arr).get(1) + "3";
		String ClassID;
		ClassID = ((ArrayList<String>)arr).get(3) + " " + ((ArrayList<String>)arr).get(4);
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM classes;");
			while(rs.next())
			{
				if(rs.getString(1).equals(ClassID) && rs.getString(2).equals(SemID))
				{	
					Result.add("Failed");
					return Result;
				}
			}
			PreparedStatement updateUnit = con.prepareStatement( "INSERT INTO classes (ClassID, SemID, ClassName, ClassNumber, MaxStudentsNum, CurStudentsNum) VALUES(?, ?, ?, ?, ?, ?)");
			updateUnit.setString(1,ClassID);
			updateUnit.setString(2,SemID);
			updateUnit.setString(3,((ArrayList<String>)arr).get(3));
			updateUnit.setString(4,((ArrayList<String>)arr).get(4));
			updateUnit.setString(5,((ArrayList<String>)arr).get(5));
			updateUnit.setString(6,"0");
			updateUnit.executeUpdate();
			updateUnit.close();
			Result.add("Succeed");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * VIEW THE SEMESTER CLASSES
	 * @param con the DB connection
	 * @param client the current client
	 * @return the last 10 semesters details in ArrayList to the client
	 */
	public static ArrayList<String> ViewClassSemesters(Connection con, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ClassSemesters");
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
	 * VIEW SEMSTER CLASSES
	 * @param con the DB connection
	 * @param arr semester details
	 * @param client the current client
	 * @return the classes details in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewSemesterClasses(Connection con, Object arr, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewSemesterClasses");
		String SemID;
		if(((ArrayList<String>)arr).get(2).equals("1. Winter"))
			SemID = ((ArrayList<String>)arr).get(1) + "1";
		else if(((ArrayList<String>)arr).get(2).equals("2. Spring"))
				SemID = ((ArrayList<String>)arr).get(1) + "2";
			else
				SemID = ((ArrayList<String>)arr).get(1) + "3";
		Statement stmt;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM classes;");
			while(rs.next())
			{
				if(rs.getString(2).equals(SemID))
				{
					Result.add(rs.getString(3));
					Result.add(rs.getString(4));
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * VIEW SEMSTER CLASSES
	 * @param con the DB connection
	 * @param client the current client
	 * @return the classes details in ArrayList to the client
	 */
	public static ArrayList<String> ViewSemesterClasses2(Connection con, ConnectionToClient client)//Add
	{
		String SemID="";
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewSemesterClasses2");
		Statement stmt;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))//ONLY CLASSES FROM CURRENT SEMESTER
				{
					SemID=rs.getString(1);
					break;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM classes;");
			while(rs.next())
			{
				if(rs.getString(2).equals(SemID))
					{
						Result.add(rs.getString(3));//CLASS NAME
						Result.add(rs.getString(4));//CLASS NUMBER
					}
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}

	/**
	 * VIEW MAX STUDENT
	 * @param con the DB connection
	 * @param arr semester and class details
	 * @param client the current client
	 * @return the class maximum number of student in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewMaxStudents(Connection con, Object arr, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewMaxStudents");
		String SemID;
		if(((ArrayList<String>)arr).get(2).equals("1. Winter"))
			SemID = ((ArrayList<String>)arr).get(1) + "1";
		else if(((ArrayList<String>)arr).get(2).equals("2. Spring"))
				SemID = ((ArrayList<String>)arr).get(1) + "2";
			else
				SemID = ((ArrayList<String>)arr).get(1) + "3";
		String ClassID = ((ArrayList<String>)arr).get(3) + " " + ((ArrayList<String>)arr).get(4);
		Statement stmt;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM classes;");
			while(rs.next())
				if(rs.getString(2).equals(SemID) && rs.getString(1).equals(ClassID))
					Result.add(rs.getString(5));
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * EDIT CLASS INFORMATION
	 * @param con the DB connection
	 * @param arr class details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> EditClassInfo(Connection con, Object arr, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("EditClassInfo");
		String SemID;
		if(((ArrayList<String>)arr).get(2).equals("1. Winter"))
			SemID = ((ArrayList<String>)arr).get(1) + "1";
		else if(((ArrayList<String>)arr).get(2).equals("2. Spring"))
				SemID = ((ArrayList<String>)arr).get(1) + "2";
			else
				SemID = ((ArrayList<String>)arr).get(1) + "3";
		Statement stmt;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
				if(rs.getString(1).equals(SemID) && rs.getString(6).equals("1"))
					Result.add("Start Editing");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * SAVE CLASS EDIT
	 * @param con the DB connection
	 * @param arr class new details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> SaveClassEdit(Connection con, Object arr, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SaveClassEdit");
		String SemID;
		if(((ArrayList<String>)arr).get(2).equals("1. Winter"))
			SemID = ((ArrayList<String>)arr).get(1) + "1";
		else if(((ArrayList<String>)arr).get(2).equals("2. Spring"))
				SemID = ((ArrayList<String>)arr).get(1) + "2";
			else
				SemID = ((ArrayList<String>)arr).get(1) + "3";
		String ClassID = ((ArrayList<String>)arr).get(3) + " " + ((ArrayList<String>)arr).get(4);
		Statement stmt;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM classes;");
			while(rs.next())
				if(rs.getString(2).equals(SemID) && rs.getString(1).equals(ClassID))
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE classes SET MaxStudentsNum = ? WHERE ClassID = ? AND SemID = ?");
					updateUnit.setString(1, ((ArrayList<String>)arr).get(5));
					updateUnit.setString(2, ClassID);
					updateUnit.setString(3, SemID);
					updateUnit.executeUpdate();
					updateUnit.close();
					Result.add("Update Succeed");
					break;
				}
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * SEARCH TEACHER
	 * @param con the DB connection
	 * @param arr Teacher ID
	 * @param client the current client
	 * @return the teacher courses in classes if exist in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> SearchTeacher(Connection con, Object arr, ConnectionToClient client)//Add
	{
		String SemID="";
		ArrayList<String> Result = new ArrayList<String>();
		if(((ArrayList<String>)arr).get(0).equals("SearchTeacher"))
			Result.add("SearchTeacher");
		else
			Result.add("SearchTeacher2");
		Result.add(((ArrayList<String>)arr).get(1));//old name
		
		Statement stmt;
		try
		{
			int flag=0;
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
			rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course WHERE TID = "+((ArrayList<String>)arr).get(1) + " AND SemID = "+ SemID);
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>)arr).get(1)))
				{
					
					Result.add(rs.getString(2));
					Result.add(rs.getString(3));
					flag=1;
				}
			}
			if(flag==0)
				Result.add("faild");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * changing teacher appointing, including checks for new teacher teaching unit,
	  if new teacher exist, if new teacher weekly hours not exceed
	 * @param con the DB connection
	 * @param arr appointing change details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> SendTeacherChange(Connection con, Object arr, ConnectionToClient client)//Add
	{
		String courseTu="";
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SendTeacherChange");
		String ID="2000";
		String SemID="";
		int id=0;
		int flag=0;
		int flag2=0;
		int flag3=0;
		int CourseWeekHours=0;
		int TactualHours=0;
		int TmaxWeekHours=0;
		Statement stmt;
		try {
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
			rs = stmt.executeQuery("SELECT * FROM changerequests;");
			while(rs.next())
			{
				ID=rs.getString(1);
			}
			id=Integer.parseInt(ID);
			id++;
			ID=String.valueOf(id);
		    rs = stmt.executeQuery("SELECT * FROM changerequests;");
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(1)) && rs.getString(3).equals(((ArrayList<String>) arr).get(2)) &&
						rs.getString(4).equals(((ArrayList<String>) arr).get(3)) &&rs.getString(6).equals(((ArrayList<String>) arr).get(4))&&
						rs.getString(7).equals(((ArrayList<String>) arr).get(5))&&rs.getString(8).equals(SemID))
				{
					Result.add("The request is already sent");
					return Result;
				}
			}
			  rs = stmt.executeQuery("SELECT * FROM teachers;");
			  while(rs.next())
			  {
				  if(rs.getString(1).equals(((ArrayList<String>) arr).get(4)))
				  {
					  TactualHours=Integer.parseInt(rs.getString(3));
					  TmaxWeekHours=Integer.parseInt(rs.getString(2));
					  flag=1;
					  break;
				  }
			  }
			  rs = stmt.executeQuery("SELECT * FROM courses;");
			  while(rs.next())
			  {
				  if(rs.getString(2).equals(((ArrayList<String>) arr).get(3)))
				  {
					  courseTu=rs.getString(4);
					  CourseWeekHours=Integer.parseInt(rs.getString(3));
					  break;
				  }
			  }
			  if(TactualHours+CourseWeekHours<=TmaxWeekHours)
			  {
				  flag3=1;
			  }
			  rs = stmt.executeQuery("SELECT * FROM teacher_tunit;");
			  while(rs.next())
			  {
				  if(rs.getString(1).equals(((ArrayList<String>) arr).get(4))&&rs.getString(2).equals(courseTu))
				  {
					  flag2=1;
					  break;
				  }
			  }
			  if(flag==1&&flag2==1&&flag3==1)
			  {
				  PreparedStatement updateUnit = con.prepareStatement( "INSERT INTO changerequests ( ReqID, OldTeacher, ClassName, CourseName ,TeachingUnit, NewTeacher, Status, SemID) VALUES(?, ?, ?, ?, ?,?, ?, ?)");
				  updateUnit.setString(1,ID);
				  updateUnit.setString(2,((ArrayList<String>) arr).get(1));
				  updateUnit.setString(3,((ArrayList<String>) arr).get(2));
				  updateUnit.setString(4,((ArrayList<String>) arr).get(3));
				  updateUnit.setString(5,courseTu);
				  updateUnit.setString(6,((ArrayList<String>) arr).get(4));
				  updateUnit.setString(7,((ArrayList<String>) arr).get(5));
				  updateUnit.setString(8,SemID);
				  updateUnit.executeUpdate();
				  updateUnit.close();
				  Result.add("Request Added.");
			  }
			  else if(flag==0)
				  Result.add("The New Teacher ID is Not Exist!!!");
			  else if(flag2==0)
				  Result.add("The New Teacher Dont Have This Course Teaching Unit!!!");
			  else if(flag3==0)
				  Result.add("The New Teacher Weeekly Hours Excee His Maximum!!!");
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * the function get the status of a changing teacher appointing request
	 * @param con the DB connection
	 * @param arr appointing change details
	 * @param client the current client
	 * @return the appointing change status to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> GetChangeStatus(Connection con, Object arr, ConnectionToClient client)//Add
	{
		int flag=0;
		String SemID="";
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("GetChangeStatus");
		Statement stmt;
		try {
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
				if(rs.getString(2).equals(((ArrayList<String>) arr).get(1))&&rs.getString(3).equals(((ArrayList<String>) arr).get(2))&&
						rs.getString(4).equals(((ArrayList<String>) arr).get(3)))
				{
					Result.add(rs.getString(6));
					Result.add(rs.getString(7));
					flag=1;
				}
			}
			if(flag==0)
				Result.add("faild");
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;		
	}
	
	/**
	 * SAVE STUDENT INFO
	 * @param con the DB connection
	 * @param arr student new details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> SaveStudentInfo(Connection con, Object arr, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("SaveStudentInfo");
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>)arr).get(1)))
				{
					PreparedStatement updateUnit = con.prepareStatement( "UPDATE users SET UID = ? , FirstName = ?, LastName = ?, BirthDate = ?, Password = ?, phonenumber = ?, address = ? WHERE UID = ?");
					updateUnit.setString(1, ((ArrayList<String>)arr).get(1));
					updateUnit.setString(2, ((ArrayList<String>)arr).get(2));
					updateUnit.setString(3, ((ArrayList<String>)arr).get(3));
					updateUnit.setString(4, ((ArrayList<String>)arr).get(7));
					updateUnit.setString(5, ((ArrayList<String>)arr).get(4));
					updateUnit.setString(6, ((ArrayList<String>)arr).get(5));
					updateUnit.setString(7, ((ArrayList<String>)arr).get(6));
					updateUnit.setString(8, ((ArrayList<String>)arr).get(1));
					updateUnit.executeUpdate();
					updateUnit.close();
					Result.add("Update Succeed");
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
	 * GET SEMSTER DETAILS
	 * @param con the DB connection
	 * @param client the current client
	 * @return the current semester and classes details in ArrayList to the client
	 */
	public static ArrayList<String> OpenAssociateStudentPage(Connection con, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("OpenAssociateStudentPage");
		Statement stmt;
		String SemID = "";
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM semesters;");
			while(rs.next())
			{
				if(rs.getString(6).equals("1"))
				{
					SemID = rs.getString(1);
					Result.add(rs.getString(5));
					Result.add(rs.getString(2));
					break;
				}
			}
			rs = stmt.executeQuery("SELECT * FROM classes;");
			while(rs.next())
				if(rs.getString(2).equals(SemID))
					Result.add(rs.getString(3) + " " + rs.getString(4));
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * GET THE MAXIMUM STUDENT NUMBER
	 * @param con the DB connection
	 * @param arr semester and class details
	 * @param client the current client
	 * @return the class maximum and current students in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> GetMaxAndCurStudentsNum(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("GetMaxAndCurStudentsNum");
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
				
			rs = stmt.executeQuery("SELECT * FROM classes;");
			while(rs.next())
			{
				if((rs.getString(3) + " " + rs.getString(4)).equals(((ArrayList<String>)arr).get(1))&&rs.getString(2).equals(SemID))
				{
					Result.add(rs.getString(5));
					Result.add(rs.getString(6));
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
	 * ADD STUDENT TO CLASS
	 * @param con the DB connection
	 * @param arr current semester and classes details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<String> AddStdToClass(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		ArrayList<String> Courses = new ArrayList<String>();
		ArrayList<String> PreCourses = new ArrayList<String>();
		ArrayList<String> StdPreCourses = new ArrayList<String>();
		ArrayList<String> StdmissPreCourses = new ArrayList<String>();
		ArrayList<String> StdmissCourses = new ArrayList<String>();
		String missCourse;
		Result.add("AddStdToClass");
		int cur, max;
		Statement stmt;
		try 
		{
			boolean associated = false;
			boolean precourse = false;
			boolean exist = false;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course;");
			while(rs.next())
			{
				if(rs.getString(2).equals(((ArrayList<String>)arr).get(2))&&rs.getString(4).equals(((ArrayList<String>)arr).get(1)))//classid and semid
				{
					Courses.add(rs.getString(3));
				}
			}
			rs = stmt.executeQuery("SELECT * FROM course_pre;");
			while(rs.next())
			{
				for(int i=0;i<Courses.size();i++)
					if(rs.getString(2).equals(Courses.get(i)))
						PreCourses.add(rs.getString(3));
			}
			PreCourses = (ArrayList<String>) PreCourses.stream().distinct().collect(Collectors.toList());
			rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>)arr).get(3)) && rs.getString(2).equals("5"))
				{
					exist = true;
					break;
				}
				else
					if(rs.getString(1).equals(((ArrayList<String>)arr).get(3)) && !rs.getString(2).equals("5"))
					{
						Result.add(rs.getString(3) + " " + rs.getString(4) + " is not a student");
						break;
					}
			}
			if(exist)
			{
				rs = stmt.executeQuery("SELECT * FROM student_in_a_class;");
				while(rs.next())
				{
					if((rs.getString(1).equals(((ArrayList<String>)arr).get(3)) && rs.getString(3).equals(((ArrayList<String>)arr).get(1))))
					{
						associated = true;
					}
				}
				if(!associated)
				{
					rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
					while(rs.next())
					{
						if(rs.getString(1).equals(((ArrayList<String>)arr).get(3)))
						{
							for(int i=0;i<PreCourses.size();i++)
							{
								if(rs.getString(2).equals(PreCourses.get(i))&&rs.getInt(3)>55)
									StdPreCourses.add(rs.getString(2));	
							}	
						}
					}
					if(StdPreCourses.size()==PreCourses.size())
						precourse=true;
						int flag55=0;
						if(!precourse)//if the student don't have all the precourses
						{
							for(int i=0;i<PreCourses.size();i++)
							{
								for(int j=0;j<StdPreCourses.size();j++)
								{
									if(PreCourses.get(i).equals(StdPreCourses.get(j)))
										flag55=1;
								}
								if(flag55==0)
									StdmissPreCourses.add(PreCourses.get(i));
							}
							
							rs = stmt.executeQuery("SELECT * FROM course_pre;");
							while(rs.next())
							{
								for(int i=0;i<StdmissPreCourses.size();i++)
									for(int j=0;j<Courses.size();j++)
										if(StdmissPreCourses.get(i).equals(rs.getString(3))&&Courses.get(j).equals(rs.getString(2)))
											StdmissCourses.add(Courses.get(j));
							}
						}
						StdmissCourses = (ArrayList<String>) StdmissCourses.stream().distinct().collect(Collectors.toList());
							rs = stmt.executeQuery("SELECT * FROM classes;");
							while(rs.next())
							{
								max = Integer.valueOf(rs.getString(5));
								cur = Integer.valueOf(rs.getString(6));
								if(rs.getString(1).equals(((ArrayList<String>)arr).get(2)) && rs.getString(2).equals(((ArrayList<String>)arr).get(1)))
								{
									if((max - cur) > 0)
									{
										PreparedStatement updateUnit = con.prepareStatement( "INSERT INTO student_in_a_class (UID, ClassID, SemID) VALUES(?, ?, ?)");
										updateUnit.setString(1,((ArrayList<String>)arr).get(3));
										updateUnit.setString(2,((ArrayList<String>)arr).get(2));
										updateUnit.setString(3,((ArrayList<String>)arr).get(1));
										updateUnit.executeUpdate();
										updateUnit.close();
										int a = Integer.valueOf(rs.getString(6)) + 1;
										updateUnit = con.prepareStatement( "UPDATE classes SET CurStudentsNum = ? WHERE ClassID = ? AND SemID = ?");
										updateUnit.setString(1, String.valueOf(a));
										updateUnit.setString(2, String.valueOf(((ArrayList<String>)arr).get(2)));
										updateUnit.setString(3, String.valueOf(((ArrayList<String>)arr).get(1)));
										updateUnit.executeUpdate();
										updateUnit.close();	
										if(precourse)
										{
											rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
											while(rs.next())
											{
												for(int i = 0;i<Courses.size();i++)
												{
													if(rs.getString(1).equals(((ArrayList<String>)arr).get(3))&&rs.getString(2).equals(Courses.get(i))&&rs.getString(4).equals(((ArrayList<String>)arr).get(1)))
														Courses.remove(i);
												}
											}
												for(int i = 0;i<Courses.size();i++)
												{
													updateUnit = con.prepareStatement( "INSERT INTO student_in_a_course (UID, CourseName, Grade, SemID, ClassID, ExceptionalReg) VALUES(?, ?, ?, ?, ?, ?)");
													updateUnit.setString(1,((ArrayList<String>)arr).get(3));//stid
													updateUnit.setString(2,Courses.get(i));//courseName
													updateUnit.setInt(3, 0);//grade
													updateUnit.setString(4,((ArrayList<String>)arr).get(1));//semid
													updateUnit.setString(5,((ArrayList<String>)arr).get(2));//classid
													updateUnit.setString(6,"0");//exceptional
													updateUnit.executeUpdate();
													updateUnit.close();
												}
												Result.add("Student Added Successfully");
												Result.add("1");
											}
											else//if the student don't have all precourses.
											{
												rs = stmt.executeQuery("SELECT * FROM student_in_a_course;");
												while(rs.next())
												{
													for(int i = 0;i<Courses.size();i++)
													{
														if(rs.getString(1).equals(((ArrayList<String>)arr).get(3))&&rs.getString(2).equals(Courses.get(i))&&rs.getString(4).equals(((ArrayList<String>)arr).get(1)))
															Courses.remove(i);
													}
												}	
												for(int j=0;j<StdmissCourses.size();j++)
													for(int i = 0;i<Courses.size();i++)
														if(Courses.get(i).equals(StdmissCourses.get(j)))
															Courses.remove(i);
														
														
													for(int i = 0;i<Courses.size();i++)
													{
														updateUnit = con.prepareStatement( "INSERT INTO student_in_a_course (UID, CourseName, Grade, SemID, ClassID, ExceptionalReg) VALUES(?, ?, ?, ?, ?, ?)");
														updateUnit.setString(1,((ArrayList<String>)arr).get(3));//stid
														updateUnit.setString(2,Courses.get(i));//courseName
														updateUnit.setInt(3, 0);//grade
														updateUnit.setString(4,((ArrayList<String>)arr).get(1));//semid
														updateUnit.setString(5,((ArrayList<String>)arr).get(2));//classid
														updateUnit.setString(6,"0");//exceptional
														updateUnit.executeUpdate();
														updateUnit.close();
													}
													missCourse= String.join("\n", StdmissCourses);
													Result.add("Student Added Successfully\n\nThe Student Dont Have Pre Courses For This Courses:\n"
															+ missCourse+ "\n\nAnd Was Not Associate For This Courses");
													Result.add("1");
											}
												
										break;
									}
									else
										Result.add("The Class is Full");
								}
							}
					}
					else
						Result.add("Student already associated to a class");
				}
				else
					if(Result.size() == 1)
						Result.add(((ArrayList<String>)arr).get(3) + " doesn't exist in the database.");
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * REMOVE STUDENT FROM CLASS
	 * @param con the DB connection
	 * @param arr current semester and classes details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> RemoveStdToClass(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("RemoveStdToClass");
		boolean associated = false;
		boolean exist = false;
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>)arr).get(3)) && rs.getString(2).equals("5"))
				{
					exist = true;
					break;
				}
				else
					if(rs.getString(1).equals(((ArrayList<String>)arr).get(3)) && !rs.getString(2).equals("5"))
					{
						Result.add(rs.getString(3) + " " + rs.getString(4) + " is not a student");
						break;
					}
			}
			if(exist)
			{
				rs = stmt.executeQuery("SELECT * FROM student_in_a_class;");
				while(rs.next())
				{
					if((rs.getString(1).equals(((ArrayList<String>)arr).get(3)) && rs.getString(2).equals(((ArrayList<String>)arr).get(2)) && rs.getString(3).equals(((ArrayList<String>)arr).get(1))))
					{
						PreparedStatement updateUnit = con.prepareStatement( "DELETE FROM student_in_a_class WHERE UID = ? AND ClassID = ? AND SemID = ?;");
						updateUnit.setString(1, ((ArrayList<String>)arr).get(3));
						updateUnit.setString(2, ((ArrayList<String>)arr).get(2));
						updateUnit.setString(3, ((ArrayList<String>)arr).get(1));
						updateUnit.executeUpdate();
						updateUnit.close();
						associated = true;
						break;
					}
				}
				if(associated)
				{
					rs = stmt.executeQuery("SELECT * FROM classes;");
					while(rs.next())
					{
						if(rs.getString(1).equals(((ArrayList<String>)arr).get(2)) && rs.getString(2).equals(((ArrayList<String>)arr).get(1)))
						{
							int a = (Integer.valueOf(rs.getString(6)));
							a--;
							PreparedStatement updateUnit = con.prepareStatement( "UPDATE classes SET CurStudentsNum = ? WHERE ClassID = ? AND SemID = ?");
							updateUnit.setString(1, String.valueOf(a));
							updateUnit.setString(2, ((ArrayList<String>)arr).get(2));
							updateUnit.setString(3, ((ArrayList<String>)arr).get(1));
							updateUnit.executeUpdate();
							updateUnit.close();
							break;
						}
					}
					Result.add("Student Deleted Successfully");
				}
				else
					Result.add("Student isn't associated to the class");
			}
			else
				Result.add(((ArrayList<String>)arr).get(3) + " doesn't exist in the database");
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * GET THE STUDENT ASSOCIATE DETAILS
	 * @param con the DB connection
	 * @param client the current client
	 * @return the last 10 semesters details to the client
	 */
	public static ArrayList<String> OpenViewAssociationsPage(Connection con, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("OpenViewAssociationsPage");
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
	 * GET THE STUDENT ASSOCIATE DETAILS
	 * @param con the DB connection
	 * @param arr semester and student details
	 * @param client the current client
	 * @return the association details in ArrayList to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ViewStudentAssociation(Connection con, Object arr, ConnectionToClient client)//Add
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewStudentAssociation");
		Statement stmt;
		boolean exist = false;
		boolean associated = false;
		try
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			while(rs.next())
			{
				if(rs.getString(1).equals(((ArrayList<String>)arr).get(1)))
					if(rs.getString(2).equals("5"))
					{
						exist = true;
						break;
					}
					else
					{
						Result.add(rs.getString(3) + " " + rs.getString(4) + " is not a student");
						return Result;
					}
			}
			if(exist)
			{
				rs = stmt.executeQuery("SELECT * FROM student_in_a_class;");
				while(rs.next())
				{
					if(rs.getString(1).equals(((ArrayList<String>)arr).get(1)) && rs.getString(3).equals(((ArrayList<String>)arr).get(2)))
					{
						Result.add("Found");
						Result.add(rs.getString(2));
						associated = true;
						break;
					}
				}
				if(!associated)
				{
						Result.add("Not Found");
						Result.add("Not Associated");
				}
			}
			else
				Result.add(((ArrayList<String>)arr).get(1) + " doesn't exist in the database");
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
	
	/**
	 * VIEW COURSES SCHEDULE
	 * @param con the DB connection
	 * @param client the current client
	 * @return the courses schedule in ArrayList to the client
	 */
	public static ArrayList<String> ViewCoursesSchedule(Connection con, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("ViewCoursesSchedule");
		Statement stmt1, stmt2;
		String SemID = null;
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
			rs1 = stmt1.executeQuery("SELECT * FROM teacher_in_class_course;");
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
	 * DELETE COURSE FROM SCHEDULE
	 * @param con the DB connection
	 * @param arr scheduled course details
	 * @param client the current client
	 * @return alert message to the client
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> DeleteCourseFromSchedule(Connection con, Object arr, ConnectionToClient client)
	{
		ArrayList<String> Result = new ArrayList<String>();
		Result.add("DeleteCourseFromSchedule");
		Statement stmt;
		String SemID = null;
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
			rs = stmt.executeQuery("SELECT * FROM teacher_in_class_course;");
			while(rs.next())
			{
				if(rs.getString(4).equals(SemID) && rs.getString(3).equals(((ArrayList<String>) arr).get(1)) && rs.getString(2).equals(((ArrayList<String>) arr).get(2)))
				{
					PreparedStatement updateUnit = con.prepareStatement( "DELETE FROM teacher_in_class_course WHERE ClassID = ? AND CourseName = ? AND SemID = ?;");
					updateUnit.setString(1, ((ArrayList<String>)arr).get(2));
					updateUnit.setString(2, ((ArrayList<String>)arr).get(1));
					updateUnit.setString(3, SemID);
					updateUnit.executeUpdate();
					updateUnit.close();
					Result.add("Course Deleted");
					break;
				}
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
			}
		return Result;
	}
}
