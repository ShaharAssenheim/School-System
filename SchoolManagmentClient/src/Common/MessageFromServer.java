package Common;

import java.util.ArrayList;

import Controller.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;

/**
 * The Class MessageFromServer to Handle all the messages from the server.
 */
public class MessageFromServer
{
	/**
	 * Handle all the messages from the server
	 *
	 * @param msg the message from the server
	 */
	@SuppressWarnings("unchecked")
	public static void MFS(Object msg)
	{
		try
		{
			if(((ArrayList<Object>) msg).get(0).equals("Error: Can't setup connection!"))
		    {
				LoginController l = (LoginController)LoginController.last;
		    	l.ErrorMessage("Server is offline");
		    }
			else if(((ArrayList<Object>) msg).get(0) instanceof byte[])
			{
				StudentController t = (StudentController)StudentController.last;
			   	t.DownloadHW((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			} 
		    else if(((ArrayList<Object>) msg).get(0).equals("TeacherFile"))
			{
		    	((ArrayList<Object>) msg).remove(0);
				TeacherController t = (TeacherController)TeacherController.last;
			    t.DownloadHW((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			}
		    else if(((ArrayList<Object>) msg).get(0).equals("EvaluationFile") ||
		    		((ArrayList<Object>) msg).get(0).equals("CheckedFile"))
			{
		    	((ArrayList<Object>) msg).remove(0);
				StudentController t = (StudentController)StudentController.last;
			   	t.DownloadHW((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			}
		    else if(((ArrayList<Object>) msg).get(0).equals("SubFile"))
			{
		    	((ArrayList<Object>) msg).remove(0);
				StudentController t = (StudentController)StudentController.last;
			   	t.DownloadSubFile((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			}
		    else if(((ArrayList<Object>) msg).get(0).equals("TeacherSubFile"))
			{
		    	((ArrayList<Object>) msg).remove(0);
		    	TeacherController t = (TeacherController)TeacherController.last;
			   	t.DownloadSubFile((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			}
		    else if(((ArrayList<String>) msg).get(0).equals("OK"))
		    {
		    	AlertType AT = AlertType.CONFIRMATION;
		    	String msg2 = "The file uploaded successfully";
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.message(AT ,msg2);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("OK2"))
		    {
		    	AlertType AT = AlertType.CONFIRMATION;
		    	String msg2 = "The file updated successfully";
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.message(AT ,msg2);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("Error"))
		    {
		    	AlertType AT = AlertType.ERROR;
		    	String msg2 = "The file name already exist in the selected course\n"
						+ "in the current semester.\nPlease choose another file name "
						+ "or go to Homework->View/Update Homework for editing.";
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.message(AT ,msg2);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("Error2"))
		    {
		    	AlertType AT = AlertType.ERROR;
		    	String msg2 = "You don't teach the selected course in the selected class this semester";
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.message(AT ,msg2);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("StuOK"))
		    {
		    	AlertType AT = AlertType.CONFIRMATION;
		    	String msg2 = "The file uploaded successfully";
		    	StudentController t = (StudentController)StudentController.last;
		    	t.message(AT ,msg2);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("StuError"))
		    {
		    	AlertType AT = AlertType.ERROR;
		    	String msg2 = "You already subbmited file to this HW";
		    	StudentController t = (StudentController)StudentController.last;
		    	t.message(AT ,msg2);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("Login"))
		    {
		    	LoginController t = (LoginController)LoginController.last;
		    	LoginController.Login(msg, t);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("UserInfo"))
		    {
		    	BaseController b = (BaseController)BaseController.last;
		    	b.SelectScreen(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ChangePass"))
		    {
		    	BaseController b = (BaseController)BaseController.last;
		    	if(((ArrayList<String>) msg).get(1).equals("Password Changed Successfully"))
		    		b.MessageInfo(((ArrayList<String>) msg).get(1));
		    	else
		    		b.MessageError(((ArrayList<String>) msg).get(1));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("Teachunit"))
		    {
		    	SystemManagerController s = (SystemManagerController)SystemManagerController.last;
		    	s.setTunit(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("TeachUnitEdit"))
		    {
		    	SystemManagerController s = (SystemManagerController)SystemManagerController.last;
		    	s.TeachU(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("AddCourse"))
		    {
		    	BaseController b = (BaseController)BaseController.last;
		    	b.Message(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewCourse1"))
		    {
		    		SystemManagerController s = (SystemManagerController)SystemManagerController.last;
		    		s.ViewCourseInfo(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewCourse2"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.ViewCourseInfo2(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("UpdateCourse"))
		    {
		    	BaseController b = (BaseController)BaseController.last;
		    	b.Message(msg);
		    }
		    else if (((ArrayList<String>) msg).get(0).equals("Student_HWInfo"))
		    {
		    	StudentController t = (StudentController)StudentController.last;
		    	t.ViewHomeworkList2(msg);
		    }
		    else if (((ArrayList<String>) msg).get(0).equals("CourseName"))
		    {
		    	if(((ArrayList<String>) msg).get(1).equals("1"))
		    	{
		    		StudentController t = (StudentController)StudentController.last;
		    		t.SubmitHomework2(msg);
		    	}
		    	else if(((ArrayList<String>) msg).get(1).equals("2"))
		    	{
		    		TeacherController t = (TeacherController)TeacherController.last;
		    		t.SubmissionsList2(msg);
		    	}	    		
		    }
		    else if (((ArrayList<String>) msg).get(0).equals("TCourseName"))
		    {	    		
		    		TeacherController t = (TeacherController)TeacherController.last;
		    		t.SubmissionsList2(msg);   		
		    }
		    else if (((ArrayList<String>) msg).get(0).equals("HWName"))
		    {
		    	StudentController t = (StudentController)StudentController.last;
		    	t.HomeworkChoice((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("TeacherHWName"))
		    {
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.HomeworkChoice((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("Courses and Classes"))
		    {
		    	if(((ArrayList<String>) msg).get(1).equals("3"))
		    	{
		    		((ArrayList<String>)msg).remove(0);
		    		((ArrayList<String>)msg).remove(0);
		    		TeacherController t = (TeacherController)TeacherController.last;
		    		t.ViewHomework2(((ArrayList<String>)msg));
		    	}
		    	else if(((ArrayList<String>) msg).get(1).equals("2"))
		    	{
		    		((ArrayList<String>)msg).remove(0);
		    		((ArrayList<String>)msg).remove(0);
		    		TeacherController t = (TeacherController)TeacherController.last;
		    		t.Teacher_ClassOrCourse(((ArrayList<String>)msg));
		    	}
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("TClasses"))
		    {
		    	((ArrayList<String>)msg).remove(0);
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.Teacher_NewHW(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("TCourses"))
		    {
		    	((ArrayList<String>)msg).remove(0);
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.updateCourseCB(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("SID"))
		    {
		    	((ArrayList<String>)msg).remove(0);
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.UpdateStudentData(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("StudentInfo2"))
		    {
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.ViewStudentsList2(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("StName"))
		    {
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.ShowStudentsList(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("HWInfo2"))
		    {
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.UpdateHWPage(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("FindHW"))
		    {
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.setHWInfo(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("NewSem"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).get(1).equals("Semester already exists."))
		    		s.MessageError("Semester already exists.");
		    	else
		    		s.MessageInfo("Semester Added.");
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewSem"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).size() == 4)
		    		s.SetSemesterInfo((ArrayList<String>) msg);
		    	else
		    		s.MessageError(((ArrayList<String>) msg).get(1));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("CheckCur"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).get(1).equals("Move to Edit."))
		    		s.EditSemeter();
		    	else
		    		s.MessageError("You Cann't Edit This Semester");
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("EditSem"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).get(1).equals("Edit Succeed."))
		    	{
		    		s.MessageInfo("Edit Succeed");
		    		s.CloseEditSem();
		    	}
		    	else
		    		s.MessageError("Edit didn't success");
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("CoursesClassType"))
		    {
		    	((ArrayList<String>)msg).remove(0);
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.setCoursesClasType(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("CourseClassesType11"))
		    {
		    	((ArrayList<String>)msg).remove(0);
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.setCoursesClasType123(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("CoursesSet") || ((ArrayList<String>) msg).get(0).equals("CoursesSet2"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.setCourses(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("NewRegRequest") || ((ArrayList<String>) msg).get(0).equals("SendTeacherChange"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    		if(((ArrayList<String>) msg).get(1).equals("The request is already sent"))
		    			s.MessageError("The request is already sent");
		    		else
		    			s.MessageInfo(((ArrayList<String>) msg).get(1));
		    	
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewRegStatus"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    		if(((ArrayList<String>) msg).size()==2)
		    		{
		    			s.MessageError(((ArrayList<String>) msg).get(1));
		    			s.clearViewRegStd();
		    		}
		    		else
		    			s.ViewRegStatus(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("OpenNewClassP"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
	    		s.OpenNCScreen((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("AddNewClass"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).get(1).equals("Succeed"))
		    		s.MessageInfo("Class Added Successfully");
		    	else
		    		s.MessageError("Class Already Exists");
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ClassSemesters"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
	    		s.SetVCSemesters((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewSemesterClasses") || ((ArrayList<String>) msg).get(0).equals("ViewSemesterClasses2"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
	    		s.ViewSemesterClasses((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewMaxStudents"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
	    		s.setMaxStudentsValue((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("EditClassInfo"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).size() == 2)
		    		s.GetClassEditFields();
		    	else
		    		s.MessageError("You cann't edit a class from an old semester");
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("SaveClassEdit"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).size() == 2)
		    	{
		    		s.MessageInfo("The class updated successfully");
		    		s.BackAfterClassEdit();
		    	}
		    	else
		    		s.MessageError("Update Failed");
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("SearchTeacher") || ((ArrayList<String>) msg).get(0).equals("SearchTeacher2"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).size() == 3)
		    		s.MessageInfo("The Teacher doesnt teach Any Class");
		    	else if(((ArrayList<String>) msg).get(0).equals("SearchTeacher"))
		    		s.SetClassChang2((ArrayList<String>) msg);
		    	else
		    		s.SetClassChang((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("GetChangeStatus"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.GetChangeStatus2((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("StudentInfo"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).size() == 2)
		    		s.MessageError(((ArrayList<String>) msg).get(1));
		    	else
		    		s.StudentInfo((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("SubInfo"))
		    {
		    	StudentController t = (StudentController)StudentController.last;
		    	t.updateHW2(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("DisplayAppointings"))
		    {
		    	TeacherController t = (TeacherController)TeacherController.last;
		    	t.updateTeacherController(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("SaveStudentInfo"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.MessageInfo("Student Info Updated Succefully");
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("OpenAssociateStudentPage"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.OpenAssStdPage((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("GetMaxAndCurStudentsNum"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.SetMaxAndCurStudentsNum((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("AddStdToClass"))
	    	{
	    		SecretaryController s = (SecretaryController)SecretaryController.last;
	    		if(((ArrayList<String>) msg).size()>2)
	    			s.MessageInfo(((ArrayList<String>) msg).get(1));
	    		else 
	    			s.MessageError(((ArrayList<String>) msg).get(1));
	    		
	    	}
		    else if(((ArrayList<String>) msg).get(0).equals("RemoveStdToClass"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).get(1).equals("Student Deleted Successfully"))
		    		s.MessageInfo(((ArrayList<String>) msg).get(1));
		    	else
		    		s.MessageError(((ArrayList<String>) msg).get(1));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("OpenViewAssociationsPage"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
	    		s.OpenViewAssPage((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewStudentAssociation"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).size() == 3)
		    		s.StdAss((ArrayList<String>) msg);
		    	else
		    		s.MessageError(((ArrayList<String>) msg).get(1));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("SaveClassesToCourse"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).get(0).equals("1"))
		    		s.MessageInfo(((ArrayList<String>) msg).get(2));
		    	else
		    		s.MessageInfo(((ArrayList<String>) msg).get(2));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("NewAppointingClasses"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.NewAppointingClasses((ArrayList<String>) msg);
		    	
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("AddNewApp"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	if(((ArrayList<String>) msg).get(1).equals("1"))
		    		s.MessageError("The Class Already Have A Teacher");
		    	else if(((ArrayList<String>) msg).get(1).equals("2"))
		    		s.MessageInfo("The Teacher Appoint Succefuly");
		    	else if(((ArrayList<String>) msg).get(1).equals("3"))
		    		s.MessageError("The Teacher Teaching Unit Dont Match To The Course");
		    	else
		    		s.MessageError("The Teacher Max Weekly Hours Is Exceed");
		    }	
		    else if(((ArrayList<String>) msg).get(0).equals("ViewTeaAppointing"))
	    	{
	    		SecretaryController s = (SecretaryController)SecretaryController.last;
	    		if(((ArrayList<String>) msg).size() == 2)
	    			s.MessageError(((ArrayList<String>) msg).get(1));
	    		else
	    			s.ViewTeaAppointing2((ArrayList<String>) msg);
	    		
	    	}
		    else if(((ArrayList<String>) msg).get(0).equals("ForgotPass"))
		    {
		    	LoginController t = (LoginController)LoginController.last;
		    	t.SendPassToMail(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("SDViewStdInfo"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	if(((ArrayList<String>) msg).size() == 2)
		    		sch.MessageError(((ArrayList<String>) msg).get(1));
		    	else
		    		sch.ViewStudentInfo2((ArrayList<String>) msg);    		
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("GetClasses"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	if(((ArrayList<String>) msg).get(1).equals("No Classes Are Found"))
		    		sch.MessageError("No Classes Are Found");
		    	else
		    		sch.SetClassesNames((ArrayList<String>)msg);
		    	
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("AccessBlock"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	if(((ArrayList<String>) msg).get(1).equals("User Blocked") || ((ArrayList<String>) msg).get(1).equals("User Unblocked"))
		    		sch.MessageInfo(((ArrayList<String>) msg).get(1));
		    	else
		    		sch.MessageError(((ArrayList<String>) msg).get(1));
		    	
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("GetLast10Semesters"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.viewReports((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewExceptionalStudentRegistration"))
		    {
		    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		    	t.ViewExcepStdRegistration((ArrayList<String>)msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewAppointREQ"))
		    {
		    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		    	t.ViewAppointREQ((ArrayList<String>)msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ApproveExpReg")||((ArrayList<String>) msg).get(0).equals("RejectExpReg"))
		    {
		    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		    	t.MessageInfo(((ArrayList<String>) msg).get(1));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("RejectAppChng")||((ArrayList<String>) msg).get(0).equals("ApproveAppChng"))
		    {
		    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		    	t.MessageInfo(((ArrayList<String>) msg).get(1));	
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("GenerateReport1"))
		    {
		    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		    		t.ShowGraph1(((ArrayList<String>) msg));
		    	
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("GenerateReport2"))
		    {
		    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		    	t.ShowGraph2(((ArrayList<String>) msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("GenerateReport3"))
		    {
		    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		    	t.ShowGraph3(((ArrayList<String>) msg));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("GenerateReportError"))
		    {
		    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		    	t.MessageError("Teacher ID doesn't exist");
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewBlockingCheckBox"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	if(((ArrayList<String>) msg).get(1).equals("Yes") || ((ArrayList<String>) msg).get(1).equals("No"))
		    		sch.ViewBlkCheckBox(((ArrayList<String>) msg).get(1));
		    	else
		    		sch.MessageError(((ArrayList<String>) msg).get(1));
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("GetSons"))
		    {
		    	ParentController p = (ParentController)ParentController.last;
		    	p.ViewStudentDataPage((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ParentShowStdInfo"))
		    {
		    	ParentController p = (ParentController)ParentController.last;
		    	p.ParentShowStdInfo((ArrayList<String>) msg);
		    }
		    else if (((ArrayList<String>) msg).get(0).equals("Parent_HWInfo"))
		    {
		    	ParentController p = (ParentController)ParentController.last;
		    	p.ViewStdHomeworks2(msg);
		    }
		    else if(((ArrayList<Object>) msg).get(0).equals("ParentFile"))
			{
		    	((ArrayList<Object>) msg).remove(0);
		    	ParentController t = (ParentController)ParentController.last;
			   	t.DownloadHW((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			}
		    else if(((ArrayList<String>) msg).get(0).equals("ParentSubInfo"))
		    {
		    	ParentController t = (ParentController)ParentController.last;
		    	t.updateHW2(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<Object>) msg).get(0).equals("ParentSubFile"))
			{
		    	((ArrayList<Object>) msg).remove(0);
		    	ParentController t = (ParentController)ParentController.last;
			   	t.DownloadSubFile((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			}
		    else if(((ArrayList<Object>) msg).get(0).equals("ParentEvaluationFile") ||
		    		((ArrayList<Object>) msg).get(0).equals("ParentCheckedFile"))
			{
		    	((ArrayList<Object>) msg).remove(0);
		    	ParentController t = (ParentController)ParentController.last;
			   	t.DownloadHW((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			}
		    else if (((ArrayList<String>) msg).get(0).equals("SDViewStdHomeworkList"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.ViewStdHomeworks2(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ParentSubInfo"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.updateHW2(((ArrayList<String>)msg));
		    }
		    else if(((ArrayList<Object>) msg).get(0).equals("SchDirEvaluationFile") ||
		    		((ArrayList<Object>) msg).get(0).equals("SchDirCheckedFile"))
			{
		    	((ArrayList<Object>) msg).remove(0);
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
			   	sch.DownloadHW((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			}
		    else if(((ArrayList<Object>) msg).get(0).equals("SchDirFile"))
			{
		    	((ArrayList<Object>) msg).remove(0);
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
			   	sch.DownloadHW((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			}
		    else if(((ArrayList<Object>) msg).get(0).equals("SchDirSubFile"))
			{
		    	((ArrayList<Object>) msg).remove(0);
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
			   	sch.DownloadSubFile((byte[])((ArrayList<Object>) msg).get(0),(String)((ArrayList<Object>) msg).get(1));
			}
		    else if(((ArrayList<String>) msg).get(0).equals("SchDirSubInfo"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.updateHW2(((ArrayList<String>)msg));
		    }
		    else if (((ArrayList<String>) msg).get(0).equals("SDViewStdCoursesList"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.ViewStdCourses2(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("SDViewParInfo"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	if(((ArrayList<String>) msg).size() == 2)
		    		sch.MessageError(((ArrayList<String>) msg).get(1));
		    	else
		    		sch.ViewParentInfo2((ArrayList<String>) msg);    		
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewParChildren"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.ViewChildrenTable(msg);    		
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("SDViewTeaInfo"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	if(((ArrayList<String>) msg).size() == 2)
		    		sch.MessageError(((ArrayList<String>) msg).get(1));
		    	else
		    		sch.ViewTeacherInfo2((ArrayList<String>) msg);    		
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewTeaHomeworks"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.ViewTeaHomeworks2(msg);    		
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewTeaCourses"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.ViewTeaCourses2(msg);    		
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewCoursesSchedule"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.ViewCoursesSchedule2(msg);    		
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("Last10SemestersYears"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.ViewSemesterInfo(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("Last10SemestersNums"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.ViewYearSemeters((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("ViewSemesterCourses"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.ViewSemesterCourses2((ArrayList<String>) msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("SchDirViewCourses"))
		    {
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.ViewCoursesData(msg);
		    }
		    else if(((ArrayList<String>) msg).get(0).equals("DeleteCourseFromSchedule"))
		    {
		    	SecretaryController s = (SecretaryController)SecretaryController.last;
		    	s.MessageInfo(((ArrayList<String>)msg).get(1));
		    	ActionEvent event = null;
		    	s.ViewCoursesSchedule(event);
		    }
		    else if (((ArrayList<String>) msg).get(0).equals("ParentViewStdCoursesList"))
		    {
		    	ParentController p = (ParentController)ParentController.last;
		    	p.ViewStdCourses2(msg);
		    }
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    	}
	}
}
