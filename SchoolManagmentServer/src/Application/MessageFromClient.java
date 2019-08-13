package Application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import Controller.*;
import OCSF.ConnectionToClient;

/**
 * The Class MessageFromClient to handle with the messages from the client.
 */
public class MessageFromClient
{
	
	/**
	 * Handle with the message from the client
	 *
	 * @param arr the the message from the client
	 * @param client the current client
	 * @param conn the connection to DB
	 */
	@SuppressWarnings("unchecked")
	public static void MFC(Object arr, ConnectionToClient client, Connection conn)
	{
		String str = "Message received: " + arr + " from " + client;
		Server2Controller t = (Server2Controller)Server2Controller.last;
		t.PrintToLog(str);
		if(((ArrayList<Object>) arr).get(0) instanceof byte[])
		{
			try {
				
				ArrayList<String> Result = new ArrayList<String>();
				Result.add(TeacherMethods.UploadedFileData(conn, arr, client));
				client.sendToClient(Result);
			}				
			catch (IOException e) {
				e.printStackTrace();
				}
		}
		else if(((ArrayList<Object>) arr).get(0).equals("studentSub")) //upload file by student
		{
			try {
				ArrayList<String> Result = new ArrayList<String>();
				Result.add(StudentMethods.UploadedFileData(conn, arr, client));
				client.sendToClient(Result);
			}				
			catch (IOException e) {
				e.printStackTrace();
				}
		}		
		else if (((ArrayList<String>) arr).get(0).equals("file"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = StudentMethods.GetHWFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("TeacherFile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = TeacherMethods.GetHWFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("EvaluationFile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = StudentMethods.GetEVFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("CheckedFile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = StudentMethods.GetCHFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("Submittedfile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = StudentMethods.GetSubFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("TeacherSubmittedfile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = TeacherMethods.GetSubFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("UpdateGrade"))
		{
			try
			{
				File file = null;
				if(!(((ArrayList<String>)arr).get(10).equals("-")))
				{
					file = new File(Server1Controller.CHWPath+"\\"+((ArrayList<Object>) arr).get(2)+"-CheckedHW-"+((ArrayList<Object>) arr).get(9)+"."+((ArrayList<Object>) arr).get(8));
					file.getParentFile().mkdirs();
					@SuppressWarnings({ "unused", "resource" })
					FileWriter writer = new FileWriter(file);
					FileOutputStream fos = new FileOutputStream(file);
					fos.write((byte[])((ArrayList<Object>) arr).get(6));
					fos.close();
				}
				File file2 = new File(Server1Controller.EvPath+"\\"+((ArrayList<Object>) arr).get(2)+"-Evaluation-"+((ArrayList<Object>) arr).get(9)+"."+((ArrayList<Object>) arr).get(7));
				file2.getParentFile().mkdirs();
				@SuppressWarnings({ "unused", "resource" })
				FileWriter writer2 = new FileWriter(file2);
				FileOutputStream fos2 = new FileOutputStream(file2);
				fos2.write((byte[])((ArrayList<Object>) arr).get(5));
				fos2.close();
					
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.UpdateGrade(conn, arr,file,file2, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("Login"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = LoginMethods.Login(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ChangePass"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = UserMethods.ChangePass(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("Logout"))
				LoginMethods.Logout(conn, arr, client);
		else if(((ArrayList<String>) arr).get(0).equals("UserInfo") || ((ArrayList<String>) arr).get(0).equals("ViewStudentInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				if(((ArrayList<String>) arr).get(0).equals("UserInfo"))
					Result = UserMethods.UserInfo(conn, arr, client);
				else
					Result = UserMethods.StudentInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("TCourses")) //get courses and classes names
		{
			try 
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.TCourses(conn, arr, client);				
				client.sendToClient(Result);
				}catch(IOException e){
					e.printStackTrace();
					}	
		}
		else if(((ArrayList<String>) arr).get(0).equals("TClasses")) //get courses and classes names
		{
			try 
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.TClasses(conn, arr, client);				
				client.sendToClient(Result);
				}catch(IOException e){
					e.printStackTrace();
					}	
		}
		else if(((ArrayList<String>) arr).get(0).equals("Courses") && ((ArrayList<String>) arr).get(1).equals("Classes")) //get students list by class for teacher
		{
			try {
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.ClassAndCourse(conn, arr, client);				
				client.sendToClient(Result);
				}catch(IOException e){
					e.printStackTrace();
					}	
		}
		else if(((ArrayList<String>) arr).get(0).equals("Classes") && ((ArrayList<String>) arr).get(1).equals("Students")) //get students list by class for teacher
		{
			try {
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.StudentsFromClass(conn, arr, client);				
				client.sendToClient(Result);
				}catch(IOException e){
					e.printStackTrace();
					}	
		}
		else if(((ArrayList<String>) arr).get(0).equals("Courses") && ((ArrayList<String>) arr).get(1).equals("Students")) //get students list by course
		{
			try 
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.StudentsFromCourse(conn, arr, client);				
				client.sendToClient(Result);
				} catch (IOException e) {
					e.printStackTrace();
					}	
		}
		else if(((ArrayList<String>) arr).get(0).equals("Teachunit") || ((ArrayList<String>) arr).get(0).equals("TeachUnitEdit"))//get teaching unit to choisebox
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SystemManagerMethods.Teachunit(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("AddCourse"))//add course to DB
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SystemManagerMethods.AddCourse(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
					e.printStackTrace();
					}
				
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewCourse1"))//get course info
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SystemManagerMethods.ViewCourse(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewCourse2"))//get course info
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.ViewCourse(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("UpdateCourse"))//get course info
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SystemManagerMethods.UpdateCourse(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("Student_HWInfo")) //get all HW info
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = StudentMethods.Student_HWInfo(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("HWInfo2")) //get all HW info
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.HWInfo2(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("HWName")) //get just HW name and DLD
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = StudentMethods.HWName(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("TeacherHWName")) //get just HW names
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.HWName(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("CourseName")) //get courses names for student or teacher
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = StudentMethods.CourseName(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("TCourseName")) //get courses names for student or teacher
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.TCourseName(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("StName")) //get students list by HW name
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.StudentsNames(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SID")) //get student data
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.GetStudentData(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("FindHW")) //get student data
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.FindHW(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("NewSemester"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.NewSem(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewSemester"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.ViewSem(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("CheckCurrent"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.CheckCur(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("EditSemester"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.EditSem(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("CoursesClassType"))//get course info
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.CoursesClasses(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("CourseClassesType11"))//get course info
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.CourseClassesType11(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("CoursesSet") || ((ArrayList<String>) arr).get(0).equals("CoursesSet2"))//get course info
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.Courses(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("NewRegRequest"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.NewRegRequest(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewRegStatus"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.ViewRegStatus(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("OpenNewClassPage"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.OpenNewClassP(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("AddNewClass"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.AddNewClass(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewClassSemesters"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.ViewClassSemesters(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewSemesterClasses"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.ViewSemesterClasses(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewSemesterClasses2"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.ViewSemesterClasses2(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewMaxStudents"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.ViewMaxStudents(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("EditClassInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.EditClassInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SaveClassEdit"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.SaveClassEdit(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SearchTeacher") || ((ArrayList<String>) arr).get(0).equals("SearchTeacher2"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.SearchTeacher(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SendTeacherChange"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.SendTeacherChange(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("GetChangeStatus"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.GetChangeStatus(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SubInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = StudentMethods.GetSubInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("DisplayAppointings"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = TeacherMethods.DisplayAppointings(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SaveStudentInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.SaveStudentInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("OpenAssociateStudentPage"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.OpenAssociateStudentPage(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("GetMaxAndCurStudentsNum"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.GetMaxAndCurStudentsNum(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("AddStdToClass"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.AddStdToClass(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("RemoveStdToClass"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.RemoveStdToClass(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("OpenViewAssociationsPage"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.OpenViewAssociationsPage(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewStudentAssociation"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.ViewStudentAssociation(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SaveClassesToCourse"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.SaveClassesToCourse(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("NewAppointingClasses"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.NewAppointingClasses(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("AddNewApp"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.AddNewApp(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewTeaAppointing"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.ViewTeaAppointing(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ForgotPass"))//FORGOT EMAIL
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = LoginMethods.ForgotPass(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
					}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SDViewStdInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.SDViewStdInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("GetClasses"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.GetClasses(conn,arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("AccessBlock"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.AccessBlock(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
					}
		}
		else if(((ArrayList<String>) arr).get(0).equals("GetLast10Semesters"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.GetLast10Semesters(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewExceptionalStudentRegistration"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.ViewExceptionalStudentRegistration(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewAppointREQ"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.ViewAppointREQ(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ApproveExpReg"))//approved exceptional student request
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.ApproveExpReg(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("RejectExpReg"))//reject exceptional student request
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.RejectExpReg(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ApproveAppChng"))//approve change teacher appointing
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.ApproveAppChng(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("RejectAppChng"))//reject change teacher appointing
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.RejectAppChng(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("GenerateReport"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.GenerateReport(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewBlockingCheckBox"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.ViewBlockingCheckBox(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("GetSons"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = ParentMethods.GetSons(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ParentShowStdInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = ParentMethods.ParentShowStdInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("Parent_HWInfo")) //get all HW info
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = ParentMethods.Parent_HWInfo(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("ParentFile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = ParentMethods.GetHWFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ParentSubInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = ParentMethods.GetSubInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("ParentSubmittedfile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = ParentMethods.GetSubFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("ParentEvaluationFile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = ParentMethods.GetEVFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("ParentCheckedFile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = ParentMethods.GetCHFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SDViewStdHomeworkList")) //get all HW info
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.SDViewStdHomeworkList(conn ,arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SchDirSubInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.GetSubInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("SchDirCheckedFile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = SchoolDirectorMethods.GetCHFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("SchDirEvaluationFile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = SchoolDirectorMethods.GetEVFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("SchDirFile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = SchoolDirectorMethods.GetHWFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("SchDirSubmittedfile"))
		{
			try
			{
				ArrayList<Object> Result = new ArrayList<Object>();
				Result = SchoolDirectorMethods.GetSubFile(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("SDViewStdCoursesList"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.SDViewStdCoursesList(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SDViewParInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.SDViewParInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewParChildren"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.ViewParChildren(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SDViewTeaInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.SDViewTeaInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewTeaHomeworks"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.ViewTeaHomeworks(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewTeaCourses"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.ViewTeaCourses(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("Last10SemestersYears"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.Last10SemestersYears(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("Last10SemestersNums"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.Last10SemestersNums(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewCoursesSchedule"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.ViewCoursesSchedule(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("GetSemesterInfo"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.GetSemesterInfo(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("ViewSemesterCourses"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.ViewSemesterCourses(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("SchDirViewCourses"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SchoolDirectorMethods.SchDirViewCourses(conn, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if(((ArrayList<String>) arr).get(0).equals("DeleteCourseFromSchedule"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = SecretaryMethods.DeleteCourseFromSchedule(conn, arr, client);
				client.sendToClient(Result);
			}catch(IOException e){
				e.printStackTrace();
				}
		}
		else if (((ArrayList<String>) arr).get(0).equals("ParentViewStdCoursesList"))
		{
			try
			{
				ArrayList<String> Result = new ArrayList<String>();
				Result = ParentMethods.ParentViewStdCoursesList(conn, arr, client);
				client.sendToClient(Result);
			}catch (IOException e){
				e.printStackTrace();
				}
		}
	}
}
