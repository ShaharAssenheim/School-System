package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import Entity.Course;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

/**
 * this is the SystemManager Controller, responsible for add new course, edit course.
 * @author shachar & Mohamad
 */
public class SystemManagerController extends BaseController
{
	/**
	 * Constructor, set the name and call to the clock method.
	 */
	public SystemManagerController()
	{
		last = this;
		Platform.runLater(new Runnable(){
			@Override
			public void run()
			{
				UName.setText(Username);
				setTime();
			}
		});	
	}	
	
	
	/** The class number. */
	public static String cNUM;
	
	/** The class name. */
	public static String cNAME;
		
    /** The button new course. */
    @FXML // fx:id="Btn_NewCourse"
    private MenuItem Btn_NewCourse; // Value injected by FXMLLoader

    /** The button view course. */
    @FXML // fx:id="Btn_ViewCourse"
    private MenuItem Btn_ViewCourse; // Value injected by FXMLLoader
    
    /** The Message. */
    @FXML // fx:id="Message"
    private Text Message; // Value injected by FXMLLoader

    /** The View course button. */
    @FXML // fx:id="ViewCourse_Btn"
    private Button ViewCourse_Btn; // Value injected by FXMLLoader

    /** The Course number. */
    @FXML // fx:id="CourseNum"
    private TextField CourseNum; // Value injected by FXMLLoader

    /** The Course weekly hours. */
    @FXML // fx:id="CourseH"
    private TextField CourseH; // Value injected by FXMLLoader

    /** The Course pre-courses. */
    @FXML // fx:id="CoursePreC"
    private TextField CoursePreC; // Value injected by FXMLLoader
    
    /** The Pre-courses. */
    @FXML // fx:id="PreCourses"
    private TextField PreCourses; // Value injected by FXMLLoader

    /** The Pre-courses. */
    @FXML // fx:id="PreC"
    private TextField PreC; // Value injected by FXMLLoader

    /** The Weekly Hours. */
    @FXML // fx:id="WeekH"
    private TextField WeekH; // Value injected by FXMLLoader

    /** The Teaching unit. */
    @FXML // fx:id="TeachingUnit"
    private TextField TeachingUnit; // Value injected by FXMLLoader

    /** The button clear. */
    @FXML // fx:id="Btn_Clear"
    private Button Btn_Clear; // Value injected by FXMLLoader

    /** The Weekly hours. */
    @FXML // fx:id="WeekHours"
    private TextField WeekHours; // Value injected by FXMLLoader
    
    /** The flag. */
	private int flag=0;
	
	/** The arr. */
	private ArrayList<String> arr = new ArrayList<String>();	
	
	/** The Old teaching Unit. */
	private String OldTeachU;


/**
 * Handler when press add new course, get the teaching units from server
 * @param event Gets the ActionEvent when the function called.
 * @throws InterruptedException thread exception
 */
    @FXML
    void NewCourse(ActionEvent event) throws InterruptedException
    {
    	str.add("Teachunit");
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
    
/**
 * Handler when press view course, open the screen in GuiPLayer class
 * @param event Gets the ActionEvent when the function called.
 * @throws IOException thread exception
 */
    @FXML
    void ViewCourse(ActionEvent event) throws IOException
    {
    	screen.SysMng_ViewCourse();
    }
/**
 * Handler when press main screen, open the screen in GuiPLayer class
 * @param event Gets the ActionEvent when the function called.
 * @throws IOException thread exception.
 */
    @FXML
    void ShowMainScreen(ActionEvent event) throws IOException
    {
    	screen.SysMng_MainMenu();
    }
    
/**
 * Handler when press cancel, call to ShowMainScreen method.
 * @param event Gets the ActionEvent when the function called.
 * @throws IOException thread exception.
 */
    @FXML
    void CancelNewCourse(ActionEvent event) throws IOException 
    {
    	ShowMainScreen(event);
    }
/**
 * Handler when press save, send to server the course details.
 * @param event Gets the ActionEvent when the function called.
 * @throws InterruptedException thread exception.
 */
    @FXML
    void AddNewCourse(ActionEvent event) throws InterruptedException
    {
    	
    	int flag2=0;
    	try { 
            Integer.parseInt(CourseNum.getText()); 
        } catch(NumberFormatException e) { 
            flag2=1;
            MessageInfo("Course Number Must Be an Integer!!!");
            CourseNum.clear();
        } catch(NullPointerException e) {
        	flag2=1;
        	MessageInfo("Course Number Must Be an Integer!!!");
        	CourseNum.clear();
        }
       
    	if(CourseNum.getText().length()==5&&!(CourseN.getText().isEmpty())&&flag2==0)
    	{
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION");
			alert.setHeaderText(null);
			alert.setContentText("Are You Sure?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				str.add("AddCourse");
				str.add(CourseNum.getText());
				str.add(CourseN.getText());
				str.add(ChooseTeachingU.getSelectionModel().getSelectedItem());
				str.add(CourseH.getText());
				if(CoursePreC.getText().isEmpty())
					str.add("No-Pre");
				else
				str.add(CoursePreC.getText());
				last = this;
				client.Accept(str);
				Thread.sleep(250);
				str.clear();
				CourseNum.clear();
				CourseN.clear();
				CourseH.clear();
				CoursePreC.clear();
			}
    	}
    	else{
    		if(!(CourseNum.getText().length()==5)&&flag2==0)
    		{
    			MessageInfo("Course Number Must Be 5 Digits!!!");
    			CourseNum.clear();
    		}
    		else if(CourseN.getText().isEmpty())
    		{
    			MessageInfo("Course Name Is Empty!!!");
    		}
    	}
    }
    
/**
 * Handler when press search, send to server name or course number. 
 * @param event Gets the ActionEvent when the function called.
 * @throws InterruptedException thread exception.
 */
    @FXML
    void ViewCourseInfo(ActionEvent event) throws InterruptedException
    {
    	str.add("ViewCourse1");
    	if(!CourseNum.getText().isEmpty())
    	{
    		str.add("Num");
    		str.add(CourseNum.getText());
    	}
    	if(!CourseN.getText().isEmpty())
    	{
    		str.add("Name");
    		str.add(CourseN.getText());
    	}
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
    
/**
 * Handler when press edit, get from server the teaching units.
 * @param event Gets the ActionEvent when the function called.
 * @throws InterruptedException thread exception.
 */
    @FXML
    void EditCourse(ActionEvent event) throws InterruptedException
    {
    	CourseN.setEditable(true);
    	CourseNum.setEditable(true);
    	str.clear();
    	Btn_Clear.setVisible(false);
    	Btn_Edit.setVisible(false);
    	Btn_View.setVisible(false);
    	Btn_Save.setVisible(true);
    	Btn_Cancel.setVisible(true);
    	TeachingUnit.setVisible(false);
    	WeekHours.setVisible(false);
    	PreCourses.setVisible(false);
    	ChooseTeachingU.setVisible(true);
    	WeekH.setVisible(true);
    	PreC.setVisible(true);
    	WeekH.setText(WeekHours.getText());
    	PreC.setText(PreCourses.getText());
    	OldTeachU = TeachingUnit.getText();
    	cNUM = CourseNum.getText();
    	cNAME=CourseN.getText();
    	
    	if(flag == 0)
    	{
    		str.add("TeachUnitEdit");
    		last = this;
    		client.Accept(str);
    		Thread.sleep(250);
    		str.clear();
    		flag=1;
    	}
    }
    
/**
 * Handler when press save in edit screen, send to server the course details.
 * @param event Gets the ActionEvent when the function called.
 * @throws InterruptedException thread exception.
 */
    @FXML
    void SaveEditCourse(ActionEvent event) throws InterruptedException
    {
    	Btn_Edit.setVisible(false);
    	Btn_View.setVisible(true);
    	Btn_Save.setVisible(false);
    	Btn_Cancel.setVisible(false);
    	ChooseTeachingU.setVisible(false);
    	WeekH.setVisible(false);
    	PreC.setVisible(false);
    	TeachingUnit.setVisible(true);
    	WeekHours.setVisible(true);
    	PreCourses.setVisible(true);
    	Btn_Clear.setVisible(true);
    	int flag3=0;
    	try { 
            Integer.parseInt(CourseNum.getText()); 
        } catch(NumberFormatException e) { 
            flag3=1;
            MessageInfo("Course Number Must Be an Integer!!!");
            CourseNum.clear();
        } catch(NullPointerException e) {
        	MessageInfo("Course Number Must Be an Integer!!!");
        	CourseNum.clear();
        }
    	if(CourseNum.getText().length()==5&&flag3==0)
    	{
    		str.add("UpdateCourse");
    		str.add(cNUM);
    		str.add(CourseNum.getText());
    		str.add(cNAME);
    		str.add(CourseN.getText());
    		str.add(ChooseTeachingU.getSelectionModel().getSelectedItem());
    		str.add(WeekH.getText());
    		if(PreC.getText().isEmpty())
				str.add("No-Pre");
    		str.add(PreC.getText());
    		last = this;
    		client.Accept(str);
    		Thread.sleep(250);
    		str.clear();
    		TeachingUnit.setText(ChooseTeachingU.getSelectionModel().getSelectedItem());
    		WeekHours.setText(WeekH.getText());
    		PreCourses.setText(PreC.getText());
    	}
    	else if(!(CourseNum.getText().length()==5)&&flag3==0){
    		MessageInfo("Course Number Must Be 5 Digits");
    		CourseNum.clear();
    	}
    }
    
/**
 * Handler when press cancel in edit course.
 * @param event Gets the ActionEvent when the function called.
 */
    @FXML
    void CancelEditCourse(ActionEvent event)
    {
    	Btn_Clear.setVisible(true);
    	Btn_Edit.setVisible(true);
    	Btn_View.setVisible(true);
    	Btn_Save.setVisible(false);
    	Btn_Cancel.setVisible(false);
    	ChooseTeachingU.setVisible(false);
    	WeekH.setVisible(false);
    	PreC.setVisible(false);
    	TeachingUnit.setVisible(true);
    	WeekHours.setVisible(true);
    	PreCourses.setVisible(true);
    }
    
/**
 * Handler when press clear in edit course, clear the screen.
 * @param event Gets the ActionEvent when the function called.
 */
    @FXML
    void ClearView(ActionEvent event)
    {
    	CourseNum.clear();
    	CourseN.clear();
    	TeachingUnit.clear();
    	WeekHours.clear();
    	PreCourses.clear();
    	Btn_Edit.setVisible(false);
    }
    
 /**
 * get object of teaching units, set the teaching units in choose box
 * @param obj teaching units.
 */
    public void TeachU(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run()
			{
				int i=1;
				while(((ArrayList<String>) obj).size()>i)
				{
					arr.add(((ArrayList<String>) obj).get(i)+" ("+((ArrayList<String>) obj).get(i+1)+")");
					i=i+2;
				}
				ObservableList<String> list ;
				list = FXCollections.observableArrayList(arr);
				ChooseTeachingU.setItems(list);
				if(((ArrayList<String>) obj).get(0).equals("Teachunit"))
					ChooseTeachingU.getSelectionModel().selectFirst();
				else
					ChooseTeachingU.getSelectionModel().select(OldTeachU);
			}
		});
    }
    
 /**
  * open the new course screen from GuiPlayer class   
  * @param obj teaching units.
  */
    public void setTunit(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					screen.SysMng_NewCourse(obj);
				}catch(IOException e){
					e.printStackTrace();
					}
			}
		});
    }
    
/**
 * set the course details in the choose boxes
 * @param obj course details
 */
    public void ViewCourseInfo(Object obj)
    {
    	Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run()
			{
				if(((ArrayList<String>) obj).size()>2)
				{
					Course course= new Course(((ArrayList<String>) obj).get(1),((ArrayList<String>) obj).get(2),
							((ArrayList<String>) obj).get(4),((ArrayList<String>) obj).get(3),
							((ArrayList<String>) obj).get(5));
					CourseNum.setText(course.getCourseNumber());
					CourseN.setText(course.getCourseName());
					TeachingUnit.setText(course.getTeachingUnit());
					WeekHours.setText(course.getWeekHours());
					PreCourses.setText(course.getPrecourses());
					Btn_Edit.setVisible(true);
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
}