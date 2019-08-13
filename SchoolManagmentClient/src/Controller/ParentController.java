package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Boundery.GUIPlayer;
import Entity.HomeWork;
import Entity.StudentCourses;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * The Class ParentController.
 * @author adiel
 */
public class ParentController extends BaseController
{
	/**
	 * Instantiates a new parent controller.
	 */
	public ParentController()
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
	
	
	/** The Button view student data. */
	@FXML // fx:id="Btn_ViewStudentData"
	private MenuItem Btn_ViewStudentData; // Value injected by FXMLLoader
	
	
	/** The Student. */
	@FXML // fx:id="Student"
    protected Text Student; // Value injected by FXMLLoader

	
    /** The Student ID text. */
    @FXML // fx:id="StudentIDText"
    protected Text StudentIDText; // Value injected by FXMLLoader
    
    
    /** The First name text. */
    @FXML // fx:id="FirstNameText"
    protected Text FirstNameText; // Value injected by FXMLLoader
    
    
    /** The Last name text. */
    @FXML // fx:id="LastNameText"
    protected Text LastNameText; // Value injected by FXMLLoader
    
    
    /** The Phone number text. */
    @FXML // fx:id="PhoneNumText"
    protected Text PhoneNumText; // Value injected by FXMLLoader
    
    
    /** The Address text. */
    @FXML // fx:id="AddressText"
    protected Text AddressText; // Value injected by FXMLLoader
    
    
    /** The Birthdate text. */
    @FXML // fx:id="BirthdateText"
    protected Text BirthdateText; // Value injected by FXMLLoader
    
    
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
    
    
    /** The Select student. */
    @FXML // fx:id="SelectStudent"
    private ChoiceBox<String> SelectStudent; // Value injected by FXMLLoader
    
    
    /** The Button view homeworks. */
    @FXML // fx:id="Btn_ViewHomeworks"
    private Button Btn_ViewHomeworks; // Value injected by FXMLLoader
    
    /** The Button view courses. */
    @FXML // fx:id="Btn_ViewCourses"
    private Button Btn_ViewCourses; // Value injected by FXMLLoader 
    
    /** The Button select. */
    @FXML // fx:id="Btn_Select"
    private Button Btn_Select; // Value injected by FXMLLoader
    
    
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
    
    
    /** The Homeworks table. */
    @FXML // fx:id="HomeworksTable"
    private TableView<HomeWork> HomeworksTable; // Value injected by FXMLLoader
    
    /** The Courses list. */
    @FXML // fx:id="CoursesTable"
    private TableView<StudentCourses> CoursesTable; // Value injected by FXMLLoader   
    
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
      
    /** The Course number column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="CourseNumColumn"
    private TableColumn CourseNumColumn; // Value injected by FXMLLoader
    
    /** The Week hours column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="WeekHoursColumn"
    private TableColumn WeekHoursColumn; // Value injected by FXMLLoader
    
    /** The Teacher name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="TeacherNameColumn"
    private TableColumn TeacherNameColumn; // Value injected by FXMLLoader    
    
    /** The Exceptional registration column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ExpRegColumn"
    private TableColumn ExpRegColumn; // Value injected by FXMLLoader  
    
	/** The Student name. */
	private static String StdName;
	
	
	/** The Student ID. */
	private static String StdID;
	
	
	/** The Submitted file. */
	private static String SubmittedFile;
	
	/** The list 2. */
	ArrayList<String> list2 = new ArrayList<String>();

	/** The list. */
	public ObservableList<HomeWork> list = FXCollections.observableArrayList();
	
	/** The list of the courses. */
	ArrayList<String> listcourses = new ArrayList<String>();
	
	/** The list of student courses. */
	public ObservableList<StudentCourses> listcr = FXCollections.observableArrayList();
		
	
	/**
	 * View student data.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void ViewStudentData(ActionEvent event) throws IOException
	{
		str.add("GetSons");
		str.add(Username);
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
    	screen.Parent_MainMenu();
    }
    
    
    /**
     * View Student information.
     *
     * @param event the event
     */
    @FXML
    void ViewStdInfo(ActionEvent event)
    {
    	int i = 1;
    	while(i < parent_student.size())
    	{
    		if(parent_student.get(i).equals(SelectStudent.getSelectionModel().getSelectedItem()))
    		{
    			StdName = SelectStudent.getSelectionModel().getSelectedItem();
    			str.add("ParentShowStdInfo");
    			str.add(parent_student.get(i - 1));
    			break;
    		}
    		i += 3;
    	}
    	if(parent_student.get(i + 1).equals("No"))
    	{
    		last = this;
    		client.Accept(str);
    	}
    	else
    		MessageError("You Can't View " + parent_student.get(i).substring(0, parent_student.get(i).length() - 10) + " Info");
    	str.clear();
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
    	ViewStudentData(event);
    }

    /**
     * View Student homeworks.
     * 
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void ViewStdHomeworks(ActionEvent event) throws IOException
    {
    	StdID = StudentID.getText();
    	str.add("Parent_HWInfo");
		str.add(StdID);
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * Back to student information.
     * 
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void BackStdInfo(ActionEvent event) throws IOException
    {
		str.add("ParentShowStdInfo");
		str.add(StdID);
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
    	StdID = StudentID.getText();
    	str.add("ParentViewStdCoursesList");
    	str.add(StdID);
    	last = this;
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
		ask.add("ParentCheckedFile");
		ask.add(StdID+"-CheckedHW-"+SubmissionName.getText());
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
		ask.add("ParentEvaluationFile");
		ask.add(StdID+"-Evaluation-"+SubmissionName.getText());
		client.Accept(ask);
		ask.clear();
    }
    
    
    /**
     * gets the homework file.
     *
     * @param event the event
     */
    @FXML
	protected void ParentGetHomeworkFile(ActionEvent event)
	{
		last = this;
		ask.add("ParentFile");
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
    protected void ParentGetSubmittedFile(ActionEvent event)
    {
    	last = this;
    	ask.add("ParentSubmittedfile");
    	ask.add(SubmittedFile);
    	client.Accept(ask);
    	ask.clear();
    }
    
    @FXML
    void BackStdHomeworkList(ActionEvent event)
    {
    	str.add("Parent_HWInfo");
		str.add(StdID);
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    /**
     * View student data page.
     *
     * @param obj the Students details
     */
    public void ViewStudentDataPage(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
    		public void run()
    		{
				try {
					screen.Parent_ChooseStudent(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
    		}
		});
    }
    
    
    /**
     * Sets the sons names in the choice box.
     *
     * @param obj the Students details
     */
    public void SetSons(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
    		public void run()
    		{
				int i = 1;
				if(parent_student.size() == 0)
				{
					while(i < obj.size())
					{
						parent_student.add(obj.get(i));
						i++;
					}
				}
				i = 2;
				ArrayList<String> arr = new ArrayList<String>();
				while(i < obj.size())
				{
					arr.add(obj.get(i));
					i += 3;
				}
				ObservableList<String> list;
    			list = FXCollections.observableArrayList(arr);
    			SelectStudent.setItems(list);
    			SelectStudent.getSelectionModel().selectFirst();
    		}
		});
    }
    
    /**
     * Parent show student details.
     *
     * @param obj the Students details
     */
    public void ParentShowStdInfo(ArrayList<String> obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
    		public void run()
    		{
				try
				{
					screen.Parent_ViewStdInfo(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
    		}
		});
    }
    
    
    /**
     * Parent show student details.
     *
     * @param obj the Students details
     */
    public void ParentShowStdInfo2(ArrayList<String> obj)
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
    		}
		});
    }
    
    
    /**
     * View student homework list screen.
     *
     * @param msg the homework details.
     */
    public void ViewStdHomeworks2(Object msg) //2-show Student_ViewHomeworksList screen
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					screen.Parent_ViewStdHWList(msg);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    
    /**
     * Update student Homework table.
     *
     * @param msg the Homework details.
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
							list.add(HW);
						}
					}
				}
				else
				{
					for(int i=1;i<list2.size();i=i+7)
					{
						HomeWork HW = new HomeWork(list2.get(i),list2.get(i+1),list2.get(i+2),list2.get(i+3),list2.get(i+4),list2.get(i+6),list2.get(i+5));
						list.add(HW);
					}
				}
				HomeworksTable.setEditable(true);
				Callback<TableColumn, TableCell> cellFactory =
			             new Callback<TableColumn, TableCell>() {
			                 public TableCell call(TableColumn p) {
			                    return new HWEditingCell(list,"Parent");
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
		        HomeworksTable.setItems(list);  
			}
		});
    }
    
    
    /**
     * Update Homework screen fields.
     *
     * @param homeWork the homework details.
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
				if(Grade.getText().equals("") || Grade.isDisable())
				{
					Btn_EvaluationOpen.setDisable(true);
					Grade.setDisable(true);
					Btn_CheckedOpen.setDisable(true);
				}
				else
				{
					Btn_CheckedOpen.setDisable(false);
				}
				str.add("ParentSubInfo");
				for(int i=0;i<parent_student.size();i++)
				{
					if(StdName.equals(parent_student.get(i)))
					{
						StdID = parent_student.get(i-1);
						break;
					}
				}
				str.add(StdID);
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
     * Update Homework screen fields part 2.
     *
     * @param msg the rest homework details.
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
     * Download Homework file.
     *
     * @param msg a file
     * @param ext a file extension
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
     * @param ext a file extension
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
     * @param msg the details for finding courses
     */
    public void ViewStdCourses2(Object msg) //2-show Student_ViewHomeworksList screen
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.Parent_ViewStdCoursesList(msg);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
    /**
     * Update student courses table.
     *
     * @param msg the details for finding courses
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
}

