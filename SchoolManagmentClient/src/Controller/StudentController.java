package Controller;

import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import Boundery.GUIPlayer;
import Entity.HomeWork;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

/**
 * This is the Student Controller, responsible for submit HW, edit submission and student data
 * @author Eliran Abregel
 */
public class StudentController extends BaseController
{
	
	/**
	 * Instantiates a new student controller.
	 */
	// Constructor
	public StudentController()
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
	
	/** The Grade. */
    @FXML
    private TextField Grade;

    /** The View submission name. */
    @FXML
    private TextField ViewSubmissionName;

    /** The Submission date. */
    @FXML
    private TextField SubmissionDate;
    
    /** The Btn evaluation open. */
    @FXML
    private Button Btn_EvaluationOpen;

    /** The Btn checked open. */
    @FXML
    private Button Btn_CheckedOpen;
    
    /** The View homework list btn. */
    @FXML
    private Button ViewHomeworkList_Btn;

    /** The Homework open btn. */
    @FXML
    private Button HomeworkOpen_Btn;

    /** The Submission open btn. */
    @FXML
    private Button SubmissionOpen_Btn;

    /** The View HW name. */
    @FXML
    private TextField ViewHWName;

    /** The Homework file. */
    @FXML
    private TextField HomeworkFile;

    /** The Evaluation file. */
    @FXML
    private TextField EvaluationFile;

    /** The Submission file. */
    @FXML
    private TextField SubmissionFile;
    
    /** The Search by HW name. */
    @FXML // fx:id="SearchByHWName"
    private TextField SearchByHWName; // Value injected by FXMLLoader

	/** The Submitted file Name. */
	@FXML // fx:id="SubmitN"
    private TextField SubmitN; // Value injected by FXMLLoader

    /** The button attach submission. */
    @FXML // fx:id="Btn_AttachSubmission"
    private Button Btn_AttachSubmission; // Value injected by FXMLLoader

    /** The Choose homework ChoiceBox. */
    @FXML // fx:id="ChooseHomework"
    private ChoiceBox<String> ChooseHomework; // Value injected by FXMLLoader
    
    /** The Choose course ChoiceBox. */
    @FXML // fx:id="ChooseCourse"
    private ChoiceBox<String> ChooseCourse;

    /** The View homework button. */
    @FXML // fx:id="ViewHomework_Btn"
    private Button ViewHomework_Btn; // Value injected by FXMLLoader

    /** The button save. */
    @FXML // fx:id="Btn_Save"
    private Button Btn_Save; // Value injected by FXMLLoader

    /** The button cancel. */
    @FXML // fx:id="Btn_Cancel"
    private Button Btn_Cancel; // Value injected by FXMLLoader
    
    /** The Submit file. */
    @FXML // fx:id="SubmitFile"
    private TextField SubmitFile;
	
	/** The Homework table. */
	@FXML // fx:id="HomeworksTable"
    private TableView<HomeWork> HomeworksTable; // Value injected by FXMLLoader
		
	/** The Homework ID column. */
	// Student Controller Add-ons and Methods
    @SuppressWarnings("rawtypes")
	@FXML
    private TableColumn HomeworkIDColumn;
    
	/** The Grade column. */
	@SuppressWarnings("rawtypes")
	@FXML // fx:id="GradeColumn"
    private TableColumn GradeColumn; // Value injected by FXMLLoader
	
	/** The Checked column. */
	@SuppressWarnings("rawtypes")
	@FXML // fx:id="CheckedColumn"
	private TableColumn CheckedColumn; // Value injected by FXMLLoader
	
	/** The Homework name column. */
	@SuppressWarnings("rawtypes")
	@FXML // fx:id="HomeworkNameColumn"
	private TableColumn HomeworkNameColumn; // Value injected by FXMLLoader
    
    /** The Class name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="ClassNameColumn"
	private TableColumn ClassNameColumn; // Value injected by FXMLLoader
    
    /** The Course name column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="CourseNameColumn"
	private TableColumn CourseNameColumn; // Value injected by FXMLLoader

    /** The Deadline date column. */
    @SuppressWarnings("rawtypes")
	@FXML // fx:id="DeadlineDateColumn"
	private TableColumn DeadlineDateColumn; // Value injected by FXMLLoader
    
    /** The button submit homework. */
    @FXML // fx:id="Btn_SubmitHomework"
    private MenuItem Btn_SubmitHomework; // Value injected by FXMLLoader
    
    /** The button home. */
    @FXML // fx:id="Btn_Home"
    private Button Btn_Home; // Value injected by FXMLLoader

    /** The User name. */
    @FXML // fx:id="UName"
    private Text UName; // Value injected by FXMLLoader

    /** The button logout. */
    @FXML // fx:id="Btn_Logout"
    private Button Btn_Logout; // Value injected by FXMLLoader

    /** The button user info. */
    @FXML // fx:id="Btn_UserInfo"
    private MenuItem Btn_UserInfo; // Value injected by FXMLLoader

    /** The Time. */
    @FXML // fx:id="Time"
    private Text Time; // Value injected by FXMLLoader

    /** The Date. */
    @FXML // fx:id="Date"
    private Text Date; // Value injected by FXMLLoader

    /** The button view homework. */
    @FXML // fx:id="Btn_ViewHomework"
    private MenuItem Btn_ViewHomework; // Value injected by FXMLLoader
    
    /** The button view homework list. */
    @FXML // fx:id="Btn_ViewHomework1"
    private MenuItem Btn_ViewHomeworkList; // Value injected by FXMLLoader
    
    /** The HW name. */
    @FXML 
    private TextField HWName; 
    
    /** The Deadline date. */
    @FXML // fx:id="DeadlineDate"
    private TextField DeadlineDate; // Value injected by FXMLLoader
    
    /** The Class name. */
    @FXML // fx:id="ClassName"
    private TextField ClassName; // Value injected by FXMLLoader
    
    /** The Course name. */
    @FXML // fx:id="CourseName"
    private TextField CourseName; // Value injected by FXMLLoader
    
    /** The list 2 ArrayList. */
    private ArrayList<String> list2 = new ArrayList<String>();
    
	/** The list ArrayList. */
	private ObservableList<HomeWork> list = FXCollections.observableArrayList();
	
	/** The ask ArrayList. */
	private ArrayList<String> ask = new ArrayList<String>();
	
    /** The bytes array. */
    private byte[] bytesArray;
    
    /** The Uploaded file. */
    private File Uploadedfile;
	
    /**
     * Show main screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void ShowMainScreen(ActionEvent event) throws IOException
    {
    	screen.Student_MainMenu();
    }
     
    
	/**
	 * Get homework names for homework list.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
    void ViewHomeworkList(ActionEvent event) throws IOException //1-get homework names
    {
		str.add("Student_HWInfo");
		str.add(LoginController.Username);
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
	
	/**
 	 * Find HW from table by HW name.
 	 *
 	 * @param event the event
 	 */
 	@FXML
	 void FindByHWName(ActionEvent event)
	 {
		 list.removeAll(list);
		 StudentController t = (StudentController)StudentController.last;
		 t.UpdateHWTable(list2,SearchByHWName.getText().trim()); 
	 }
	 
	 /**
 	 * Clear HW search in table.
 	 *
 	 * @param event the event
 	 */
 	@FXML
	 void ClearSearch(ActionEvent event)
	 {
		 list.removeAll(list);
		 SearchByHWName.clear();
		 StudentController t = (StudentController)StudentController.last;
		 t.UpdateHWTable(list2,"");
	 }
 	
 	
 	/**
     * Get courses names for Submit homework.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void SubmitHomework(ActionEvent event) throws IOException  //1-get courses names
    {
    	str.add("CourseName");
    	str.add("1");
    	str.add(LoginController.Username);
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    
    /**
     * Attach file.
     *
     * @param event the event
     */
    @SuppressWarnings("resource")
	@FXML
    void AttachFile(ActionEvent event)  //7-convert the file to byte array
    {
    	FileChooser fc = new FileChooser();
        //Stage stage = new Stage();
        fc.setTitle("Get Text");
        fc.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));    
        Uploadedfile = fc.showOpenDialog(GUIPlayer.PrimaryStage);
        if (Uploadedfile != null)
        {	
            try {
            	String path = Uploadedfile.getPath();
            	SubmitFile.setText(path);
                bytesArray = new byte[(int) Uploadedfile.length()];
                FileInputStream fis = new FileInputStream(Uploadedfile);
                fis.read(bytesArray); //read file into bytes[]
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
    
    /**
     * Upload submitted HW
     *
     * @param event the event
     */
    @FXML
    void SubmitHomeworkBtn(ActionEvent event) { //8-upload the file
    	if((SubmitN.getText().trim().isEmpty() || bytesArray == null || ChooseCourse.getSelectionModel().isEmpty() || ChooseHomework.getSelectionModel().isEmpty()) && ChooseHomework.isVisible())// || DeadlineDate.getValue() == null)
    	{
    		message(AlertType.ERROR, "Please fill all the fields");
    	}
    	else if(bytesArray == null)// || DeadlineDate.getValue() == null)
    	{
    		message(AlertType.ERROR, "Please fill all the fields");
    	}
    	else
    	{
    	String Ext = getExt();
    	ArrayList<Object> content = new ArrayList<Object>();
    	content.add("studentSub");
    	content.add(bytesArray);
    	if(!(ChooseHomework.isVisible()))
    	{
    		content.add(CourseName.getText());
        	content.add(HWName.getText());
    	}else
    	{
	    	content.add(ChooseCourse.getSelectionModel().getSelectedItem().toString());
	    	content.add(ChooseHomework.getSelectionModel().getSelectedItem().toString());
    	}
    	content.add(SubmitN.getText());
    	content.add(SubmitFile.getText());
    	content.add(Ext);    	
    	content.add(LoginController.Username);
    	if(!(SubmitN.isEditable()))
    		content.add("");
    	client.Accept(content);
    	content.clear();
    	}
    }
    
    
    /**
     * Cancel submission.
     *
     * @param event the event
     */
        @FXML
        void CancelSubmission(ActionEvent event)
        {
        	try {
    			screen.Student_MainMenu();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }

        

        /**
         * Gets the checked file.
         *
         * @param event the event
         */
        @FXML
        void GetCheckedFile(ActionEvent event) {
        	last = this;
    		ask.add("CheckedFile");
    		ask.add(LoginController.Username+"-CheckedHW-"+SubmissionName.getText());
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
    		ask.add("EvaluationFile");
    		ask.add(LoginController.Username+"-Evaluation-"+SubmissionName.getText());
    		client.Accept(ask);
    		ask.clear();
        }
        
        /**
         * View homework.
         *
         * @param event the event
         * @throws IOException Signals that an I/O exception has occurred.
         */
        @FXML
        void ViewHomework(ActionEvent event) throws IOException
        {

        }
        
        /**
         * Find submission for edit.
         *
         * @param event the event
         */
        @FXML
        void EditSubmission(ActionEvent event)
        {
        	ArrayList<String> arr = new ArrayList<String>();
        	arr.add("@@@");
        	arr.add(CourseName.getText());
        	arr.add(ViewHWName.getText());
        	arr.add(SubmissionName.getText());
        	arr.add(DeadlineDate.getText());
        	try {
    			screen.Student_SubmitHomework(arr);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }
        
        @FXML
        void BackHomeworkList(ActionEvent event) throws IOException
        {
        	ViewHomeworkList(event);
        }
 	
	
	/**
	 * Show Student_ViewHomeworksList screen
	 *
	 * @param msg the homework details
	 */
	public void ViewHomeworkList2(Object msg) //2-show Student_ViewHomeworksList screen
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
			try {
				screen.Student_ViewHomeworksList(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			
		});
    }
	

	
    
	 /**
 	 * Update and show HW table.
 	 *
 	 * @param msg the the homework details
 	 * @param HWName the HW name for search
 	 */
 	public void UpdateHWTable(Object msg, String HWName)  //3-insert homework names to the table
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
				                    return new HWEditingCell(list,"Student");
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
     * Update HW details screen.
     *
     * @param homeWork the homework details
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
						Btn_Edit.setVisible(false);
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
						Btn_Edit.setVisible(false);
						Btn_CheckedOpen.setDisable(false);
					}
				
				str.add("SubInfo");
				str.add(LoginController.Username);
				str.add(homeWork.getHomeworkNameColumn());
				str.add(homeWork.getCourseNameColumn());
		    	//last = this;
		    	client.Accept(str);
		    	str.clear();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}	
			}
	
		});	
    }
    
    /**
     * Update HW details screen continue
     *
     * @param msg the homework details
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
					BaseController.Subfile = msg.get(1);
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
     * Download HW.
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
     * Download sub file.
     *
     * @param msg the sub file
     * @param ext the extension of sub file
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
 	 * View homework list.
 	 */
 	void ViewHomeworkList3() //show HW list again
	    {
			str.add("HWInfo");
			str.add(LoginController.Username);
	    	last = this;
	    	client.Accept(str);
	    	str.clear();
	    }
    
   

    
    
    
    
    /**
     * Show Student_SubmitHomework screen
     *
     * @param msg the courses and classes names
     */
    public void SubmitHomework2(Object msg) //2-show Student_SubmitHomework screen
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
			try {
				screen.Student_SubmitHomework(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			
		});
    }
    
    
    
    /**
     * Insert courses names to choice box and choose course and choose course
     *
     * @param arr the courses names
     */
    public void CourseChoice(ArrayList<String> arr)  //3-insert courses names to choice box and choose course
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
			ChooseCourse.getSelectionModel().selectedItemProperty().addListener( 
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> SubmitHomework3(newValue) );				
			arr.remove(0);
			arr.remove(0);
			ArrayList<String> HW = new ArrayList<String>();
			for(int i = 0 ; i<arr.size();i++)
			{
					HW.add(arr.get(i));
			}
			ObservableList<String> list = FXCollections.observableArrayList(HW);
			ChooseCourse.setItems(list);
			ChooseCourse.getSelectionModel().clearSelection();
			}
		});
    }
    
    /**
     * Get HW names by Course name
     *
     * @param Course the course name
     */
    void SubmitHomework3(String Course)  //4-get HW names by Course name
    {
    	DeadlineDate.clear();
    	str.add("HWName");
    	str.add(Course);
    	str.add(LoginController.Username);
    	last = this;
    	client.Accept(str);
    	str.clear();
    }
    
    
    
    /**
     * Insert HW names to choice box and choose HW and coose HW
     *
     * @param arr the HW names
     */
    public void HomeworkChoice(ArrayList<String> arr) //5-insert HW names to choice box and choose HW
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
			arr.remove(0);
			ChooseHomework.getSelectionModel().selectedItemProperty().addListener( 
						(ObservableValue<? extends String> observable, String oldValue, String newValue) -> ShowDLD(arr,newValue) );
			ArrayList<String> HW = new ArrayList<String>();
			for(int i = 0 ; i<arr.size();i=i+2)
			{
					HW.add(arr.get(i));
			}
			ObservableList<String> list = FXCollections.observableArrayList(HW);
			ChooseHomework.setItems(list);
			ChooseHomework.getSelectionModel().selectFirst();
			}
		});
    }
    
    
    /**
     * Show deadline date in HW details screen.
     *
     * @param arr the deadlines of all HWS
     * @param newValue the HW name
     */
    public void ShowDLD(ArrayList<String> arr, String newValue) {  //6-show deadline date of the selected HW
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		String temp = df.format(d);			
    	for(int i=0;i<arr.size();i=i+2)
    	{
    		if(arr.get(i).equals(newValue))
    		{    			
				try {
					Date d3 = df.parse(temp);
					Date d2 = (Date)df.parse(arr.get(i+1));
					if(d3.after(d2))
						DeadlineDate.setStyle("-fx-text-inner-color: red");
					else
						DeadlineDate.setStyle("-fx-text-inner-color: black");
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}				
    			DeadlineDate.setText(arr.get(i+1));
    			break;
    		}	
    	}
	}
    

   
    /**
     * Gets the extension of file.
     *
     * @return the extension of the file
     */
    public String getExt() //get extension of uploaded file
    {
    	String extension = "";		
		int i = Uploadedfile.toString().lastIndexOf('.');
		if (i > 0) {
		    extension = Uploadedfile.toString().substring(i+1);
		}
		return extension;
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
					if(msg.equals("The file uploaded successfully"))
					{
						try {
							screen.Student_MainMenu();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
    }
    
    /**
     * Edit the submission.
     *
     * @param msg the submission details
     */
    public void CourseChoice2(ArrayList<String> msg)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				ChooseCourse.setVisible(false);
				ChooseHomework.setVisible(false);
				CourseName.setVisible(true);
				HWName.setVisible(true);
				CourseName.setText(msg.get(1));
				HWName.setText(msg.get(2));
				SubmitN.setText(msg.get(3));
				try {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date d = new Date();
					String temp = df.format(d);		
					Date d3 = df.parse(temp);
					Date d2 = (Date)df.parse(msg.get(4));
					if(d3.after(d2))
						DeadlineDate.setStyle("-fx-text-inner-color: red");
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}		
				DeadlineDate.setText(msg.get(4));
				if(!(msg.get(3).equals(""))) SubmitN.setEditable(false);
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
        

