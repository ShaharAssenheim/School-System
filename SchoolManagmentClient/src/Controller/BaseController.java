package Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

/**
 * The Class BaseController is for shared Methods for all users.
 */
public class BaseController extends LoginController
{
	
	/** The Address. */
	@FXML // fx:id="Address"
    protected TextField Address; // Value injected by FXMLLoader

    /** The First name. */
    @FXML // fx:id="FirstName"
    protected TextField FirstName; // Value injected by FXMLLoader
    
    /** The Birth date. */
    @FXML // fx:id="Birthdate"
    protected TextField Birthdate; // Value injected by FXMLLoader

    /** The Max hours. */
    @FXML // fx:id="MaxHours"
    protected TextField MaxHours; // Value injected by FXMLLoader
    
    /** The Button change password. */
    @FXML // fx:id="Btn_ChngPass"
    protected Button Btn_ChngPass; // Value injected by FXMLLoader
    
    /** The Date. */
    @FXML // fx:id="Date"
    protected Text Date; // Value injected by FXMLLoader
    
    /** The Button home. */
    @FXML // fx:id="Btn_Home"
    protected Button Btn_Home; // Value injected by FXMLLoader

    /** The User name. */
    @FXML // fx:id="UName"
    protected Text UName; // Value injected by FXMLLoader
    
    /** The Button logout. */
    @FXML // fx:id="Btn_Logout"
    protected Button Btn_Logout; // Value injected by FXMLLoader
    
    /** The Student ID. */
    @FXML // fx:id="StdID"
    protected TextField StdID; // Value injected by FXMLLoader
    
    /** The Student ID. */
    @FXML // fx:id="StudentID"
    protected TextField StudentID; // Value injected by FXMLLoader

    /** The Role name. */
    @FXML // fx:id="RoleName"
    protected TextField RoleName; // Value injected by FXMLLoader

    /** The User ID. */
    @FXML // fx:id="UserID"
    protected TextField UserID; // Value injected by FXMLLoader

    /** The Phone number. */
    @FXML // fx:id="PhoneNumber"
    protected TextField PhoneNumber; // Value injected by FXMLLoader
    
    /** The Last name. */
    @FXML // fx:id="LastName"
    protected TextField LastName; // Value injected by FXMLLoader
    
    /** The Password. */
    @FXML // fx:id="Password"
    protected TextField Password; // Value injected by FXMLLoader
    
    /** The Button user info. */
    @FXML // fx:id="Btn_UserInfo"
    protected MenuItem Btn_UserInfo; // Value injected by FXMLLoader

    /** The Button attach homework. */
    @FXML // fx:id="Btn_AttachHomework"
    protected Button Btn_AttachHomework; // Value injected by FXMLLoader
    
    /** The Class name. */
    @FXML // fx:id="ClassName"
    protected TextField ClassName; // Value injected by FXMLLoader
    
    /** The Class number. */
    @FXML // fx:id="ClassNumber"
    protected TextField ClassNumber; // Value injected by FXMLLoader
    
    /** The Course name. */
    @FXML // fx:id="CourseName"
    protected TextField CourseName; // Value injected by FXMLLoader
    
    /** The Button save. */
    @FXML // fx:id="Btn_Save"
    protected Button Btn_Save; // Value injected by FXMLLoader
    
    /** The Button cancel. */
    @FXML // fx:id="Btn_Cancel"
    protected Button Btn_Cancel; // Value injected by FXMLLoader
    
    /** The Button view. */
    @FXML // fx:id="Btn_View"
    protected Button Btn_View; // Value injected by FXMLLoader
    
    /** The Button edit. */
    @FXML // fx:id="Btn_Edit"
    protected Button Btn_Edit; // Value injected by FXMLLoader
    
    /** The Submission date. */
    @FXML // fx:id="SubmissionDate"
    protected TextField SubmissionDate; // Value injected by FXMLLoader

    /** The Deadline date. */
    @FXML // fx:id="DeadlineDate"
    protected TextField DeadlineDate; // Value injected by FXMLLoader
    
    /** The Submission name. */
    @FXML // fx:id="SubmissionName"
    protected TextField SubmissionName; // Value injected by FXMLLoader
    
    /** The Homework name. */
    @FXML // fx:id="HomeworkName"
    protected TextField HomeworkName; // Value injected by FXMLLoader
    
    /** The Homework file. */
    @FXML // fx:id="HomeworkFile"
    protected TextField HomeworkFile; // Value injected by FXMLLoader
    
    /** The Submission file. */
    @FXML // fx:id="SubmissionFile"
    protected TextField SubmissionFile; // Value injected by FXMLLoader
    
    /** The Button submission open. */
    @FXML // fx:id="Btn_SubmissionOpen"
    protected Button Btn_SubmissionOpen; // Value injected by FXMLLoader
    
    /** The Button homework open. */
    @FXML // fx:id="Btn_HomeworkOpen"
    protected Button Btn_HomeworkOpen; // Value injected by FXMLLoader
    
    /** The Button search. */
    @FXML // fx:id="Btn_Search"
    protected Button Btn_Search; // Value injected by FXMLLoader
    
    /** The Class Name. */
    @FXML // fx:id="ClassN"
    protected TextField ClassN; // Value injected by FXMLLoader

    /** The Teacher ID. */
    @FXML // fx:id="TeaID"
    protected TextField TeaID; // Value injected by FXMLLoader

    /** The teaching Unit ChoiceBox. */
    @FXML // fx:id="ChooseTeachingU"
    protected ChoiceBox<String> ChooseTeachingU; // Value injected by FXMLLoader

    /** The Status. */
    @FXML // fx:id="Status"
    protected TextField Status; // Value injected by FXMLLoader
    
    /** The Type. */
    @FXML // fx:id="Type"
    protected TextField Type; // Value injected by FXMLLoader

    /** The type ChoiceBox. */
    @FXML // fx:id="ChooseType"
    protected ChoiceBox<String> ChooseType; // Value injected by FXMLLoader

    /** The Course Name. */
    @FXML // fx:id="CourseN"
    protected TextField CourseN; // Value injected by FXMLLoader
    
    /** The Choose class Name. */
    @FXML // fx:id="ChooseClassN"
    protected ChoiceBox<String> ChooseClassN; // Value injected by FXMLLoader
       
    /** The button change password. */
    @FXML // fx:id="Btn_ChangePass"
    protected Button Btn_ChangePass;
    
    /** The button back. */
    @FXML // fx:id="Btn_Back"
    protected Button Btn_Back; // Value injected by FXMLLoader
    
    /** The ask. */
    protected static ArrayList<String> ask = new ArrayList<String>();
    
    /** The parent student. */
    protected static ArrayList<String> parent_student = new ArrayList<String>();
    
    /** The file name. */
    protected static String file;
    
    /** The Submitted file name. */
    protected static String Subfile;
    
    /** The Current password. */
    @FXML // fx:id="PasswordInput"
    private PasswordField CurPass; // Value injected by FXMLLoader
    
    /** The New password. */
    @FXML // fx:id="PasswordInput"
    private PasswordField NewPass; // Value injected by FXMLLoader
    
    /** The Confirm new password. */
    @FXML // fx:id="PasswordInput"
    private PasswordField ConfirmNewPass; // Value injected by FXMLLoader
    
    /**
     * Gets User info.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    protected void UserInfo(ActionEvent event) throws InterruptedException
    {
    	str.add("UserInfo");
    	str.add(Username);
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
    
    /**
     * User Logout.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    protected void Logout(ActionEvent event) throws IOException, InterruptedException
    {
    	str.add("Logout");
    	str.add(Username);
    	last = this;
    	client.Accept(str);
    	str.clear();
    	parent_student.clear();
    	Thread.sleep(250);
    	screen.Login();
    }
    
    /**
     * Display Change password screen.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    protected void ChangePassword(ActionEvent event) throws IOException
    {
    	ShowChngPassScreen();
    }
    
    /**
	 * Gets the homework file.
	 *
	 * @param event the event
	 */
    @FXML
    protected void GetHomeworkFile(ActionEvent event)
    {
    	last = this;
    	ask.add("file");
    	ask.add(file);
    	client.Accept(ask);
    	ask.clear();
    }	
    
    /**
     * Gets the submitted file.
     *
     * @param event the event
     */
    @FXML
    protected void GetSubmittedFile(ActionEvent event)
    {
    	last = this;
    	ask.add("Submittedfile");
    	ask.add(Subfile);
    	client.Accept(ask);
    	ask.clear();
    }

	/**
	 * Change password process.
	 *
	 * @param event the event
	 */
    @FXML
    protected void ChangePass(ActionEvent event)
    {
    	str.add("ChangePass");
    	str.add(Username);
    	if(NewPass.getText().equals(ConfirmNewPass.getText()) && !CurPass.getText().equals(NewPass.getText()))
    	{
    		str.add(CurPass.getText());
    		str.add(NewPass.getText());
    		client.Accept(str);
    		str.clear();
    	}
    	else if(!NewPass.getText().equals(ConfirmNewPass.getText()))
    	{
    		MessageError("The New Password and the Confirmation Password are not Matching");
    		str.clear();
    	}
    	else
    	{
    		MessageError("Please Enter a New Different Password");
    		str.clear();
    	}
    }
    
	/**
	 * Select user info screen to display by role.
	 *
	 * @param obj the user details
	 */
    public void SelectScreen(Object obj)
	{
		Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run()
			{
				switch (((ArrayList<String>) obj).get(8))
				{
					case "System Manager":
					{
						try
						{
							screen.SysMng_Info(obj);
						} catch(IOException e){
							e.printStackTrace();
							}
						break;
					}
					case "School Director":
					{
						try
						{
							screen.SchDir_Info(obj);
						} catch(IOException e){
							e.printStackTrace();
							}
						break;
					}
					case "Secretary":
					{
						try
						{
							screen.Secretary_Info(obj);
						}catch (IOException e){
							e.printStackTrace();
							}
						break;
					}
					case "Teacher":
					{
						try
						{
							screen.Teacher_Info(obj);
						} catch(IOException e){
							e.printStackTrace();
							}
						break;
					}
					case "Student":
					{
						try
						{
							screen.Student_Info(obj);
						} catch(IOException e){
							e.printStackTrace();
						}
						break;
					}
					case "Parent":
					{
						try
						{
							screen.Parent_Info(obj);
						} catch(IOException e){
							e.printStackTrace();
							}
						break;
					}
				}	
			}
		});
    }
    
    /**
     * Sets the user info.
     *
     * @param obj the user info
     */
    public void SetUserInfo(Object obj)
	{
		Platform.runLater(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run()
			{
				UserID.setText(((ArrayList<String>) obj).get(1));
				FirstName.setText(((ArrayList<String>) obj).get(2));
				LastName.setText(((ArrayList<String>) obj).get(3));
				RoleName.setText(((ArrayList<String>) obj).get(8));
				Password.setText(((ArrayList<String>) obj).get(4));
				PhoneNumber.setText(((ArrayList<String>) obj).get(5));
				Address.setText(((ArrayList<String>) obj).get(6));
				String bDate = ((ArrayList<String>) obj).get(7);
				bDate = bDate.substring(8, 10) + "/" + bDate.substring(5, 7) + "/" + bDate.substring(0, 4);
				Birthdate.setText(bDate);
				if(((ArrayList<String>) obj).get(8).equals("Teacher"))
					MaxHours.setText(((ArrayList<String>) obj).get(9));
			}
		});
    }
    
	/**
	 * Display pop-up Message.
	 *
	 * @param obj the message
	 */
    @SuppressWarnings("unchecked")
   	public void Message(Object obj)
    {
       	Platform.runLater(new Runnable()
   		{
   			@Override
   			public void run()
   			{
   				Alert alert = new Alert(AlertType.INFORMATION);
   				alert.setTitle("Error");
   				alert.setHeaderText(null);
   				alert.setContentText(((ArrayList<String>) obj).get(1));
   				alert.showAndWait();
   			}	
   		});
    }
    
	/**
	 * Sets the time.
	 */
    public void setTime()
    {
    	Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
            	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            	Calendar cal = Calendar.getInstance();
            	String time = dateFormat.format(cal.getTime()).toString();
            	Date.setText(time);
            }
        }, 0, 1000);
    }
    
    /**
     * Back from change password screen to user info screen.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    void BackChangePass(ActionEvent event) throws InterruptedException 
    {
    	str.add("UserInfo");
    	str.add(Username);
    	last = this;
    	client.Accept(str);
    	Thread.sleep(250);
    	str.clear();
    }
    
    /**
     * Display pop-up Message info.
     *
     * @param msg the Message
     */
    public void MessageInfo(String msg)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Info");
				alert.setHeaderText(null);
				alert.setContentText(msg);
				alert.showAndWait();
			}
		});
    }
    
    /**
     * Display pop-up Message error.
     *
     * @param msg the Message
     */
    public void MessageError(String msg)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText(msg);
				alert.showAndWait();
			}
		});
    }
    
    /**
     * Display pop-up Message confirm.
     *
     * @param msg the Message
     */
    public void MessageConfirm(String msg)
    {
    	Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirm");
				alert.setHeaderText(null);
				alert.setContentText(msg);
				alert.showAndWait();
			}
		});
    }
    
    /**
     * Show change password screen.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void ShowChngPassScreen() throws IOException
    {
   		switch (role)
    	{
    		case "1":
   			{
   				screen.SysMng_ChngPass();
    	  	  	break;
    		}
    		case "2":
   			{
    			screen.SchDir_ChngPass();
    			break;
   			}
   			case "3":
   			{
    			screen.Secretary_ChngPass();
    			break;
    		}
   			case "4":
   			{
   				screen.Teacher_ChngPass();
   				break;
    		}
    		case "5":
   			{
   				screen.Student_ChngPass();
   				break;
    		}
    		case "6":
    		{
   				screen.Parent_ChngPass();
   				break;
   			}
   		}
    }
    
    /** Runs the logout method and closes client 
     *@throws InterruptedException the interrupted exception
     *@throws IOException Signals that an I/O exception has occurred. 
     **/
    public void RunLogout() throws IOException, InterruptedException
    {
    	ActionEvent event = new ActionEvent();
    	Logout(event);
    	Exit(event);
    }
}
