/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

/**
 * The Class LoginController is for shared Methods of log-in screen for all users.
 */
public class LoginController extends MainController
{
	
	/** The User name. */
	public static String Username;
	
	/** The Host main. */
	public static String HostMain;
	
	/** The Port main. */
	public static String PortMain;
	
	/** The str. */
	ArrayList<String> str = new ArrayList<String>();
	
	/** The flag. */
	public boolean flag;
	
	/** The role. */
	public static String role;
	
	/** The user name. */
	public static String user;
	
    /** The Button exit. */
    @FXML // fx:id="Btn_Exit"
    private Button Btn_Exit; // Value injected by FXMLLoader

    /** The user name. */
    @FXML
    private Text UName2;
    
    /** The Error text. */
    @FXML // fx:id="ErrorText"
    private Text ErrorText; // Value injected by FXMLLoader

    /** The User name input. */
    @FXML // fx:id="UsernameInput"
    private TextField UsernameInput; // Value injected by FXMLLoader

    /** The Password input. */
    @FXML // fx:id="PasswordInput"
    private PasswordField PasswordInput; // Value injected by FXMLLoader

    /** The Button login. */
    @FXML // fx:id="Btn_Login"
    private Button Btn_Login; // Value injected by FXMLLoader
    
    /** The Button forgot pass. */
    @FXML // fx:id="Btn_ForgotPass"
    protected Button Btn_ForgotPass; // Value injected by FXMLLoader
    
    /**
     * User Login.
     *
     * @param event the event
     */
    @FXML
    void Login(ActionEvent event)
    {
    	last = this;
    	Username = UsernameInput.getText();
    	str.add("Login");
    	str.add(Username);
    	str.add(PasswordInput.getText());
    	client.Accept(str);
    	str.clear();
    	try
    	{
			Thread.sleep(500);
			if(flag == true)
	    	{
	    		flag = false;
	    		ShowScreen(event);
	    	}
		}catch(InterruptedException e){
			e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
				}
    }
    
    /**
	 * Checks if the user logged in successfully.
	 *
	 * @param obj the log-in answer
	 * @param th the LoginController instance
	 */
	@SuppressWarnings("unchecked")
	public static void Login(Object obj, LoginController th)
	{
		if(!((ArrayList<String>) obj).get(1).equals("Pass"))
		{
			th.ErrorMessage(((ArrayList<String>) obj).get(1));
			th.flag = false;
		}
		else
		{
			th.flag = true;
			LoginController.role = ((ArrayList<String>) obj).get(2);
			LoginController.user = ((ArrayList<String>) obj).get(3);
		}
	}

    /**
     * Exit from the system.
     *
     * @param event the event
     */
    @FXML
    void Exit(ActionEvent event)
    {
    	System.exit(1);
    }
    
    /**
     * Display Error message.
     *
     * @param msg the message
     */
    public void ErrorMessage(Object msg)
    {
    	Platform.runLater(new Runnable()
    	{
    		@Override
    		public void run()
			{
    			ErrorText.setText(msg.toString());
			}
    	});
    }

    /**
     * Select main screen to display by role.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void ShowScreen(ActionEvent event) throws IOException
    {
    	last = this;
   		switch (role)
    	{
    		case "1":
   			{
   				screen.SysMng_MainMenu();
    	  	  	break;
    		}
    		case "2":
   			{
    			screen.SchDir_MainMenu();
    			break;
   			}
   			case "3":
   			{
    			screen.Secretary_MainMenu();
    			break;
    		}
   			case "4":
   			{
   				screen.Teacher_MainMenu();
   				break;
    		}
    		case "5":
   			{
   				screen.Student_MainMenu();
   				break;
    		}
    		case "6":
    		{
   				screen.Parent_MainMenu();
   				break;
   			}
   		}
    }
    
    /**
     * start Forgot password process.
     *
     * @param event the event
     */
    @FXML
    void ForgotPass(ActionEvent event) 
    {
    	if(!UsernameInput.getText().isEmpty())
    	{
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("CONFIRMATION");
    		alert.setHeaderText(null);
    		alert.setContentText("Would You Like That Your Password Send To Your Email?\nMake Sure That You Are Connected To The Internet");
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK)
    		{
    			str.add("ForgotPass");
    			str.add(UsernameInput.getText());
    			last = this;
    			client.Accept(str);
    			str.clear();
    		}
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Info");
			alert.setHeaderText(null);
			alert.setContentText("Enter Your UserName First");
			alert.showAndWait();
    	}
    }
    
    /**
     * Send password to mail.
     *
     * @param obj the details for reset password
     */
    public void SendPassToMail(Object obj) 
    {
    	Platform.runLater(new Runnable()
    	{
    		@SuppressWarnings("unchecked")
    		@Override
    		public void run()
			{
					if(((ArrayList<String>)obj).size()>2)
					{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Info");
						alert.setHeaderText(null);
						alert.setContentText("Password Sent Successfuly, It Will Take Few Secondes");
						alert.showAndWait();
						SendEmail send=new SendEmail();
						send.createAndSendEmail(((ArrayList<String>)obj).get(1), "ASIS Password", "Hey "+((ArrayList<String>)obj).get(3)
								+" Your Password Is: "+((ArrayList<String>)obj).get(2));
						
					}
					else
					{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Info");
						alert.setHeaderText(null);
						alert.setContentText("Sorry, We Dont Have Your Email Address In DB.");
						alert.showAndWait();
					}
			}
		});
    		
    }
    
    
    /**
     * Update user first name on main screen.
     */
    public void updateName()
    {
    	Platform.runLater(new Runnable()
    	{
    		@Override
    		public void run()
			{
    			UName2.setText(user+",");
			}
    	});
    }


}
