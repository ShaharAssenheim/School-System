package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Entity.CoursesSchedule;
import Entity.StudentCourses;
import Entity.teacherAppoint;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.scene.control.Alert.AlertType;

/**
 * The Class SecretaryController.
 * @author shachar & Mohammad
 */
public class SecretaryController extends BaseController
{
	
	/**
	 * Instantiates a new secretary controller.
	 */
	public SecretaryController()
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
		
	/** The Menu Button view course. */
	@FXML // fx:id="Btn_ViewCourse"
    private MenuItem Btn_ViewCourse; // Value injected by FXMLLoader

    /** The Menu Button view exceptional student registration. */
    @FXML // fx:id="Btn_ViewExpStdReg"
    private MenuItem Btn_ViewExpStdReg; // Value injected by FXMLLoader

    /** The Menu Button changing teacher appointing. */
    @FXML // fx:id="Btn_ChngTeaApt"
    private MenuItem Btn_ChngTeaApt; // Value injected by FXMLLoader

    /** The Menu Button view student data. */
    @FXML // fx:id="Btn_ViewStudentData"
    private MenuItem Btn_ViewStudentData; // Value injected by FXMLLoader

    /** The Menu Button new exceptional student registration. */
    @FXML // fx:id="Btn_NewExpStdReg"
    private MenuItem Btn_NewExpStdReg; // Value injected by FXMLLoader

    /** The Menu Button view teacher appointing. */
    @FXML // fx:id="Btn_ViewTeaApt"
    private MenuItem Btn_ViewTeaApt; // Value injected by FXMLLoader

    /** The Menu Button view semester. */
    @FXML // fx:id="Btn_ViewSemester"
    private MenuItem Btn_ViewSemester; // Value injected by FXMLLoader

    /** The Menu Button open new semester. */
    @FXML // fx:id="Btn_OpenNewSemester"
    private MenuItem Btn_OpenNewSemester; // Value injected by FXMLLoader

    /** The Menu Button associate student. */
    @FXML // fx:id="Btn_AssociateStudent"
    private MenuItem Btn_AssociateStudent; // Value injected by FXMLLoader
    
    /** The Menu Button view associations. */
    @FXML // fx:id="Btn_ViewAssociations"
    private MenuItem Btn_ViewAssociations; // Value injected by FXMLoader

    /** The Menu Button new appointing. */
    @FXML // fx:id="Btn_NewAppointing"
    private MenuItem Btn_NewAppointing; // Value injected by FXMLLoader

    /** The Menu Button view appointing. */
    @FXML // fx:id="Btn_ViewAppointing"
    private MenuItem Btn_ViewAppointing; // Value injected by FXMLLoader

    /** The Menu Button view class. */
    @FXML // fx:id="Btn_ViewClass"
    private MenuItem Btn_ViewClass; // Value injected by FXMLLoader

    /** The Menu Button new class. */
    @FXML // fx:id="Btn_NewClass"
    private MenuItem Btn_NewClass; // Value injected by FXMLLoader
    
    /** The Menu Button View Courses Schedule. */
    @FXML // fx:id="Btn_ViewCoursesSchedule"
    private MenuItem Btn_ViewCoursesSchedule; // Value injected by FXMLLoader
    
    /** The Text semester number. */
    @FXML // fx:id="TextSemNum"
    private Text TextSemNum; // Value injected by FXMLLoader
    
    /** The Text view course name. */
    @FXML // fx:id="TextViewCourseName"
    private Text TextViewCourseName; // Value injected by FXMLLoader
    
    /** The Text semester year. */
    @FXML // fx:id="TextSemYear"
    private Text TextSemYear; // Value injected by FXMLLoader
    
    /** The Text class numumber. */
    @FXML // fx:id="TextClassNum"
    private Text TextClassNum; // Value injected by FXMLLoader
    
    /** The Text class name. */
    @FXML // fx:id="TextClassName"
    private Text TextClassName; // Value injected by FXMLLoader
    
    /** The Text course name. */
    @FXML // fx:id="TextCourseName"
    private Text TextCourseName; // Value injected by FXMLLoader
    
    /** The Text course number. */
    @FXML // fx:id="TextCourseNumber"
    private Text TextCourseNumber; // Value injected by FXMLLoader
    
    /** The Text teaching unit. */
    @FXML // fx:id="TextTeachingUnit"
    private Text TextTeachingUnit; // Value injected by FXMLLoader
    
    /** The Text week hours. */
    @FXML // fx:id="TextWeekHours"
    private Text TextWeekHours; // Value injected by FXMLLoader
    
    /** The Text pre-courses. */
    @FXML // fx:id="TextPreCourses"
    private Text TextPreCourses; // Value injected by FXMLLoader
    
    /** The Text max students. */
    @FXML // fx:id="TextMaxStudents"
    private Text TextMaxStudents; // Value injected by FXMLLoader
    
    /** The Text teacher ID. */
    @FXML // fx:id="TeaxtTeacherID"
    private Text TeaxtTeacherID; // Value injected by FXMLLoader
    
    /** The Semester ID. */
    @FXML // fx:id="SemesterID"
    private TextField SemesterID; // Value injected by FXMLLoader
    
    /** The New teacher ID. */
    @FXML // fx:id="SemesterID"
    private TextField NewTeacherID; // Value injected by FXMLLoader
    
    /** The Maximum students. */
    @FXML // fx:id="MaxStudents"
    private TextField MaxStudents; // Value injected by FXMLLoader

    /** The Current students. */
    @FXML // fx:id="CurrentStudents"
    private TextField CurrentStudents; // Value injected by FXMLLoader
    
    /** The Semester year. */
    @FXML // fx:id="SemesterYear"
    private TextField SemesterYear; // Value injected by FXMLLoader

    /** The Semester number. */
    @FXML // fx:id="SemesterNum"
    private TextField SemesterNumber; // Value injected by FXMLLoader
    
    /** The Class information. */
    @FXML // fx:id="SemesterY"
    private TextField ClassInfo; // Value injected by FXMLLoader
    
    /** The Maximum students. */
    @FXML // fx:id="MaxStd"
    private TextField MaxStd; // Value injected by FXMLLoader

    /** The Semester Year. */
    @FXML // fx:id="SemesterY"
    private TextField SemesterY; // Value injected by FXMLLoader
    
    /** The Maximum students. */
    @FXML // fx:id="MaxStudentsEdit"
    private TextField MaxStds; // Value injected by FXMLLoader

    /** The Class ID input. */
    @FXML // fx:id="ClassIDInput"
    private TextField ClassIDInput; // Value injected by FXMLLoader
    
    /** The Teaching unit. */
    @FXML // fx:id="TeachingUnit"
    private TextField TeachingUnit; // Value injected by FXMLLoader
    
    /** The Teacher name. */
    @FXML // fx:id="TeacherName"
    private TextField TeacherName; // Value injected by FXMLLoader

    /** The Course number. */
    @FXML // fx:id="CourseNum"
    private TextField CourseNum; // Value injected by FXMLLoader

    /** The pre-courses. */
    @FXML // fx:id="PreCourses"
    private TextField PreCourses; // Value injected by FXMLLoader

    /** The Week hours. */
    @FXML // fx:id="WeekHours"
    private TextField WeekHours; // Value injected by FXMLLoader
    
    /** The Starting date. */
    @FXML // fx:id="StartingDate"
    private TextField StartingDate; // Value injected by FXMLLoader

    /** The Ending date. */
    @FXML // fx:id="EndingDate"
    private TextField EndingDate; // Value injected by FXMLLoader
    
    /** The Last Name. */
    @FXML // fx:id="LastN"
    private TextField LastN; // Value injected by FXMLLoader
    
    /** The First Name. */
    @FXML // fx:id="FirstN"
    private TextField FirstN; // Value injected by FXMLLoader

    /** The Address. */
    @FXML // fx:id="Addr"
    private TextField Addr; // Value injected by FXMLLoader

    /** The Role Name. */
    @FXML // fx:id="RoleN"
    private TextField RoleN; // Value injected by FXMLLoader

    /** The Password. */
    @FXML // fx:id="Pass"
    private TextField Pass; // Value injected by FXMLLoader

    /** The Phone number. */
    @FXML // fx:id="PhoneNum"
    private TextField PhoneNum; // Value injected by FXMLLoader
    
    /** The Choose class number. */
    @FXML // fx:id="ChooseClassNum"
    private ChoiceBox<String> ChooseClassNum; // Value injected by FXMLLoader
    
    /** The Choose semester number. */
    @FXML // fx:id="ChooseSemesterNum"
    private ChoiceBox<String> ChooseSemesterNum; // Value injected by FXMLLoader
    
    /** The Choose semester Year. */
    @FXML // fx:id="ChooseSemesterY"
    private ChoiceBox<String> ChooseSemesterY; // Value injected by FXMLLoader
    
    /** The Choose teacher requests. */
    @FXML // fx:id="ChooseTeacherReq"
    private ChoiceBox<String> ChooseTeacherReq; // Value injected by FXMLLoader
    
    /** The Choose class. */
    @FXML // fx:id="ChooseClass"
    private ChoiceBox<String> ChooseClass; // Value injected by FXMLLoader
    
    /** The Choose course Name. */
    @FXML // fx:id="ChooseCourse"
    private ChoiceBox<String> ChooseCourseN; // Value injected by FXMLLoader
    
    /** The Combo class number. */
    @FXML // fx:id="ComboClassNum"
    private ComboBox<String> ComboClassNum; // Value injected by FXMLLoader
    
    /** The Semester end date picker. */
    @FXML // fx:id="SemEnd"
    private DatePicker SemEnd; // Value injected by FXMLLoader
    
    /** The Semester start date picker. */
    @FXML // fx:id="SemStart"
    private DatePicker SemStart; // Value injected by FXMLLoader
    
    /** The Birth. */
    @FXML // fx:id="Birth"
    private DatePicker Birth; // Value injected by FXMLLoader
    
    /** The Betton remove. */
    @FXML // fx:id="Btn_Remove"
    private Button Btn_Remove; // Value injected by FXMLLoader
    
    /** The Button add. */
    @FXML // fx:id="Btn_Add"
    private Button Btn_Add; // Value injected by FXMLLoader
    
    /** The Button clear. */
    @FXML // fx:id="Btn_Clear"
    private Button Btn_Clear; // Value injected by FXMLLoader
    
    /** The Button Delete. */
    @FXML // fx:id="Btn_Delete"
    private Button Btn_Delete; // Value injected by FXMLLoader
    
    /** The Button set class. */
    @FXML // fx:id="Btn_SetClass"
    private Button Btn_SetClass; // Value injected by FXMLLoader
    
    /** The Courses schedule list. */
    @FXML // fx:id="CoursesSchedule"
    private TableView<CoursesSchedule> CoursesSchedule; // Value injected by FXMLLoader
    
    /** The Teacher appointing list. */
    @FXML // fx:id="TeaAptList"
    private TableView<teacherAppoint> TeaAptList; // Value injected by FXMLLoader
    
    /** The Teacher ID column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn TeacherIDcol;
    
    /** The Class ID column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn ClassIDcol;
    
    /** The Course ID column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn CourseIDcol;
    
    /** The Teaching Unit details column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn TUDcol;
    
    /** The Week hours  ID columns. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn WHsIDcol;
    
    /** The Course name column*/
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="CourseNColumn"
    private TableColumn CourseNColumn; // Value injected by FXMLLoader
    
    /** The Index column*/
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="IndexColumn"
    private TableColumn IndexColumn; // Value injected by FXMLLoader
    
    /** The Teaching Unit column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="TeachingUnitColumn"
    private TableColumn TeachingUnitColumn; // Value injected by FXMLLoader
    
    /** The Class name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ClassNColumn"
    private TableColumn ClassNColumn; // Value injected by FXMLLoader
    
    /** The Teacher name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="TeacherNColumn"
    private TableColumn TeacherNColumn; // Value injected by FXMLLoader
    
    /** The Weekly hours column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="WeeklyHoursColumn"
    private TableColumn WeeklyHoursColumn; // Value injected by FXMLLoader
    
    /** The Back picture. */
    @FXML
    private ImageView BackPic;
    
    /** The courses list. */
    ArrayList<String> courseslist = new ArrayList<String>();
    
    /** The semester number list. */
	ArrayList<String> semnum = new ArrayList<String>();
    
    /** The courses schedule list. */
	public ObservableList<CoursesSchedule> coursesschedule = FXCollections.observableArrayList();
	
	/** The list. */
	public ObservableList<teacherAppoint> list = FXCollections.observableArrayList();

    /**
     * View associations student to a class.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void ViewAssociations(ActionEvent event) throws InterruptedException
    {
    	str.add("OpenViewAssociationsPage");
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
     * Open new semester page.
     *@param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void OpenNewSemester(ActionEvent event) throws IOException
    {
    	last = this;
    	screen.Secretary_NewSemester();
    }

    /**
     * View semester details.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ViewSemester(ActionEvent event) throws IOException
    {
    	last = this;
    	screen.Secretary_ViewSemester();
    }

    /**
     * open New class page.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void NewClass(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("OpenNewClassPage");
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
     * View class details page.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void ViewClass(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("ViewClassSemesters");
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
    
    /**
     * Associate student to a class page.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void AssociateStudent(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("OpenAssociateStudentPage");
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
     * View course details.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void ViewCourse(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("CoursesSet2");
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
     * New teacher appointing page.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void NewAppointing(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("NewAppointingClasses");
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
     * View  teacher appointing.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ViewAppointing(ActionEvent event) throws IOException
    {
    	screen.Secretary_ViewAppointing();
    }

    /**
     * View student details page.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ViewStudentData(ActionEvent event) throws IOException
    {
    	screen.Secretary_ViewStudentData();
    }

    /**
     * New student exceptional regestrasion/deletion.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void NewExpStdReg(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("CoursesClassType");
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
     * View student exceptional regestrasion/deletion
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void ViewExpStdReg(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("CoursesSet");
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
     * change teacher appointing.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ChngTeaApt(ActionEvent event) throws IOException
    {
    	screen.Secretary_ChngTeaApt();
    }

    /**
     * View teacher appointing.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ViewTeaApt(ActionEvent event) throws IOException
    {
    	Object obj=null;
    	screen.Secretary_ViewTeaApt(obj);
    }

    /**
     * Show main screen.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ShowMainScreen(ActionEvent event) throws IOException
    {
    	screen.Secretary_MainMenu();
    }
    
	/**
	* Removes the student from class.
	* @param event Gets the ActionEvent when the function called.
	* @throws IOException Signals that an I/O exception has occurred.
	* @throws InterruptedException the interrupted exception
	*/
    @FXML
    void RemoveStdFromClass(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("RemoveStdToClass");
    	StdToClassInfo(event);
    }

    /**
     * Adds the student to class.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void AddStdToClass(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("AddStdToClass");
    	StdToClassInfo(event);
    }
    
    /**
    * View student association.
    * @param event Gets the ActionEvent when the function called.
    * @throws InterruptedException the interrupted exception
    */
    @FXML
    void ViewStudentAssociation(ActionEvent event) throws InterruptedException
    {
    	String SemID = ChooseSemesterY.getSelectionModel().getSelectedItem()
    			+ ChooseSemesterNum.getSelectionModel().getSelectedItem().substring(0, 1);
    	if(!StdID.getText().isEmpty() && StdID.getText().length() == 9)
    	{
    		try
    		{
    			Integer.valueOf(StdID.getText());
    			str.add("ViewStudentAssociation");
    			str.add(StdID.getText());
    			str.add(SemID);
    	    	client.Accept(str);
    	    	Thread.sleep(250);
    	    	str.clear();
    		}catch(NumberFormatException e){ 
    			MessageError(" Student ID Must Be an Integer!!!");
    			str.clear();
    			}
    	}
    	else if(!StdID.getText().isEmpty())
    	{
    		try
    		{
    			Integer.valueOf(StdID.getText());
    			MessageError("Student ID Must Be 9 Digits!!!");
    			str.clear();
    		}catch(NumberFormatException e){ 
    			MessageError("Wrong Input You Must Enter a 9 Digits Integer!!!");
    			str.clear();
    			}
    	}
    	else if(StdID.getText().isEmpty())
    	{
    		MessageError("You Must Inter a Student ID!!!");
			str.clear();
    	}
    }

    /**
    * Cancel teacher appointing request.
    * @param event Gets the ActionEvent when the function called.
    * @throws IOException Signals that an I/O exception has occurred.
    */
    @FXML
    void CancelTeaAppRequest(ActionEvent event) throws IOException
    {
    	screen.Secretary_MainMenu();
    }

    /**
     * Send teacher appointing request.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void SendTeaAppRequest(ActionEvent event) throws InterruptedException
    {
    	str.add("SendTeacherChange");
    	str.add(TeaID.getText());
    	str.add(ChooseClassN.getSelectionModel().getSelectedItem());
    	str.add(ChooseCourseN.getSelectionModel().getSelectedItem());
    	str.add(NewTeacherID.getText());
    	str.add("Wating");
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
    
    /**
     * View teacher details.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void ViewTeacher(ActionEvent event) throws InterruptedException
    {
    	str.add("SearchTeacher");
    	str.add(TeaID.getText());
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
	       
	/**
	* Adds the new teacher appointing.
	* @param event Gets the ActionEvent when the function called.
	* @throws InterruptedException the interrupted exception
	*/
    @FXML
    void AddNewApp(ActionEvent event) throws InterruptedException
    {
    	str.add("AddNewApp");
    	str.add(TeaID.getText());
    	str.add(ChooseClassN.getSelectionModel().getSelectedItem());
    	str.add(ChooseCourseN.getSelectionModel().getSelectedItem());
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
    * Cancel new teacher appointing.
    * @param event Gets the ActionEvent when the function called.
    * @throws IOException Signals that an I/O exception has occurred.
    */
    @FXML
    void CancelNewApp(ActionEvent event) throws IOException
    {
    	screen.Secretary_MainMenu();

    }    
	    
    /**
    * Cancel new class.
    * @param event Gets the ActionEvent when the function called.
    * @throws IOException Signals that an I/O exception has occurred.
    */
    @FXML
    void CancelNewClass(ActionEvent event) throws IOException
    {
    	screen.Secretary_MainMenu();
    }

    /**
     * Adds the new class.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void AddNewClass(ActionEvent event) throws InterruptedException
    {
    	str.add("AddNewClass");
    	str.add(SemesterYear.getText());
    	str.add(SemesterNumber.getText());
    	str.add(ChooseClassN.getSelectionModel().getSelectedItem());
    	str.add(ChooseClassNum.getSelectionModel().getSelectedItem());
    	if(!MaxStd.getText().isEmpty())
    	{
    		str.add(MaxStd.getText());
    		client.Accept(str);
    		Thread.sleep(250);
    		str.clear();
    	}
    	else
    		MessageError("You must enter maximum amount of students");
    }

    /**
    * Send new exceptional registration request.
    * @param event Gets the ActionEvent when the function called.
    * @throws InterruptedException the interrupted exception
    */
    @FXML
    void SendNewExpRegRequest(ActionEvent event) throws InterruptedException
    {
    	int flag1=0;
    	if(!StdID.getText().isEmpty())
    	{
    		try { 
    			Integer.parseInt(StdID.getText()); 
    		}catch(NumberFormatException e){ 
    			flag1=1;
    			MessageError("ID Must Be an Integer!!!");
    			StdID.clear();
    		}
    	}
    	else{
    		MessageError("Yuo Must Enter ID!!!");
    		flag1=1;
    		StdID.clear();
    	}
    
    	
    	if(StdID.getText().length()==9 && flag1==0)
    	{
    		str.add("NewRegRequest");
    		str.add(ChooseCourseN.getSelectionModel().getSelectedItem());
    		str.add(ChooseClassN.getSelectionModel().getSelectedItem());
    		str.add(ChooseType.getSelectionModel().getSelectedItem());
    		str.add(StdID.getText());
    		str.add("Wating");
    		last = this;
    		client.Accept(str);
    		Thread.sleep(250);
    		str.clear();
    	}
    	else if(flag1==0)
    		MessageError("ID Must Be 9 Digits!!!");
    }

    /**
     * Cancel new exceptional registration request.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void CancelNewExpRegRequest(ActionEvent event) throws IOException
    {
    	screen.Secretary_MainMenu();
    }      
    
    /**
    * Adds the new semester.
    * @param event Gets the ActionEvent when the function called.
    * @throws InterruptedException the interrupted exception
    */
    @FXML
    void AddNewSemester(ActionEvent event) throws InterruptedException
    {
    	int flag2=0;
    	try { 
            Integer.parseInt(SemesterY.getText()); 
        } catch(NumberFormatException e) { 
            flag2=1;
            MessageInfo("Semester Year Must Be an Integer!!!");
            SemesterY.clear();
        } catch(NullPointerException e) {
        	flag2=1;
        	MessageInfo("Semester Year Must Be an Integer!!!");
        	SemesterY.clear();
        }
    	
    	
    	if(!SemesterY.getText().isEmpty()&&SemesterY.getText().length()==4&&flag2==0)
    	{
    			if(SemStart.getValue() != null&&SemEnd.getValue() != null)
    			{
    				if(SemStart.getValue().isBefore(SemEnd.getValue()))
    				{
    					if(SemEnd.getValue().toString().substring(0, 4).equals(SemesterY.getText()))
    					{
    						str.add("NewSemester");
    						str.add(SemesterY.getText());
    						str.add(ChooseSemesterNum.getSelectionModel().getSelectedItem());
    						str.add(SemStart.getValue().toString());
    						str.add(SemEnd.getValue().toString());
    						last = this;
    						client.Accept(str);
    						Thread.sleep(250);
    						str.clear();
    					}else
    						MessageError("The Semester Must End in " + SemesterY.getText());
    				}else
    					MessageError("Illegal Dates");
    			}
    			else
    				MessageError("You must pick dates");
        }
    	else if(flag2==0)
    		MessageError("You must enter a 4 digit year.");
    }

    /**
     * Cancel new semester page.
     * @param event Gets the ActionEvent when the function called.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void CancelNewSemester(ActionEvent event) throws IOException
    {
    	screen.Secretary_MainMenu();
    }
   
    /**
   * View tea appointing.
   * @param event Gets the ActionEvent when the function called.
   * @throws InterruptedException the interrupted exception
   */
   @FXML
    void ViewTeaAppointing(ActionEvent event) throws InterruptedException
    {
    	
    	if(!TeaID.getText().isEmpty())
    	{
    		
    		str.add("ViewTeaAppointing");
    		str.add(TeaID.getText());
    		last = this;
    		client.Accept(str);
    		Thread.sleep(250);
    		str.clear();
    	}
    	else
    		MessageError("You Must Enter ID");
    }
    
    /**
     * Back  from teacher appoint page.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void BackTeacherAppoint(ActionEvent event) 
    {
    	TeaxtTeacherID.setVisible(true);
		TeaID.setVisible(true);
		Btn_View.setVisible(true);
		TeaAptList.setVisible(false);
		list.removeAll(list);
		Btn_Back.setVisible(false);
		BackPic.toBack();
    }
   
   /**
   * View class max students.
   * @param event Gets the ActionEvent when the function called.
   * @throws InterruptedException the interrupted exception
   */
   @FXML
    void ViewClassMaxStudents(ActionEvent event) throws InterruptedException
    {
    	str.add("ViewMaxStudents");
    	str.add(ChooseSemesterY.getSelectionModel().getSelectedItem());
    	str.add(ChooseSemesterNum.getSelectionModel().getSelectedItem());
    	str.add(ChooseClassN.getSelectionModel().getSelectedItem());
    	str.add(ChooseClassNum.getSelectionModel().getSelectedItem());
    	Btn_Edit.setVisible(true);
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
    
    /**
     * View semester classes.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void ViewSemesterClasses(ActionEvent event) throws InterruptedException
    {
    	str.add("ViewSemesterClasses");
    	str.add(ChooseSemesterY.getSelectionModel().getSelectedItem());
    	str.add(ChooseSemesterNum.getSelectionModel().getSelectedItem());
    	ChooseSemesterNum.setVisible(false);
		ChooseSemesterY.setVisible(false);
		Btn_Search.setVisible(false);
		TextSemYear.setVisible(false);
		TextSemNum.setVisible(false);
		TextClassName.setVisible(true);
		TextClassNum.setVisible(true);
		TextMaxStudents.setVisible(true);
		ChooseClassN.setVisible(true);
		ChooseClassNum.setVisible(true);
		MaxStudents.setVisible(true);
		Btn_View.setVisible(true);
		Btn_Back.setVisible(true);
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
     * Edits the class info.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void EditClassInfo(ActionEvent event) throws InterruptedException
    {
    	Btn_Back.setVisible(false);
    	str.add("EditClassInfo");
    	str.add(ChooseSemesterY.getSelectionModel().getSelectedItem());
    	str.add(ChooseSemesterNum.getSelectionModel().getSelectedItem());
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
     * Save class edit.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void SaveClassEdit(ActionEvent event) throws InterruptedException
    {
    	str.add("SaveClassEdit");
    	str.add(ChooseSemesterY.getSelectionModel().getSelectedItem());
    	str.add(ChooseSemesterNum.getSelectionModel().getSelectedItem());
    	str.add(ClassName.getText());
    	str.add(ClassNumber.getText());
    	str.add(MaxStds.getText());
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
    * Cancel class edit.
    * @param event Gets the ActionEvent when the function called.
    */
    @FXML
    void CancelClassEdit(ActionEvent event)
    {
    	MaxStds.setVisible(false);
    	MaxStudents.setVisible(true);
    	ClassName.setVisible(false);
    	ClassNumber.setVisible(false);
    	ChooseClassN.setVisible(true);
    	ChooseClassNum.setVisible(true);
    	Btn_View.setVisible(true);
    	Btn_Edit.setVisible(true);
    	Btn_Back.setVisible(true);
    	Btn_Save.setVisible(false);
    	Btn_Cancel.setVisible(false);
    }
    
    /**
     * Back to view class.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void BackToViewClass(ActionEvent event)
    {
    	ChooseSemesterNum.setVisible(true);
		ChooseSemesterY.setVisible(true);
		Btn_Search.setVisible(true);
		TextSemYear.setVisible(true);
		TextSemNum.setVisible(true);
		TextClassName.setVisible(false);
		TextClassNum.setVisible(false);
		TextMaxStudents.setVisible(false);
		ChooseClassN.setVisible(false);
		ChooseClassNum.setVisible(false);
		MaxStudents.setVisible(false);
		Btn_Edit.setVisible(false);
		MaxStudents.clear();
		Btn_View.setVisible(false);
		Btn_Back.setVisible(false);
    }
   
   /**
   * Sets the classes to course.
   * @param event Gets the ActionEvent when the function called.
   * @throws InterruptedException the interrupted exception
   */
   @FXML
    void SetClassesToCourse(ActionEvent event) throws InterruptedException 
    {
    	TextCourseName.setVisible(false);
        TextCourseNumber.setVisible(false);
        TextTeachingUnit.setVisible(false);
        TextWeekHours.setVisible(false);
        TextPreCourses.setVisible(false);
        ChooseCourseN.setVisible(false);
        CourseNum.setVisible(false);
        TeachingUnit.setVisible(false);
        WeekHours.setVisible(false);
        PreCourses.setVisible(false);
        Btn_SetClass.setVisible(false);
        TextClassNum.setVisible(true);
        TextClassName.setVisible(true);
        ChooseClassN.setVisible(true);
        ChooseClassNum.setVisible(true);
        Btn_Save.setVisible(true);
        TextViewCourseName.setVisible(true);
        Btn_Back.setVisible(true);
        TextViewCourseName.setText(ChooseCourseN.getSelectionModel().getSelectedItem());
        str.add("ViewSemesterClasses2");
        last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();  
    }
    
    /**
     * Save classes to course.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void SaveClassesToCourse(ActionEvent event) throws InterruptedException 
    {
    	str.add("SaveClassesToCourse");
    	str.add(ChooseClassN.getSelectionModel().getSelectedItem());
    	str.add(ChooseClassNum.getSelectionModel().getSelectedItem());
    	str.add(ChooseCourseN.getSelectionModel().getSelectedItem());
        last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
    
    /**
    * Back from  classes to course page.
    * @param event Gets the ActionEvent when the function called.
    */
    @FXML
    void BackClassesToCourse(ActionEvent event) 
    {
    	TextCourseName.setVisible(true);
        TextCourseNumber.setVisible(true);
        TextTeachingUnit.setVisible(true);
        TextWeekHours.setVisible(true);
        TextPreCourses.setVisible(true);
        ChooseCourseN.setVisible(true);
        CourseNum.setVisible(true);
        TeachingUnit.setVisible(true);
        WeekHours.setVisible(true);
        PreCourses.setVisible(true);
        Btn_SetClass.setVisible(true);
        TextClassNum.setVisible(false);
        TextClassName.setVisible(false);
        ChooseClassN.setVisible(false);
        ChooseClassNum.setVisible(false);
        Btn_Save.setVisible(false);
        TextViewCourseName.setVisible(false);
        Btn_Back.setVisible(false);
    }
    
    /**
    * Deleting a course from the semester schedule.
    * @param event Gets the ActionEvent when the function called.
    */
    @FXML
    void DeleteCourseFromSchedule(ActionEvent event)
    {
    	str.add("DeleteCourseFromSchedule");
    	str.add(CourseName.getText());
    	str.add(ClassName.getText());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }

   /**
   * View exceptional registration status.
   * @param event Gets the ActionEvent when the function called.
   * @throws InterruptedException the interrupted exception
   */
    @FXML
    void ViewExpStdRegStatus(ActionEvent event) throws InterruptedException
    {
    	str.add("ViewRegStatus");
    	str.add(StdID.getText());
    	str.add(ChooseCourseN.getSelectionModel().getSelectedItem());
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }      
	    
   /**
   * View semester information.
   * @param event Gets the ActionEvent when the function called.
   * @throws InterruptedException the interrupted exception
   */
   @FXML
    void ViewSemesterInfo(ActionEvent event) throws InterruptedException
    {
		int flag2=0;
	   	try
	   	{ 
	    	Integer.parseInt(SemesterY.getText()); 
	    }catch(NumberFormatException e){ 
	    	flag2=1;
	    	MessageInfo("Semester Year Must Be an Integer!!!");
	    	SemesterY.clear();
	    	}catch(NullPointerException e){
	    		flag2=1;
	    		MessageInfo("Semester Year Must Be an Integer!!!");
	    		SemesterY.clear();
	        	}
	   	if(!SemesterY.getText().isEmpty()&&SemesterY.getText().length()==4&&flag2==0)
	   	{
	   		str.add("ViewSemester");
	   		str.add(SemesterY.getText());
	   		str.add(ChooseSemesterNum.getSelectionModel().getSelectedItem());
	   		last = this;
	   		client.Accept(str);
	   		Thread.sleep(250);
	   		str.clear();
	   	}else if(flag2 == 0)
	   		MessageError("You must enter a 4 digit year.");
    }

    /**
     * Edits the semester information.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void EditSemesterInfo(ActionEvent event) throws InterruptedException
    {
    	str.add("CheckCurrent");
    	str.add(SemesterID.getText());
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }

    /**
     * Save semester edit.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void SaveSemesterEdit(ActionEvent event) throws InterruptedException
    {
    	str.add("EditSemester");
    	str.add(SemesterID.getText());
    	if(!SemesterY.getText().isEmpty())
    	{
    		
    		str.add(SemesterY.getText());
    		str.add(SemesterNumber.getText());
        	if(SemStart.getValue() != null&&SemEnd.getValue() != null)
        	{
        		if(SemStart.getValue().isBefore(SemEnd.getValue()))
        		{
        			str.add(SemStart.getValue().toString());
        			str.add(SemEnd.getValue().toString());
        			last = this;
            		client.Accept(str);
            		Thread.sleep(250);
            		str.clear();
            		SemesterY.setEditable(true);
            		ChooseSemesterNum.setVisible(true);
            		SemesterNumber.setVisible(false);
        		}else
        			MessageError("Illegal Dates");
        	}
        	else
        		MessageError("You must pick dates");
        }
    	else
    		MessageError("You must enter a year.");
    }

    /**
    * Cancel semester edit.
    * @param event Gets the ActionEvent when the function called.
    */
    @FXML
    void CancelSemesterEdit(ActionEvent event)
    {
    	SemesterY.setEditable(true);
    	Btn_Edit.setVisible(true);
		Btn_View.setVisible(true);
		Btn_Save.setVisible(false);
		Btn_Cancel.setVisible(false);
		SemStart.setVisible(false);
		SemEnd.setVisible(false);
		StartingDate.setVisible(true);
		EndingDate.setVisible(true);
		ChooseSemesterNum.setVisible(true);
		SemesterNumber.setVisible(false);
    }
    
    /**
    * View student information.
    * @param event Gets the ActionEvent when the function called.
    * @throws InterruptedException the interrupted exception
    */
    @FXML
    void ViewStudentInfo(ActionEvent event) throws InterruptedException
    {
    	str.add("ViewStudentInfo");
    	if(!StdID.getText().isEmpty() && StdID.getText().length() == 9)
     	{
     		try
     		{
     			Integer.valueOf(StdID.getText());
     			str.add(StdID.getText());
        		last = this;
        		client.Accept(str);
     		}catch(NumberFormatException e){ 
     			MessageError(" Student ID Must Be an Integer!!!");
     			}
     	}
     	else if(!StdID.getText().isEmpty())
     	{
     		try
     		{
     			Integer.valueOf(StdID.getText());
     			MessageError("Student ID Must Be 9 Digits!!!");
     		}catch(NumberFormatException e){ 
     			MessageError("Wrong Input You Must Enter a 9 Digits Integer!!!");
     			}
     	}
     	else
    		MessageError("You Must Enter Student ID");
    	str.clear();
    }

    /**
    * Edits the student information.
    * @param event Gets the ActionEvent when the function called.
    */
    @FXML
    void EditStudentInfo(ActionEvent event)
    {
    	Btn_Edit.setVisible(false);
    	Btn_Save.setVisible(true);
		Btn_Cancel.setVisible(true);
		Btn_View.setVisible(false);
		Btn_Clear.setVisible(false);
		FirstName.setEditable(true);
		LastName.setEditable(true);
		Password.setEditable(true);
		PhoneNumber.setEditable(true);
		Address.setEditable(true);
		Birthdate.setVisible(false);
		String Date = Birthdate.getText();
		Date = Date.substring(6, 10) + "-" + Date.substring(3, 5) + "-" + Date.substring(0, 2);
		Birth.setValue(LOCAL_DATE(Date));
		Birth.setVisible(true);
		StdID.setEditable(false);
    }

    /**
    * Save student edit.
    * @param event Gets the ActionEvent when the function called.
    * @throws InterruptedException the interrupted exception
    */
    @FXML
    void SaveStudentEdit(ActionEvent event) throws InterruptedException
    {
    	str.add("SaveStudentInfo");
		str.add(StdID.getText());
		str.add(FirstName.getText());
		str.add(LastName.getText());
		str.add(Password.getText());
		str.add(PhoneNumber.getText());
		str.add(Address.getText());
		str.add(Birth.getValue().toString());
		last = this;
		client.Accept(str);
		Thread.sleep(250);
		str.clear();
		FirstName.setEditable(false);
		LastName.setEditable(false);
		Password.setEditable(false);
		PhoneNumber.setEditable(false);
		Address.setEditable(false);
		Birth.setVisible(false);
		Birthdate.setVisible(true);
		String Date = Birth.getValue().toString();
		Date = Date.substring(8, 10) + "/" + Date.substring(5, 7) + "/" + Date.substring(0, 4);
		Birthdate.setText(Date);
		Btn_Edit.setVisible(true);
    	Btn_Save.setVisible(false);
		Btn_Cancel.setVisible(false);
		Btn_View.setVisible(true);
		Btn_Clear.setVisible(true);
		StdID.setEditable(true);
    }

    /**
    * Cancel student edit.
    * @param event Gets the ActionEvent when the function called.
    */
    @FXML
    void CancelStudentEdit(ActionEvent event)
    {
    	StdID.setEditable(true);
    	Btn_Edit.setVisible(true);
    	Btn_Save.setVisible(false);
		Btn_Cancel.setVisible(false);
		Btn_View.setVisible(true);
		Btn_Clear.setVisible(true);
		FirstName.setEditable(false);
		LastName.setEditable(false);
		RoleName.setEditable(false);
		Password.setEditable(false);
		PhoneNumber.setEditable(false);
		Address.setEditable(false);
		Birth.setVisible(false);
		Birthdate.setVisible(true);
    }
    
    
    @FXML
    void ClearStudentInfo(ActionEvent event)
    {
    	StdID.clear();
		FirstName.clear();
		LastName.clear();
		RoleName.clear();
		Password.clear();
		PhoneNumber.clear();
		Address.clear();
		Birthdate.clear();
		Btn_Clear.setVisible(false);
		Btn_Edit.setVisible(false);
    }
    
    /**
     * View teacher appointing status.
     * @param event Gets the ActionEvent when the function called.
     * @throws InterruptedException the interrupted exception
     */
     @FXML
     void ViewTeacherAppointingStatus(ActionEvent event) throws InterruptedException
     {
     	str.add("SearchTeacher2");
     	str.add(TeaID.getText());
     	last = this;
     	client.Accept(str);
     	Thread.sleep(250);
     	str.clear();
     }
     
     /**
      * show the student in class info.
      * @param event Gets the ActionEvent when the function called.
      * @throws IOException Signals that an I/O exception has occurred.
      * @throws InterruptedException the interrupted exception
      */
     private void StdToClassInfo(ActionEvent event) throws IOException, InterruptedException
     {
     	String SemID = SemesterYear.getText() + SemesterNumber.getText().substring(0, 1);
     	str.add(SemID);
     	str.add(ChooseClassN.getSelectionModel().getSelectedItem());
     	if(!StdID.getText().isEmpty() && StdID.getText().length() == 9)
     	{
     		try
     		{
     			Integer.valueOf(StdID.getText());
     			str.add(StdID.getText());
     	    	client.Accept(str);
     		}catch(NumberFormatException e){ 
     			MessageError(" Student ID Must Be an Integer!!!");
     			}
     	}
     	else if(!StdID.getText().isEmpty())
     	{
     		try
     		{
     			Integer.valueOf(StdID.getText());
     			MessageError("Student ID Must Be 9 Digits!!!");
     		}catch(NumberFormatException e){ 
     			MessageError("Wrong Input You Must Enter a 9 Digits Integer!!!");
     			}
     	}
     	else if(StdID.getText().isEmpty())
     		MessageError("You Must Inter a Student ID!!!");
 			
     	str.clear();
     	AssociateStudent(event);
     }
     
     /**
      * Show view courses schedule.
      * @param event Gets the ActionEvent when the function called.
      */
     @FXML
     public void ViewCoursesSchedule(ActionEvent event)
     {
     	str.add("ViewCoursesSchedule");
     	last = this;
     	client.Accept(str);
     	str.clear();
     }
     
     /**
      * view student association page
      * @param obj class info
      */
     public void StdAss(ArrayList<String> obj)
     {
     	Platform.runLater(new Runnable()
 		{
 			@Override
 			public void run()
 			{
 				ClassInfo.setText(obj.get(2));
 			}	
 		});
     }
    
    /**
    * View courses schedule screen.
    * @param msg courses info
    */
    public void ViewCoursesSchedule2(Object msg)
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.Secretary_ViewCoursesSchedule(msg);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
	}
    
    /**
    * Updates the courses schedule table.
    * @param msg the scheduled courses information.
    * @param CRName the course name to search.
    */
    public void UpdateSecretaryScheduleTable(Object msg, String CRName)
	{
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void run()
			{
				courseslist = (ArrayList<String>) msg;
				String index;
				for(int i = 1, j = 1; i < courseslist.size(); i = i + 5, j++)
				{
					index = String.valueOf(j);
					CoursesSchedule CS;
					CS = new CoursesSchedule(index, courseslist.get(i), courseslist.get(i+1), courseslist.get(i+2), courseslist.get(i+3), courseslist.get(i+4));
					coursesschedule.add(CS);
				}
				CoursesSchedule.setEditable(true);
				Callback<TableColumn, TableCell> cellFactory =
			             new Callback<TableColumn, TableCell>() {
			                 public TableCell call(TableColumn p) {
			                    return new CourseEditingCell(coursesschedule, "DeleteCourse");
			                 }
			             };
				IndexColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("IndexColumn"));
				IndexColumn.setCellFactory(cellFactory);
				IndexColumn.setOnEditCommit(
				            new EventHandler<CellEditEvent<CoursesSchedule, String>>() {
				                @Override
				                public void handle(CellEditEvent<CoursesSchedule, String> t) {
				                    ((CoursesSchedule) t.getTableView().getItems().get(
				                        t.getTablePosition().getRow())
				                        ).getClass();
				                }
				            }
				        );
				CourseNColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("CourseNColumn"));
				TeachingUnitColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("TeachingUnitColumn"));		
				ClassNColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("ClassNColumn"));
				TeacherNColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("TeacherNColumn"));
				WeeklyHoursColumn.setCellValueFactory(new PropertyValueFactory<StudentCourses, String>("WeeklyHoursColumn"));
				CoursesSchedule.setItems(coursesschedule);
			}
		});
    }
    
    /**
    * View course deletion screen.
    * @param course course info
    */
    public void CourseDeletionFields(CoursesSchedule course)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				CourseName.setText(course.getCourseNColumn());
				TeachingUnit.setText(course.getTeachingUnitColumn());
				ClassName.setText(course.getClassNColumn());
				WeekHours.setText(course.getWeeklyHoursColumn());
				TeacherName.setText(course.getTeacherNColumn());
			}
		});
    }
    
    /**
     * Back to schedule table screen.
     * @param CoursesSchedule course.
     */
    @FXML
    void BackToSchedule(ActionEvent event)
    {
    	ViewCoursesSchedule(event);
    }
    
    /**
    * Sets the semester information.
    * @param arr semester info
    */
    public void SetSemesterInfo(ArrayList<String> arr)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				SemesterID.setText(arr.get(1));
				String Date = arr.get(2);
    			Date = Date.substring(8, 10) + "/" + Date.substring(5, 7) + "/" + Date.substring(0, 4);
				StartingDate.setText(Date);
				Date = arr.get(3);
    			Date = Date.substring(8, 10) + "/" + Date.substring(5, 7) + "/" + Date.substring(0, 4);
				EndingDate.setText(Date);
				Btn_Edit.setVisible(true);
			}
		});
    }
    
    /**
    * Edits the semester information page.
    */
    public void EditSemeter()
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				SemesterY.setEditable(false);
				Btn_Edit.setVisible(false);
				Btn_View.setVisible(false);
				Btn_Save.setVisible(true);
				Btn_Cancel.setVisible(true);
				SemStart.setVisible(true);
				SemEnd.setVisible(true);
				String Date = StartingDate.getText();
    			Date = Date.substring(6, 10) + "-" + Date.substring(3, 5) + "-" + Date.substring(0, 2);
				SemStart.setValue(LOCAL_DATE(Date));
				Date = EndingDate.getText();
    			Date = Date.substring(6, 10) + "-" + Date.substring(3, 5) + "-" + Date.substring(0, 2);
				SemEnd.setValue(LOCAL_DATE(Date));
				StartingDate.setVisible(false);
				EndingDate.setVisible(false);
				ChooseSemesterNum.setVisible(false);
				SemesterNumber.setText(ChooseSemesterNum.getSelectionModel().getSelectedItem());
        		SemesterNumber.setVisible(true);
			}
		});
    }
    
    /**
    * Close edit semester page.
    */
    public void CloseEditSem()
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				SemesterY.setEditable(true);
				Btn_Edit.setVisible(true);
				Btn_View.setVisible(true);
				Btn_Save.setVisible(false);
				Btn_Cancel.setVisible(false);
				String Date = SemStart.getValue().toString();
				Date = Date.substring(8, 10) + "/" + Date.substring(5, 7) + "/" + Date.substring(0, 4);
				StartingDate.setText(Date);
				Date = SemEnd.getValue().toString();
				Date = Date.substring(8, 10) + "/" + Date.substring(5, 7) + "/" + Date.substring(0, 4);
				EndingDate.setText(Date);
				SemStart.setVisible(false);
				SemEnd.setVisible(false);
				StartingDate.setVisible(true);
				EndingDate.setVisible(true);
				ChooseSemesterNum.setVisible(true);
        		SemesterNumber.setVisible(false);
			}
		});
    }
	   
    /**
    * set the semester number in combo box.
    */
    public void SemNum()
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				ChooseSemesterNum.setItems(FXCollections.observableArrayList("1. Winter", "2. Spring"));
				ChooseSemesterNum.getSelectionModel().selectFirst();
			}
		});
    }
    
    /**
    * Open new class screen.
    * @param obj classes info
    */
    public void OpenNCScreen(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.Secretary_NewClass(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }

    /**
    * New class page.
    * @param obj classes info
    */
    public void NewClassPage(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				SemesterYear.setText(obj.get(1));
				SemesterNumber.setText(obj.get(2));
				ChooseClassN.setItems(FXCollections.observableArrayList("1st Grade", "2nd Grade", "3rd Grade",
						"4th Grade", "5th Grade", "6th Grade", "7th Grade", "8th Grade", "9th Grade",
						"10th Grade", "11th Grade", "12th Grade"));
				ChooseClassN.getSelectionModel().selectFirst();
				ChooseClassNum.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5"));
				ChooseClassNum.getSelectionModel().selectFirst();
			}
		});
    }
    
    /**
    * Sets the view class in the semesters.
    * @param obj classes info
    */
    public void SetVCSemesters(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.Secretary_ViewClass(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }

    /**
    * View class page.
    * @param obj classes info
    */
    public void ViewClassPage(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				ChooseSemesterY.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> ViewYearSemeters(newValue, obj));
				int i = 1;
				ObservableList<String> list;
				ArrayList<String> years = new ArrayList<String>();
				while(i < obj.size() - 1)
				{
					years.add(obj.get(i));
					i += 2;
				}
				list = FXCollections.observableArrayList(years);
				ChooseSemesterY.setItems(list);
				ChooseSemesterY.getSelectionModel().selectFirst();
			}
		});
    }
    
    /**
    * View year semesters.
    * @param s name of semester
    * @param obj classes info
    */
    public void ViewYearSemeters(String s, ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			int i = 1;
    			ObservableList<String> list;
    			ArrayList<String> nums = new ArrayList<String>();
    			while(i < obj.size() - 1)
    			{
    				if(obj.get(i).equals(s))
    					nums.add(obj.get(i + 1));
    				i += 2;
    			}
    			list = FXCollections.observableArrayList(nums);
    			ChooseSemesterNum.setItems(list);
    			ChooseSemesterNum.getSelectionModel().selectFirst();
    		}
		});
    }
    
    /**
    * View semester classes.
    * @param obj classes info
    */
    public void ViewSemesterClasses(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			ChooseClassN.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> ViewClassNumbers(newValue, obj));
    			int i = 1;
    			boolean exist = false;
    			ObservableList<String> list;
    			ArrayList<String> names = new ArrayList<String>();
    			int j;
    			while(i < obj.size() - 1)
    			{
    				for(j = 0; j < names.size(); j++)
    					if(obj.get(i).equals(names.get(j)))
    							exist = true;
    				if(!exist)
    				{
    					names.add(obj.get(i));
    					exist = false;
    				}
    				i += 2;
    				exist = false;
    			}
    			list = FXCollections.observableArrayList(names);
    			ChooseClassN.setItems(list);
    			ChooseClassN.getSelectionModel().selectFirst();
    		}
		});
    }
    
    /**
    * View class numbers.
    * @param s class name
    * @param obj ArrayList of class
    */
    public void ViewClassNumbers(String s, ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			int i = 1;
    			ObservableList<String> list;
    			ArrayList<String> nums = new ArrayList<String>();
    			while(i < obj.size() - 1)
    			{
    				if(obj.get(i).equals(s))
    					nums.add(obj.get(i + 1));
    				i += 2;
    			}
    			list = FXCollections.observableArrayList(nums);
    			
    			
    				ChooseClassNum.setItems(list);
    				ChooseClassNum.getSelectionModel().selectFirst();
    		}
		});
    }
    
    /**
    * Sets the max students value.
    * @param obj the new max students value
    */
    public void setMaxStudentsValue(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			MaxStudents.setText(obj.get(1));
    		}
		});
    }
    
    /**
     * Gets the class edit fields, set the details in screen.
     */
    public void GetClassEditFields()
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
		    	MaxStds.setText(MaxStudents.getText());
		    	MaxStds.setVisible(true);
		    	MaxStudents.setVisible(false);
		    	ClassName.setText(ChooseClassN.getSelectionModel().getSelectedItem());
		    	ClassNumber.setText(ChooseClassNum.getSelectionModel().getSelectedItem());
		    	ClassName.setVisible(true);
		    	ClassNumber.setVisible(true);
		    	ChooseClassN.setVisible(false);
		    	ChooseClassNum.setVisible(false);
		    	Btn_View.setVisible(false);
		    	Btn_Edit.setVisible(false);
		    	Btn_Save.setVisible(true);
		    	Btn_Cancel.setVisible(true);
    		}
		});
    }

    /**
     * set the student information.
     * @param obj student info
     */
    public void StudentInfo2(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			StdID.setText(obj.get(1));
    			FirstName.setText(obj.get(2));
    			LastName.setText(obj.get(3));
    			RoleName.setText("Student");
    			Password.setText(obj.get(4));
    			PhoneNumber.setText(obj.get(5));
    			Address.setText(obj.get(6));
    			String Date = obj.get(7);
    			Date = Date.substring(8, 10) + "/" + Date.substring(5, 7) + "/" + Date.substring(0, 4);
    			Birthdate.setText(Date);
    			Btn_Edit.setVisible(true);
    		}
		});
    }
    
    /**
    * Open association student to class page.
    * @param obj student information
    */
    public void OpenAssStdPage(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			try
    			{
					screen.Secretary_AssociateStudent(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
    		}
		});
    }
    
    /**
    * Open view association page.
    * @param obj student info
    */
    public void OpenViewAssPage(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			try
    			{
					screen.Secretary_ViewAssociations(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
    		}
		});
    }
    
    /**
    * Associate student page, set the details.
    * @param obj student info
    */
    public void AssociateStudentPage(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			SemesterYear.setText(obj.get(1));
    			SemesterNumber.setText(obj.get(2));
    			obj.remove(0);
    			obj.remove(0);
    			obj.remove(0);
    			ChooseClassN.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> GetMaxAndCurStudentsNum(newValue));
    			ObservableList<String> list = FXCollections.observableArrayList(obj);
    			ChooseClassN.setItems(list);
    			ChooseClassN.getSelectionModel().selectFirst();	
    		}
		});
    }
    
    /**
    * View associations page.
    * @param obj student info
    */
    public void ViewAssociationsPage(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			ChooseSemesterY.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> ViewYearSemeters(newValue, obj));
				int i = 1;
				ObservableList<String> list;
				ArrayList<String> years = new ArrayList<String>();
				while(i < obj.size() - 1)
				{
					years.add(obj.get(i));
					i += 2;
				}
				list = FXCollections.observableArrayList(years);
				ChooseSemesterY.setItems(list);
				ChooseSemesterY.getSelectionModel().selectFirst();
    		}
		});
    }
    
    /**
    * Gets the max and cur students number.
    * @param string of class
    */
    public void GetMaxAndCurStudentsNum(String string)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
		    	str.add("GetMaxAndCurStudentsNum");
		    	str.add(string);
		    	client.Accept(str);
		    	try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    	str.clear();
    		}
		});
    }
    
    /**
    * Sets the max and cur students number.
    * @param obj max student
    */
    public void SetMaxAndCurStudentsNum(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			MaxStudents.setText(obj.get(1));
    			CurrentStudents.setText(obj.get(2));
    		}
		});
    }
    
    /**
    * Sets the courses class type.
    * @param obj classes and courses
    */
    public void setCoursesClasType(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.Secretary_NewExpStdReg(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    /**
    * set Courses class type.
    * @param obj courses and classes
    */
    public void CoursesClasType(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run() 
			{
				ChooseCourseN.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> CourseClassesType11( obj));
				
				ObservableList<String> list ;
				list = FXCollections.observableArrayList(((ArrayList<String>) obj));
				ChooseCourseN.setItems(list);
				ChooseCourseN.getSelectionModel().selectFirst();
			}
		});
    }
    
    /**
    * get the courses and classes from server
    * @param obj courses and classes info
    */
    public void CourseClassesType11(Object obj) 
    {
    	str.add("CourseClassesType11");
    	str.add(ChooseCourseN.getSelectionModel().getSelectedItem());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
    * Sets the courses class type.
    * @param obj classes info
    */
    public void setCoursesClasType123(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run()
			{
				ArrayList<String> arr1 = new ArrayList<String>();
				ObservableList<String> list ;
				list = FXCollections.observableArrayList(((ArrayList<String>) obj));
				ChooseClassN.setItems(list);
				ChooseClassN.getSelectionModel().selectFirst();
				arr1.clear();
				arr1.add("Registration");
				arr1.add("Deletion");
				list = FXCollections.observableArrayList(arr1);
				ChooseType.setItems(list);
				ChooseType.getSelectionModel().selectFirst();
			}
		});
    }
    
    /**
    * Sets the courses details.
    * @param obj courses info
    */
    public void setCourses(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run()
			{
				try
				{
					if(((ArrayList<String>) obj).get(0).equals("CoursesSet"))
						screen.Secretary_ViewExpStdReg(obj);
					else
						screen.Secretary_ViewCourse(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    /**
    * Choose courses.
    * @param obj courses info
    */
    public void ChoiseCourses(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run() 
			{
				if(((ArrayList<String>) obj).get(0).equals("CoursesSet2"))
					ChooseCourseN.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> CourseInfo( obj));
				((ArrayList<String>) obj).remove(0);
				ObservableList<String> list ;
				list = FXCollections.observableArrayList(((ArrayList<String>) obj));
				ChooseCourseN.setItems(list);
				ChooseCourseN.getSelectionModel().selectFirst();
			}
		});
    }
  
    /**
    * Course information.
    * @param obj the course details
    */
    public void CourseInfo(Object obj) 
    {
    	str.add("ViewCourse2");
    	str.add(ChooseCourseN.getSelectionModel().getSelectedItem());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
    * View course info.
    * @param obj course details
    */
    public void ViewCourseInfo2(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run()
			{
				if(((ArrayList<String>) obj).size()>2)
				{
					CourseNum.setText(((ArrayList<String>) obj).get(1));
					TeachingUnit.setText(((ArrayList<String>) obj).get(3));
					WeekHours.setText(((ArrayList<String>) obj).get(2));
					PreCourses.setText(((ArrayList<String>) obj).get(4));
				} else
				{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText(((ArrayList<String>) obj).get(1));
					alert.showAndWait();
				}	
			}
		});
    }
    
    /**
    * View student request status.
    * @param obj request details
    */
    public void ViewRegStatus(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run()
			{
				ChooseClassN.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> ViewRegStatus2(newValue,(ArrayList<String>)obj));
				ObservableList<String> list;
				ArrayList<String> classes = new ArrayList<String>();
				int i;
				for(i=1;i<((ArrayList<String>) obj).size();i=i+3)
				{
					classes.add(((ArrayList<String>) obj).get(i));
				}
				list = FXCollections.observableArrayList(classes);
				ChooseClassN.setItems(list);
				ChooseClassN.getSelectionModel().selectFirst();
			}
		});
    }
    
    /**
    * View student request status.
    * @param obj request details 
    * @param s student id
    */
    public void ViewRegStatus2(String s, ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			int i=1;
    			while(i < obj.size() - 1)
    			{
    				if(obj.get(i).equals(s))
    				{
    					Type.setText(((ArrayList<String>) obj).get(i+1));
						Status.setText(((ArrayList<String>) obj).get(i+2));
    				}
    				i++;
    			}
    		}
		});
    }
    
    /**
    * Sets the class in changing appoint screen.
    * @param obj classes info
    */
    public void SetClassChang(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.Secretary_ViewTeaApt(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    /**
    * Sets the class in changing appoint.
    * @param obj classes info
    */
    public void SetClassChang2(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()/*Change teacher appointing*/
    		{
    			TeaID.setText(obj.get(1));
    			ChooseClassN.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> SetCoursesChang(newValue, obj));
    			int i = 2;
    			boolean exist = false;
    			ObservableList<String> list;
    			ArrayList<String> names = new ArrayList<String>();
    			int j;
    			while(i < obj.size() - 1)
    			{
    				for(j = 0; j < names.size(); j++)
    					if(obj.get(i).equals(names.get(j)))
    							exist = true;
    				if(!exist)
    				{
    					names.add(obj.get(i));
    					exist = false;
    				}
    				i += 2;
    				exist = false;
    			}
    			list = FXCollections.observableArrayList(names);
    			ChooseClassN.setItems(list);
    			ChooseClassN.getSelectionModel().selectFirst();
    		}
		});
    }
    
    /**
    * Sets the courses in changing appoint.
    * @param s course
    * @param obj courses info
    */   
    public void SetCoursesChang(String s, ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()/*Change teacher appointing*/
    		{
    			if(obj.get(0).equals("SearchTeacher2"))
    				ChooseCourseN.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> GetChangeStatus( obj));
    			int i = 2;
    			ObservableList<String> list;
    			ArrayList<String> nums = new ArrayList<String>();
    			while(i < obj.size() - 1)
    			{
    				if(obj.get(i).equals(s))
    					nums.add(obj.get(i + 1));
    				i += 2;
    			}
    			list = FXCollections.observableArrayList(nums);
    			ChooseCourseN.setItems(list);
    			ChooseCourseN.getSelectionModel().selectFirst();
    		}
		});
    }
    
    /**
    * Gets the change appoint status.
    * @param obj teacher info
    */
    public void GetChangeStatus(ArrayList<String> obj)
    {
    	str.add("GetChangeStatus");
    	str.add(TeaID.getText());
    	str.add(ChooseClassN.getSelectionModel().getSelectedItem());
    	str.add(ChooseCourseN.getSelectionModel().getSelectedItem());
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
    * Gets the change appoint status.
    * @param obj teacher info
    */
    public void GetChangeStatus2(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			ChooseTeacherReq.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> GetChangeStatus3( newValue,obj));
    			int i = 1;
    			ObservableList<String> list;
    			ArrayList<String> IDS = new ArrayList<String>();
    			while(i < obj.size() - 1)
    			{
    				IDS.add(obj.get(i));
    				i=i+2;
    			}
    			list = FXCollections.observableArrayList(IDS);
    			ChooseTeacherReq.setItems(list);
    			ChooseTeacherReq.getSelectionModel().selectFirst();
    		}
		});
    }
    
    /**
    * Gets the change appoint status.
    * @param obj teacher info
    * @param s class name
    */
    public void GetChangeStatus3(String s,ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			int i = 1;
    			String Status1="";
    			while(i < obj.size() - 1)
    			{
    				if(obj.get(i).equals(s))
    					Status1=(obj.get(i + 1));
    				i += 2;
    			}
    			Status.setText(Status1);
    		}
		});
    }
   
    /**
    * Student info.
    * @param obj student info
    */
    public void StudentInfo(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			StdID.setText(obj.get(1));
    			FirstName.setText(obj.get(2));
    			LastName.setText(obj.get(3));
    			RoleName.setText("Student");
    			Password.setText(obj.get(4));
    			PhoneNumber.setText(obj.get(5));
    			Address.setText(obj.get(6));
    			String bDate = obj.get(7);
    			bDate = bDate.substring(8, 10) + "/" + bDate.substring(5, 7) + "/" + bDate.substring(0, 4);
    			Birthdate.setText(bDate);
    			Btn_Edit.setVisible(true);
    			Btn_Clear.setVisible(true);
    		}
		});
    }
    
    /**
    * New appointing classes.
    * @param obj classes info
    */
    public void NewAppointingClasses(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.Secretary_NewAppointing(obj);;
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    /**
    * New appointing classes.
    * @param obj classes info
    */
    public void NewAppointingClasses2(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			ChooseClassN.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> NewAppointingClasses3( newValue,obj));
    			int i = 1;
    			int j;
    			int flag=0;
    			ObservableList<String> list;
    			ArrayList<String> IDS = new ArrayList<String>();
    			while(i < obj.size() - 1)
    			{
    				for(j=0;j<IDS.size();j++)
    				{
    					if(IDS.get(j).equals(obj.get(i)))
    						flag=1;
    				}
    				if(flag==0)
    					IDS.add(obj.get(i));
    				i=i+2;
    				flag=0;
    			}
    			list = FXCollections.observableArrayList(IDS);
    			ChooseClassN.setItems(list);
    			ChooseClassN.getSelectionModel().selectFirst();
    		}
		});
    }
    
    /**
    * New appointing classes.
    * @param obj classes info
    */
    public void NewAppointingClasses3(String s,ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			ObservableList<String> list;
    			ArrayList<String> crs = new ArrayList<String>();
    			int i = 1;
    			while(i < obj.size() - 1)
    			{
    				if(obj.get(i).equals(s))
    					crs.add(obj.get(i+1));
    				i += 2;
    			}
    			list = FXCollections.observableArrayList(crs);
    			ChooseCourseN.setItems(list);
    			ChooseCourseN.getSelectionModel().selectFirst();
    		}
		});
    }
    
    /**
    * Clear view registration student info.
    */
    public void clearViewRegStd()
    {
    	Platform.runLater(new Runnable()
		{
    		@Override
    		public void run()
    		{
    			ChooseClassN.getSelectionModel().clearSelection();
    			ChooseClassN.getItems().clear();
    			Type.clear();;
				Status.clear();;
    		}
		});
    }
    
    
    /**
    * View tea appointing in table.
    * @param obj teacher info
    */
    public void ViewTeaAppointing2(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
    		@SuppressWarnings("unchecked")
			@Override
    		public void run()
    		{
    			Btn_Back.setVisible(true);
    			TeaxtTeacherID.setVisible(false);
        		TeaID.setVisible(false);
        		Btn_View.setVisible(false);
        		TeaAptList.setVisible(true);
    			ArrayList<String> list2 = (ArrayList<String>) obj;
				for(int i=1;i<list2.size();i=i+5)
				{
					teacherAppoint ST = new teacherAppoint(list2.get(i),list2.get(i+1),list2.get(i+2),list2.get(i+3),list2.get(i+4));
					list.add(ST);
				}
				BackPic.toFront();
				TeaAptList.setEditable(true);
				TeacherIDcol.setCellValueFactory(new PropertyValueFactory<teacherAppoint, String>("TeacherIDcol"));
				ClassIDcol.setCellValueFactory(new PropertyValueFactory<teacherAppoint, String>("ClassIDcol"));		            
				CourseIDcol.setCellValueFactory(new PropertyValueFactory<teacherAppoint, String>("CourseIDcol"));				
				TUDcol.setCellValueFactory(new PropertyValueFactory<teacherAppoint, String>("TUDcol"));				
				WHsIDcol.setCellValueFactory(new PropertyValueFactory<teacherAppoint, String>("WHsIDcol"));			
				TeaAptList.setItems(list);	
    		}
		});
    }
    
    /**
     * 
     * View Class info after edit.
     *  
     */
    public void BackAfterClassEdit()
    {
    	Btn_Save.setVisible(false);
    	Btn_Cancel.setVisible(false);
    	Btn_View.setVisible(true);
    	Btn_Edit.setVisible(true);
    	Btn_Back.setVisible(true);
    	MaxStudents.setText(MaxStds.getText());
    	MaxStds.setVisible(false);
    	MaxStudents.setVisible(true);
    	ClassNumber.setVisible(false);
    	ChooseClassNum.setVisible(true);
    	ClassName.setVisible(false);
    	ChooseClassN.setVisible(true);
    }
    
    /**
     * set the date
     * @param dateString the date string
     * @return the local date
     */
     public static final LocalDate LOCAL_DATE (String dateString)
     {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         LocalDate localDate = LocalDate.parse(dateString, formatter);
         return localDate;
     }
}
