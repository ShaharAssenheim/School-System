package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import Boundery.GUIPlayer;
import Entity.Appointing;
import Entity.HomeWork;
import Entity.Student;
import Entity.StudentForList;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

/**
 * This is the Teacher Controller, responsible for add new homework, edit homework, check submitted files,
 * view student data, view students list by class or course, view every appointing of the teacher, view teacher data.
 * @author Eliran Abregel
 */
public class TeacherController extends BaseController
{
	
	/**
	 * Instantiates a new teacher controller.
	 */
	// Constructor
	public TeacherController()
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
	
	/** The Homework N. */
	@FXML // fx:id="NewHomeworkName"
    private TextField HomeworkN; // Value injected by FXMLLoader
    
    /** The Student ID. */
    @FXML // fx:id="StudentID"
    private TextField StudentID; // Value injected by FXMLLoader
	
	/** The Attachment. */
	@FXML // fx:id="Attachment"
    private Button Attachment; // Value injected by FXMLLoader

    /** The Pick deadline. */
    @FXML // fx:id="DeadlineDate"
    private DatePicker PickDeadlineD; // Value injected by FXMLLoader

    /** The Homework file name. */
    @FXML // fx:id="HomeworkFile"
    private TextField HomeworkFile; // Value injected by FXMLLoader

    /** The New HM course name. */
    @FXML // fx:id="NewHMCourseName"
    private TextField NewHMCourseName; // Value injected by FXMLLoader

    /** The New HW dead line date. */
    @FXML // fx:id="NewHWDeadLineDate"
    private TextField NewHWDeadLineDate; // Value injected by FXMLLoader

    /** The New HW class name. */
    @FXML // fx:id="NewHWClassName"
    private TextField NewHWClassName; // Value injected by FXMLLoader
	
	/** The work hours column. */
	@SuppressWarnings("rawtypes")
	@FXML
    private TableColumn WHCol;
    
    /** The Course column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn CourseCol;

    /** The Class column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn ClassCol;
    
    /** The teaching unit column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn TUCol;
    
    /** The Appointing list table. */
    @FXML
    private TableView<Appointing> AppointingList;
    
    /** The Checked HW file. */
    @FXML
    private TextField CheckedHWFile;
    
    /** The Grade. */
    @FXML
    private TextField Grd;
    
    /** The Evaluation file. */
    @FXML
    private TextField EvaluationFile;
    
    /** The Button attach CHecked HW. */
    @FXML
    private Button Btn_AttachCHHW;
    
    /** The Button attach evaluation. */
    @FXML
    private Button Btn_AttachEvaluation;
	
	/** The Grade text. */
	@FXML
    private Text GradeText;
	
	/** The Evaluation text. */
	@FXML
    private Text EvaluationText;
	
	/** The Checked HW. */
	@FXML
    private Text CheckedHW;
	
	/** The Table label. */
	@FXML
    private Label TableLabel;
	
	/** The Student ID. */
	@FXML
	private TextField StudentID2;
	
	/** The Button check. */
	@FXML
    private Button Btn_Check;
	
	/** The Button student in A class or course. */
	@FXML
    private MenuItem Btn_StudentInAClassOrCourse;
	
	/** The Student list table. */
	@FXML
    private TableView<StudentForList> StudentList;
	
	/** The Student name column. */
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn StudentNameCol;
	
	/** The Sub column. */
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn SubCol;

    /** The Student ID column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn StudentIDCol;
	
    /** The Homework ChoiceBox. */
    @FXML
    private ChoiceBox<String> HomeworkCB;
    
    /** The Course ChoiceBox. */
    @FXML
    private ChoiceBox<String> CourseCB;
    
	/** The Button home. */
	//Teacher Controller Add-ons and Methods
	@FXML // fx:id="Btn_Home"
    private Button Btn_Home; // Value injected by FXMLLoader

    /** The Button student data. */
    @FXML // fx:id="Btn_StudentData"
    private MenuItem Btn_StudentData; // Value injected by FXMLLoader

    /** The User name. */
    @FXML // fx:id="UName"
    private Text UName; // Value injected by FXMLLoader

    /** The Button logout. */
    @FXML // fx:id="Btn_Logout"
    private Button Btn_Logout; // Value injected by FXMLLoader

    /** The Button student in A course. */
    @FXML // fx:id="Btn_StudentInACourse"
    private MenuItem Btn_StudentInACourse; // Value injected by FXMLLoader

    /** The Button student in A class. */
    @FXML // fx:id="Btn_StudentInAClass"
    private MenuItem Btn_StudentInAClass; // Value injected by FXMLLoader

    /** The Button user info. */
    @FXML // fx:id="Btn_UserInfo"
    private MenuItem Btn_UserInfo; // Value injected by FXMLLoader

    /** The Button display appointing. */
    @FXML // fx:id="Btn_DisplayAppointings"
    private MenuItem Btn_DisplayAppointings; // Value injected by FXMLLoader

    /** The Time. */
    @FXML // fx:id="Time"
    private Text Time; // Value injected by FXMLLoader

    /** The Button new homework. */
    @FXML // fx:id="Btn_NewHomework"
    private MenuItem Btn_NewHomework; // Value injected by FXMLLoader

    /** The Date. */
    @FXML // fx:id="Date"
    private Text Date; // Value injected by FXMLLoader

    /** The Button check homework. */
    @FXML // fx:id="Btn_CheckHomework"
    private MenuItem Btn_CheckHomework; // Value injected by FXMLLoader

    /** The Button submissions list. */
    @FXML // fx:id="Btn_SubmissionsList"
    private MenuItem Btn_SubmissionsList; // Value injected by FXMLLoader

    /** The Button view homework. */
    @FXML // fx:id="Btn_ViewHomework"
    private MenuItem Btn_ViewHomework; // Value injected by FXMLLoader
    
    /** The Button edit. */
    @FXML // fx:id="Btn_Edit"
    private Button Btn_Edit; // Value injected by FXMLLoader

    /** The Button cancel. */
    @FXML // fx:id="Btn_Cancel"
    private Button Btn_Cancel; // Value injected by FXMLLoader
    
    /** The Button save. */
    @FXML // fx:id="Btn_Save"
    private Button Btn_Save; // Value injected by FXMLLoader
    
    /** The Button view. */
    @FXML // fx:id="Btn_View"
    private Button Btn_View; // Value injected by FXMLLoader
    
    /** The View HM name. */
    @FXML // fx:id="ViewHMName"
    private TextField ViewHMName; // Value injected by FXMLLoader
    
    /** The Choose course Name ChoiceBox. */
    @FXML // fx:id="CourseName"
    private ChoiceBox<String> ChooseCourseN; // Value injected by FXMLLoader
    
    /** The Choose class Name ChoiceBox. */
    @FXML // fx:id="CourseName"
    private ChoiceBox<String> ChooseClassN; // Value injected by FXMLLoader
    
    /** The View homework button. */
    @FXML // fx:id="ViewHomework_Btn"
    private Button ViewHomework_Btn; // Value injected by FXMLLoader
    
    /** The Students list table. */
    @FXML
    private TableView<Student> StudentsList;
    
    /** The First name column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn FirstNameCol;
    
    /** The Birth column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn BirthCol;
    
    /** The Address column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn AddrCol;
    
    /** The Student ID column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn StIDCol;
    
    /** The Phone number column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn PNumCol;
    
    /** The Last name column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn LastNameCol;
    
    /** The Class name column. */
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn ClNameCol;
    
    /** The button view in course. */
    @FXML
    private Button Btn_ViewInCourse;

    /** The button view in class. */
    @FXML
    private Button Btn_ViewInClass;
    
    /** The Class label. */
    @FXML
    private Text ClassLabel;
    
    /** The Course label. */
    @FXML
    private Text CourseLable;
    
    /** The Back picture. */
    @FXML
    private ImageView BackPic;
	
	/** The HW name. */
	private static String HWName;
	
	/** The Course name. */
	private static String CName;
		
	/** The Submitted file. */
	private static String SubmittedFile;
		
	/** The bytes array. */
	private byte[] bytesArray;
	
	/** The bytes array 2. */
	private byte[] bytesArray2;
	
	/** The file. */
	private File file;
	
	/** The file 2. */
	private File file2;
	 
	/** The list. */
	private ObservableList<Student> list = FXCollections.observableArrayList();
	
	/** The Students list. */
	private ObservableList<StudentForList> SDlist = FXCollections.observableArrayList();
	
	/** The Appointing list. */
	private ObservableList<Appointing> APlist = FXCollections.observableArrayList();


    
    /**
     * View homework screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ViewHomework(ActionEvent event) throws IOException
    {
    	ArrayList<String> content = new ArrayList<String>();
    	content.add("Courses");
    	content.add("Classes");
    	content.add("3");
    	content.add(LoginController.Username);
    	last = this;
    	client.Accept(content);
    	content.clear();
    }
    
    public void ViewHomework2(ArrayList<String> msg)
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{	
				try {
					screen.Teacher_ViewHW(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
   
    /**
     * Submissions list.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void SubmissionsList(ActionEvent event) throws IOException //1-get courses names
    {
    	str.add("TCourseName");
    	str.add("2");
    	str.add(LoginController.Username);
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    

    /**
     * Search students by course name and HW name.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void SearchStudent2(ActionEvent event) throws IOException //6-click on search button to find students list
    {
    	if(HomeworkCB.getSelectionModel().isEmpty())
    		message(AlertType.ERROR,"Please fill all the feilds");
    	else
    	{
    	str.add("StName");
    	str.add(CourseCB.getSelectionModel().getSelectedItem());
    	str.add(HomeworkCB.getSelectionModel().getSelectedItem());
    	HWName = HomeworkCB.getSelectionModel().getSelectedItem();
    	CName = CourseCB.getSelectionModel().getSelectedItem();
    	last = this;
    	client.Accept(str);
    	str.clear();
    	}
    }
    
    

    /**
     * Teacher get submitted file.
     *
     * @param event the event
     */
    @FXML
    protected void TeacherGetSubmittedFile(ActionEvent event)
    {
    	last = this;
    	ask.add("TeacherSubmittedfile");
    	ask.add(SubmittedFile);
    	client.Accept(ask);
    	ask.clear();
    }
    
 
    /**
     * Check homework by press "Check" button.
     *
     * @param event the event
     */
    @FXML
    void CheckHomework(ActionEvent event) {
    	Grd.setEditable(true);
    	GradeText.setVisible(true);
    	EvaluationText.setVisible(true);
    	EvaluationFile.setVisible(true);
    	Grd.setVisible(true);
    	CheckedHWFile.setVisible(true);
    	CheckedHW.setVisible(true);
    	Btn_AttachCHHW.setVisible(true);
    	Btn_AttachEvaluation.setVisible(true);
    	Btn_Save.setVisible(true);
    	Btn_Cancel.setVisible(true);
    	if(SubmissionName.getText().isEmpty())
    	{
    		SubmissionName.setText("-");
    		SubmissionDate.setText("-");
    		Btn_AttachCHHW.setDisable(true);
			Grd.setText("0");
    	}
    }
    
    /**
     * Attach evaluation file.
     *
     * @param event the event
     */
    @SuppressWarnings("resource")
	@FXML
    void AttachEvaluation(ActionEvent event) 
    {
    	// One File
        FileChooser fc = new FileChooser();
        //Stage stage = new Stage();
        fc.setTitle("Get Text");
        fc.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));    
        file = fc.showOpenDialog(GUIPlayer.PrimaryStage);
        if (file != null)
        {	
            try {
            	String path = file.getPath();
            	EvaluationFile.setText(path);
                bytesArray = new byte[(int) file.length()];
                FileInputStream fis = new FileInputStream(file);
                fis.read(bytesArray); //read file into bytes[]
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

    /**
     * Attach checked HM file.
     *
     * @param event the event
     */
    @SuppressWarnings("resource")
	@FXML
    void AttachCheckedHM(ActionEvent event)
    {
    	// One File
        FileChooser fc = new FileChooser();
        //Stage stage = new Stage();
        fc.setTitle("Get Text");
        fc.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));    
        file2 = fc.showOpenDialog(GUIPlayer.PrimaryStage);
        if (file2 != null)
        {	
            try {
            	String path = file2.getPath();
            	CheckedHWFile.setText(path);
            	bytesArray2 = new byte[(int) file2.length()];
                FileInputStream fis = new FileInputStream(file2);
                fis.read(bytesArray2); //read file into bytes[]
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
    
    /**
     * Save homework check with new grade, evaluation file and checked HW file.
     *
     * @param event the event
     */
    @FXML
    void SaveHomeworkCheck(ActionEvent event) {
    	if((Grd.getText().trim().isEmpty() || bytesArray == null || bytesArray2 == null) && !(Btn_AttachCHHW.isDisable()))
    	{
    		message(AlertType.ERROR, "Please fill all the fields");
    	}
    	else if((Grd.getText().trim().isEmpty() || bytesArray == null) && Btn_AttachCHHW.isDisable())
    	{
    		message(AlertType.ERROR, "Please fill all the fields");
    	}
    	else
    	{
    	String Ext2 = "";
    	String Ext = getExt(file);
    	if(!(Btn_AttachCHHW.isDisable()))
    		Ext2 = getExt(file2);
    	ArrayList<Object> content = new ArrayList<Object>();
    	content.add("UpdateGrade");
    	content.add(Grd.getText());
    	content.add(StudentID2.getText());
    	content.add(HWName);
    	content.add(CName);
    	content.add(bytesArray);
    	content.add(bytesArray2);
    	content.add(Ext);
    	content.add(Ext2);
    	content.add(SubmissionName.getText());
    	content.add(SubmissionDate.getText());
    	client.Accept(content);
    	content.clear();
    	message(AlertType.CONFIRMATION,"Submitted file updated successfully");
    	}
    }

    /**
     * Cancel homework check.
     *
     * @param event the event
     */
    @FXML
    void CancelHomeworkCheck(ActionEvent event) {
    	try {
			screen.Teacher_MainMenu();
		}catch(IOException e){
			e.printStackTrace();
			}
    }
    


	/**
	 * Get homework file for download.
	 *
	 * @param event the event
	 */
	@FXML
	protected void TeacherGetHomeworkFile(ActionEvent event)
	{
		last = this;
		ask.add("TeacherFile");
		ask.add(HWName);
		client.Accept(ask);
		ask.clear();
	}
    
    
    /**
     * Show main screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void ShowMainScreen(ActionEvent event) throws IOException
    {
    	screen.Teacher_MainMenu();
    }
 
    
    /**
     * Get courses and classes names
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void NewHomework(ActionEvent event) throws IOException //1-get courses and classes names
    {
    	ArrayList<String> content = new ArrayList<String>();
    	content.add("TClasses");
    	content.add(LoginController.Username);
    	last = this;
    	client.Accept(content);
    	content.clear();
    }
    
    
    
    
    /**
     * Attach homework file.
     *
     * @param event the event
     */
    @SuppressWarnings("resource")
	@FXML
    void AttachHomeworkFile(ActionEvent event) //4-convert the file to byte array
    {
    	// One File
        FileChooser fc = new FileChooser();
        //Stage stage = new Stage();
        fc.setTitle("Get Text");
        fc.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));    
        file = fc.showOpenDialog(GUIPlayer.PrimaryStage);
        if (file != null)
        {	
            try {
            	String path = file.getPath();
            	HomeworkFile.setText(path);
                bytesArray = new byte[(int) file.length()];
                FileInputStream fis = new FileInputStream(file);
                fis.read(bytesArray); //read file into bytes[]
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

    /**
     * Save new homework.
     *
     * @param event the event
     */
    @FXML
    void SaveNewHomework(ActionEvent event) //5-upload the file to the server
    {
    	if(HomeworkN.getText().trim().isEmpty() || bytesArray == null || PickDeadlineD.getValue() == null)
    	{
    		message(AlertType.ERROR, "Please fill all the fields");
    	}
    	else
    	{
    	java.util.Date date = 
        	    java.util.Date.from(PickDeadlineD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    	String Ext = getExt(file);
    	ArrayList<Object> content = new ArrayList<Object>();
    	content.add(bytesArray);
    	content.add(HomeworkN.getText());
    	content.add(Ext);
    	content.add(sqlDate);
    	content.add(ChooseClassN.getSelectionModel().getSelectedItem().toString());
    	content.add(ChooseCourseN.getSelectionModel().getSelectedItem().toString());
    	content.add(LoginController.Username);
    	last=this;
    	client.Accept(content);
    	content.clear();
    	}
    }
    

    /**
     * Show Student data screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void StudentData(ActionEvent event) throws IOException //1-show Teacher_StudentData screen
    {
    	screen.Teacher_StudentData();
    }
    
    
    /**
     * Search student from table by ID.
     *
     * @param event the event
     */
    @FXML
    void SearchStudent(ActionEvent event)  //2-search student by ID
    {
    	if(StdID.getText().trim().isEmpty())
    	{
    		message(AlertType.ERROR, "Please fill Student ID field");
    	}
    	else
    	{
	    	ArrayList<String> list = new ArrayList<String>();
	    	list.add("SID");
	    	list.add(StdID.getText());
	    	last = this;
	    	client.Accept(list);
	    	list.clear();
    	}
    }
    
    
    

    
    /**
     * Get classes and courses names
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void StudentInAClassOrCourse(ActionEvent event) throws IOException //1-get classes and courses names
    {
    	ArrayList<String> content = new ArrayList<String>();
    	content.add("Courses");
    	content.add("Classes");
    	content.add("2");
    	content.add(LoginController.Username);
    	last = this;
    	client.Accept(content);
    	content.clear();
    }
    
    
    
    
    
    /**
     * find students from class list.
     *
     * @param event the event
     */
    @FXML
    void ViewClassList(ActionEvent event) { //3-find students by class name
    	ArrayList<String> content = new ArrayList<String>();
    	content.add("Classes");
    	content.add("Students");
    	content.add(ChooseClassN.getSelectionModel().getSelectedItem().toString());
    	last = this;
    	client.Accept(content);
    	content.clear();
    }
    
    
    

    /**
     * Find students by course name
     *
     * @param event the event
     */
    @FXML
    void ViewCourseList(ActionEvent event) { //3-find students by course name
    	ArrayList<String> content = new ArrayList<String>();
    	content.add("Courses");
    	content.add("Students");
    	content.add(ChooseCourseN.getSelectionModel().getSelectedItem().toString());
    	last = this;
    	client.Accept(content);
    	content.clear();
    }
    

    /**
     * Display all teacher appointing.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void DisplayAppointings(ActionEvent event) throws IOException
    {
    	str.add("DisplayAppointings");
    	str.add(LoginController.Username);
    	last = this;
    	client.Accept(str);
    	str.clear();
    	//
    }
   
    
    /**
     * find homework info.
     *
     * @param event the event
     */
    @FXML
    void ViewHomeworkInfo(ActionEvent event)
    {
    	if(HomeworkN.getText().trim().isEmpty())
    		message(AlertType.ERROR,"Please fill Homework Name feild");
    	else{
    		ArrayList<String> list = new ArrayList<String>();
    		list.add("FindHW");
    		list.add(HomeworkN.getText());
    		list.add(LoginController.Username);
    		list.add(ChooseClassN.getSelectionModel().getSelectedItem());
    		list.add(ChooseCourseN.getSelectionModel().getSelectedItem());
    		client.Accept(list);
    		list.clear();
    	}
    }
    
    

    /**
     * Attach file.
     *
     * @param event the event
     */
    @FXML
    void AttachFile(ActionEvent event)
    {
    	
    }

    /**
     * Edit the homework by press on "Edit" button.
     *
     * @param event the event
     */
    @FXML
    void EditHomework(ActionEvent event)
    {
    	ChooseClassN.setVisible(false);
		ChooseCourseN.setVisible(false);
		ClassName.setVisible(true);
		CourseName.setVisible(true);    	
    	HomeworkFile.setDisable(false);
    	Btn_Edit.setVisible(false);
    	Btn_Save.setVisible(true);
		Btn_Cancel.setVisible(true);
    	bytesArray = null;
    	HomeworkFile.clear();
    	HomeworkN.setEditable(false);
    	Btn_AttachHomework.setVisible(true);
    	Btn_View.setVisible(false);
    	PickDeadlineD.setVisible(true);
    	DeadlineDate.setVisible(false);

    	
    }
    

    /**
     * Save homework edit.
     *
     * @param event the event
     */
    @FXML
    void SaveHomeworkEdit(ActionEvent event)
    {
    	if(bytesArray == null || PickDeadlineD.getValue() == null)
    	{
    		message(AlertType.ERROR, "Please fill all the fields");
    	}
    	else
    	{
    	java.util.Date date = 
        	    java.util.Date.from(PickDeadlineD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    	String Ext = getExt(file);
    	ArrayList<Object> content = new ArrayList<Object>();
    	content.add(bytesArray);
    	content.add(HomeworkN.getText());
    	content.add(Ext);
    	content.add(sqlDate);
    	content.add(ClassName.getText());
    	content.add(CourseName.getText());
    	content.add(LoginController.Username);
    	content.add("");
    	last = this;
    	client.Accept(content);
    	content.clear();
    	}
    }

    /**
     * Cancel homework edit.
     *
     * @param event the event
     */
    @FXML
    void CancelHomeworkEdit(ActionEvent event)
    {
    	HomeworkN.setEditable(true);
    	Btn_View.setVisible(true);
		Btn_Save.setVisible(false);
		Btn_AttachHomework.setVisible(false);
		PickDeadlineD.setVisible(false);
    	ChooseClassN.setVisible(false);
    	ChooseCourseN.setVisible(false);
    	DeadlineDate.setVisible(true);
    	ChooseClassN.setVisible(true);
    	ChooseCourseN.setVisible(true);
    	ClassName.setVisible(false);
    	CourseName.setVisible(false);
		HomeworkN.clear();
    	HomeworkFile.clear();
		DeadlineDate.clear();
		ClassName.clear();
		CourseName.clear();
    }
    
    /**
     * View submissions list.
     *
     * @param event the event
     */
    @FXML
    void ViewSubmissionsList(ActionEvent event)
    {

    }
 

    /**
     * Cancel new homework screen.
     *
     * @param event the event
     */
    @FXML
    void CancelNewHomework(ActionEvent event)
    {
    	try {
			screen.Teacher_MainMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
     
    /**
     * show HW Submissions list.
     *
     * @param msg the submissions details
     */
    public void SubmissionsList2(Object msg)  //2-show Teacher_SubmittedHWList screen
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
			try {
				screen.Teacher_SubmittedHWList(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			
		});
    }   
    
    
    /**
     * Course choice from choice box.
     *
     * @param arr the courses names
     */
    public void CourseChoice(ArrayList<String> arr)  //3-insert and choose course names from choice box
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
			CourseCB.getSelectionModel().selectedItemProperty().addListener( 
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> ChooseHW(newValue));				
			arr.remove(0);
			arr.remove(0);
			ArrayList<String> HW = new ArrayList<String>();
			for(int i = 0 ; i<arr.size();i++)
			{
					HW.add(arr.get(i));
			}
			ObservableList<String> list = FXCollections.observableArrayList(HW);
			CourseCB.setItems(list);
			CourseCB.getSelectionModel().clearSelection();
			}
		});
    }
    

    
    /**
     * find HW names by course name.
     *
     * @param Course the course name
     */
    public void ChooseHW(String Course)  //4-get homework names
    {
		StudentList.getItems().clear();
    	str.add("TeacherHWName");
    	str.add(Course);
    	str.add(LoginController.Username);
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * Homework choice from choice box.
     *
     * @param arr the HW names
     */
    public void HomeworkChoice(ArrayList<String> arr) //5-insert and choose homework name from choice box
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
			arr.remove(0);
			ArrayList<String> HW = new ArrayList<String>();
			for(int i = 0 ; i<arr.size();i++)
			{
					HW.add(arr.get(i));
			}
			ObservableList<String> list = FXCollections.observableArrayList(HW);
			HomeworkCB.setItems(list);
			HomeworkCB.getSelectionModel().selectFirst();
			}
		});
    }
    
    
    
    /**
     * Download HW file.
     *
     * @param msg the HW file
     * @param ext the extension of HW file
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
     * Download submitted file.
     *
     * @param msg the submitted file
     * @param ext the extension of submitted file
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
     * Show students list in table.
     *
     * @param msg the students details
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void ShowStudentsList(ArrayList<String> msg) //7-show students in the table
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				SDlist.removeAll(SDlist);
				if(msg.size()>1) TableLabel.setVisible(false);
				ArrayList<String> list2 = (ArrayList<String>) msg;
				for(int i=1;i<list2.size();i=i+4)
				{
					StudentForList SD = new StudentForList(list2.get(i),list2.get(i+1)+" "+list2.get(i+2),list2.get(i+3));
					SDlist.add(SD);
				}
				StudentList.setEditable(true);
				Callback<TableColumn, TableCell> cellFactory =
			             new Callback<TableColumn, TableCell>() {
			                 public TableCell call(TableColumn p) {
			                    return new SDEditingCell(SDlist);
			                 }
			             };
				
			    StudentIDCol.setCellValueFactory(new PropertyValueFactory<StudentForList, String>("StudentIDCol"));				
			    StudentNameCol.setCellValueFactory(new PropertyValueFactory<StudentForList, String>("StudentNameCol"));
			    StudentNameCol.setCellFactory(cellFactory);
			    StudentNameCol.setOnEditCommit(
			            new EventHandler<CellEditEvent<StudentForList, String>>() {
			                @Override
			                public void handle(CellEditEvent<StudentForList, String> t) {
			                    ((StudentForList) t.getTableView().getItems().get(
			                        t.getTablePosition().getRow())
			                        ).getClass();
			                }
			            }
			        );	
			    SubCol.setCellValueFactory(new PropertyValueFactory<StudentForList, String>("SubCol"));	
			    StudentList.setItems(SDlist);
		       
			}
		});
    }
    
    
    /**
     * Update student details screen .
     *
     * @param msg the student details
     */
    public void updateStudent(StudentForList msg)  //8-click on name of student pass to student HW page
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				StudentID2.setText(msg.getStudentIDCol());
				HomeworkName.setText(HWName);
				CourseName.setText(CName);
				str.add("HWInfo2");
				str.add(msg.getStudentIDCol());
				str.add(HWName);
				str.add(CName);
				client.Accept(str);
				str.clear();
			}
	
		});	
    }
    
    
/**
 * Update HW page.
 *
 * @param msg the HW details
 */
public void UpdateHWPage(ArrayList<String> msg)
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date d = new Date();
					String temp = df.format(d);		
					Date d3 = df.parse(temp);
					Date d2 = (Date)df.parse(msg.get(1));
					if(d3.after(d2))
						DeadlineDate.setStyle("-fx-text-inner-color: red");
				
				DeadlineDate.setText(msg.get(1));
				ClassName.setText(msg.get(2));
				if(!(msg.get(3).equals("/")))
				{
					String S = "";
					int i = msg.get(3).indexOf('-');
					if(i>0) S = msg.get(3).substring(i+1);
					SubmissionName.setText(S);
					if(!msg.get(4).equals("-"))
					{
						Date d5 = (Date)df.parse(msg.get(4));
						Date d4 = (Date)df.parse(DeadlineDate.getText());
						if(d5.after(d4))
							SubmissionDate.setStyle("-fx-text-inner-color: red");
					}
					SubmissionDate.setText(msg.get(4));	
					SubmittedFile = msg.get(3);
				}
				else Btn_SubmissionOpen.setDisable(true);
				if(d3.before(d2)){
					Btn_Check.setDisable(true);
				}
				if(msg.size()==6)
				{
					GradeText.setVisible(true);
					Grd.setVisible(true);
					Grd.setEditable(false);
					Grd.setText(msg.get(5));
				}
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}	
			}
		});
	}

/**
 * Show Teacher_NewHW screen
 *
 * @param arr the arr
 */
public void Teacher_NewHW(ArrayList<String> arr) //2-show Teacher_NewHW screen
{
	Platform.runLater(new Runnable()
	{
		@Override
		public void run()
		{
	    	try {
				screen.Teacher_NewHW(arr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	});
}

/**
 * Insert classes and courses names to 2 choice boxes
 *
 * @param arr the courses and classes names
 */
public void updateCourse(ArrayList<String> arr) //3-insert classes and courses names to 2 choice boxes
{
	Platform.runLater(new Runnable()
	{
		@Override
		public void run()
		{
			int flag = 0;
		ArrayList<String> courses = new ArrayList<String>();
		ArrayList<String> classes = new ArrayList<String>();
		for(int i = 0 ; i<arr.size();i++)
		{
			if (arr.get(i).equals("stop"))
				{
					flag++;
					continue;
				}
			if(flag==0)
			{
				courses.add(arr.get(i));
			}
			else
			{
				classes.add(arr.get(i));
			}
		}
		ObservableList<String> list = FXCollections.observableArrayList(courses);
		ChooseCourseN.setItems(list);
		ChooseCourseN.getSelectionModel().selectFirst();
		
    	ObservableList<String> list2 = FXCollections.observableArrayList(classes);
    	ChooseClassN.setItems(list2);
    	ChooseClassN.getSelectionModel().selectFirst();
		}
	});
}



/**
 * Update class CB.
 *
 * @param arr the classes names
 */
public void updateClassCB(ArrayList<String> arr) //3-insert classes names to choice boxe
{
	Platform.runLater(new Runnable()
	{
		@Override
		public void run()
		{
		ArrayList<String> classes = new ArrayList<String>();
		for(int i = 0 ; i<arr.size();i++)
		{
			classes.add(arr.get(i));
		}
		
    	ObservableList<String> list2 = FXCollections.observableArrayList(classes);
    	ChooseClassN.setItems(list2);
    	ChooseClassN.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> GetCourses(newValue));

		}
	});
}

/**
 * Gets the courses names.
 *
 * @param classN the class Name
 */
public void GetCourses(String classN)
{
	ArrayList<String> content = new ArrayList<String>();
	content.add("TCourses");
	content.add(LoginController.Username);
	content.add(classN);
	last = this;
	client.Accept(content);
	content.clear();
}


/**
 * Update course CB.
 *
 * @param arr the courses names
 */
public void updateCourseCB(ArrayList<String> arr) //3-insert courses names to choice boxe
{
	Platform.runLater(new Runnable()
	{
		@Override
		public void run()
		{
		ArrayList<String> courses = new ArrayList<String>();
		for(int i = 0 ; i<arr.size();i++)
		{
			courses.add(arr.get(i));
		}
		
    	ObservableList<String> list2 = FXCollections.observableArrayList(courses);
    	ChooseCourseN.setItems(list2);
    	ChooseCourseN.getSelectionModel().selectFirst();
		}
	});
}

/**
 * Gets the file extension.
 *
 * @param file the file name
 * @return the file extension
 */
public String getExt(File file) //get uploaded file extension
{
	String extension = "";		
	int i = file.toString().lastIndexOf('.');
	if (i > 0) {
	    extension = file.toString().substring(i+1);
	}
	return extension;
}


/**
 * Update student data.
 *
 * @param msg the student info
 */
public void UpdateStudentData(ArrayList<String> msg) //3-show student info
{
	Platform.runLater(new Runnable()
	{
		@Override
		public void run()
		{
			if(msg.size()==1)
			{
				FirstName.clear();
				LastName.clear();
				PhoneNumber.clear();
				Address.clear();
				Birthdate.clear();
				MessageError(msg.get(0));
			}
			else
			{
				FirstName.setText(msg.get(0));
				LastName.setText(msg.get(1));
				PhoneNumber.setText(msg.get(2));
				Address.setText(msg.get(3));
				String bDate = msg.get(4);
    			bDate = bDate.substring(8, 10) + "/" + bDate.substring(5, 7) + "/" + bDate.substring(0, 4);
    			Birthdate.setText(bDate);
			}
		}
	});
}

/**
 * Show Teacher class or course screen.
 *
 * @param arr the classes and courses names
 */
public void Teacher_ClassOrCourse(ArrayList<String> arr) //2-show Teacher_StdInClassOrCourse screen
{
	Platform.runLater(new Runnable()
	{
		@Override
		public void run()
		{
	    	try {
	    		screen.Teacher_StdInClassOrCourse(arr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	});
}

/**
 * show students from class list.
 *
 * @param msg the students details
 */
public void ViewStudentsList2(ArrayList<String> msg)//4-show students by class or course
{
	Platform.runLater(new Runnable()
	{
		@SuppressWarnings({ "unchecked"})
		@Override
		public void run()
		{
			if(msg.size()<=2)
			{
				message(AlertType.ERROR,"No students found");
			}
			else
			{
			ChooseClassN.setVisible(false);
			ChooseCourseN.setVisible(false);
			Btn_ViewInClass.setVisible(false);
			Btn_ViewInCourse.setVisible(false);
			ChooseClassN.setVisible(false);
			ClassLabel.setVisible(false);
			CourseLable.setVisible(false);
			StudentsList.setVisible(true);
			//BackPic.toFront();
			if(msg.get(1).equals("StudentsFromCourse"))
				{
					ClNameCol.setText("Course Name");
					msg.remove(1);
				}
			
			ArrayList<String> list2 = (ArrayList<String>) msg;
			String bDate;
			for(int i=1;i<list2.size();i=i+7)
			{
				bDate = list2.get(i+3);
    			bDate = bDate.substring(8, 10) + "/" + bDate.substring(5, 7) + "/" + bDate.substring(0, 4);
				Student ST = new Student(list2.get(i),list2.get(i+1),list2.get(i+2),bDate,list2.get(i+4),list2.get(i+5),list2.get(i+6));
				list.add(ST);
			}
			StudentsList.setEditable(true);
		    StIDCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("StIDCol"));
		    FirstNameCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("FirstNameCol"));		            
		    LastNameCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("LastNameCol"));				
		    BirthCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("BirthCol"));				
		    PNumCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("PNumCol"));
		    AddrCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("AddrCol"));
		    ClNameCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("ClNameCol"));				
		    StudentsList.setItems(list);			    
			}
		}
		
	});
}


/**
 * Teacher_Display Appointing screen
 *
 * @param msg the details for every teacher Appointing 
 */
public void updateTeacherController(ArrayList<String> msg)
{
	Platform.runLater(new Runnable()
	{
		@Override
		public void run()
		{
	    	try {
				screen.Teacher_DispApt(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	});
}


/**
 * Update Teacher_Display Appointing table
 *
 * @param msg the details for every teacher Appointing 
 */
public void updateTeacherController2(ArrayList<String> msg)
{
	Platform.runLater(new Runnable()
	{
		@SuppressWarnings("unchecked")
		@Override
		public void run()
		{				
			ArrayList<String> list2 = (ArrayList<String>) msg;
			for(int i=1;i<list2.size();i=i+4)
			{
				Appointing AP = new Appointing(list2.get(i),list2.get(i+1),list2.get(i+2),list2.get(i+3));
				APlist.add(AP);
			}
			AppointingList.setEditable(true);
			ClassCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("ClassCol"));
			CourseCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("CourseCol"));		            
			TUCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("TUCol"));				
			WHCol.setCellValueFactory(new PropertyValueFactory<HomeWork, String>("WHCol"));						
			AppointingList.setItems(APlist);		
			
		}
	});
}


/**
 * Sets the HW info to the screen.
 *
 * @param msg the new HW info
 */
public void setHWInfo(ArrayList<String> msg)
{
	Platform.runLater(new Runnable()
	{
		@Override
		public void run()
		{
			if(msg.size()==2)
				{
				Btn_Edit.setVisible(false);
				Btn_Save.setVisible(false);
				Btn_AttachHomework.setVisible(false);
				HomeworkFile.clear();
				DeadlineDate.clear();
				ClassName.clear();
				CourseName.clear();
				message(AlertType.ERROR,msg.get(1));
				}
			else
			{
				HomeworkN.setEditable(false);
				Btn_Edit.setVisible(true);
				HomeworkFile.setDisable(true);
				try {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date d = new Date();
					String temp = df.format(d);		
					Date d3 = df.parse(temp);
					Date d2 = (Date)df.parse(msg.get(2));
					if(d3.after(d2))
						DeadlineDate.setStyle("-fx-text-inner-color: red");
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}	
				DeadlineDate.setText((msg.get(2)));
				ClassName.setVisible(true);
				CourseName.setVisible(true);
				ChooseClassN.setVisible(false);
				ChooseCourseN.setVisible(false);
				ClassName.setText((msg.get(3)));
				CourseName.setText((msg.get(4)));
			}
		}
	});
}


/**
 * Message.
 *
 * @param AT the alert type
 * @param msg the message to display
 */
public void message(AlertType AT, String msg)
{
	Platform.runLater(new Runnable()
	{
		@Override
		public void run()
		{
			Alert alert = new Alert(AT);
			alert.setTitle("");
			alert.setHeaderText(null);
			alert.setContentText(msg);
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK)
			{
				if(msg.equals("The file uploaded successfully") || msg.equals("Submitted file updated successfully") || msg.equals("The file updated successfully"))
				{
					try {
						screen.Teacher_MainMenu();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	});
}

    
    
/**
 * Sets the time.
 */
public void setTime()
    {
    	Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            	Calendar cal = Calendar.getInstance();
            	String time=dateFormat.format(cal.getTime()).toString();
            	Date.setText(time);
            }
        }, 0, 1000);
    }

	
    

}

