package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Boundery.GUIPlayer;
import Entity.*;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * The Class SchoolDirectorController.
 * @author shachar & Mohammad
 */
public class SchoolDirectorController extends BaseController
{

	/**
	 * Instantiates a new school director controller.
	 */
	public SchoolDirectorController()
	{
		last = this;
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				UName.setText(Username);
				setTime();
			}
		});	
	}
		
	/** The Button exceptional student registration. */
	@FXML // fx:id="Btn_ExceptionalStudentRegistration"
    private MenuItem Btn_ExceptionalStudentRegistration; // Value injected by FXMLLoader
	
    /** The Button teacher appointing. */
    @FXML // fx:id="Btn_TeacherAppointing"
    private MenuItem Btn_TeacherAppointing; // Value injected by FXMLLoader
    
    /** The Button grades reports. */
    @FXML // fx:id="Btn_GradesPerTeacher"
    private MenuItem Btn_GradesReports; // Value injected by FXMLLoader
    
    /** The Button temporary access blocking. */
    @FXML // fx:id="Btn_TemporaryAccessBlocking"
    private MenuItem Btn_TemporaryAccessBlocking; // Value injected by FXMLLoader
    
    /** The Button view courses data. */
    @FXML // fx:id="Btn_ViewCoursesData"
    private MenuItem Btn_ViewCoursesData; // Value injected by FXMLLoader    
    
    /** The Button view semester data. */
    @FXML // fx:id="Btn_ViewSemesterData"
    private MenuItem Btn_ViewSemesterData; // Value injected by FXMLLoader    
    
    /** The Button view student data. */
    @FXML // fx:id="Btn_ViewStudentData"
    private MenuItem Btn_ViewStudentData; // Value injected by FXMLLoader    
    
    /** The Button view parent data. */
    @FXML // fx:id="Btn_ViewStudentData"
    private MenuItem Btn_ViewParentData; // Value injected by FXMLLoader    
    
    /** The Button view teacher data. */
    @FXML // fx:id="Btn_ViewStudentData"
    private MenuItem Btn_ViewTeacherData; // Value injected by FXMLLoader	
    
    /** The Text select number. */
    @FXML // fx:id="TextSelectNum"
    private Text TextSelectNum; // Value injected by FXMLLoader    
    
    /** The Text select report. */
    @FXML // fx:id="TextSelectReport"
    private Text TextSelectReport; // Value injected by FXMLLoader   
    
    /** The Text choose class. */
    @FXML // fx:id="TextChooseClass"
    private Text TextChooseClass; // Value injected by FXMLLoader    
    
    /** The Text Teacher ID. */
    @FXML // fx:id="TextTID"
    private Text TextTID; // Value injected by FXMLLoader    
    
    /** The Text temporary block. */
    @FXML // fx:id="TextTempBlk"
    private Text TextTempBlk; // Value injected by FXMLLoader    
    
    /** The Student text. */
    @FXML // fx:id="StudentTextV"
    private Text StudentTextV; // Value injected by FXMLLoader    
    
    /** The Student ID text. */
    @FXML // fx:id="StudentIDText"
    private Text StudentIDText; // Value injected by FXMLLoader   
    
    /** The First name text. */
    @FXML // fx:id="FirstNameText"
    private Text FirstNameText; // Value injected by FXMLLoader    
    
    /** The Last name text. */
    @FXML // fx:id="LastNameText"
    private Text LastNameText; // Value injected by FXMLLoader    
    
    /** The Class name text. */
    @FXML // fx:id="ClassNameText"
    private Text ClassNameText; // Value injected by FXMLLoader    
    
    /** The Phone number text. */
    @FXML // fx:id="PhoneNumText"
    private Text PhoneNumText; // Value injected by FXMLLoader    
    
    /** The Address text. */
    @FXML // fx:id="AddressText"
    private Text AddressText; // Value injected by FXMLLoader   
    
    /** The Birth date text. */
    @FXML // fx:id="BirthdateText"
    private Text BirthdateText; // Value injected by FXMLLoader    
    
    /** The Starting date. */
    @FXML // fx:id="StartingDate"
    private TextField StartingDate; // Value injected by FXMLLoader    
    
    /** The Ending date. */
    @FXML // fx:id="EndingDate"
    private TextField EndingDate; // Value injected by FXMLLoader    
    
    /** The Semester ID. */
    @FXML // fx:id="SemesterID"
    private TextField SemesterID; // Value injected by FXMLLoader    
    
    /** The Teacher ID. */
    @FXML // fx:id="TID"
    private TextField TID; // Value injected by FXMLLoader    
    
    /** The Teaching unit. */
    @FXML // fx:id="TeachingUnit"
    private TextField TeachingUnit; // Value injected by FXMLLoader
    
    /** The Teacher ID. */
    @FXML // fx:id="TeacherID"
    private TextField TeacherID; // Value injected by FXMLLoader    
    
    /** The New teacher ID. */
    @FXML // fx:id="NewTeacherID"
    private TextField NewTeacherID; // Value injected by FXMLLoader    
    
    /** The Student ID search. */
    @FXML // fx:id="StdIDSearch"
    private TextField StdIDSearch; // Value injected by FXMLLoader    
    
    /** The Teacher ID search. */
    @FXML // fx:id="TeaIDSearch"
    private TextField TeaIDSearch; // Value injected by FXMLLoader
    
    /** The Parent ID. */
    @FXML // fx:id="TempAccParentID"
    private TextField ParentID; // Value injected by FXMLLoader    
    
    /** The Submission name. */
    @FXML // fx:id="SubmissionName"
    private TextField SubmissionName; // Value injected by FXMLLoader
    
    /** The Submission date. */
    @FXML // fx:id="SubmissionDate"
    private TextField SubmissionDate; // Value injected by FXMLLoader   
    
    /** The Deadline date. */
    @FXML // fx:id="DeadlineDate"
    private TextField DeadlineDate; // Value injected by FXMLLoader   
    
    /** The View Homework name. */
    @FXML // fx:id="ViewHWName"
    private TextField ViewHWName; // Value injected by FXMLLoader    
    
    /** The Grade. */
    @FXML // fx:id="Grade"
    private TextField Grade; // Value injected by FXMLLoader    
    
    /** The Parent ID. */
    @FXML // fx:id="ParID"
    private TextField ParID; // Value injected by FXMLLoader   
    
    /** The Current hours. */
    @FXML // fx:id="CurHours"
    private TextField CurHours; // Value injected by FXMLLoader    
    
    /** The Choose semester number. */
    @FXML // fx:id="ChooseSemesterNum"
    private ChoiceBox<String> ChooseSemesterNum; // Value injected by FXMLLoader    
    
    /** The Choose semester year. */
    @FXML // fx:id="ChooseSemesterNum"
    private ChoiceBox<String> ChooseSemesterYear; // Value injected by FXMLLoader   
    
    /** The Choose report. */
    @FXML // fx:id="ChooseReport"
    private ChoiceBox<String> ChooseReport; // Value injected by FXMLLoader    
    
    /** The Choose semester. */
    @FXML // fx:id="ChooseReport"
    private ChoiceBox<String> ChooseSemester; // Value injected by FXMLLoader    
    
    /** The Choose number of semesters. */
    @FXML // fx:id="ChooseNumSem"
    private ChoiceBox<String> ChooseNumSem; // Value injected by FXMLLoader    
    
    /** The Choose class. */
    @FXML // fx:id="ChooseClass"
    private ComboBox<String> ChooseClass; // Value injected by FXMLLoader    
    
    /** The Block. */
    @FXML // fx:id="Block"
    private CheckBox Block; // Value injected by FXMLLoader    
    
    /** The Button back. */
    @FXML // fx:id="Btn_Back"
    private Button Btn_Back; // Value injected by FXMLLoader    
    
    /** The Button generate. */
    @FXML // fx:id="Btn_Generate"
    private Button Btn_Generate; // Value injected by FXMLLoader    
    
    /** The Button approve. */
    @FXML // fx:id="Btn_Approve"
    private Button Btn_Approve; // Value injected by FXMLLoader
    
    /** The Button reject. */
    @FXML // fx:id="Btn_Reject"
    private Button Btn_Reject; // Value injected by FXMLLoader    
    
    /** The Button select. */
    @FXML // fx:id="Btn_Select"
    private Button Btn_Select; // Value injected by FXMLLoader    
    
    /** The Button view homeworks. */
    @FXML // fx:id="Btn_ViewHomeworks"
    private Button Btn_ViewHomeworks; // Value injected by FXMLLoader    
    
    /** The Button view courses. */
    @FXML // fx:id="Btn_ViewCourses"
    private Button Btn_ViewCourses; // Value injected by FXMLLoader    
    
    /** The Button evaluation open. */
    @FXML // fx:id="Btn_EvaluationOpen"
    private Button Btn_EvaluationOpen; // Value injected by FXMLLoader    
    
    /** The Button submission open. */
    @FXML // fx:id="Btn_SubmissionOpen"
    private Button Btn_SubmissionOpen; // Value injected by FXMLLoader
    
    /** The Button homework open. */
    @FXML // fx:id="Btn_HomeworkOpen"
    private Button Btn_HomeworkOpen; // Value injected by FXMLLoader    
    
    /** The Button checked open. */
    @FXML // fx:id="Btn_CheckedOpen"
    private Button Btn_CheckedOpen; // Value injected by FXMLLoader    
    
    /** The Button view children. */
    @FXML // fx:id="Btn_ViewChildren"
    private Button Btn_ViewChildren; // Value injected by FXMLLoader   
    
    /** The Button registration list. */
    @FXML // fx:id="ExpRegList"
    private TableView<regrequest> ExpRegList; // Value injected by FXMLLoader   
    
    /** The Appointing change list. */
    @FXML // fx:id="AppChngList"
    private TableView<ChangeAppointList> AppChngList; // Value injected by FXMLLoader   
    
    /** The Courses schedule table. */
    @FXML // fx:id="CoursesScheduleTable"
    private TableView<CoursesSchedule> CoursesScheduleTable; // Value injected by FXMLLoader   
    
    /** The Courses list. */
    @FXML // fx:id="CoursesList"
    private TableView<Courses> CoursesList; // Value injected by FXMLLoader   
    
    /** The Homeworks table. */
    @FXML // fx:id="HomeworksTable"
    private TableView<HomeWork> HomeworksTable; // Value injected by FXMLLoader    
    
    /** The Courses table. */
    @FXML // fx:id="CoursesTable"
    private TableView<StudentCourses> CoursesTable; // Value injected by FXMLLoader    
    
    /** The Children table. */
    @FXML // fx:id="ChildrenTable"
    private TableView<ParentChildren> ChildrenTable; // Value injected by FXMLLoader   
    
    /** The Tea courses table. */
    @FXML // fx:id="TeaCoursesTable"
    private TableView<TeacherCourses> TeaCoursesTable; // Value injected by FXMLLoader   
    
    /** The Course column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn Coursecol;
    
    /** The Student ID column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn Stidcol;
    
    /** The First name column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn Firstnamecol;
   
    /** The Class column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn Classcol;
    
    /** The Status column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn Statuscol;   
    
    /** The Last name column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn Lastnamecol;  
    
    /** The Type column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn Typecol;   
    
    /** The Request column. */
    @SuppressWarnings("rawtypes")
    @FXML // fx:id="RequestCol"
    private TableColumn RequestCol; // Value injected by FXMLLoader    
    
    /** The Course name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="Cnamecol"
    private TableColumn Cnamecol; // Value injected by FXMLLoader
    
    /** The Teaching unit column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="tUnitCOL"
    private TableColumn TUnitCOL; // Value injected by FXMLLoader   
    
    /** The Teacher ID column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="TIDcol"
    private TableColumn TIDcol; // Value injected by FXMLLoader   
    
    /** The Last name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="Lnamecol"
    private TableColumn Lnamecol; // Value injected by FXMLLoader
    
    /** The Course name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="CourseNAMEcol"
    private TableColumn CourseNAMEcol; // Value injected by FXMLLoader    
    
    /** The First name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="Fnamecol"
    private TableColumn Fnamecol; // Value injected by FXMLLoader
    
    /** The New teacher column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="NEWteachercol"
    private TableColumn NEWteachercol; // Value injected by FXMLLoader    
    
    /** The New status column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="Newstatuscol"
    private TableColumn Newstatuscol; // Value injected by FXMLLoader   
    
    /** The Course Name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="CourseNColumn"
    private TableColumn CourseNColumn; // Value injected by FXMLLoader   
    
    /** The Teaching unit column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="TeachingUnitColumn"
    private TableColumn TeachingUnitColumn; // Value injected by FXMLLoader    
    
    /** The Class Name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ClassNColumn"
    private TableColumn ClassNColumn; // Value injected by FXMLLoader   
    
    /** The Teacher Name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="TeacherNColumn"
    private TableColumn TeacherNColumn; // Value injected by FXMLLoader  
    
    /** The Weekly hours column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="WeeklyHoursColumn"
    private TableColumn WeeklyHoursColumn; // Value injected by FXMLLoader   
    
    /** The Pre-courses column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="PreCoursesColumn"
    private TableColumn PreCoursesColumn; // Value injected by FXMLLoader   
    
    /** The Checked column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="CheckedColumn"
    private TableColumn CheckedColumn; // Value injected by FXMLLoader   
    
    /** The Course name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="CourseNameColumn"
    private TableColumn CourseNameColumn; // Value injected by FXMLLoader   
    
    /** The Homework name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="HomeworkNameColumn"
    private TableColumn HomeworkNameColumn; // Value injected by FXMLLoader   
    
    /** The Class name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ClassNameColumn"
    private TableColumn ClassNameColumn; // Value injected by FXMLLoader   
    
    /** The Deadline date column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="DeadlineDateColumn"
    private TableColumn DeadlineDateColumn; // Value injected by FXMLLoader   
    
    /** The Grade column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="GradeColumn"
    private TableColumn GradeColumn; // Value injected by FXMLLoader   
    
    /** The Homework ID column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="HomeworkIDColumn"
    private TableColumn HomeworkIDColumn; // Value injected by FXMLLoader   
    
    /** The Teacher name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="TeacherNameColumn"
    private TableColumn TeacherNameColumn; // Value injected by FXMLLoader    
    
    /** The Exceptional registration column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ExpRegColumn"
    private TableColumn ExpRegColumn; // Value injected by FXMLLoader    
    
    /** The Course number column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="CourseNumColumn"
    private TableColumn CourseNumColumn; // Value injected by FXMLLoader
    
    /** The Week hours column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="WeekHoursColumn"
    private TableColumn WeekHoursColumn; // Value injected by FXMLLoader    
    
    /** The Child ID. */
    @SuppressWarnings("rawtypes")
    @FXML // fx:id="ChildID"
    private TableColumn ChildID; // Value injected by FXMLLoader
    
    /** The Child name. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ChildName"
    private TableColumn ChildName; // Value injected by FXMLLoader    
    
    /** The Child phone number. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ChildPhoneNum"
    private TableColumn ChildPhoneNum; // Value injected by FXMLLoader   
    
    /** The Child class. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ChildClass"
    private TableColumn ChildClass; // Value injected by FXMLLoader
    
    /** The Child birthdate. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ChildBirthdate"
    private TableColumn ChildBirthdate; // Value injected by FXMLLoader   
    
    /** The Class column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ClassColumn"
    private TableColumn ClassColumn; // Value injected by FXMLLoader   
    
    /** The Graph. */
    @FXML // fx:id="Graph"
    private BarChart<?, ?> Graph; // Value injected by FXMLLoader   
    
    /** The Block rectangle. */
    @FXML // fx:id="BlkRec"
    private Rectangle BlkRec; // Value injected by FXMLLoader   
    
	/** The list. */
	ArrayList<String> list = new ArrayList<String>();
		
	/** The list 2. */
	ArrayList<String> list2 = new ArrayList<String>();	
	
	/** The list of the courses. */
	ArrayList<String> listcourses = new ArrayList<String>();	
	
	/** The list of the children. */
	ArrayList<String> listchildren = new ArrayList<String>();	
	
	/** The courses list. */
	ArrayList<String> courseslist = new ArrayList<String>();	
	
	/** The list of homeworks. */
	public ObservableList<HomeWork> listhm = FXCollections.observableArrayList();	
	
	/** The list of student courses. */
	public ObservableList<StudentCourses> listcr = FXCollections.observableArrayList();	
	
	/** The list of teacher courses. */
	public ObservableList<TeacherCourses> listteacr = FXCollections.observableArrayList();	
	
	/** The list of parent children. */
	public ObservableList<ParentChildren> listchild = FXCollections.observableArrayList();	
	
	/** The courses schedule list. */
	public ObservableList<CoursesSchedule> coursesschedule = FXCollections.observableArrayList();	
	
	/** The courses. */
	public ObservableList<Courses> courses = FXCollections.observableArrayList();	
	
	/** The list 3. */
	public ObservableList<regrequest> list3 = FXCollections.observableArrayList();    
	
	/** The list 4. */
	public ObservableList<ChangeAppointList> list4 = FXCollections.observableArrayList();	
	
	/** The Student ID string. */
	private static String StdIDString;	
	
	/** The Submitted file. */
	private static String SubmittedFile;	
	
	/** The Student identity. */
	private static String StudentIdentity;	
	
	/** The Parent identity. */
	private static String ParentIdentity;	
	
	/** The Teacher identity. */
	private static String TeacherIdentity;	

	/** The Chosen semester. */
	private String ChoosedSemester;   
	
	/** The Chosen report type. */
	private String ChoosedReportType;    
	
	/** The Chosen class. */
	private String ChoosedClass;   
	
	/** The Chosen teacher. */
	private String ChoosedTeacher;   
	
	/** The Chosen semester year. */
	private String ChoosedSemesterYear;
	
	/** The Chosen semesters number. */
	private String ChoosedSemesterNum;
	/**
	 * View Choose student screen.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
    void ChooseStudent(ActionEvent event) throws IOException
    {
    	screen.SchDir_ChooseStudent();
    }
	
	/**
	 * Back to choose student screen.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
    void BackToChooseStudent(ActionEvent event) throws IOException
	{
		StudentID.clear();
		FirstName.clear();
		LastName.clear();
		ClassName.clear();
		PhoneNumber.clear();
		Address.clear();
		Birthdate.clear();
		screen.SchDir_ChooseStudent();
    }
    
    /**
     * View Choose parent screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ChooseParent(ActionEvent event) throws IOException
    {
    	screen.SchDir_ChooseParent();
    }
    
    /**
     * Back to choose parent screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void BackToChooseParent(ActionEvent event) throws IOException
    {
    	ParentID.clear();
		FirstName.clear();
		LastName.clear();
		PhoneNumber.clear();
		Address.clear();
		Birthdate.clear();
		screen.SchDir_ChooseParent();
    }
    
    /**
     * View Choose teacher screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ChooseTeacher(ActionEvent event) throws IOException
    {
    	screen.SchDir_ChooseTeacher();
    }
    
    /**
     * Back to choose teacher screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void BackToChooseTeacher(ActionEvent event) throws IOException
    {
    	TeacherID.clear();
		FirstName.clear();
		LastName.clear();
		PhoneNumber.clear();
		Address.clear();
		Birthdate.clear();
		MaxHours.clear();
		CurHours.clear();
		screen.SchDir_ChooseTeacher();
    }

    /**
     * View Temporary access blocking screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void TemporaryAccessBlocking(ActionEvent event) throws IOException
    {
    	screen.SchDir_TempAccBlk();
    }
    
    /**
     * View Exceptional student registration list screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void ExceptionalStudentRegistration(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("ViewExceptionalStudentRegistration");
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
    
    /**
     * View Teacher appointing list screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void TeacherAppointing(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("ViewAppointREQ");
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
    
    /**
     * View Choose Report screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void Reports(ActionEvent event) throws IOException
    {
    	str.add("GetLast10Semesters");
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * Show main screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ShowMainScreen(ActionEvent event) throws IOException
    {
    	screen.SchDir_MainMenu();
    }
       
    /**
     * View choose semester screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ViewSemester(ActionEvent event) throws IOException
    {
    	str.add("Last10SemestersYears");
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
       
    /**
     * View courses schedule in the semester.
     *
     * @param event the event
     */
    @FXML
    void ViewCourses(ActionEvent event)
    {
    	str.add("SchDirViewCourses");
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * Back to view semester screen.
     *
     * @param event the event
     */
    @FXML
    void BackViewSemester(ActionEvent event)
    {
    	str.add("Last10SemestersYears");
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
     
    /**
     * Generate report.
     *
     * @param event the event
     */
    @FXML
    void GenerateReport(ActionEvent event)
    {	
    	String Semester = ChooseSemester.getSelectionModel().getSelectedItem();
    	String Semester2 = "";
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
		Semester2 = Semester2.concat(SemYear) + SemNum; 	
    	String Type = String.valueOf(ChooseReport.getSelectionModel().getSelectedIndex());
    	ChoosedSemester = Semester;
    	ChoosedSemesterYear = SemYear;
    	ChoosedReportType = ChooseReport.getSelectionModel().getSelectedItem();
    	str.add("GenerateReport");
    	str.add(Semester2);
    	str.add(Type);
    	if(Type.equals("0"))
    	{
    		str.add(TID.getText());
    		ChoosedTeacher = TID.getText();
    	}
    	else
    	{
    		String Class = ChooseClass.getSelectionModel().getSelectedItem();
    		str.add(Class);
    		ChoosedClass = ChooseClass.getSelectionModel().getSelectedItem();
    	}
    	ChoosedSemesterNum = ChooseNumSem.getSelectionModel().getSelectedItem();
    	str.add(ChooseNumSem.getSelectionModel().getSelectedItem());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
       
    /**
     * Approve exceptional registration.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void ApproveExpReg(ActionEvent event) throws InterruptedException
    {
    	str.add("ApproveExpReg");
    	str.add(CourseName.getText());
    	str.add(ClassName.getText());
    	str.add(Type.getText());
    	str.add(StudentID.getText());
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    	Status.setText("Approve");
    }
   
    /**
     * Reject exceptional registration.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void RejectExpReg(ActionEvent event) throws InterruptedException
    {
    	
    	str.add("RejectExpReg");
    	str.add(CourseName.getText());
    	str.add(ClassName.getText());
    	str.add(Type.getText());
    	str.add(StudentID.getText());
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    	Status.setText("Reject");
    }
    
    /**
     * Back to exceptional registration list.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void BackToExpRegList(ActionEvent event) throws InterruptedException
    {
    	str.add("ViewExceptionalStudentRegistration");
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
        
    /**
     * Approve appointing change.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void ApproveAppChng(ActionEvent event) throws InterruptedException
    {
    	str.add("ApproveAppChng");
    	str.add(TeacherID.getText());
    	str.add(ClassName.getText());
    	str.add(CourseName.getText());
    	str.add(NewTeacherID.getText());
    	str.add(Type.getText());
    	str.add(Status.getText());
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    	Status.setText("Approve");
    }
    
    /**
     * Reject appointing change.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void RejectAppChng(ActionEvent event) throws InterruptedException
    {
    	str.add("RejectAppChng");
    	str.add(TeacherID.getText());
    	str.add(ClassName.getText());
    	str.add(CourseName.getText());
    	str.add(NewTeacherID.getText());
    	str.add(Type.getText());
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    	Status.setText("Reject");
    }
  
    /**
     * Back to appointing change list.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void BackToAppChngList(ActionEvent event) throws InterruptedException
    {
    	str.add("ViewAppointREQ");
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
       
    /**
     * View blocking check box.
     *
     * @param event the event
     */
    @FXML
    void ViewBlockingCheckBox(ActionEvent event)
    {
    	str.add("ViewBlockingCheckBox");
    	if(!ParentID.getText().equals("") && !StdID.getText().equals(""))
    	{
    		str.add(ParentID.getText());
    		str.add(StdID.getText());
    		last = this;
        	client.Accept(str);
    	}
    	else if(ParentID.getText().equals("") && !StdID.getText().equals(""))
    		MessageError("You must enter parent ID");
    	else if(!ParentID.getText().equals("") && StdID.getText().equals(""))
    		MessageError("You must enter student ID");
    	else
    		MessageError("No input");
    	str.clear();
    }
    
    /**
     * Save access block.
     *
     * @param event the event
     */
    @FXML
    void SaveAccessBlock(ActionEvent event)
    {
    	str.add("AccessBlock");
    	str.add(ParentID.getText());
    	str.add(StdID.getText());
    	if(Block.isSelected())
    		str.add("1");
    	else
    		str.add("0");
    	last = this;
    	client.Accept(str);
    	str.clear();
    	BlkRec.setVisible(false);
    	TextTempBlk.setVisible(false);
    	Block.setVisible(false);
    	Btn_Save.setVisible(false);
    	Btn_Cancel.setVisible(false);
    	ParentID.clear();
    	StdID.clear();
    	ParentID.setEditable(true);
    	StdID.setEditable(true);
    	Btn_View.setVisible(true);
    }
    
    /**
     * Cancel access block.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void CancelAccessBlock(ActionEvent event) throws IOException
    {
    	BlkRec.setVisible(false);
    	TextTempBlk.setVisible(false);
    	Block.setVisible(false);
    	Btn_Save.setVisible(false);
    	Btn_Cancel.setVisible(false);
    	ParentID.clear();
    	StdID.clear();
    	ParentID.setEditable(true);
    	StdID.setEditable(true);
    	Btn_View.setVisible(true);
    }
    
    /**
     * View semester courses.
     *
     * @param event the event
     */
    @FXML
    void ViewSemesterCourses(ActionEvent event)
    {
    	str.add("ViewSemesterCourses");
    	str.add(SemesterID.getText());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * View student information.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ViewStdInfo(ActionEvent event) throws IOException
    {
    	str.add("SDViewStdInfo");
    	if(!StdID.getText().isEmpty())
    	{
    		try
    		{
    			Integer.valueOf(StdID.getText());
    			if(StdID.getText().length() == 9)
    			{
    				StudentIdentity = StdID.getText();
    				str.add(StudentIdentity);
    				client.Accept(str);
    				str.clear();
    			}
    			else
    				MessageError("Student ID Must Be 9 Digits");
    		}catch(NumberFormatException e){ 
    			MessageError(" Student ID Must Be an Integer");
    			str.clear();
    			}
    	}
    	else
    		MessageError("You Must Enter a Student ID");
    	str.clear();
    }
    
    /**
     * View student homeworks.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ViewStdHomeworks(ActionEvent event) throws IOException
    {
    	str.add("SDViewStdHomeworkList");
    	str.add(StudentID.getText());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * View student courses.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ViewStdCourses(ActionEvent event) throws IOException
    {
    	str.add("SDViewStdCoursesList");
    	str.add(StudentID.getText());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * Back student info.
     *
     * @param event the event
     */
    @FXML
    void BackStdInfo(ActionEvent event)
    {
    	str.add("SDViewStdInfo");
    	str.add(StudentIdentity);
		client.Accept(str);
		str.clear();
    }
    
    /**
     * Gets the checked file.
     *
     * @param event the event
     */
    @FXML
    void GetCheckedFile(ActionEvent event)
    {
    	last = this;
		ask.add("SchDirCheckedFile");
		ask.add(StdIDString+"-CheckedHW-"+SubmissionName.getText());
		client.Accept(ask);
		ask.clear();
    }
    
    /**
     * Gets the evaluation file.
     *
     * @param event the event
     */
    @FXML
    void GetEvaluationFile(ActionEvent event)
    {
    	last = this;
		ask.add("SchDirEvaluationFile");
		ask.add(StdIDString+"-Evaluation-"+SubmissionName.getText());
		client.Accept(ask);
		ask.clear();
    }
    
    /**
     * gets the homework file.
     *
     * @param event the event
     */
    @FXML
	protected void SchDirGetHomeworkFile(ActionEvent event)
	{
		last = this;
		ask.add("SchDirFile");
		ask.add(file);
		client.Accept(ask);
		ask.clear();
	}
    
    /**
     * gets the submitted file.
     *
     * @param event the event
     */
    @FXML
    protected void SchDirGetSubmittedFile(ActionEvent event)
    {
    	last = this;
    	ask.add("SchDirSubmittedfile");
    	ask.add(SubmittedFile);
    	client.Accept(ask);
    	ask.clear();
    }
    
    /**
     * Back student homework list.
     *
     * @param event the event
     */
    @FXML
    void BackStdHomeworkList(ActionEvent event)
    {
    	str.add("SDViewStdHomeworkList");
    	str.add(StudentIdentity);
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * View parent information.
     *
     * @param event the event
     */
    @FXML
    void ViewParentInfo(ActionEvent event)
    {
    	str.add("SDViewParInfo");
    	if(!ParID.getText().isEmpty())
    	{
    		try
    		{
    			Integer.valueOf(ParID.getText());
    			if(ParID.getText().length() == 9)
    			{
    				ParentIdentity = ParID.getText();
    				str.add(ParentIdentity);
    				client.Accept(str);
    				str.clear();
    			}
    			else
    				MessageError("Parent ID Must Be 9 Digits");
    		}catch(NumberFormatException e){ 
    			MessageError("Parent ID Must Be an Integer");
    			str.clear();
    			}
    	}
    	else
    		MessageError("You Must Enter a Parent ID");
    	str.clear();
    }
    
    /**
     * View parent children.
     *
     * @param event the event
     */
    @FXML
    void ViewParChildren(ActionEvent event)
    {
    	str.add("ViewParChildren");
    	str.add(ParentID.getText());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * Back parent information.
     *
     * @param event the event
     */
    @FXML
    void BackParInfo(ActionEvent event)
    {
    	str.add("SDViewParInfo");
    	str.add(ParentIdentity);
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * View teacher information.
     *
     * @param event the event
     */
    @FXML
    void ViewTeacherInfo(ActionEvent event)
    {
    	str.add("SDViewTeaInfo");
    	if(!TeaIDSearch.getText().isEmpty())
    	{
    		try
    		{
    			Integer.valueOf(TeaIDSearch.getText());
    			if(TeaIDSearch.getText().length() == 9)
    			{
    				TeacherIdentity = TeaIDSearch.getText();
    				str.add(TeacherIdentity);
    				client.Accept(str);
    				str.clear();
    			}
    			else
    				MessageError("Teacher ID Must Be 9 Digits");
    		}catch(NumberFormatException e){ 
    			MessageError("Teacher ID Must Be an Integer");
    			str.clear();
    			}
    	}
    	else
    		MessageError("You Must Enter a Teacher ID");
    	str.clear();
    }
    
    /**
     * View teacher courses.
     *
     * @param event the event
     */
    @FXML
    void ViewTeaCourses(ActionEvent event)
    {
    	str.add("ViewTeaCourses");
    	str.add(TeacherID.getText());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }

    /**
     * View teacher homeworks.
     *
     * @param event the event
     */
    @FXML
    void ViewTeaHomeworks(ActionEvent event)
    {
    	str.add("ViewTeaHomeworks");
    	str.add(TeacherID.getText());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * Back teacher information.
     *
     * @param event the event
     */
    @FXML
    void BackTeaInfo(ActionEvent event)
    {
    	str.add("SDViewTeaInfo");
    	str.add(TeacherIdentity);
		client.Accept(str);
		str.clear();
    }
    
    /**
     * Back teacher homework list.
     *
     * @param event the event
     */
    @FXML
    void BackTeaHomeworkList(ActionEvent event)
    {
    	str.add("ViewTeaHomeworks");
    	str.add(TeacherIdentity);
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * Search by student ID.
     *
     * @param event the event
     */
    @FXML
    void SearchByStudentID(ActionEvent event)
    {
    	list3.removeAll(list3);
		 SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		 t.ViewExcepStdRegistration2(list,StdIDSearch.getText().trim()); 
    }
    
    /**
     * Clear search.
     *
     * @param event the event
     */
    @FXML
	void ClearSearch(ActionEvent event)
	{
    	list3.removeAll(list3);
    	StdIDSearch.clear();
    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
    	t.ViewExcepStdRegistration2(list,"");
	}
    
    /**
     * Search by teacher ID.
     *
     * @param event the event
     */
    @FXML
    void SearchByTeacherID(ActionEvent event)
    {
    	list4.removeAll(list4);
		SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		t.ViewAppointREQ2(list,TeaIDSearch.getText().trim()); 
    }
    
    /**
     * Clear search 2.
     *
     * @param event the event
     */
    @FXML
	void ClearSearch2(ActionEvent event)
	{
    	list4.removeAll(list4);
    	TeaIDSearch.clear();
    	SchoolDirectorController t = (SchoolDirectorController)SchoolDirectorController.last;
		t.ViewAppointREQ2(list,"");
	}

    
    /**
     * Shows the 1st graph.
     *
     * @param msg the details for graph
     */
    public void ShowGraph1(ArrayList<String> msg)
    {
    	Platform.runLater(new Runnable()
		{
    		@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
    		public void run()
    		{
    				String ReportType = "";
    				String SemNum = "";
    				msg.remove(0);
    		    	Stage stage = new Stage();
    		        stage.setTitle("Grades Histogram");
    		        final CategoryAxis xAxis = new CategoryAxis();
    		        final NumberAxis yAxis = new NumberAxis();
    		        final StackedBarChart<String,Number> GradeGraph = new StackedBarChart<String,Number>(xAxis,yAxis);
    		        int j = ChoosedReportType.indexOf(".");
    				if (j > 0) {
    					ReportType = ChoosedReportType.substring(j+2);
    				}
    				int k = ChoosedSemester.indexOf(",");
    				if (k > 0) {
    					SemNum = ChoosedSemester.substring(k+2);
    				}
    				if(ChoosedSemesterNum.equals("0"))
    				{
    				GradeGraph.setTitle(ReportType+" : ID "+ChoosedTeacher+" , Semester "+SemNum+" ("+ChoosedSemesterYear+")");	
    				}
    				else
    				{
    		        GradeGraph.setTitle(ReportType+" : ID "+ChoosedTeacher+" , Semester "+SemNum+" ("+ChoosedSemesterYear+") "
    		        		+ "& "+ChoosedSemesterNum+" previous semester(s)");
    				}
    		        xAxis.setLabel("Courses");       
    		        yAxis.setLabel("Grades");
    		    	ObservableList<XYChart.Series> SerList = FXCollections.observableArrayList();
    		        
    		        for(int i=0;i<msg.size()/2;i++)
        	        {
    		        	XYChart.Series series = new XYChart.Series();
    		        	series.getData().add(new XYChart.Data(msg.get(i), Integer.parseInt(msg.get((msg.size()/2)+i))));
    		        	series.setName((msg.get(i))); 
    		        	SerList.add(series);
        	        }
    		        
    		        Scene scene  = new Scene(GradeGraph,800,600);
    		        for (int i=0;i<SerList.size();i++)
    		        {
    		      	 GradeGraph.getData().add(SerList.get(i));
    		        }
    		        stage.setScene(scene);
    		        stage.show();     
    		}
		});
    }
    
    
    /**
     * Shows the 2nd graph.
     *
     * @param msg the details for graph
     */
    public void ShowGraph2(ArrayList<String> msg)
    {
    	Platform.runLater(new Runnable()
		{
    		@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
    		public void run()
    		{
    				String ReportType = "";
    				String SemNum = "";
    				msg.remove(0);
    		    	Stage stage = new Stage();
    		        stage.setTitle("Grades Histogram");
    		        final CategoryAxis xAxis = new CategoryAxis();
    		        final NumberAxis yAxis = new NumberAxis();
    		        final StackedBarChart<String,Number> GradeGraph = new StackedBarChart<String,Number>(xAxis,yAxis);
    		        int j = ChoosedReportType.indexOf(".");
    				if (j > 0) {
    					ReportType = ChoosedReportType.substring(j+2);
    				}
    				int k = ChoosedSemester.indexOf(",");
    				if (k > 0) {
    					SemNum = ChoosedSemester.substring(k+2);
    				}
    				if(ChoosedSemesterNum.equals("0"))
    				{
    		        GradeGraph.setTitle(ReportType+" : "+ChoosedClass+" , Semester "+SemNum+" ("+ChoosedSemesterYear+")");
    				}
    				else
    				{
    				GradeGraph.setTitle(ReportType+" : "+ChoosedClass+" , Semester "+SemNum+" ("+ChoosedSemesterYear+") "
    	    		        + "& "+ChoosedSemesterNum+" previous semester(s)");
    				}
    		        xAxis.setLabel("Teachers ID");       
    		        yAxis.setLabel("Grades");
    		    	ObservableList<XYChart.Series> SerList = FXCollections.observableArrayList();
    		        
    		        for(int i=0;i<msg.size()/2;i++)
        	        {
    		        	XYChart.Series series = new XYChart.Series();
    		        	series.getData().add(new XYChart.Data(msg.get(i), Integer.parseInt(msg.get((msg.size()/2)+i))));
    		        	series.setName((msg.get(i))); 
    		        	SerList.add(series);
        	        }
    		        
    		        Scene scene  = new Scene(GradeGraph,800,600);
    		        for (int i=0;i<SerList.size();i++)
    		        {
    		      	 GradeGraph.getData().add(SerList.get(i));
    		        }
    		        stage.setScene(scene);
    		        stage.show(); 
    		}
		});
    }
    
    
    /**
     * Show the 3rd graph.
     *
     * @param msg the details for graph
     */
    public void ShowGraph3(ArrayList<String> msg)
    {
    	Platform.runLater(new Runnable()
		{
    		@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
    		public void run()
    		{
    				String ReportType = "";
    				String SemNum = "";
    				msg.remove(0);
    		    	Stage stage = new Stage();
    		        stage.setTitle("Grades Histogram");
    		        final CategoryAxis xAxis = new CategoryAxis();
    		        final NumberAxis yAxis = new NumberAxis();
    		        final StackedBarChart<String,Number> GradeGraph = new StackedBarChart<String,Number>(xAxis,yAxis);
    		        int j = ChoosedReportType.indexOf(".");
    				if (j > 0) {
    					ReportType = ChoosedReportType.substring(j+2);
    				}
    				int k = ChoosedSemester.indexOf(",");
    				if (k > 0) {
    					SemNum = ChoosedSemester.substring(k+2);
    				}
    				if(ChoosedSemesterNum.equals("0"))
    				{
    				GradeGraph.setTitle(ReportType+" : "+ChoosedClass+" , Semester "+SemNum+" ("+ChoosedSemesterYear+")");	
    				}
    				else
    				{
    		        GradeGraph.setTitle(ReportType+" : "+ChoosedClass+" , Semester "+SemNum+" ("+ChoosedSemesterYear+") "
    		        		+ "& "+ChoosedSemesterNum+" previous semester(s)");
    				}
    		        xAxis.setLabel("Courses");       
    		        yAxis.setLabel("Grades");
    		    	ObservableList<XYChart.Series> SerList = FXCollections.observableArrayList();
    		        
    		        for(int i=0;i<msg.size()/2;i++)
        	        {
    		        	XYChart.Series series = new XYChart.Series();
    		        	series.getData().add(new XYChart.Data(msg.get(i), Integer.parseInt(msg.get((msg.size()/2)+i))));
    		        	series.setName((msg.get(i))); 
    		        	SerList.add(series);
        	        }
    		        
    		        Scene scene  = new Scene(GradeGraph,800,600);
    		        for (int i=0;i<SerList.size();i++)
    		        {
    		      	 GradeGraph.getData().add(SerList.get(i));
    		        }
    		        stage.setScene(scene);
    		        stage.show(); 
    		}
		});
    }
    
    
    /**
     * View semester information part1.
     *
     * @param obj semester info
     */
    public void ViewSemesterInfo(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					screen.SchDir_ViewSemester(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    
    /**
     * View semester information part2.
     *
     * @param obj the obj
     */
    public void ViewSemesterInfo2(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run()
			{
				try
				{
					((ArrayList<String>) obj).remove(0);
					ArrayList<String> years = new ArrayList<String>();
					for(int i = 0; i < ((ArrayList<String>) obj).size(); i++)
			    	{
						years.add(((ArrayList<String>)obj).get(i));
					}
					ChooseSemesterYear.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> GetLast10SemetersNums(newValue));
					ObservableList<String> list = FXCollections.observableArrayList(years);
					ChooseSemesterYear.setItems(list);
					ChooseSemesterYear.getSelectionModel().selectFirst();
				}catch(Exception e){
					e.printStackTrace();
					}
			}
		});
    }
    
    
    /**
     * View year semesters.
     *
     * @param s semester year
     * @param obj semesters info
     */
    public void GetLast10SemetersNums(String s)
    {
    	str.add("Last10SemestersNums");
    	str.add(s);
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    	
    	
    /**
     * View year semesters.
     *
     * @param s semester year
     * @param obj semesters info
     */
    public void ViewYearSemeters(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			try
    			{
    				obj.remove(0);
        			ChooseSemesterNum.getItems().clear();
        			ChooseSemesterNum.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> ViewSemesterInfo3(obj, newValue));
        			ArrayList<String> nums = new ArrayList<String>();
        			for(int i = 0; i < obj.size(); i = i + 3)
        			{
        				nums.add(obj.get(i));
        			}
        			ObservableList<String> list = FXCollections.observableArrayList(nums);
        			ChooseSemesterNum.setItems(list);
        			ChooseSemesterNum.getSelectionModel().selectFirst();

            	}catch(Exception e){
            		e.printStackTrace();
    				}
    		}
		});
    }
    
    /**
     * View semester information part3.
     *
     * @param obj semester info
     */
    public void ViewSemesterInfo3(ArrayList<String> obj, String s)
    {
    	for(int i = 0; i < obj.size(); i = i + 3)
    	{
    		if(obj.get(i).equals(s))
    		{    			
				try {
						String SemID = ChooseSemesterYear.getSelectionModel().getSelectedItem() + ChooseSemesterNum.getSelectionModel().getSelectedItem().substring(0, 1);
						SemesterID.setText(SemID);
						String Date = obj.get(i + 1);
						Date = Date.substring(8, 10) + "/" + Date.substring(5, 7) + "/" + Date.substring(0, 4);
						StartingDate.setText(Date);
						Date = obj.get(i + 2);
						Date = Date.substring(8, 10) + "/" + Date.substring(5, 7) + "/" + Date.substring(0, 4);
						EndingDate.setText(Date);
				}catch(Exception e){
					e.printStackTrace();
					}
    		}
    	}
    }
    
    
    /**
     * View semester courses part2.
     *
     * @param msg courses info
     */
    public void ViewSemesterCourses2(Object msg)
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.SchDir_ViewCoursesSchedule(msg);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
	}
    
    
    /**
     * Update semester courses schedule table.
     *
     * @param msg Schedule Table info
     * @param CRName course name
     */
    public void UpdateSchDirScheduleTable(Object msg, String CRName)
	{
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings({ "unchecked" })
			@Override
			public void run()
			{
				courseslist = (ArrayList<String>) msg;
				for(int i = 1; i < courseslist.size(); i = i + 5)
				{
					CoursesSchedule CS;
					CS = new CoursesSchedule("", courseslist.get(i), courseslist.get(i+1), courseslist.get(i+2), courseslist.get(i+3), courseslist.get(i+4));
					coursesschedule.add(CS);
				}
				CoursesScheduleTable.setEditable(true);
				CourseNColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("CourseNColumn"));				
				TeachingUnitColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("TeachingUnitColumn"));		
				ClassNColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("ClassNColumn"));
				TeacherNColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("TeacherNColumn"));
				WeeklyHoursColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("WeeklyHoursColumn"));
				CoursesScheduleTable.setItems(coursesschedule);
			}
		});
    }
    
    
    /**
     * View courses data.
     *
     * @param obj courses data
     */
    public void ViewCoursesData(Object obj)
    {
    	Platform.runLater(new Runnable()
    	{
    		@Override
    		public void run()
    		{
    			try
    			{
					screen.SchDir_ViewCourses(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
    		}
    	});
    }
    
    
    /**
     * View courses data part2.
     *
     * @param msg courses data
     * @param CRName course name
     */
    public void ViewCoursesData2(Object msg, String CRName)
    {
    	Platform.runLater(new Runnable()
    	{
    		@SuppressWarnings("unchecked")
			@Override
    		public void run()
    		{
    			courseslist = (ArrayList<String>) msg;
				for(int i = 1; i < courseslist.size(); i = i + 5)
				{
					Courses CS;
					CS = new Courses(courseslist.get(i+1), courseslist.get(i), courseslist.get(i+2), courseslist.get(i+3), courseslist.get(i+4));
					courses.add(CS);
				}
				CoursesList.setEditable(true);
				CourseNumColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("CourseNumColumn"));				
				CourseNColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("CourseNColumn"));		
				TeachingUnitColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("TeachingUnitColumn"));
				WeekHoursColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("WeekHoursColumn"));
				PreCoursesColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("PreCoursesColumn"));
				CoursesList.setItems(courses);
    		}
    	});
    }
    
    
    /**
     * View student information part2.
     *
     * @param obj student info
     */
    public void ViewStudentInfo2(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			try
    			{
					screen.SchDir_ViewStdInfo();
					SchoolDirectorController sch = (SchoolDirectorController)SchoolDirectorController.last;
					sch.ViewStudentInfo3(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
    		}
		});
    }
    
    
    /**
     * View student information part3.
     *
     * @param obj student info
     */
    public void ViewStudentInfo3(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			StudentID.setText(obj.get(1));
    			FirstName.setText(obj.get(2));
    			LastName.setText(obj.get(3));
    			PhoneNumber.setText(obj.get(4));
    			Address.setText(obj.get(5));
    			String bDate = obj.get(6);
    			bDate = bDate.substring(8, 10) + "/" + bDate.substring(5, 7) + "/" + bDate.substring(0, 4);
    			Birthdate.setText(bDate);
    			ClassName.setText(obj.get(7));
    			StdIDString = StudentID.getText();
    		}
		});
    }
      
    
    /**
     * View student homework part2.
     *
     * @param msg homework info
     */
    public void ViewStdHomeworks2(Object msg) //2-show Student_ViewHomeworksList screen
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					screen.SchDir_ViewStdHomeworksList(msg);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    
    /**
     * Update student homework table.
     *
     * @param msg homework info
     * @param HWName homework name
     */
    public void UpdateStdHWTable(Object msg, String HWName)  //3-insert homework names to the table
	{
		Platform.runLater(new Runnable()
		{
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void run()
			{
				list2 = (ArrayList<String>) msg;
				if(!(HWName.equals("")))
				{
					for(int i=1;i<list2.size();i=i+7)
					{
						if(HWName.equals(list2.get(i+1)))
						{
							HomeWork HW = new HomeWork(list2.get(i),list2.get(i+1),list2.get(i+2),list2.get(i+3),list2.get(i+4),list2.get(i+6),list2.get(i+5));
							listhm.add(HW);
						}
					}
				}
				else
				{
					for(int i=1;i<list2.size();i=i+7)
					{
						HomeWork HW = new HomeWork(list2.get(i),list2.get(i+1),list2.get(i+2),list2.get(i+3),list2.get(i+4),list2.get(i+6),list2.get(i+5));
						listhm.add(HW);
					}
				}
				HomeworksTable.setEditable(true);
				Callback<TableColumn, TableCell> cellFactory =
			             new Callback<TableColumn, TableCell>() {
			                 public TableCell call(TableColumn p) {
			                    return new HWEditingCell(listhm,"School Director");
			                 }
			             };
			    HomeworkIDColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("HomeworkIDColumn"));	
			    HomeworkIDColumn.setCellFactory(cellFactory);
			    HomeworkIDColumn.setOnEditCommit(
			            new EventHandler<CellEditEvent<HomeWork, String>>() {
			                @Override
			                public void handle(CellEditEvent<HomeWork, String> t) {
			                    ((HomeWork) t.getTableView().getItems().get(
			                        t.getTablePosition().getRow())
			                        ).getClass();
			                }
			            }
			        );		
			    HomeworkNameColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("HomeworkNameColumn"));
			    DeadlineDateColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("DeadlineDateColumn"));				
				CourseNameColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("CourseNameColumn"));
				ClassNameColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("ClassNameColumn"));
				CheckedColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("CheckedColumn"));				
				GradeColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("GradeColumn"));	
		        HomeworksTable.setItems(listhm);  
			}
		});
    }
    
   
    /**
     * Update homework page.
     *
     * @param homeWork homework info
     */
    public void updateHW(HomeWork homeWork) //4-click on HW name pass to HW info page
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				BaseController.file = homeWork.getHomeworkNameColumn();
				ViewHWName.setText(homeWork.getHomeworkNameColumn());
				try {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date d = new Date();
					String temp = df.format(d);		
					Date d3 = df.parse(temp);
					Date d2 = (Date)df.parse(homeWork.getDeadlineDateColumn());
					if(d3.after(d2))
					{
						DeadlineDate.setStyle("-fx-text-inner-color: red");
					}
				DeadlineDate.setText(homeWork.getDeadlineDateColumn());
				ClassName.setText(homeWork.getClassNameColumn());
				CourseName.setText(homeWork.getCourseNameColumn());
				Grade.setText(homeWork.getGradeColumn());
				if(Grade.getText().equals(""))
				{
					Btn_EvaluationOpen.setDisable(true);
					Grade.setDisable(true);
					Btn_CheckedOpen.setDisable(true);
				}
				str.add("SchDirSubInfo");
				str.add(StdIDString);
				str.add(homeWork.getHomeworkNameColumn());
				str.add(homeWork.getCourseNameColumn());
		    	//last = this;
		    	client.Accept(str);
		    	str.clear();
				}catch (java.text.ParseException e) {
					e.printStackTrace();
					}
			}
	
		});	
    }
    
    
    /**
     * Update homework part2.
     *
     * @param msg homework info
     */
    public void updateHW2(ArrayList<String> msg) 
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				if(msg.size()>1)
				{
					SubmittedFile = msg.get(1);
					String SN = "-";
					int i = msg.get(1).indexOf('-');
					if(i>0) SN = msg.get(1).substring(i+1);
					SubmissionName.setText(SN);
					SubmissionDate.setText(msg.get(2));
			
					try {
						if(!(SubmissionName.getText().equals("-")))
						{
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							Date d = (Date)df.parse(msg.get(2));				
							Date d2 = (Date)df.parse(DeadlineDate.getText());
							if(d.after(d2))
							{
								SubmissionDate.setStyle("-fx-text-inner-color: red");
							}
						}
						if(SubmissionName.getText().equals("-"))
						{
								Btn_CheckedOpen.setDisable(true);	
								Btn_SubmissionOpen.setDisable(true);
						}
					} catch (java.text.ParseException e) {
						e.printStackTrace();
					}		
				}
				else{
					Btn_SubmissionOpen.setDisable(true);
				}
			}
	
		});	
    }
    
    
    /**
     * Download homework.
     *
     * @param msg a file
     * @param ext file extension
     */
    public void DownloadHW (byte[] msg, String ext) //5-download the HW file
    {
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{	
				if(ext.equals("Empty"))
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("The file doesn't exist");
					alert.showAndWait();
				}
				else
				{
				FileChooser fileChooser = new FileChooser(); 
		        //Set extension filter
		    	FileChooser.ExtensionFilter Ext = new FileChooser.ExtensionFilter(ext+" file (*."+ext+")", "*."+ext+"");
		        fileChooser.getExtensionFilters().add(Ext); 
		        //Show save file dialog
		     try{
		        File file = fileChooser.showSaveDialog(GUIPlayer.PrimaryStage);
		        if (file!=null)
		        {
			        FileOutputStream fos = new FileOutputStream(file);
					fos.write(msg);
					fos.close();
		        }
		     }
		     catch (IOException e) {
					e.printStackTrace();
					}
				}
	        
			}});
	}
    
    
    /**
     * Download submission file.
     *
     * @param msg a file
     * @param ext file extension
     */
    public void DownloadSubFile (byte[] msg, String ext) //5-download the HW file
    {
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{	
				if(ext.equals("Empty"))
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("The file doesn't exist");
					/*Optional<ButtonType> result = alert.showAndWait();
					if(result.get() == ButtonType.OK)
					{
						ViewHomeworkList3();
					}*/
				}
				else
				{
				FileChooser fileChooser = new FileChooser(); 
		        //Set extension filter
		    	FileChooser.ExtensionFilter Ext = new FileChooser.ExtensionFilter(ext+" file (*."+ext+")", "*."+ext+"");
		        fileChooser.getExtensionFilters().add(Ext); 
		        //Show save file dialog
		     try{
		        File file = fileChooser.showSaveDialog(GUIPlayer.PrimaryStage);
		        if (file!=null)
		        {
			        FileOutputStream fos = new FileOutputStream(file);
					fos.write(msg);
					fos.close();
		        }
		     }
		     catch (IOException e) {
					e.printStackTrace();
					}
				}
	        
			}});
	}
       
    
    /**
     * View student courses part2.
     *
     * @param msg courses info
     */
    public void ViewStdCourses2(Object msg) //2-show Student_ViewHomeworksList screen
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					screen.SchDir_ViewStdCoursesList(msg);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    
    /**
     * Update student courses table.
     *
     * @param msg courses info
     */
    public void UpdateStdCoursesTable(Object msg)  //3-insert homework names to the table
	{
		Platform.runLater(new Runnable()
		{
			@SuppressWarnings({ "unchecked" })
			@Override
			public void run()
			{
				listcourses = (ArrayList<String>) msg;
				for(int i = 1; i < listcourses.size(); i = i + 5)
				{
					StudentCourses SC;
					if(listcourses.get(i+4).equals("0"))
						SC = new StudentCourses(listcourses.get(i), listcourses.get(i+1), listcourses.get(i+2), listcourses.get(i+3), "No");
					else
						SC = new StudentCourses(listcourses.get(i), listcourses.get(i+1), listcourses.get(i+2), listcourses.get(i+3), "Yes");
					listcr.add(SC);
				}
				CoursesTable.setEditable(true);
				CourseNumColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("CourseNumColumn"));				
				CourseNameColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("CourseNameColumn"));		
				WeekHoursColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("WeekHoursColumn"));
				TeacherNameColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("TeacherNameColumn"));
				ExpRegColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("ExpRegColumn"));
				CoursesTable.setItems(listcr);
			}
		});
    }
    
    
    /**
     * View parent information part2.
     *
     * @param obj parent info
     */
    public void ViewParentInfo2(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			try
    			{
					screen.SchDir_ViewParentInfo(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
    		}
		});
    }
    
    
    /**
     * View parent information part3.
     *
     * @param obj parent info
     */
    public void ViewParentInfo3(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			ParentID.setText(obj.get(1));
    			FirstName.setText(obj.get(2));
    			LastName.setText(obj.get(3));
    			PhoneNumber.setText(obj.get(4));
    			Address.setText(obj.get(5));
    			String bDate = obj.get(6);
    			bDate = bDate.substring(8, 10) + "/" + bDate.substring(5, 7) + "/" + bDate.substring(0, 4);
    			Birthdate.setText(bDate);
    		}
		});
    }
    
    
    /**
     * View children table.
     *
     * @param obj children info
     */
    public void ViewChildrenTable(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					screen.SchDir_ViewParChildren(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    
    /**
     * Update children table.
     *
     * @param msg children info
     */
    public void UpdateChildrenTable(Object msg)  //3-insert homework names to the table
	{
		Platform.runLater(new Runnable()
		{
			@SuppressWarnings({ "unchecked" })
			@Override
			public void run()
			{
				String bDate;
				listchildren = (ArrayList<String>) msg;
				for(int i = 1; i < listchildren.size(); i = i + 5)
				{
					ParentChildren SC;
					bDate = listchildren.get(i+2);
	    			bDate = bDate.substring(8, 10) + "/" + bDate.substring(5, 7) + "/" + bDate.substring(0, 4);
					SC = new ParentChildren(listchildren.get(i), listchildren.get(i+1), listchildren.get(i+4), bDate, listchildren.get(i+3));
					listchild.add(SC);
				}
				ChildrenTable.setEditable(true);
				ChildID.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("ChildID"));				
				ChildName.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("ChildName"));		
				ChildClass.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("ChildClass"));
				ChildBirthdate.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("ChildBirthdate"));
				ChildPhoneNum.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("ChildPhoneNum"));
				ChildrenTable.setItems(listchild);
			}
		});
    }
    
    
    /**
     * View teacher information part2.
     *
     * @param obj teacher info
     */
    public void ViewTeacherInfo2(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			try
    			{
					screen.SchDir_ViewTeacherInfo(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
    		}
		});
    }
    
    
    /**
     * View teacher information part3.
     *
     * @param obj teacher info
     */
    public void ViewTeacherInfo3(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			TeacherID.setText(obj.get(1));
    			FirstName.setText(obj.get(2));
    			LastName.setText(obj.get(3));
    			PhoneNumber.setText(obj.get(4));
    			Address.setText(obj.get(5));
    			String bDate = obj.get(6);
    			bDate = bDate.substring(8, 10) + "/" + bDate.substring(5, 7) + "/" + bDate.substring(0, 4);
    			Birthdate.setText(bDate);
    			MaxHours.setText(obj.get(7));
    			CurHours.setText(obj.get(8));
    			TeacherID.getText();
    		}
		});
    }
    

    /**
     * View teacher homework part2.
     *
     * @param msg homework info
     */
    public void ViewTeaHomeworks2(Object msg) //2-show Student_ViewHomeworksList screen
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					screen.SchDir_ViewTeaHomeworksList(msg);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    
    /**
     * Update teacher homework table.
     *
     * @param msg homework info
     * @param HWName homework name
     */
    public void UpdateTeaHWTable(Object msg, String HWName)  //3-insert homework names to the table
	{
		Platform.runLater(new Runnable()
		{
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void run()
			{
				list2 = (ArrayList<String>) msg;
				if(!(HWName.equals("")))
				{
					for(int i=1;i<list2.size();i=i + 5)
					{
						if(HWName.equals(list2.get(i+1)))
						{
							HomeWork HW = new HomeWork(list2.get(i), list2.get(i+1), list2.get(i+2), list2.get(i+3), list2.get(i+4), "", "");
							listhm.add(HW);
						}
					}
				}
				else
				{
					for(int i=1;i<list2.size();i=i + 5)
					{
						HomeWork HW = new HomeWork(list2.get(i), list2.get(i+1), list2.get(i+2), list2.get(i+3), list2.get(i+4), "", "");
						listhm.add(HW);
					}
				}
				HomeworksTable.setEditable(true);
				Callback<TableColumn, TableCell> cellFactory =
			             new Callback<TableColumn, TableCell>() {
			                 public TableCell call(TableColumn p) {
			                    return new HWEditingCell(listhm,"School Director Teacher");
			                 }
			             };
			    HomeworkIDColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("HomeworkIDColumn"));
			    HomeworkIDColumn.setCellFactory(cellFactory);
			    HomeworkIDColumn.setOnEditCommit(
			            new EventHandler<CellEditEvent<HomeWork, String>>() {
			                @Override
			                public void handle(CellEditEvent<HomeWork, String> t) {
			                    ((HomeWork) t.getTableView().getItems().get(
			                        t.getTablePosition().getRow())
			                        ).getClass();
			                }
			            }
			        );		
			    HomeworkNameColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("HomeworkNameColumn"));	
			    DeadlineDateColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("DeadlineDateColumn"));				
				CourseNameColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("CourseNameColumn"));
				ClassNameColumn.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("ClassNameColumn"));	
		        HomeworksTable.setItems(listhm);  
			}
		});
    }
    

    /**
     * Update teacher homework.
     *
     * @param homeWork homework info
     */
    public void updateTeaHW(HomeWork homeWork) //4-click on HW name pass to HW info page
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				BaseController.file = homeWork.getHomeworkNameColumn();
				ViewHWName.setText(homeWork.getHomeworkNameColumn());
				DeadlineDate.setText(homeWork.getDeadlineDateColumn());
				ClassName.setText(homeWork.getClassNameColumn());
				CourseName.setText(homeWork.getCourseNameColumn());
			}
		});	
    }
    

    /**
     * View teacher courses part2.
     *
     * @param msg courses info
     */
    public void ViewTeaCourses2(Object msg) //2-show Student_ViewHomeworksList screen
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					screen.SchDir_ViewTeaCoursesList(msg);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    
    /**
     * Update teacher courses table.
     *
     * @param msg courses info
     */
    public void UpdateTeaCoursesTable(Object msg)  //3-insert homework names to the table
	{
		Platform.runLater(new Runnable()
		{
			@SuppressWarnings({ "unchecked" })
			@Override
			public void run()
			{
				listcourses = (ArrayList<String>) msg;
				for(int i = 1; i < listcourses.size(); i = i + 4)
				{
					TeacherCourses SC;
					SC = new TeacherCourses(listcourses.get(i), listcourses.get(i+1), listcourses.get(i+3), listcourses.get(i+2));
					listteacr.add(SC);
				}
				TeaCoursesTable.setEditable(true);
				CourseNumColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("CourseNumColumn"));				
				CourseNameColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("CourseNameColumn"));		
				ClassColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("ClassColumn"));
				WeekHoursColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("WeekHoursColumn"));
				TeaCoursesTable.setItems(listteacr);
			}
		});
    }
    

    /**
     * View reports screen part1.
     *
     * @param obj report info
     */
    public void viewReports(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					screen.SchDir_Reports(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }

    
    /**
     * View reports screen part2.
     *
     * @param obj report info
     */
    public void ReportsView(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				ChooseReport.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> ShowFields(newValue));
				ChooseNumSem.setItems(FXCollections.observableArrayList("0", "1", "2", "3", "4", "5"));
				ChooseNumSem.getSelectionModel().selectFirst();
				int i = 1;
				ArrayList<String> arr = new ArrayList<String>();
				while(i < obj.size() - 1)
				{
					arr.add(obj.get(i) + " , " + obj.get(i + 1));
					i += 2;
				}
				ObservableList<String> list;
    			list = FXCollections.observableArrayList(arr);
    			ChooseSemester.setItems(list);
    			ChooseSemester.getSelectionModel().selectFirst();
    			ChooseSemester.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> ShowClasses(newValue));
				ChooseReport.setItems(FXCollections.observableArrayList("1. Grades per Teacher",
						"2. Grades per Teachers in Class",
						"3. Grades per Courses in Class"));
				ChooseReport.getSelectionModel().selectFirst();
			}
		});
    }
    
    
    /**
     * View reports screen part3.
     *
     * @param s report type
     */
    public void ShowFields(String s)
    {
    	if(s.equals("1. Grades per Teacher"))
    	{
    		TextChooseClass.setVisible(false);
    		ChooseClass.setVisible(false);
    		TextTID.setVisible(true);
    		TID.setVisible(true);
    	}
    	else
    	{
    		TextChooseClass.setVisible(true);
    		ChooseClass.setVisible(true);
    		TextTID.setVisible(false);
    		TID.setVisible(false);
    		ChooseClass.getItems().clear();
	    	str.add("GetClasses");
			str.add(ChooseSemester.getSelectionModel().getSelectedItem());
			last = this;
			client.Accept(str);
			str.clear();
    	}
    }
    
    /**
     * Get classes names.
     *
     * @param Semester the chosen semester
     */
    public void ShowClasses(String Semester)
    {
    	if(!(ChooseReport.getSelectionModel().getSelectedItem().equals("1. Grades per Teacher")))
    	{
	    	ChooseClass.getItems().clear();
	    	str.add("GetClasses");
			str.add(Semester);
			last = this;
			client.Accept(str);
			str.clear();
    	}
    }
    
    
    /**
     * Sets the classes names.
     *
     * @param obj classes names
     */
    public void SetClassesNames(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				obj.remove(0);
				ObservableList<String> list;
    			list = FXCollections.observableArrayList(obj);
    			ChooseClass.setItems(list);
    			ChooseClass.getSelectionModel().selectFirst();
			}
		});
    }
    
    
    /**
     * View exceptional student registration.
     *
     * @param obj requests details
     */
    public void ViewExcepStdRegistration(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.SchDir_ExpStdRegList(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    
    /**
     * View exceptional student registration part2.
     *
     * @param obj requests info
     * @param ID user ID
     */
    public void ViewExcepStdRegistration2(ArrayList<String> obj, String ID)
    {
    	Platform.runLater(new Runnable()
		{
    		@SuppressWarnings({ "unchecked", "rawtypes"})
			@Override
    		public void run()
    		{
    			list = (ArrayList<String>) obj;
    			if(!(ID.equals("")))
				{
					for(int i=1;i<list.size();i=i+8)
					{
						if(ID.equals(list.get(i+1)))
						{
							regrequest ST = new regrequest(list.get(i),list.get(i+1),list.get(i+2),list.get(i+3),list.get(i+4),list.get(i+5),list.get(i+6),list.get(i+7));
							list3.add(ST);
						}
					}
					
				}
				else
				{
					for(int i=1;i<list.size();i=i+8)
					{
						regrequest ST = new regrequest(list.get(i),list.get(i+1),list.get(i+2),list.get(i+3),list.get(i+4),list.get(i+5),list.get(i+6),list.get(i+7));
						list3.add(ST);
					}
				}
				
				ExpRegList.setEditable(true);
				Callback<TableColumn, TableCell> cellFactory =
			             new Callback<TableColumn, TableCell>() {
							public TableCell call(TableColumn p) {
			                    return new ReqEditingCell(list3);
			                 }
			             };
			    Callback<TableColumn, TableCell> cellFactory2 =
					 new Callback<TableColumn, TableCell>() {
							public TableCell call(TableColumn p) {
						      return new AppChngEditingCell2();
					        }
					   };
			             RequestCol.setCellValueFactory(new PropertyValueFactory<regrequest, String>("RequestCol"));
			             RequestCol.setCellFactory(cellFactory);
			             RequestCol.setOnEditCommit(
			            new EventHandler<CellEditEvent<regrequest, String>>() {
			                @Override
			                public void handle(CellEditEvent<regrequest, String> t) {
			                    ((regrequest) t.getTableView().getItems().get(
			                        t.getTablePosition().getRow())
			                        ).getClass();
			                }
			            }
			        );		
				Stidcol.setCellValueFactory(new PropertyValueFactory<regrequest, String>("Stidcol"));
				Firstnamecol.setCellValueFactory(new PropertyValueFactory<regrequest, String>("Firstnamecol"));		            
				Lastnamecol.setCellValueFactory(new PropertyValueFactory<regrequest, String>("Lastnamecol"));				
				Coursecol.setCellValueFactory(new PropertyValueFactory<regrequest, String>("Coursecol"));				
				Classcol.setCellValueFactory(new PropertyValueFactory<regrequest, String>("Classcol"));	
				Typecol.setCellValueFactory(new PropertyValueFactory<regrequest, String>("Typecol"));				
				Statuscol.setCellValueFactory(new PropertyValueFactory<regrequest, String>("Statuscol"));	
				Statuscol.setCellFactory(cellFactory2);
				ExpRegList.setItems(list3);
    		}
		});
    }
    
    
    /**
     * View exceptional student registration part3.
     *
     * @param regrequest regrequest
     */
    public void ViewExcepStdRegistration3(regrequest regrequest)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
    		public void run()
    		{
				CourseName.setText(regrequest.getCoursecol());
				ClassName.setText(regrequest.getClasscol());
				Type.setText(regrequest.getTypecol());
				StudentID.setText(regrequest.getStidcol());
				Status.setText(regrequest.getStatuscol());
    		}
		});
    }
    
    
    /**
     * View appointing requests.
     *
     * @param obj requests info
     */
    public void ViewAppointREQ(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.SchDir_TeaAppList(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    
    /**
     * View appointing requests part2.
     *
     * @param obj requests info
     * @param ID user ID
     */
    public void ViewAppointREQ2(ArrayList<String> obj, String ID)
    {
    	Platform.runLater(new Runnable()
		{
    		@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
    		public void run()
    		{
    			list = (ArrayList<String>) obj;
    			if(!(ID.equals("")))
				{
					for(int i=1;i<list.size();i=i+9)
					{
						if(ID.equals(list.get(i+1)))
						{
							ChangeAppointList ST = new ChangeAppointList(list.get(i),list.get(i+1),list.get(i+2),list.get(i+3),list.get(i+4),list.get(i+5),list.get(i+6),list.get(i+7),list.get(i+8));
							list4.add(ST);
						}
					}
					
				}
				else
				{
					for(int i=1;i<list.size();i=i+9)
					{
						ChangeAppointList ST = new ChangeAppointList(list.get(i),list.get(i+1),list.get(i+2),list.get(i+3),list.get(i+4),list.get(i+5),list.get(i+6),list.get(i+7),list.get(i+8));
						list4.add(ST);
					}
				}
				
				AppChngList.setEditable(true);
				Callback<TableColumn, TableCell> cellFactory =
			             new Callback<TableColumn, TableCell>() {
							public TableCell call(TableColumn p) {
			                    return new AppChngEditingCell(list4);
			                 }
			             };
			    Callback<TableColumn, TableCell> cellFactory2 =
					     new Callback<TableColumn, TableCell>() {
							public TableCell call(TableColumn p) {
					            return new AppChngEditingCell2();
					           }
					    };
			    RequestCol.setCellValueFactory(new PropertyValueFactory<ChangeAppointList, String>("RequestCol"));
				RequestCol.setCellFactory(cellFactory);
				RequestCol.setOnEditCommit(
			            new EventHandler<CellEditEvent<ChangeAppointList, String>>() {
			                @Override
			                public void handle(CellEditEvent<ChangeAppointList, String> t) {
			                    ((ChangeAppointList) t.getTableView().getItems().get(
			                        t.getTablePosition().getRow())
			                        ).getClass();
			                }
			            }
			        );	
				TIDcol.setCellValueFactory(new PropertyValueFactory<ChangeAppointList, String>("TIDcol"));
				Fnamecol.setCellValueFactory(new PropertyValueFactory<ChangeAppointList, String>("Fnamecol"));
				Lnamecol.setCellValueFactory(new PropertyValueFactory<ChangeAppointList, String>("Lnamecol"));		            
				Cnamecol.setCellValueFactory(new PropertyValueFactory<ChangeAppointList, String>("Cnamecol"));				
				CourseNAMEcol.setCellValueFactory(new PropertyValueFactory<ChangeAppointList, String>("CourseNAMEcol"));				
				TUnitCOL.setCellValueFactory(new PropertyValueFactory<ChangeAppointList, String>("TUnitCOL"));	
				NEWteachercol.setCellValueFactory(new PropertyValueFactory<ChangeAppointList, String>("NEWteachercol"));				
				Newstatuscol.setCellValueFactory(new PropertyValueFactory<ChangeAppointList, String>("Newstatuscol"));
				Newstatuscol.setCellFactory(cellFactory2);
				AppChngList.setItems(list4);
    		}
		});
    }
    
    
    /**
     * View appointing requests part3.
     *
     * @param ChangeAppointList change appoint list
     */
    public void ViewAppointREQ3(ChangeAppointList ChangeAppointList)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
    		public void run()
    		{
				TeacherID.setText(ChangeAppointList.getTIDcol());
				ClassName.setText(ChangeAppointList.getCnamecol());
				CourseName.setText(ChangeAppointList.getCourseNAMEcol());
				NewTeacherID.setText(ChangeAppointList.getNEWteachercol());
				Type.setText(ChangeAppointList.getTUnitCOL());
				Status.setText(ChangeAppointList.getNewstatuscol());
    		}
		});
    }
    
    
    /**
     * View block check box.
     *
     * @param s block status
     */
    public void ViewBlkCheckBox(String s)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
    		public void run()
    		{
				BlkRec.setVisible(true);
    	    	TextTempBlk.setVisible(true);
    	    	Block.setVisible(true);
    	    	Btn_Save.setVisible(true);
    	    	Btn_Cancel.setVisible(true);
    	    	Btn_View.setVisible(false);
    	    	ParentID.setEditable(false);
    	    	StdID.setEditable(false);
    	    	if(s.equals("Yes"))
    	    		Block.setSelected(true);
    	    	else
    	    		Block.setSelected(false);
    		}
		});
    }
}
