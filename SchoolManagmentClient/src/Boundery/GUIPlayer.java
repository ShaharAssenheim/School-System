package Boundery;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import Entity.*;
//import Entity.HomeWork;
import Controller.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The Class GUIPlayer to display all the application screens.
 */
public class GUIPlayer
{
	
	/** The Primary stage. */
	public static Stage PrimaryStage = new Stage();
	
	/** The url. */
	private URL url;
	
	/** The pane. */
	private AnchorPane pane;
	
	/** The scene. */
	private Scene scene;
	
/*------------------------------ Client ------------------------------*/
	/**
 * Connect to server screen.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
	public void ClientUI() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("ClientUI.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.setResizable(false);
		PrimaryStage.centerOnScreen();
		PrimaryStage.setTitle("Academic High School IT System");
		PrimaryStage.show();
	}

/*------------------------------- Login -------------------------------*/
	
/**
 * Display Login screen.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
	public void Login() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Login.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);
				
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.centerOnScreen();
		PrimaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
			@Override
			public void handle(WindowEvent event)
			{
				BaseController b = new BaseController();
				try
				{
					b.RunLogout();
				}catch(IOException | InterruptedException e){
					e.printStackTrace();
					}
			}
		});
		PrimaryStage.show();
	}	

/*--------------------------- System Manager ---------------------------*/
	
/**
 * Display System manager main menu.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
	public void SysMng_MainMenu() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SysMng_MainMenu.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);  
		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
		LoginController t = (LoginController)LoginController.last;
    	t.updateName();
	}
	
	/**
	 * Display System manager info.
	 *
	 * @param obj the System manager info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SysMng_Info(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SysMng_Info.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);  
		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
		BaseController b = (BaseController)BaseController.last;
		b.SetUserInfo(obj);
	}
	
	/**
	 * Display System manager changing password screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SysMng_ChngPass() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SysMng_ChngPass.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);  
		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
	}
	
	/**
	 * Display System manager add new course screen.
	 *
	 * @param obj the details of the course
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SysMng_NewCourse(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SysMng_NewCourse.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);  
		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
		SystemManagerController s = (SystemManagerController)SystemManagerController.last;
		s.TeachU(obj);
	}
	
	/**
	 * Display System manager view course screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SysMng_ViewCourse() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SysMng_ViewCourse.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);  
		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
		
	}

/*--------------------------- School Director ---------------------------*/

/**
 * Display School Director main menu.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
	public void SchDir_MainMenu() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_MainMenu.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	LoginController t = (LoginController)LoginController.last;
    	t.updateName();
	}
	
	/**
	 * Display School Director info.
	 *
	 * @param obj the School Director info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_Info(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_Info.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	BaseController b = (BaseController)BaseController.last;
		b.SetUserInfo(obj);
	}
	
	/**
	 * Display School Director change password screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ChngPass() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ChngPass.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);  
		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
	}
	
	/**
	 * Display School Director exceptional student registration screen.
	 *
	 * @param regrequest the request details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ExpStdReg(regrequest regrequest) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ExpStdReg.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
    	t.ViewExcepStdRegistration3(regrequest);
	}
	
	/**
	 * Display School Director exceptional student registration requests list screen.
	 *
	 * @param obj the exceptional student registration requests details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	public void SchDir_ExpStdRegList(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ExpStdRegList.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController b = (SchoolDirectorController)SchoolDirectorController.last;
		b.ViewExcepStdRegistration2((ArrayList<String>) obj,"");
	}
	
	/**
	 * Display School Director reports screen.
	 *
	 * @param obj the details for the report
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_Reports(ArrayList<String> obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_Reports.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
    	sch.ReportsView(obj);
	}
	
	/**
	 * Display School Director teacher appointing request screen.
	 *
	 * @param changeAppointList the request details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_TeaApp(ChangeAppointList changeAppointList) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_TeaApp.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
    	t.ViewAppointREQ3(changeAppointList);
	}
	
	/**
	 * Display School Director teacher appointing requests list screen.
	 *
	 * @param obj the teacher appointing requests details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	public void SchDir_TeaAppList(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_TeaAppList.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController b = (SchoolDirectorController)SchoolDirectorController.last;
		b.ViewAppointREQ2((ArrayList<String>) obj,"");
	}
	
	/**
	 * Display School Director temporary parent access block screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_TempAccBlk() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_TempAccBlk.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
	}
	
	/**
	 * Display School Director view semester screen.
	 *
	 * @param obj the teacher appointing requests details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewSemester(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewSemester.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);
		    			
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
		SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		sch.ViewSemesterInfo2(obj);
	}
	
	/**
	 * Display School Director view courses schedule screen.
	 *
	 * @param obj the teacher appointing requests details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewCoursesSchedule(Object obj) throws IOException
	{
		// Constructing our scene
				url = getClass().getResource("SchDir_CoursesSchedule.fxml");
		    	pane = FXMLLoader.load(url);
		    	scene = new Scene(pane);
		    		
		    	// Setting the stage
		    	PrimaryStage.setScene(scene);
		    	PrimaryStage.show();
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.UpdateSchDirScheduleTable(obj, "");
	}
	
	/**
	 * Display School Director view courses screen.
	 *
	 * @param obj the teacher appointing requests details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewCourses(Object obj) throws IOException
	{
		// Constructing our scene
				url = getClass().getResource("SchDir_ViewCourses.fxml");
		    	pane = FXMLLoader.load(url);
		    	scene = new Scene(pane);
		    		
		    	// Setting the stage
		    	PrimaryStage.setScene(scene);
		    	PrimaryStage.show();
		    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		    	sch.ViewCoursesData2(obj, "");
	}
	
	/**
	 * Display School Director choose student screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ChooseStudent() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ChooseStudent.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
	}
	
	/**
	 * Display School Director view student info screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewStdInfo() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewStdInfo.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
	}
	
	/**
	 * Display School Director view student homework list screen.
	 *
	 * @param msg the homework details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewStdHomeworksList(Object msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewStdHomeworksList.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		sch.UpdateStdHWTable(msg,"");
	}
	
	/**
	 * Display School Director view student homework screen.
	 *
	 * @param homeWork the homework details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewStdHomework(HomeWork homeWork) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewStdHomework.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		sch.updateHW(homeWork);
	}
	
	/**
	 * Display School Director view student courses list screen.
	 *
	 * @param msg the courses details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewStdCoursesList(Object msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewStdCoursesList.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		sch.UpdateStdCoursesTable(msg);
	}
	
	/**
	 * Display School Director choose parent screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ChooseParent() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ChooseParent.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
	}
	
	/**
	 * Display School Director view parent info screen.
	 *
	 * @param obj the parent info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewParentInfo(ArrayList<String> obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewParentInfo.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		sch.ViewParentInfo3(obj);
	}
	
	/**
	 * Display School Director view parent children screen.
	 *
	 * @param msg the parent children names
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewParChildren(Object msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewParentChildrenList.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		sch.UpdateChildrenTable(msg);
	}
	
	/**
	 * Display School Director choose teacher screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ChooseTeacher() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ChooseTeacher.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
	}
	
	/**
	 * Display School Director view teacher info.
	 *
	 * @param obj the teacher info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewTeacherInfo(ArrayList<String> obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewTeacherInfo.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		sch.ViewTeacherInfo3(obj);
	}
	
	/**
	 * Display School Director view teacher homework list.
	 *
	 * @param msg the homework details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewTeaHomeworksList(Object msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewTeacherHomeworkList.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		sch.UpdateTeaHWTable(msg,"");
	}
	
	/**
	 * Display School Director view teacher homework screen.
	 *
	 * @param homeWork the homework details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewTeacherHomework(HomeWork homeWork) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewTeacherHomework.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		sch.updateTeaHW(homeWork);
	}
	
	/**
	 * Display School Director view teacher courses list screen.
	 *
	 * @param msg the courses details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void SchDir_ViewTeaCoursesList(Object msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("SchDir_ViewTeacherCourses.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
		sch.UpdateTeaCoursesTable(msg);
	}
	
/*----------------------------- Secretary -----------------------------*/

/**
 * Display Secretary main menu screen.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
	public void Secretary_MainMenu() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_MainMenu.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	LoginController t = (LoginController)LoginController.last;
    	t.updateName();
	}
	
	/**
	 * Display Secretary info screen.
	 *
	 * @param obj the Secretary info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_Info(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_Info.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	BaseController b = (BaseController)BaseController.last;
		b.SetUserInfo(obj);
	}
	
	/**
	 * Display Secretary change password screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_ChngPass() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_ChngPass.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);  
		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
	}
	
	/**
	 * Display Secretary associate student screen.
	 *
	 * @param obj the student details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_AssociateStudent(ArrayList<String> obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_AssociateStudent.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SecretaryController s = (SecretaryController)SecretaryController.last;
		s.AssociateStudentPage(obj);
	}
	
	/**
	 * Display Secretary associations.
	 *
	 * @param obj the associations details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_ViewAssociations(ArrayList<String> obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_ViewAssociations.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SecretaryController s = (SecretaryController)SecretaryController.last;
		s.ViewAssociationsPage(obj);
	}
	
	/**
	 * Display Secretary change teacher appointing screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_ChngTeaApt() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_ChngTeaApt.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
	}
	
	/**
	 * Display Secretary new appointing screen.
	 *
	 * @param obj the appointing details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	public void Secretary_NewAppointing(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_NewAppointing.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SecretaryController s = (SecretaryController)SecretaryController.last;
		s.NewAppointingClasses2((ArrayList<String>) obj);
	}
	
	/**
	 * Display Secretary add new class screen.
	 *
	 * @param obj the class details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_NewClass(ArrayList<String> obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_NewClass.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SecretaryController s = (SecretaryController)SecretaryController.last;
		s.NewClassPage((ArrayList<String>) obj);
	}
	
	/**
	 * Display Secretary new exceptional student registration request screen.
	 *
	 * @param obj the request details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_NewExpStdReg(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_NewExpStdReg.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SecretaryController s = (SecretaryController)SecretaryController.last;
		s.CoursesClasType(obj);
	}
	
	/**
	 * Display Secretary open new semester screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_NewSemester() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_NewSemester.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SecretaryController s = (SecretaryController)SecretaryController.last;
    	s.SemNum();
	}
	
	/**
	 * Display Secretary view appointing screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_ViewAppointing() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_ViewAppointing.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
	}
	
	/**
	 * Display Secretary view class screen.
	 *
	 * @param obj the class details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_ViewClass(ArrayList<String> obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_ViewClass.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SecretaryController s = (SecretaryController)SecretaryController.last;
		s.ViewClassPage((ArrayList<String>) obj);
	}
	
	/**
	 * Display Secretary view course screen.
	 *
	 * @param obj the course details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_ViewCourse(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_ViewCourse.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SecretaryController s = (SecretaryController)SecretaryController.last;
		s.ChoiseCourses(obj);
	}
	
	/**
	 * Display Secretary view exceptional student registration request screen.
	 *
	 * @param obj the request details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_ViewExpStdReg(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_ViewExpStdReg.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SecretaryController s = (SecretaryController)SecretaryController.last;
		s.ChoiseCourses(obj);
	}
	
	/**
	 * Display Secretary view semester screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_ViewSemester() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_ViewSemester.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	SecretaryController s = (SecretaryController)SecretaryController.last;
    	s.SemNum();
	}
	
	/**
	 * Display Secretary view view courses schedule screen.
	 *
	 * @param obj the request details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_ViewCoursesSchedule(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_CoursesSchedule.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);
		    		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
		SecretaryController s = (SecretaryController)SecretaryController.last;
		s.UpdateSecretaryScheduleTable(obj, "");
	}
	
	/**
	 * Display Secretary Delete course from class screen.
	 *
	 * @param course the course details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_DelCourseFromClass(CoursesSchedule course) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_DelCourseFromClass.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);
				    		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
		SecretaryController s = (SecretaryController)SecretaryController.last;
		s.CourseDeletionFields(course);
	}
	
	/**
	 * Display Secretary view student data screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Secretary_ViewStudentData() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_ViewStudentData.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
	}
	
	/**
	 * Display Secretary view teacher appointing screen.
	 *
	 * @param obj the appointing details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	public void Secretary_ViewTeaApt(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Secretary_ViewTeaApt.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    		
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	if(obj!=null)
    	{
    		SecretaryController s = (SecretaryController)SecretaryController.last;
    		s.SetClassChang2((ArrayList<String>) obj);
    	}
	}
	
/*------------------------------ Teacher ------------------------------*/

/**
 * Display Teacher main menu screen.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
	public void Teacher_MainMenu() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Teacher_MainMenu.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	LoginController t = (LoginController)LoginController.last;
    	t.updateName();
	}
	
	/**
	 * Display Teacher info screen.
	 *
	 * @param obj the Teacher info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Teacher_Info(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Teacher_Info.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	BaseController b = (BaseController)BaseController.last;
		b.SetUserInfo(obj);
	}
	
	/**
	 * Display Teacher change password screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Teacher_ChngPass() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Teacher_ChngPass.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);  
		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
	}
	
	/**
	 * Display Teacher check HW screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Teacher_CheckHW() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Teacher_CheckHW.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
	}
	
	/**
	 * Display Teacher display appointing screen.
	 *
	 * @param msg the appointing details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Teacher_DispApt(ArrayList<String> msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Teacher_DispApt.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	TeacherController t = (TeacherController)TeacherController.last;
    	t.updateTeacherController2(msg);
	}
	
	/**
	 * Display Teacher add new HW screen.
	 *
	 * @param arr the courses and classes details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Teacher_NewHW(ArrayList<String> arr) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Teacher_NewHW.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	TeacherController t = (TeacherController)TeacherController.last;
		t.updateClassCB(arr);
	}
	
	/**
	 * Display Teacher student in class or course screen.
	 *
	 * @param arr the courses and classes details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Teacher_StdInClassOrCourse(ArrayList<String> arr) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Teacher_StdInClassOrCourse.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	TeacherController t = (TeacherController)TeacherController.last;
		t.updateCourse(arr);
	}
	
	/**
	 * Display Teacher student data screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Teacher_StudentData() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Teacher_StudentData.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
	}
	
	/**
	 * Display Teacher submitted HW screen.
	 *
	 * @param msg the submitted HW details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Teacher_SubmittedHW(StudentForList msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("TeacherSubmittedHW.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	TeacherController t = (TeacherController)TeacherController.last;
		t.updateStudent(msg);
	}
	
	/**
	 * Display Teacher submitted HW list screen.
	 *
	 * @param msg the courses and HWS names
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	public void Teacher_SubmittedHWList(Object msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Teacher_SubmittedHWList.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	TeacherController t = (TeacherController)TeacherController.last;
    	t.CourseChoice((ArrayList<String>)msg);
    	
	}
	
	/**
	 * Display Teacher view HW screen.
	 * @param msg  homework info
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Teacher_ViewHW(ArrayList<String> msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Teacher_ViewHW.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	TeacherController t = (TeacherController)TeacherController.last;
		t.updateCourse(msg);
	}

/*------------------------------ Student ------------------------------*/

/**
 * Display Student main menu screen.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
	public void Student_MainMenu() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Student_MainMenu.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	LoginController t = (LoginController)LoginController.last;
    	t.updateName();
	}
	
	/**
	 * Display Student info screen.
	 *
	 * @param obj the Student info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Student_Info(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Student_Info.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	BaseController b = (BaseController)BaseController.last;
		b.SetUserInfo(obj);
	}
	
	/**
	 * Display Student change password screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Student_ChngPass() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Student_ChngPass.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);  
		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
	}
	
	/**
	 * Display Student submit homework screen.
	 *
	 * @param obj the courses and HWS names
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	public void Student_SubmitHomework(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Student_SubmitHomework.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	StudentController t = (StudentController)StudentController.last;
    	if(((ArrayList<String>)obj).get(0).equals("@@@")) t.CourseChoice2((ArrayList<String>) obj);
    	else t.CourseChoice((ArrayList<String>) obj);
	}
	
	/**
	 * Display Student view homework screen.
	 *
	 * @param homeWork the homework details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Student_ViewHomework(HomeWork homeWork) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Student_ViewHW.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	StudentController s = (StudentController)StudentController.last;
		s.updateHW(homeWork);
	}
	
	/**
	 * Display Student view homework list screen.
	 *
	 * @param msg the homework details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Student_ViewHomeworksList(Object msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Student_ViewHomeworksList.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	StudentController s = (StudentController)StudentController.last;
		s.UpdateHWTable(msg,"");
	}
	
/*------------------------------ Parent ------------------------------*/

/**
 * Display Parent main menu screen.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
	public void Parent_MainMenu() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Parent_MainMenu.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	LoginController t = (LoginController)LoginController.last;
    	t.updateName();
	}
	
	/**
	 * Display Parent info screen.
	 *
	 * @param obj the Parent info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Parent_Info(Object obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Parent_Info.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	BaseController b = (BaseController)BaseController.last;
		b.SetUserInfo(obj);
	}
	
	/**
	 * Display Parent change password screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Parent_ChngPass() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Parent_ChngPass.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);  
		
		// Setting the stage
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
	}
	
	/**
	 * Display Parent view student HW list.
	 *
	 * @param msg the student HWS details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Parent_ViewStdHWList(Object msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Parent_ViewStdHWList.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	ParentController p = (ParentController)ParentController.last;
		p.UpdateStdHWTable(msg,"");
	}
	
	/**
	 * Display Parent view student HW screen.
	 *
	 * @param homeWork the homework details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Parent_ViewStdHW(HomeWork homeWork) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Parent_ViewStdHW.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	ParentController p = (ParentController)ParentController.last;
		p.updateHW(homeWork);
	}
	
	/**
	 * Display Parent view student info screen.
	 *
	 * @param obj the student names and info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Parent_ChooseStudent(ArrayList<String> obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Parent_ChooseStudent.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	ParentController p = (ParentController)ParentController.last;
    	p.SetSons(obj);
	}
	
	/**
	 * Display Parent view student info screen.
	 *
	 * @param obj the student names and info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Parent_ViewStdInfo(ArrayList<String> obj) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Parent_ViewStdInfo.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    				
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	ParentController p = (ParentController)ParentController.last;
    	p.ParentShowStdInfo2(obj);
	}
	
	/**
	 * Display School Director view student courses list screen.
	 *
	 * @param msg the courses details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Parent_ViewStdCoursesList(Object msg) throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Parent_ViewStdCourses.fxml");
    	pane = FXMLLoader.load(url);
    	scene = new Scene(pane);
    			
    	// Setting the stage
    	PrimaryStage.setScene(scene);
    	PrimaryStage.show();
    	ParentController p = (ParentController)ParentController.last;
		p.UpdateStdCoursesTable(msg);
	}
}
